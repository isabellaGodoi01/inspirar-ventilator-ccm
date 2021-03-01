import React, { useEffect, useState } from 'react'
import { useHistory } from "react-router-dom"
import './footer-style.scss'

const Footer = () => {
    const history = useHistory()
  const [cabecalho, setCabecalho] = useState(null)
  const [hora, setHora] = useState()

  useEffect(() => {
    if(!localStorage.getItem('bearerToken')) {
      history.push('/')
    }
    async function getMonitoramento () {
      const res = await fetch(`http://ccm.projetoinspirar.com.br:8081/inspirar-ventilator-ccm/api/v1/monitor`,{
        method: 'GET',
        headers: {
          Authorization: localStorage.getItem('bearerToken')
        }
      })
      if(res.status === 200) {
        const json = await res.json()
        setCabecalho(json.cabecalhoTotal)
      }
    }
    
    getMonitoramento()  
      
    const handleFunc = (e) => {
        if(e.detail) {
        setHora(e.detail)
        }
    }

    window.addEventListener('monitoramentoAtt', handleFunc )
    return(() => {
    window.removeEventListener('monitoramentoAtt', handleFunc )
    })
  }, [])

  return (
    <>
        <footer className='container'>
            <div className='footer'>
                <div className='row '>
                    <div className="col-6">
                        <div className="ultimaAtt">
                            <p>Última atualização</p>
                            <b>{hora || '-'}</b>
                        </div>
                    </div>
                    <div className="col-6">
                        <p>Versão: 1.0.0</p>
                        <p>Copyright by INSPIRAR - 2020</p>
                    </div>

                </div>
            </div>
        </footer>
    </>
  )
}

export default Footer