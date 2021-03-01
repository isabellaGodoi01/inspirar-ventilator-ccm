import React from 'react'
import './modalconfirmation-style.scss'

const ModalConfirmation = ({
  setShowDeletePaciente,
  userDelete
}) => {

  async function handleDelete () {
    const res = await fetch(`http://ccm.projetoinspirar.com.br:8081/inspirar-ventilator-ccm/api/v1/monitor/${userDelete.id}`,{
        method: 'DELETE',
        headers: {
          Authorization: localStorage.getItem('bearerToken')
        }
      })
      if(res.status === 200) {
        setShowDeletePaciente(false)
      }
  }

  return (
    <div className='styleModal'>
      <div className='backdrop'></div>
      <div className='content contentModalConfiguracoes'>
        <div className='row headerModal'>
          <div className='col-4'>
          </div>
          <div className='col-4 center'>
          </div>
          <div className='col-4 closeStatus'>
            <span className='iconClose' onClick={() => setShowDeletePaciente(false) }>
              <svg width="1em" height="1em" viewBox="0 0 16 16" className="bi bi-x" fill="#b8b8b8" xmlns="http://www.w3.org/2000/svg">
                <path fillRule="evenodd" d="M11.854 4.146a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708-.708l7-7a.5.5 0 0 1 .708 0z"/>
                <path fillRule="evenodd" d="M4.146 4.146a.5.5 0 0 0 0 .708l7 7a.5.5 0 0 0 .708-.708l-7-7a.5.5 0 0 0-.708 0z"/>
              </svg>
            </span>
          </div>
        </div>

        <div className='box container'>
          <div className='row'>
            <p>Deseja excluir o leito?</p>
            <div className='wraButton'>
              <button onClick={() => setShowDeletePaciente(false) }>Cancelar</button>
              <button onClick={handleDelete}>Excluir</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ModalConfirmation