import { Component } from "react";

export function PadreFormulrio() {
  let elemento = { id: 1, nombre: "Pepito", apellidos: "Grillo", edad: 99 , conflictivo: true};
  return (
    <Formulario
      elemento={elemento}
      onSend={(ele) => alert(JSON.stringify(ele))}
      onCancel={() => alert("Canela")}
    />
  );
}
export class Formulario extends Component {
  // las propiedades no se modifican
  constructor(props) {
    super(props);
    this.state = {
      elemento: this.props.elemento,
      //coleccion de errores
      errors: {},
      invalid: false,
    };
    this.handleChange = this.handleChange.bind(this)
  }
  handleChange(event) {
    const cmp = event.target.name;
    const valor =
      event.target.type === "checkbox"
        ? event.target.checked
        : event.target.value;
    const errMsg = event.target.validationMessage;
    this.setState((prev, props) => {
      const ele = prev.elemento;
      let errors = prev.errors;
      let invalid = false;
      ele[cmp] = valor;
      errors[cmp] = errMsg;
      if (!errors.nombre && cmp === "nombre") {
        if (valor && valor !== valor.toUpperCase()) {
          errors.nombre = "Debe ir en mayúsculas.";
        } else errors.nombre = "";
      }
      for (let c in errors) {
        invalid =
          invalid ||
          (errors[c] !== "" &&
            errors[c] !== null &&
            typeof errors[c] !== "undefined");
      }
      return { elemento: ele, errors:errors, invalid: invalid };
    });
  }
  render() {
    return (
      <form>
        <p>
            <label htmlFor="id">Codigo</label>
            <input type ='number' id='id' name='id' value={this.state.elemento.id} onChange={this.handleChange} required min={0}/>
            <output className="error" hidden={this.state.errors?.id=== null } >{this.state.errors.id}</output>
            <br/>
            <label htmlFor="nombre">name</label>
            <input type ='text' id='nombre' name='nombre' value={this.state.elemento.nombre} onChange={this.handleChange}
            required maxLength={10}/>
            <output className="error" hidden={this.state.errors?.nombre=== null } >{this.state.errors.nombre}</output>
            <br/>
            <label htmlFor="apellidos">apellidos</label>
            <input type =''text id='apellidos' name='apellidos' value={this.state.elemento.apellidos} onChange={this.handleChange}/>
            <output className="error" hidden={this.state.errors?.apellidos=== null } >{this.state.errors.apellidos}</output>
            <br/>
            <label htmlFor="edad">edad</label>
            <input type ='number' id='edad' name='edad' value={this.state.elemento.edad} onChange={this.handleChange}/>
            <output className="error" hidden={this.state.errors?.edad=== null } >{this.state.errors.edad}</output>
            <br/>
            <label htmlFor="conflictivo">conflictivo</label>
            <input type ='checkbox' id='conflictivo' name='conflictivo' checked={this.state.elemento.conflictivo} onChange={this.handleChange}/>
            <output className="error" hidden={this.state.errors?.conflictivo=== null } >{this.state.errors.conflictivo}</output>
            <br/>
        </p>
        <p>
            <input type='button' defaultValue='Enviar' disabled={this.state.invalid} 
            onClick={() => this.props.onSend && this.props.onSend(this.state.elemento)} />
             <input type='button' defaultValue='Boton volver'  
            onClick={() => this.props.onCancel && this.props.onCancel()} />
        </p>
        {JSON.stringify(this.state.elemento)}
      </form>
    );
  }
  
}