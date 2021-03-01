import React, {useState, useEffect} from 'react';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import List from '../list';
import Table from '../table';
import './filters-style.scss';

const Filters = ({
  filterModos,
  setFilterModos,
  filterLeitos,
  setFilterLeitos
}) => {
  const [hora, setHora] = useState()
  useEffect(() => {
    const handleFunc = (e) => {
      if(e.detail) {
        setHora(e.detail)
      }
    }
    window.addEventListener('monitoramentoAtt', handleFunc ) 
    return(() => {
      window.removeEventListener('monitoramentoAtt', handleFunc )
    })
  }, [] )   
  return (
    <>
    
    <div className='filter'>
        <span className='filterTitle'>Filtrar Modos</span>
        <ul>
          <li onClick={() => ('ALL')}>
            <span className={filterModos === 'ALL' ? 'check active' : 'check'} /> 
            <span className='titleCheck'>Todos</span>
          </li>
          <li onClick={() => setFilterModos('VCV')}>
            <span className={filterModos === 'VCV' ? 'check active' : 'check'} /> 
            <span className='titleCheck'>VCV</span>
          </li>
          <li onClick={() => setFilterModos('PCV')}>
            <span className={filterModos === 'PCV' ? 'check active' : 'check'} /> 
            <span className='titleCheck'>PCV</span>
          </li>
        </ul>
      </div>
      {/* <div className='filter'>
        <span className='filterTitle'>Filtrar Leitos</span>
        <ul>
          <li onClick={() => setFilterLeitos('ALL')}>
            <span className={filterLeitos === 'ALL' ? 'check active' : 'check'} /> 
            <span className='titleCheck'>Todos</span>
          </li>
          <li onClick={() => setFilterLeitos('DESCONECTADOS')}>
            <span className={filterLeitos === 'DESCONECTADOS' ? 'check active' : 'check'} /> 
            <span className='titleCheck'>Desconectados</span>
          </li>
          <li onClick={() => setFilterLeitos('CONECTADOS')}>
            <span className={filterLeitos === 'CONECTADOS' ? 'check active' : 'check'} /> 
            <span className='titleCheck'>Conectados</span>
          </li>
        </ul>
      </div>
      <div className='ultimaAtt'>
        <p>Última atualização</p>
        <b>{hora || '-'}</b>
      </div> */}
    </>
  )
}

export default Filters

