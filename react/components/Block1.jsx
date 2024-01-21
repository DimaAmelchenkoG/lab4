//import { useRef, useEffect } from 'react';
//import { useRef } from 'react';
import './Block1.css';
import Can from './Can';
//import { MyCanvas } from './Mycanvas';
//import src1 from '/components/test.js';


export default function Block1(){
   //  alert("Hello");
    /** 
    function alert(){
        alert("Hello");
    }
*/







    return(
        <div id="N1" className="line">
            <Can/>


            <div id="R1" className="R">R</div>
            <div id="R2" className="R">R/2</div>
            <div id="R3" className="R">R</div>
            <div id="R4" className="R">R/2</div>
        </div>
    );

} 