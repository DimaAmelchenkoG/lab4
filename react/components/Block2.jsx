import axios from 'axios';
import { useState } from 'react';
import './Block2.css';
import Can from './Can';

//let arr = [];
//let nextId = 0;
export default function Block2({action}){
    const [artists, setArtists] = useState([]);

    //console.log("Blocj render");
    const[x, setX] = useState('')
    const[y, setY] = useState('')
    const[r, setR] = useState('1')

/**
    const handleClick=(e)=>{
        e.preventDefault()
        const point={x, y, r}
        console.log(point)
        fetch("http://localhost:8080/lab4/mypoints/add",{
            //mode: "no-cors",
          method:"POST",
          headers:{"Content-Type":"application/json"},
          body:JSON.stringify(point)
    
      }).then(()=>{
        console.log("New Student added")
      })
    }

*/




    const handleClick=(e)=>{
        e.preventDefault()
        //console.log("HEllo1")
        const point={x: x, y: y, r: r}
        //setArtists([
        //   ...artists, {id: nextId++, x: x, y: y, r: r}
        //])
        //console.log(artists);
        axios.post("http://localhost:8080/lab4/mypoints/add", point, {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
            }
        }).then(response =>{
            console.log("GOOD");
            console.log(response.data);
            setArtists(response.data);
        })
            .catch(error=>{
                console.log("BAD")
                console.log(error.response.status);
            })
    }

    const handleClickClean=(e)=>{
        e.preventDefault()
        //console.log("HEllo1")
        //const point={x: x, y: y, r: r}
        setArtists([]);
        axios.post("http://localhost:8080/lab4/mypoints/clean", {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
            }
        }).then(response =>{
            console.log("GOOD");
            console.log(response.data);
            //setArtists(response.data);
        })
            .catch(error=>{
                console.log("BAD")
                console.log(error.response.status);
            })
    }

    const handleClickLogout=(e)=>{
        e.preventDefault()

        console.log("Logout");
        //const user={userName, password}

        //console.log(user);
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
                //console.log(error.response.data);
                // console.log(error.response.status);
            })
    }

    let res = artists.map(function(item) {
        return <tr key={item.id}>
           <td>{item.id}</td>
           <td>{item.x}</td>
           <td>{item.y}</td>
           <td>{item.r}</td>
           <td>{item.target}</td>
           <td>{item.timeNow}</td>
           <td>{item.programTime}</td>
        </tr>;
     });

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
            <label htmlFor="x">Enter x:</label>
            <input type="text" id="x" value={x} onChange={(e) => setX(e.target.value)}/>
            <br/>
            <label htmlFor="y">Enter y:</label>
            <input type="text"  id="y" value={y} onChange={(e) => setY(e.target.value)}/>
            <br/>
            <label htmlFor="r">Enter r:</label>
            <select id="r" value={r} onChange={(e) => setR(e.target.value)}> 
                <option>1</option> 
                <option>2</option> 
                <option>3</option> 
                <option>4</option> 
                <option>5</option> 
            </select>
            <br/>
            <input type="submit" value="Create" onClick={handleClick}/>
            <input type="submit" value="CLean" onClick={handleClickClean}/>
            <input type="submit" value="Logout" onClick={handleClickLogout}/>
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
    <p>{x + y + r}</p>
</div>
</div>
    );
}