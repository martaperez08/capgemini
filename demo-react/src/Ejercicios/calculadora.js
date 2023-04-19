import { Component } from "react";
import  { Calculadora as CalculadoraObject } from "../biblioteca/calculadora"

export class Calculadora extends Component {
    constructor(props) {
        super(props)
        this.state = {
            pantalla: '0',
            resumen: null
        }
        this.calc = new CalculadoraObject(() => { }, () => { })
        this.calc.onPantallaChange = value => this.setState({ pantalla: value.replace(/\./g, ',') })
        this.calc.onResumenChange = value => this.setState({ resumen: value.replace(/\./g, ',') })
        this.ponDigito = this.ponDigito.bind(this)
        this.calcula = ev => this.calc.calcula(ev.target.value)
    }
    ponDigito(ev) {
        this.calc.ponDigito(ev.target.value);
    }
  render() {
    return (
      <div>
        <h1>Calculadora</h1>
        <output>{this.state.pantalla}</output>
        <br />
        <input type="button" defaultValue={7} onClick={this.ponDigito}/>
        <input data-number="" type="button" defaultValue={8}  onClick={this.ponDigito} />
        <input data-number="" type="button" defaultValue={9}  onClick={this.ponDigito} />
        <input data-operation="" type="button" defaultValue="x"   onClick={this.calcula}  />
        <br />
        <input data-number="" type="button" defaultValue={4}  onClick={this.ponDigito} />
        <input data-number="" type="button" defaultValue={5}onClick={this.ponDigito} />
        <input data-number="" type="button" defaultValue={6} onClick={this.ponDigito} />
        <input data-operation="" type="button" defaultValue="-"  onClick={this.calcula}  />
        <br />
        <input data-number="" type="button" defaultValue={1}  onClick={this.ponDigito} />
        <input data-number="" type="button" defaultValue={2} onClick={this.ponDigito} />
        <input data-number="" type="button" defaultValue={3}  onClick={this.ponDigito} />
        <input data-operation="" type="button" defaultValue="+"   onClick={this.calcula}  />
        <br />
        <input data-operation="" type="button" defaultValue="/"   onClick={this.calcula}  />
        <input data-operation="" type="button" defaultValue="CE" />
        <input data-number="" type="button" defaultValue={0} onClick={this.ponDigito} />
        <input
          
          type="button"
          defaultValue="="
          onClick={this.calcula} 
        />
      </div>
    );
  }
}

export class Card extends Component {}
