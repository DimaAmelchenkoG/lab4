import React, { useRef, useEffect } from 'react';
import './Block1.css'

function Can({rString, artists, size}) {
  const canvasRef = useRef(null);

  useEffect(() => {



    function check(x, y, r){
      x = Number(x);
      y = Number(y);
      r = Number(r);
      if (checkCircle(x, y, r) || checkSquare(x, y, r) || checkTriangle(x, y, r)){
          return 'Hit';
      }
      return 'No hit';
  }
  
  
  function checkSquare(x, y, r){
      return (x <= 0 && x >= -r/2) && (y <= 0 && y >= -r);
  }
  
  function checkTriangle(x, y, r){
      return (x >= 0 && y >= 0) && (y <= r && x <= r) && (x + y <= r);
  }
  
  function checkCircle(x, y, r){
      return (x <= 0 && y >= 0) && ((x*x + y*y) <= (r*r)/4);
  }
  



  
    const canvas = canvasRef.current;
    const ctx = canvas.getContext('2d');
    var r = Number(rString)
 

    ctx.fillStyle = '#FFFFFF';
    ctx.beginPath();
    ctx.moveTo(0, 500);
    ctx.lineTo(500, 500);
    ctx.lineTo(500, 0);
    ctx.lineTo(0, 0);
    ctx.lineTo(0, 500);
    ctx.fill();
    ctx.stroke();

    ctx.fillStyle = '#000000';

    //каркас
    ctx.beginPath();
    ctx.moveTo(0, 500);
    ctx.lineTo(0, 0);
    ctx.lineTo(500, 0);
    ctx.lineTo(500, 500);
    ctx.lineTo(0, 500);
    ctx.stroke();



    //Линия
    ctx.beginPath();
    ctx.moveTo(0, 250);
    ctx.lineTo(500, 250);
    ctx.lineTo(0, 250);
    ctx.stroke();

    //Линия
    ctx.beginPath();
    ctx.moveTo(250, 0);
    ctx.lineTo(250, 500);
    ctx.lineTo(250, 0);
    ctx.stroke();


    //треугольник
    ctx.fillStyle = '#FF0000';
    ctx.beginPath();
    ctx.moveTo(250, 250);
    ctx.lineTo(250, 250 - 37.5 * r);
    ctx.lineTo(250 + 37.5 * r, 250);
    ctx.lineTo(250, 250);
    ctx.fill();
    ctx.stroke();

    //прямоугольник
    ctx.beginPath();
    ctx.moveTo(250, 250);
    ctx.lineTo(250 - 18.75 * r, 250);
    ctx.lineTo(250 - 18.75 * r, 250 + 37.5 * r);
    ctx.lineTo(250, 250 + 37.5 * r);
    ctx.lineTo(250, 250);
    ctx.fill();
    ctx.stroke();


    //круг
    ctx.beginPath();
    ctx.moveTo(250, 250);
    ctx.arc(250, 250, 18.75 * r, -Math.PI / 2, Math.PI, true);
    ctx.fill();
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 250-37.5);
    ctx.lineTo(250-10, 250-37.5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 250-37.5 * 3);
    ctx.lineTo(250-10, 250-37.5 * 3);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 250+37.5 );
    ctx.lineTo(250-10, 250+37.5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 250+37.5 * 3);
    ctx.lineTo(250-10, 250+37.5 * 3);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 250 - 37.5*4);
    ctx.lineTo(250-10, 250 - 37.5*4);
    ctx.stroke();


    ctx.beginPath();
    ctx.moveTo(250+10, 250 - 37.5*2);
    ctx.lineTo(250-10, 250 - 37.5*2);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 250 - 37.5*5);
    ctx.lineTo(250-10, 250 - 37.5*5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 250 + 37.5*5);
    ctx.lineTo(250-10, 250 + 37.5*5);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 325);
    ctx.lineTo(250-10, 325);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250+10, 400);
    ctx.lineTo(250-10, 400);
    ctx.stroke();



    ctx.beginPath();
    ctx.moveTo(250 + 37.5 * 4, 250+10);
    ctx.lineTo(250 + 37.5 * 4, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 + 37.5 * 5, 250+10);
    ctx.lineTo(250 + 37.5 * 5, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 + 37.5 * 3, 250+10);
    ctx.lineTo(250 + 37.5 * 3, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 + 37.5 * 2, 250+10);
    ctx.lineTo(250 + 37.5 * 2, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 + 37.5 * 1, 250+10);
    ctx.lineTo(250 + 37.5 * 1, 250-10);
    ctx.stroke();


    ctx.beginPath();
    ctx.moveTo(250 - 37.5 * 4, 250+10);
    ctx.lineTo(250 - 37.5 * 4, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 - 37.5 * 5, 250+10);
    ctx.lineTo(250 - 37.5 * 5, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 - 37.5 * 3, 250+10);
    ctx.lineTo(250 - 37.5 * 3, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 - 37.5 * 2, 250+10);
    ctx.lineTo(250 - 37.5 * 2, 250-10);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(250 - 37.5 * 1, 250+10);
    ctx.lineTo(250 - 37.5 * 1, 250-10);
    ctx.stroke();

    for (let i=0;i<size;i++) {
      const xValue = Number(artists[i].x)
      const yValue = Number(artists[i].y)
      const rValue = Number(rString)
      const hit = check(xValue, yValue, rValue);
      if (hit === "Hit") {
          ctx.fillStyle = '#0000FF';
      } else {
          ctx.fillStyle = '#00FF00';
      }
      ctx.fillRect((250 + 37.5 * xValue), (250 - 37.5 * yValue), 5, 5);

    };
  }, [rString, artists, size]);

  return (
    <canvas ref={canvasRef}  width="500" height="500" id='canvas'/>
  );
}

export default Can;