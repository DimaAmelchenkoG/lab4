import { useState } from "react";
import axios from "axios";
import './RegistrationPage.css'

export default function RegistrationPage({action, actionTable}){
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    
    const handleClickReg=(e)=>{
        e.preventDefault()

        console.log("REG");
        const user={userName, password}

        console.log(user);
        axios.post("http://localhost:8080/lab4/register", user, {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
                'accept':'application/json',
                'Content-Type': 'application/json',
            }
        }).then(response =>{
            console.log("GOOD");
            console.log(response.data);
            action("main");
            
        })
            .catch(error=>{
                alert("Пользователь с таким именем уже существует");
                console.log("BAD");
                console.log(error.response.data);
                console.log(error.response.status);
            })
    }


    const handleClickLogin=(e)=>{
        e.preventDefault()

        console.log("LOGIN");
        const user={userName, password}

        console.log(user);
        axios.post("http://localhost:8080/lab4/login", user, {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
            }
        }).then(response =>{
            console.log("GOOD");
            console.log(response.data);
            action("main");
        })
            .catch(error=>{
                alert("Неправильный логин или пароль");
                console.log("BAD")
                console.log(error.response.data);
                console.log(error.response.status);
            })
    }
    


    let isYValid = false ;
    let isXValid = false;
    function checkALL(userName, password){
        var str = (String(userName)).replace(",", ".");
        isXValid =(str.trim().length !==0);

        str = (String(password)).replace(",", ".");
        isYValid = (str.trim().length !==0);
    
        console.log(isXValid, isYValid);
        if (isXValid && isYValid){
            return false;
        } 
        return true;
    }

    

    return(
        <div>
            <form>
            
            <div className="labels">
            <label id="text" className="text" htmlFor="login">Enter login:  </label>
            <input className="label" placeholder="login" type="text" id="login" value={userName} onChange={(e) => setUserName(e.target.value)}/>
            <br/>
            <label className="text" htmlFor="password">Enter password:  </label>
            <input className="label" placeholder="password" type="text"  id="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
            <br/>
            </div>

            <div className="buttons">
            <input className="button" type="submit" disabled={checkALL(userName, password)} value="Registration" onClick={handleClickReg}/>
            <input className="button" type="submit" disabled={checkALL(userName, password)} value="Login" onClick={handleClickLogin}/>
            </div>
            </form>
        </div>
    );
}