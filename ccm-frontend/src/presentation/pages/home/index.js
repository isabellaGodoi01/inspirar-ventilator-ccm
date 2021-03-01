import React, { useEffect, useState } from 'react';
// import { Header, Categorys, Table } from '../../components' 
import { Header, Count, Categorys, Footer } from '../../components';
import { useHistory } from "react-router-dom";
import Filters from '../../components/filters';

const Home = () => {

  return (
    <>
      <Header />
      {/* <Count />  */}
      {/* <Filters /> */}
      <Categorys />
      <Footer />
    </>
    
  )

}

export default Home