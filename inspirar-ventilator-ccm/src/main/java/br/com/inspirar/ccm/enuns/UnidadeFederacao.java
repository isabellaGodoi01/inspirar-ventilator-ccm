package br.com.inspirar.ccm.enuns;

public enum UnidadeFederacao {

  AMAZONAS(1, "Amazonas", "AM", "Manaus"), ALAGOAS(2, "Alagoas", "AL", "Maceió"), ACRE(3, "Acre", "AC", "Rio Branco"),
  AMAPA(4, "Amapá", "AP", "Macapá"), BAHIA(5, "Bahia", "BA", "Salvador"), PARA(6, "Pará", "PA", "Belém"),
  MATO_GROSSO(7, "Mato Grosso", "MT", "Cuiabá"), MINAS_GERAIS(8, "Minas Gerais", "MG", "Belo Horizonte"),
  MATO_GROSSO_DO_SUL(9, "Mato Grosso do Sul", "MS", "Campo Grande"), GOIAS(10, "Goiás", "GO", "Goiânia"),
  MARANHAO(11, "Maranhão", "MA", "São Luís"), RIO_GRANDE_DO_SUL(12, "Rio Grande do Sul", "RS", "Porto Alegre"),
  TOCANTINS(13, "Tocantins", "TO", "Palmas"), PIAUI(14, "Piauí", "PI", "Teresina"),
  SAO_PAULO(15, "São Paulo", "SP", "São Paulo"), RONDONIA(16, "Rondônia", "RO", "Porto Velho"),
  RORAIMA(17, "Roraima", "RR", "Boa Vista"), PARANA(18, "Paraná", "PR", "Curitiba"),
  CEARA(19, "Ceará", "CE", "Fortaleza"), PERNAMBUCO(20, "Pernambuco", "PE", "Recife"),
  SANTA_CATARINA(21, "Santa Catarina", "SC", "Florianópolis"), PARAIBA(22, "Paraíba", "PB", "João Pessoa"),
  RIO_GRANDE_DO_NORTE(23, "Rio Grande do Norte", "RN", "Natal"), ESPIRITO_SANTO(24, "Espírito Santo", "ES", "Vitória"),
  RIO_DE_JANEIRO(25, "Rio de Janeiro", "RJ", "Rio de Janeiro"), SERGIPE(26, "Sergipe", "SE", "Aracaju"),
  DISTRITO_FEDERAL(27, "Distrito Federal", "DF", "Brasília");

  private final int id;

  public int getId() {
    return id;
  }

  public String getCapital() {
    return capital;
  }

  public String getNome() {
    return nome;
  }

  public String getSigla() {
    return sigla;
  }

  private final String nome;
  private final String sigla;
  private final String capital;

  UnidadeFederacao(final int id, final String nome, final String sigla, final String capital) {
    this.id = id;
    this.nome = nome;
    this.sigla = sigla;
    this.capital = capital;
  }

  public static UnidadeFederacao fromSigla(final String sigla) {
    for (final UnidadeFederacao uf : UnidadeFederacao.values()) {
      if (uf.sigla.equalsIgnoreCase(sigla)) {
        return uf;
      }
    }

    throw new IllegalArgumentException(sigla);
  }

  public static UnidadeFederacao fromCodigo(final int id) {
    for (final UnidadeFederacao uf : UnidadeFederacao.values()) {
      if (uf.id == id) {
        return uf;
      }
    }

    throw new IllegalArgumentException(id + "");
  }

}