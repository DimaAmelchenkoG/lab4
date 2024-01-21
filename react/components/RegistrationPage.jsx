import { useState } from "react";
import axios from "axios";

export default function RegistrationPage({action}){
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
                console.log("BAD")
                console.log(error.response.data);
                console.log(error.response.status);
            })
    }
    
    return(
        <div>
            <form>
            <label htmlFor="login">Enter login:</label>
            <input type="text" id="login" value={userName} onChange={(e) => setUserName(e.target.value)}/>
            <br/>
            <label htmlFor="password">Enter password:</label>
            <input type="text"  id="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
            <br/>

            <input type="submit" value="Registration" onClick={handleClickReg}/>
            <input type="submit" value="Login" onClick={handleClickLogin}/>
            </form>
        </div>
    );
}