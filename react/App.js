import './App.css';
import Header from './components/Header';
import Block2 from './components/Block2';
import { useState } from 'react';
import RegistrationPage from './components/RegistrationPage';
import {useMatchMedia} from './hooks/use-match-media';

function App() {
  const [page, setPage] = useState('reg');
  
  const { isMobile, isTablet, isDesktop } = useMatchMedia();
  return (
    <div className="App">
      {isMobile && <h2>This is mobile</h2>}
      {isTablet && <h2>This is tablet</h2>}
      {isDesktop && <h2>This is desktop</h2>}
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
