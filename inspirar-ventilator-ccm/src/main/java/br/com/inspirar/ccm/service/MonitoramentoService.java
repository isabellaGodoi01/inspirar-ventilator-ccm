package br.com.inspirar.ccm.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.inspirar.ccm.entity.monitoramento.MonitoramentoEntity;
import br.com.inspirar.ccm.model.CabecalhoCount;
import br.com.inspirar.ccm.model.CabecalhoTotal;
import br.com.inspirar.ccm.model.Grafico;
import br.com.inspirar.ccm.model.Graficos;
import br.com.inspirar.ccm.model.ListaMonitoramento;
import br.com.inspirar.ccm.model.Monitoramento;
import br.com.inspirar.ccm.repository.IMonitoramentoRepository;
import br.com.inspirar.ccm.repository.MonitoramentoRepository;

@Service
public class MonitoramentoService {

	private static final Logger LOG = LoggerFactory.getLogger(MonitoramentoService.class);
	
    @Autowired
    MonitoramentoRepository monitoramentoRepository;

	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	IMonitoramentoRepository graficoRepository;
	
	@Autowired
	MonitorService monitorService;
	
    public void save(Monitoramento monitoramentoRequest){
    	
    	MonitoramentoEntity monitoramento = monitoramentoRepository.findByImei(monitoramentoRequest.getNumeroImei());

    	Integer id = monitoramento == null ? null : monitoramento.getId();
    	
		monitoramento = new MonitoramentoEntity(monitoramentoRequest, id);

		monitoramentoRepository.save(monitoramento);

		LOG.debug("Monitoramento salvo com sucesso: " + monitoramento.getImei());

    }

	public void bloquearMonitoramento(Integer id) throws InterruptedException {
		
		monitoramentoRepository.bloquearMonitoramento(id);
		
		monitorService.publicarGraficos(recuperarMonitoramento());

		LOG.debug("Bloqueando monitoramento: " + id);
	}

	public ListaMonitoramento recuperarMonitoramento() {
		
		List<MonitoramentoEntity> monitoramentos = monitoramentoRepository.findAllByExcluidoFalseOrExcluidoIsNullAndImeiIsNotNull();
		
		List<String> inImeisList = monitoramentos.stream().distinct().map(e -> e.getImei()).distinct().collect(Collectors.toList());
		
		//Manter apenas 30 ciclos
		graficoRepository.removerPorImeisGraficosMaior30Ciclos(inImeisList);
		
		List<Grafico> graficos = graficoRepository.getGraficoPorImeis(inImeisList);
		
		Map<String, Graficos> graficosMap = new HashMap<>();
		graficos.stream().forEach(g -> {
			
			Graficos gs = graficosMap.get(g.getImei());
			if(gs == null) {
				gs = new Graficos();
			}
			
			gs.setAltura(g.getAltura());
			gs.setPeso(g.getPeso());
			gs.setSexo(g.getSexo());
			
			gs.getPressaoValor().add(g.getPressaoValor());
			gs.getFluxoValor().add(g.getFluxoValor());
			gs.getVolumeValor().add(g.getVolumeValor());
			
			graficosMap.put(g.getImei(), gs);
			
		});
		
		ListaMonitoramento retorno = new ListaMonitoramento();
		retorno.setGraficos(graficosMap);
		
		monitoramentos.stream().map(e -> 
			retorno.getMonitoramentos().add(new Monitoramento()
			   .numeroImei(e.getImei())
			   .sexo(atribuirDadosPAciente(e.getImei(), "sexo", graficosMap ))
			   .altura(atribuirDadosPAciente(e.getImei(), "altura", graficosMap ))
			   .peso(atribuirDadosPAciente(e.getImei(), "peso", graficosMap ))
			   .id(e.getId().toString())
			   .leito(e.getLeito())
			   .paciente(e.getPaciente())
			   .pressaoPico(e.getPressaoPico())
			   .fio2(e.getFio2())
			   .peep(e.getPeep())
			   .fr(e.getFr())
			   .vt(e.getVt())
			   .ve(e.getVe())
			   .mensagemAlarme(e.getMensagemAlarme())
               .vcv(e.getVcv())
               .modo(e.getModo())
               .onOff(e.getOnOff())
			   .alarmePPico(e.getAlarmePPico())
			   .alarmeFio2(e.getAlarmeFio2())
			   .alarmePeep(e.getAlarmePeep())
			   .alarmeFr(e.getAlarmeFr())
			   .alarmeVt(e.getAlarmeVt())
			   .alarmeVe(e.getAlarmeVe())
			   .bloqueado(e.getBloqueado())
			   .countAlarme(e.getCountAlarme())
               .conexao(getDezMinutosSemConexão(e.getDataInclusao()))
               .ordem(getOrder(e.getDataInclusao(), e.getCountAlarme())))).collect(Collectors.toList());
		
		Comparator<Monitoramento> compareByAlarme = Comparator.comparing(Monitoramento::isOrdem).thenComparing(Monitoramento::isCountAlarme);
		
		List<Monitoramento> monitoramentosOrdenado  = retorno.getMonitoramentos().stream().sorted(compareByAlarme.reversed()).collect(Collectors.toList());
		
		retorno.setMonitoramentos(monitoramentosOrdenado);
		
		retorno.setCabecalhoTotal(contadorCabecalho());

		LOG.debug("Monitoramento: " + monitoramentosOrdenado);

		return retorno;
	}
	
	private String  atribuirDadosPAciente(String imei, String campoNome, Map<String, Graficos> graficosMap) {
		
		if(graficosMap.get(imei) == null)
			return "";
		
		if(campoNome.equals("sexo"))
		   return graficosMap.get(imei).getSexo();
		else if(campoNome.equals("altura"))
		   return graficosMap.get(imei).getAltura();
		else if(campoNome.equals("peso"))
		   return graficosMap.get(imei).getPeso();	
		
		return "";
    }
	
	private Boolean getOrder(LocalDateTime dataInclusao, Boolean countAlarme) {
		
		Boolean dentroDosDezminutos = getDezMinutosSemConexão(dataInclusao);
		
		if (!getDezMinutosSemConexão(dataInclusao)) {
			return false;
		}  

		return dentroDosDezminutos && countAlarme; 
		
	}
	
	private Boolean getDezMinutosSemConexão(LocalDateTime dataInclusao) {
		
		if (dataInclusao == null) {
			return false;
		}
		
		LocalDateTime dataAtual = LocalDateTime.now().minus(10, ChronoUnit.MINUTES);

		return dataAtual.isBefore(dataInclusao);	
		
	}
	
	private CabecalhoTotal contadorCabecalho() {
		
		CabecalhoCount quantidadePaciente =  monitoramentoRepository.totalPaciente();
		
		CabecalhoCount quantidadeVcv =  monitoramentoRepository.totalVcv();
		
		CabecalhoCount quantidadePcv =  monitoramentoRepository.totalPcv();
		
        CabecalhoCount quantidadeAlarmes =  monitoramentoRepository.totalAlarmes();
        
        CabecalhoCount quantidadeDesconectados =  monitoramentoRepository.totalDesconectados();
		
		return new CabecalhoTotal(quantidadePaciente, quantidadeVcv, quantidadePcv, quantidadeAlarmes, quantidadeDesconectados); 
	}
}
