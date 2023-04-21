import "./App.css";
import myLogo from './imagenes/logo.jpg'
import React, { Component } from "react";
import "./imagenes/logo.jpg"
import { ErrorBoundary } from "./comunes";
import { ActoresMnt } from "./componentes/actores";
import { PeliculasMnt } from "./componentes/peliculas";
import { LanguageMnt } from "./componentes/language";
import { CategoriasMnt } from "./componentes/categorias";

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cont: 0,
      main: 0,
    };
    this.menu = [
      { texto: "Actores", url: "/Actores", componente: <ActoresMnt /> },
      { texto: "Peliculas", url: "/Peliculas", componente: <PeliculasMnt /> },
      { texto: "Language", url: "/Language", componente: <LanguageMnt /> },
      {
        texto: "Categorias",
        url: "/Categorias",
        componente: <CategoriasMnt />,
      },
    ];
  }

  render() {
    return (
      <>
        <Cabecera menu={this.menu} actual={this.state.main} onSelectMenu={indice => this.setState({ main: indice })} />
        <main className='container-fluid'>
           {this.menu[this.state.main].componente}
        
        </main>
        <Pie />
      </>
    )
  }
}

function Cabecera(props) {
  return (
    <header>
      <nav className="navbar navbar-expand-lg bg-body-tertiary ">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            <img src={myLogo} height={50} alt='Logotipo corporativo' />
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon" />
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <Menu {...props} />
            <Buscar />
          </div>
        </div>
      </nav>

    </header>
  );
}

function Menu({ menu, actual, onSelectMenu }) {
  return (
    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
      {menu.map((item, index) =>
        <li key={index} className="nav-item">
          <a className={'nav-link' + (actual === index ? ' active' : '')} aria-current="page" href="."
            onClick={ev => { 
              ev.preventDefault()
              onSelectMenu && onSelectMenu(index) 
            }}>{item.texto}</a>
        </li>
      )
      }
    </ul>
  );
}

function Buscar() {
  return (
    <form className="d-flex" role="search">
    <input
      className="form-control me-2"
      type="search"
      placeholder="Search"
      aria-label="Search"
    />
    <button className="btn btn-outline-success" type="submit">
      Search
    </button>
  </form>
)
}
function Pie() {
  return null;
}