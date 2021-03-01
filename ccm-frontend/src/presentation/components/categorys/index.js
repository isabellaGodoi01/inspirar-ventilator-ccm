import ReactDOM from 'react-dom';
import React, { useState, useEffect } from 'react';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import './categorys-style.scss';
import 'react-tabs/style/react-tabs.css';
import { MODOS } from '../../../utils/constants';
import Table from '../table';
import Count from '../count';


    class FilterTabs extends React.Component {
        constructor(props) {
            super();
            this.state = {
                // TIRA A GUIA ATIVA DAS PROPRIEDADES CASO ESTEJA DEFINIDA
                activeTab: props.activeTab || 0                  
            };
            
            // VINCULAR A FUNÇÃO HANDLESELECT AQUI (NAO NA FUNÇAO DE RENDERIZAÇAO)
            this.handleSelect = this.handleSelect.bind(this);
        }

        render() {
            return (

                <Tabs activeKey={this.state.activeTab} onSelect={this.handleSelect} className='container'>
                    <Count modo={MODOS[this.state.activeTab].value}/>
                    {console.log('contador selecionado na aba : ' +  MODOS[this.state.activeTab].value)}
                    <TabList>
                        <Tab eventKey={1} title="Pacientes"> Pacientes</Tab>
                        <Tab eventKey={2} title="Modo VCV">Modo VCV</Tab>
                        <Tab eventKey={3} title="Modo PCV" >Modo PCV</Tab>
                        <Tab eventKey={4} title="Modo PSV">Modo PSV - VMNI</Tab>
                        <Tab eventKey={5} title="Modo CPAP">Modo CPAP</Tab>
                    </TabList>

                    <TabPanel>
                        {/* VERIFICA QUAL ABA ESTÁ ATIVADA E RENDERIZA O MODO SETADO NA CONSTANTS*/}
                        <Table modo={MODOS[this.state.activeTab].value} />
                    </TabPanel>

                    <TabPanel>
                        <Table modo={MODOS[this.state.activeTab].value} />
                    </TabPanel>

                    <TabPanel>
                        <Table modo={MODOS[this.state.activeTab].value} />
                    </TabPanel>

                    <TabPanel>
                        <Table modo={MODOS[this.state.activeTab].value} />
                    </TabPanel>

                    <TabPanel>
                        <Table modo={MODOS[this.state.activeTab].value} />
                    </TabPanel>
                </Tabs>
            );
        }

        handleSelect(selectedTab) {
            // A guia ativa deve ser definida no estado para que o componente Tabs saiba sobre a mudança e seja renderizado novamente.
            console.log(selectedTab.toString() + " ------> " + MODOS[selectedTab].value)
            this.setState({
                activeTab: selectedTab 
            });
        }    
    }

export default FilterTabs
