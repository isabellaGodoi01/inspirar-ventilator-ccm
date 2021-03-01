import React, { useRef, useEffect, useState } from 'react';
import './modalPaciente-style.scss';
import Chart from 'chart.js';

const ModalPaciente = ({
  setShowPacienteModal,
  modalData,
  graficos
}) => {
  const volume = useRef(null);
  const fluxo = useRef(null);
  const pressao = useRef(null);
  const [pacienteGrafico, setPacienteGrafico] = useState()
  useEffect(() => {
    if(graficos && modalData) {
      setPacienteGrafico(graficos[modalData.numeroImei])
    }
  }, [graficos, modalData])

  useEffect(() => {
    if(pacienteGrafico) {
      new Chart(volume.current, {
        type: 'line',
        data: {
          offset: true,
          labels: pacienteGrafico.volumeValor.map(value => ''),
          datasets: [{
              label: 'Volume(ml)',
              data: pacienteGrafico.volumeValor,
              borderColor: [
                  'rgba(253, 0, 0, 1)',
              ],
              borderWidth: 1
          }]
      },
      })
      new Chart(fluxo.current, {
        type: 'line',
        data: {
          offset: true,
          labels: pacienteGrafico.fluxoValor.map(value => ''),
          datasets: [{
              label: 'Fluxo(l/min)',
              data: pacienteGrafico.fluxoValor,
              borderColor: [
                  'rgba(185, 238, 0, 1)',
              ],
              borderWidth: 1
          }]
      },
      })
      new Chart(pressao.current, {
        type: 'line',
        data: {
          offset: true,
          labels: pacienteGrafico.pressaoValor.map(value => ''),
          datasets: [{
              label: 'Pressão(cmH20)',
              data: pacienteGrafico.pressaoValor,
              borderColor: [
                  'rgba(255, 126, 0, 1)',
              ],
              borderWidth: 1
          }]
      },
      })
    }
  }, [pacienteGrafico])

  return (
    <div className='styleModal'>
      <div className='backdrop'></div>
      <div className='content contentModalPaciente'>
        <div className='row headerModal'>
          <div className='col-4'>
            <span className='leito'>Leito - <b>{modalData.leito || '-'}</b></span>
          </div>
          <div className='col-4 center'>
          <span className='leito'>Paciente - <b>{modalData.paciente}</b></span>
          </div>
          <div className='col-4 closeStatus'>
            <span className='iconClose' onClick={() => setShowPacienteModal(false) }>
              <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-x" fill="#b8b8b8" xmlns="http://www.w3.org/2000/svg">
                <path fillRule="evenodd" d="M11.854 4.146a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708-.708l7-7a.5.5 0 0 1 .708 0z"/>
                <path fillRule="evenodd" d="M4.146 4.146a.5.5 0 0 0 0 .708l7 7a.5.5 0 0 0 .708-.708l-7-7a.5.5 0 0 0-.708 0z"/>
              </svg>
            </span>
            {modalData.bloqueado && 
              <span className='status'>Bloqueado</span>
            }
          </div>
        </div>

        <div className='box'>
          <div className='titleBox'>
            <ul>
              <li>
                Sexo: <b>{modalData.sexo || '-'}</b>
              </li>
              <li>
                Altura: <b>{modalData.altura || '-'}cm</b>
              </li>
              <li>
                Peso predito: <b>{modalData.peso || '-'}kg</b>
              </li>
            </ul>
          </div>
          <div className='row'>
            <div className='col-6'>
              <canvas  ref={volume} />
              <canvas  ref={fluxo} />
              <canvas  ref={pressao} />
            </div>
            <div className='col-4'>
              <div className='wrapTttleCenter'>
                {/* <span className='centerTitle'>{modalData.vcv ? 'VCV' : 'PCV'}</span> */}
                <span className='centerTitle mb-1'>{modalData.modo}</span>
              </div>
              <ul className='center'>
                <li className='mb-1'>
                  <div>
                    <p>P pico</p>
                    <p>cmH20</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.pressaoPico}</span>
                  </div>
                  <div>
                    <p>30</p>
                    <p>0</p>
                  </div>
                </li>
                <li className='mb-1'>
                  <div>
                    <p>P platô</p>
                    <p>cmH20</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.PPlato || '-'}</span>
                  </div>
                  <div>
                    <p>30</p>
                    <p>0</p>
                  </div>
                </li>
                <li className='mb-1'>
                  <div>
                    <p>VE</p>
                    <p>l/min</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.ve}</span>
                  </div>
                  <div>
                    <p>95</p>
                    <p>40</p>
                  </div>
                </li>
                <li className='w50 mb-1'>
                  <div>
                    <p>FR total</p>
                    <p>ripm</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.fr}</span>
                  </div>
                </li>
                <li className='w50'>
                  <div>
                    <p>T. Insp.</p>
                    <p>s</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.TInsp || '-'}</span>
                  </div>
                </li>
              </ul>

              <ul className='center small'>
                <li className='w50 mb-1'>
                  <div>
                    <p>CSR</p>
                    <p>ml/cmH20</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.CSR || '-'}</span>
                  </div>
                </li>
                <li className='w50 mb-1'>
                  <div>
                    <p>RVA</p>
                    <p>cmH20/s</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.RVA || '-'}</span>
                  </div>
                </li>
                <li className='w50 mb-1'>
                  <div>
                    <p>I:E</p>
                  </div>
                  <div>
                    <span className='centerNumber'>{modalData.IE || '-'}</span>
                  </div>
                </li>
              </ul>
            </div>
            <div className='col-2'>
              <ul className='right'>
                <li>
                  <span className='titleRight'>VT</span>
                  <div className='contentRight'>
                    <span className='valueRight'>{modalData.vt}</span>
                    <span className='unidadeRight'>ml</span>
                  </div>
                </li>
                <li>
                  <span className='titleRight'>Fluxo</span>
                  <div className='contentRight'>
                    <span className='valueRight'>{modalData.fluxo || '-'}</span>
                    <span className='unidadeRight'>l/min</span>
                  </div>
                </li>
                <li>
                  <span className='titleRight'>FR</span>
                  <div className='contentRight'>
                    <span className='valueRight'>{modalData.fr}</span>
                    <span className='unidadeRight'>irpm</span>
                  </div>
                </li>
                <li>
                  <span className='titleRight'>PEEP</span>
                  <div className='contentRight'>
                    <span className='valueRight'>{modalData.peep}</span>
                    <span className='unidadeRight'>cmH20</span>
                  </div>
                </li>
                <li>
                  <span className='titleRight'>Pausa Insp</span>
                  <div className='contentRight'>
                    <span className='valueRight'>{modalData.pausaInsp || '-'}</span>
                    <span className='unidadeRight'>s</span>
                  </div>
                </li>
                <li>
                  <span className='titleRight'>Sensib.</span>
                  <div className='contentRight'>
                    <span className='valueRight'>{modalData.sensib || '-'}</span>
                    <span className='unidadeRight'>cmh20</span>
                  </div>
                </li>
                <li>
                  <span className='titleRight'>FI02</span>
                  <div className='contentRight'>
                    <span className='valueRight'>{modalData.fio2}</span>
                    <span className='unidadeRight'>%</span>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        
      </div>
    </div>
  )
}

export default ModalPaciente