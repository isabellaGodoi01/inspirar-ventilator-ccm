import React from 'react';
import './list-style.scss';
 
const List = ({
  setShowPacienteModal,
  setShowDeletePaciente,
  renderList,
  setUserDelete,
  setModalData
}) => {
  return (
    <div className='table-responsive'>
        <table className='list'>
            <thead>
                <tr>
                    <th className='text-left font-weight-bold'>ALERTA</th>
                    <th className='text-center font-weight-bold'>LEITO</th>
                    <th className='text-left font-weight-bold'>PACIENTE</th>
                    <th className='text-center'>MODO</th>
                    <th className='text-center'>P PICO</th>
                    <th className='text-center'>P PLATÔ</th>
                    <th className='text-center'>VT</th>
                    <th className='text-center'>VE</th>
                    <th className='text-center'>FR. T.</th>
                    <th className='text-center'>T. INSP</th>
                    <th className='text-center'>CSR</th>
                    <th className='text-center'>RVA</th>
                    <th className='text-center'>I:E</th>
                    <th className='text-center'>PEEP</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
      
            <tbody>
                {(renderList && renderList.length === 0 )&& 
                <tr>
                    <td style={{textAlign: 'center'}} colSpan={24}>Nenhum dado encontrado</td>
                </tr>
                }
                {renderList && renderList.map((list, index) => (
                <tr key={index} >
                    {/* <td className='text-center'>
                        {list.mensagemAlarme && <div className='bg-danger text-white font-weight-bold py-2 rounded tableAlert w-100'>{list.mensagemAlarme}</div>}
                    </td> */}
                    <td className='text-center'>
                        {list.mensagemAlarme && <div className='text-white font-weight-bold py-2 rounded tableAlert w-100'>{list.mensagemAlarme}</div>}
                    </td>
                    <td className='text-center'>
                        <div className={`${list.mensagemAlarme ? 'text-danger' : '' } font-weight-bold `}>{list.leito || '-'}</div>
                    </td>
                    <td className=''>
                        <span className={`${list.mensagemAlarme ? 'text-danger font-weight-bold' : ''}`}>{list.paciente}</span>
                    </td>
                    {/* <td className='text-center'><div className={`${list.conexao ? (list.vcv ? 'VCV' : 'PCV') : '' }`}>{list.vcv ? 'VCV' : 'PCV' }</div></td> */}
                    <td className='text-center'><div className=''>{list.modo}</div></td>
                    {/* <td className='text-center'><div className=''>{`${list.modo === 'VCV' ? 'VCV' : ''}`}</div></td> */}
                    <td className='text-center'><div className=''>{list.pressaoPico}</div></td>
                    <td className='text-center'><div className=''>{list.pressaoPlato}</div></td>
                    <td className='text-center'><div className=''>{list.vt}</div></td>
                    <td className='text-center'><div className=''>{list.ve}</div></td>
                    <td className='text-center'><div className=''>{list.frt}</div></td>
                    <td className='text-center'><div className=''>{list.tinsp}</div></td>
                    <td className='text-center'><div className=''>{list.csr}</div></td>
                    <td className='text-center'><div className=''>{list.rva}</div></td>
                    <td className='text-center'><div className=''>{list.ie}</div></td>
                    <td className='text-center'><div className=''>{list.peep}</div></td>
                    <td className='text-center' onClick={() => {setModalData(list); setShowPacienteModal(true)}}>
                        <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-search" fill="blue" xmlns="http://www.w3.org/2000/svg">
                            <path fillRule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
                            <path fillRule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                        </svg>
                    </td>
                    <td className='center noPadding'>
                        {list.bloqueado && 
                        <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-lock-fill" fill="red" xmlns="http://www.w3.org/2000/svg">
                            <path d="M2.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z"/>
                            <path fillRule="evenodd" d="M4.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"/>
                        </svg>}
                        {!list.bloqueado && 
                            <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-unlock-fill" fill="green" xmlns="http://www.w3.org/2000/svg">
                            <path d="M.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z"/>
                            <path fillRule="evenodd" d="M8.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"/>
                            </svg>
                        }
                    </td>
                    <td className='center noPadding'>
                        {!list.conexao && 
                            <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-wifi-off" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M10.706 3.294A12.545 12.545 0 0 0 8 3 12.44 12.44 0 0 0 .663 5.379a.485.485 0 0 0-.048.736.518.518 0 0 0 .668.05A11.448 11.448 0 0 1 8 4c.63 0 1.249.05 1.852.148l.854-.854zM8 6c-1.905 0-3.68.56-5.166 1.526a.48.48 0 0 0-.063.745.525.525 0 0 0 .652.065 8.448 8.448 0 0 1 3.51-1.27L8 6zm2.596 1.404l.785-.785c.63.24 1.228.545 1.785.907a.482.482 0 0 1 .063.745.525.525 0 0 1-.652.065 8.462 8.462 0 0 0-1.98-.932zM8 10l.934-.933a6.454 6.454 0 0 1 2.012.637c.285.145.326.524.1.75l-.015.015a.532.532 0 0 1-.611.09A5.478 5.478 0 0 0 8 10zm4.905-4.905l.747-.747c.59.3 1.153.645 1.685 1.03a.485.485 0 0 1 .048.737.518.518 0 0 1-.668.05 11.496 11.496 0 0 0-1.812-1.07zM9.02 11.78c.238.14.236.464.04.66l-.706.706a.5.5 0 0 1-.708 0l-.707-.707c-.195-.195-.197-.518.04-.66A1.99 1.99 0 0 1 8 11.5c.373 0 .722.102 1.02.28zm4.355-9.905a.53.53 0 1 1 .75.75l-10.75 10.75a.53.53 0 0 1-.75-.75l10.75-10.75z"/>
                            </svg>
                        }
                        {list.conexao &&
                            <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-wifi" fill="green" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15.385 6.115a.485.485 0 0 0-.048-.736A12.443 12.443 0 0 0 8 3 12.44 12.44 0 0 0 .663 5.379a.485.485 0 0 0-.048.736.518.518 0 0 0 .668.05A11.448 11.448 0 0 1 8 4c2.507 0 4.827.802 6.717 2.164.204.148.489.13.668-.049z"/>
                            <path d="M13.229 8.271c.216-.216.194-.578-.063-.745A9.456 9.456 0 0 0 8 6c-1.905 0-3.68.56-5.166 1.526a.48.48 0 0 0-.063.745.525.525 0 0 0 .652.065A8.46 8.46 0 0 1 8 7a8.46 8.46 0 0 1 4.577 1.336c.205.132.48.108.652-.065zm-2.183 2.183c.226-.226.185-.605-.1-.75A6.472 6.472 0 0 0 8 9c-1.06 0-2.062.254-2.946.704-.285.145-.326.524-.1.75l.015.015c.16.16.408.19.611.09A5.478 5.478 0 0 1 8 10c.868 0 1.69.201 2.42.56.203.1.45.07.611-.091l.015-.015zM9.06 12.44c.196-.196.198-.52-.04-.66A1.99 1.99 0 0 0 8 11.5a1.99 1.99 0 0 0-1.02.28c-.238.14-.236.464-.04.66l.706.706a.5.5 0 0 0 .708 0l.707-.707z"/>
                            </svg>
                        }
                    </td>
                    <td className='noPadding' onClick={() => {setShowDeletePaciente(true); setUserDelete(list) } }>
                        {!list.conexao &&
                            <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fillRule="evenodd" d="M11.854 4.146a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708-.708l7-7a.5.5 0 0 1 .708 0z"/>
                            <path fillRule="evenodd" d="M4.146 4.146a.5.5 0 0 0 0 .708l7 7a.5.5 0 0 0 .708-.708l-7-7a.5.5 0 0 0-.708 0z"/>
                            </svg>
                        }
                    </td>
                </tr>
                ))}
            </tbody>
        </table>
    </div>
  )
}

export default List