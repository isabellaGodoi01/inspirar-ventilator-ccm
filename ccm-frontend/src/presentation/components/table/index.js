import React, { useState, useEffect } from 'react';
import { List, ModalPaciente, ModalConfirmation, Count } from '../index';
import './table-style.scss';

let username = null;
let stompClient = null;
function connect() {
  username = 'teste';
  if(username) {

      var socket = new window.SockJS(process.env.REACT_APP_BASE_URL + 'ws');
      stompClient = window.Stomp.over(socket);

      stompClient.connect({}, onConnected, onError);
  }
}

function disconnect() {
  username = 'teste';

  if(username) {

      var socket = new window.SockJS(process.env.REACT_APP_BASE_URL + 'ws');
      stompClient = window.Stomp.over(socket);

      stompClient.disconnect({});
  }
}

function onMessageReceived(payload) {
  const body = JSON.parse(payload.body)
  console.log(body)
  var data = new Date();
  var hora    = data.getHours();          // 0-23
  var min     = data.getMinutes();        // 0-59
  var seg     = data.getSeconds();        // 0-59
  var monitoramento = new CustomEvent('monitoramento', { 'detail': body.monitoramentos });
  dispatchEvent(monitoramento)
  var monitoramentoAtt = new CustomEvent('monitoramentoAtt', { 'detail': (hora < 9 ? '0' + hora : hora) + ':' + (min < 9 ? '0' + min : min) + ':' + (seg < 9 ? '0' + seg : seg) });
  dispatchEvent(monitoramentoAtt)
  var monitoramentoGrafico = new CustomEvent('monitoramentoGrafico', { 'detail': body.graficos });
  dispatchEvent(monitoramentoGrafico)
  var cabecalhoTotal = new CustomEvent('cabecalhoTotal', { 'detail': body.cabecalhoTotal });
  dispatchEvent(cabecalhoTotal)
}


function onConnected() {
  // Subscribe to the Public Topic
  stompClient.subscribe('/topic/monitor', onMessageReceived);
}

function onError(error) {
  console.log(error)
}


export default function Table( { modo } ) {
    const [filterModos, setFilterModos ] = useState(modo)
    const [showPacienteModal, setShowPacienteModal ] = useState(false)
    const [deletePaciente, setShowDeletePaciente ] = useState(false)
    const [renderList, setRenderList ] = useState()
    const [ userDelete, setUserDelete ] = useState()
    const [ modalData, setModalData ] = useState() 
    const [list, setList] = useState()
    const [graficos, setGraficos] = useState()


    // APLICA O EFEITO DE EXIBIR A LISTA TODA VEZ QUE O MODO FOR ALTERADO
    useEffect(() => {
        const handleFunc = (e) => {
            // VERIFICA OS DETALHES DA TABELA PARA FILTRAR A EXIBIÇÃO
            if(e.detail) {
                // VERIFICA SE O CONSTANTS RETORNA ALL OU NÃO
                if(filterModos === 'ALL'){
                    // SE SIM - RENDERIZA A LISTA COMPLETA
                    setList(e.detail);
                }else {
                    // SE NÃO - RENDERIZA A LISTA FILTRADA PELO MODO SELECIONADO
                    var x = filterNewModo(e.detail);
                    setList(x);
                }
            }
        }
        const handleFuncGrafic = (e) => {
          if(e.detail) {
            setGraficos(e.detail)
          }
        }
        // ADICIONA O EVENTO DO HANDLEFUNC DENTRO DA TABELA MONITORAMENTO - RENDERIZA A LISTA 
        // ADICIONA O EVENTO DO HANDLEFUNCGRAFIC DENTRO DA TABELA MONITORAMENTO GRAFICO - RENDERIZA OS GRAFICOS
        window.addEventListener('monitoramento', handleFunc )
        window.addEventListener('monitoramentoGrafico', handleFuncGrafic )
        return(() => {
            window.removeEventListener('monitoramentoGrafico', handleFuncGrafic )
            window.removeEventListener('monitoramento', handleFunc )
        })
        // RENDERIZA O EVENTO TODA VEZ QUE O FILTERMODOS É ATUALIZADO
      // eslint-disable-next-line react-hooks/exhaustive-deps  
    }, [filterModos])    

    useEffect(() => {
        if(localStorage.getItem('bearerToken')) {
        connect()
        }
    }, [])

    // FUNÇÃO PARA VERIFICAR QUAL MODO SERÁ RENDERIZADO 
    function filterNewModo (value) {
        const newModo = value.filter(list => {
            if(filterModos === 'VCV') {
                console.log('NOVO VCV : ' +  list.modo)
                return list.modo === 'VCV' 
            } else if (filterModos === 'PCV'){
                console.log('NOVOOOOOO PCV : ' + list.modo)
                // console.log('NOVO PCV : ' + list.modo.length)
                return list.modo === 'PCV'
            } else if (filterModos === 'PSV'){
                // var modo = 'PSV';
				// while(modo > 1){
                //     modo ++;                    
				// }
                console.log('NOVO PSV : ' + list.modo.sum)
       
                return list.modo === 'PSV'
            }else {
                // console.log('NOVO CPAP : ' + list.modo.length)
                return list.modo === 'CPAP'
            }
           
        });
        
        return newModo;
    }
    

    // function filter (value) { 
    //     setRenderList(
    //         value.filter(list => {
    //             if(filterModos === 'VCV') {
    //             return list.modo === 'VCV';
    //             } else if (filterModos === 'PCV'){
    //             return list.modo === 'PCV';
    //             } else if (filterModos === 'PSV'){
    //                 return list.modo === 'PSV';
    //             }else {
    //                 return list.modo === 'CPAP';
    //             }
    //         })
    //     )
    // }

    return (
        <>
            <div className='pb-80 mb-80'>
                <div className='row'>
                    <div className='col-12'>
                        <List 
                        renderList={list}
                        setShowDeletePaciente={setShowDeletePaciente}
                        setShowPacienteModal={setShowPacienteModal}
                        setModalData={setModalData}
                        setUserDelete={setUserDelete}
                        />
                    </div>
                </div>
            </div>
            {showPacienteModal &&
                <ModalPaciente setShowPacienteModal={setShowPacienteModal} modalData={modalData} graficos={graficos} />
            }
            {deletePaciente &&
                <ModalConfirmation setShowDeletePaciente={setShowDeletePaciente} userDelete={userDelete} />
            }
        
        </>
    )
}
