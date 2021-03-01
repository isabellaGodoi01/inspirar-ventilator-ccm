import React, {useState, useEffect} from 'react';
import { MODOS } from '../../../utils/constants';


const Count = () => {

    const [cabecalho, setCabecalho] = useState({
        alarmesTotal: {label: "Alarmes", quantidade: '-'},
        pacienteTotal: {label: "Paciente", quantidade: '-'}
        // desconectadosTotal: {label: "Desconectados", quantidade: '-'}
        // pcvTotal: {label: "PCV", quantidade: '-'},
        // vcvTotal: {label: "VCV", quantidade: '-'}

    })

    // useEffect(() => {
    //     const handleFuncCabecalho = (e) => {
    //         if(e.detail) {
    //             setCabecalho(e.detail)
    //         }
    //     }
    //     window.addEventListener('cabecalhoTotal', handleFuncCabecalho )
    //     return(() => {
    //         window.removeEventListener('cabecalhoTotal', handleFuncCabecalho )
    //     })
    // }, []);

    useEffect(() => {
        const handleFuncCabecalho = (e) => {            
            if(e.detail) {
                setCabecalho(e.detail)
            }          
    
            // console.log('alarme : ' + cabecalho.alarmesTotal.quantidade + ' paciente :' + cabecalho.pacienteTotal.quantidade)
        }

        console.log('alarmesTotal : ' + cabecalho.alarmesTotal.quantidade)

        window.addEventListener('cabecalhoTotal', handleFuncCabecalho )
        // console.log('alarme : ' + cabecalho.alarmesTotal.quantidade + ' paciente :' + cabecalho.pacienteTotal.quantidade)
        return(() => {
            window.removeEventListener('cabecalhoTotal', handleFuncCabecalho )
        })
       
    },[])

    return (
    <>
    <div className="container my-5 px-0">
        <div className="d-flex justify-content-end">
            <div className=''>
                <div className="input-group">
                    <span className="py-2 px-3 my-auto border border-right-0 font-weight-bold rounded-left">Alarmes Disparados</span>
                    <div className="input-group-prepend">
                        <span className="input-group-text font-weight-bold p-20 rounded-right">{cabecalho.alarmesTotal.quantidade}</span>
                    </div>
                </div>
            </div>

            <div className='pl-3'> 
                <div className="input-group">
                    <span className="py-2 px-3 my-auto border border-right-0 font-weight-bold rounded-left">Total de Pacientes</span>
                    <div className="input-group-prepend">
                        <span className="input-group-text font-weight-bold p-20 rounded-right">{cabecalho.pacienteTotal.quantidade}</span>
                    </div>
                </div>
            </div>

            {/* <div>
                <div className="input-group">
                    <span className="py-2 px-3 my-auto border border-right-0 font-weight-bold rounded-left">Desconectados</span>
                    <div className="input-group-prepend">
                        <span className="input-group-text font-weight-bold p-20 rounded-right">{cabecalho.desconectadosTotal.quantidade}</span>
                    </div>
                </div>
            </div> */}
        </div>
    </div>
    </>
  )
}

export default Count