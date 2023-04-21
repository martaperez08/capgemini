import logo from './logo.svg';
import './App.css';
import { ActoresMnt } from "./componentes/actores";
import { PeliculasMnt } from "./componentes/peliculas";
import { LanguageMnt } from "./componentes/language";
import { CategoriasMnt } from "./componentes/categorias";
import { ErrorBoundary } from './biblioteca/comunes';
import { Component } from 'react';

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      cont: 0,
      main: 0,
    };
    this.menu = [
      { texto: 'actores', url: '/Actores', componente: <ActoresMnt /> },
      { texto: 'peliculas', url: '/Peliculas', componente: <PeliculasMnt /> },  
      { texto: 'language', url: '/Language', componente: <LanguageMnt /> },
      { texto: 'categorias', url: '/Categorias', componente: <CategoriasMnt /> },
     
      
     
    ];
  }

  render() {
    return (
      <>
        <Cabecera
          menu={this.menu}
          onSelectMenu={(indice) => this.setState({ main: indice })}
        />
        <main className="container-fluid">
          <ErrorBoundary>{this.menu[this.state.main].componente}</ErrorBoundary>
        </main>
        <Pie />
      </>
    );
  }
}
function Cabecera(props) {
  return (
    <header>
      <Menu {...props} />
    </header>
  );
}

function Menu({ menu, onSelectMenu }) {
  return (
    <nav>
      {menu.map((item, index) => (
        <button
          key={index}
          type="button"
          onClick={() => onSelectMenu && onSelectMenu(index)}
        >
          {item.texto}
        </button>
      ))}
    </nav>
  );
}

function Pie() {
  return null;
}
