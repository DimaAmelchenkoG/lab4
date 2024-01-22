import './App.css';
import Header from './components/Header';
import Block2 from './components/Block2';
import { useState } from 'react';
import RegistrationPage from './components/RegistrationPage';


function App() {
  const [page, setPage] = useState('reg');
  return (
    <div className="App">
      <Header/>
      {page === 'main' && (
        <>
      <Block2 action={setPage}/>
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
