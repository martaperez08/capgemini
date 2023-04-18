import logo from './logo.svg';
import './App.css';
import React, { Component } from 'react'

export default class App extends Component {
  render() {
    return (
      <div>
        <Home/>
        <DemoJSX/>
      </div>
     
    )
  }
}



export class DemoJSX extends Component {
  
  render() {
    let saluda = <h1>Hola mundo </h1>
    return (
      <div>
         {saluda}
          <div>DemoJSX</div>
      </div>
   
    )
  }
}


function Home() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
       <h1>Hola mundoosss</h1> 
      
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}


