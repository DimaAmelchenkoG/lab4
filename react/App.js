//import logo from './logo.svg';
import './App.css';
import Test from './components/TestComponent';
import Header from './components/Header';
//import Block1 from './components/Block1';
import Block2 from './components/Block2';
import { useState } from 'react';
import Button from './components/Button';
import RegistrationPage from './components/RegistrationPage';

function App() {
  const [page, setPage] = useState('reg');
  //console.log("App render");
  return (
    <div className="App">
      <Header/>
      <Button onCange={setPage} str='reg' description='Страница регистрации'/>
      <Button onCange={setPage} str='main' description='Главная'/>
      {page === 'main' && (
        <>
      <Block2 action={setPage}/>
      <Test/>
      </>
      )}
      {page === 'reg' && (
      <>
        <RegistrationPage action={setPage}/>
      </>
      )}
    </div>
  );
}

export default App;
