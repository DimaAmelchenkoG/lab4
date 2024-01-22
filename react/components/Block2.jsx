import axios from 'axios';
import { useState, useEffect } from 'react';
import './Block2.css';
import Can from './Can';

export default function Block2({action}){


    const [artists, setArtists] = useState([]);
    const[x, setX] = useState('')
    const[y, setY] = useState('')
    const[r, setR] = useState('1')


useEffect(() => {
    axios.get("http://localhost:8080/lab4/mypoints/all", {
        withCredentials: true,
        headers: {
            'Cache-Control': 'no-cache',
        }
    }).then(response =>{
        console.log("ALL" + response.data);
        setArtists(response.data);
    })
        .catch(error=>{
            console.log(error.response.status);
        })
}, []);



    const handleClick=(e)=>{
        e.preventDefault()
        const point={x: x, y: y, r: r}
        axios.post("http://localhost:8080/lab4/mypoints/addPoint", point, {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
            }
        }).then(response =>{
            console.log("GOOD");
            console.log(response.data);
            setArtists(response.data["body"])
        console.log(artists);
        })
            .catch(error=>{
                console.log("BAD")
                console.log(error.response.status);
            })
    }

    const handleClickClean=(e)=>{
        e.preventDefault()
        console.log("CLEAN");
        setArtists([]);
        axios.post("http://localhost:8080/lab4/mypoints/cleanTable", {},  {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
            }
        }).then(response =>{
            console.log("GOOD");
            console.log(response.data);
        })
            .catch(error=>{
                console.log("BAD")
                console.log(error.response.status);
            })
    }

    const handleClickLogout=(e)=>{
        e.preventDefault()

        console.log("Logout");
        axios.post("http://localhost:8080/lab4/exit", {}, {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
            }
        }).then(response =>{
            console.log("GOOD");
            console.log(response.data);
            action("reg");
        })
            .catch(error=>{
                console.log("BAD LOGOUT");
            })
    }

    let tableId = 0;
    let res = artists.map(function(item) {
        tableId++;
        return <tr key={tableId}>
           <td>{tableId}</td>
           <td>{item.x}</td>
           <td>{item.y}</td>
           <td>{item.r}</td>
           <td>{item.result ? "Hit"  : "No hit"}</td>
           <td>{item.currentTime}</td>
           <td>{item.executionTime}</td>
        </tr>;
     });


     let isYValid = false ;
    let isXValid = false;






function checkALL(xV, yV, rV){
    console.log("CHECKK");
    //return false;
    var str = (String(xV)).replace(",", ".");
    isXValid = Number(str) <= 5 && Number(str) >= -5 && !isNaN(str) && (str.trim().length !==0);

    console.log(Number(str));
    console.log(Number(str) < 5, Number(str) > -5,  !isNaN(str),  (str.trim().length !==0));
     str = (String(yV)).replace(",", ".");
    isYValid = Number(str) <= 4 && Number(str) >= -4 && !isNaN(str) && (str.trim().length !==0);

    console.log(isXValid, isYValid);
    if (isXValid && isYValid){
        return false;
    } 
    return true;
}



    return(
        <div>
        <div id="N1" className="line">
            {console.log("BEFORE" + artists)}
        <Can rString={r} artists={artists} size={artists.length}/>


        <div id="R1" className="R">R</div>
        <div id="R2" className="R">R/2</div>
        <div id="R3" className="R">R</div>
        <div id="R4" className="R">R/2</div>
    </div>
        <div id="N2" className="line">
    <div id="inputs" className="line1">

            <form>
            <label htmlFor="x">Enter x:  </label>
            <input type="text" id="x" placeholder='[5 .. 5]' value={x} onChange={(e) => setX(e.target.value)}/>
            <br/>
            <label htmlFor="y">Enter y:  </label>
            <input type="text"  id="y" placeholder='[4 .. 4]' value={y} onChange={(e) =>     setY(e.target.value)}/>
            <br/>
            <label htmlFor="r">Enter r:  </label>
            <select id="r" value={r} onChange={(e) => setR(e.target.value)}> 
                <option>1</option> 
                <option>2</option> 
                <option>3</option> 
                <option>4</option> 
                <option>5</option> 
            </select>
            <br/>
            <div>
            <input className="button" id="Enter" value="Create" disabled={checkALL(x, y, r)} type="submit" onClick={handleClick}/>
            </div>
            <div>
            <input className="button" type="submit" value="CLean" onClick={handleClickClean}/>
            </div>
            <div>
            <input className="button" type="submit" value="Logout" onClick={handleClickLogout}/>
            </div>
            </form>
    </div>

    <div className="line1">
        <table border="1" id="maintable" className="table">
            <thead>
                <tr>
                <th> # </th>
                <th> X </th>
                <th> Y </th>
                <th> R </th>
                <th> Target </th>
                <th> Time now </th>
                <th> Program time </th>
                </tr>
            </thead>
            <tbody>
                {res}
            </tbody>
        </table>
    </div>
</div>
</div>
    );
}