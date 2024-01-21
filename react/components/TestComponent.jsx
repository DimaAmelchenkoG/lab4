import axios from "axios";

export default function Test(){

    
    const handleClick=(e)=>{
        console.log("HEllo1")
        axios.post("http://localhost:8080/lab4/mypoints/new", {
            withCredentials: true,
            headers: {
                'Cache-Control': 'no-cache',
            }
        }).then(response =>{
            console.log(response.data);
        })
            .catch(error=>{
                console.log(error.response.status);
            })
    }


    return(
        <button onClick={handleClick}>Button</button>
    );
}