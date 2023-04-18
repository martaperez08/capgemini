//obtenemos los valores 
const numberButtons = document.querySelectorAll("[data-number]");
const numberOperations = document.querySelectorAll("[data-operation]");
let button = document.querySelector("input[type= button]");
let result = document.querySelector("output");
//creamos un array para guardar los numeros
const numbers = [];
let txt = ""; // concatenar los numeros
let tipo = ""; // tipo de operacion


numberButtons.forEach((button) => {
  button.addEventListener("click", (ev) => {
    txt = txt + ev.target.value;
  });
});

numberOperations.forEach((button) => {
  button.addEventListener("click", (ev) => {
    // vamos añadiendo los numeros en los array
    // asigamos el tipo 
    //en el caso igual opramos segun el tipo
    switch (ev.target.value) {
      case "+":
        numbers.push(txt); 
        txt = "";
        tipo = "suma";
        break;

      case "-":
        numbers.push(txt);
        txt = "";
        tipo = "resta";
        break;

      case "x":
        numbers.push(txt);
        txt = "";
        tipo = "multi";
        break;
        
      case "/":
        numbers.push(txt);
        txt = "";
        tipo = "division";
        break;

      case "=":
        numbers.push(txt);
        if (tipo === "suma") result.textContent = suma(numbers[0], numbers[1]);
        if (tipo === "resta") result.textContent = resta(numbers[0], numbers[1]);
        if (tipo === "multi") result.textContent = multi(numbers[0], numbers[1]);
        if (tipo === "division") result.textContent = division(numbers[0], numbers[1]);
       
        break;
        case "CE":
            while(numbers.length >0){
                numbers.pop();
            }
            txt = "";
            tipo=" "
            result.textContent="0"

        break;
      default:
        return;
    }
  });
});

function suma(valorA, valorB) {
  return +valorA + +valorB;
}
function resta(valorA, valorB) {
  return +valorA - +valorB;
}
function multi(valorA, valorB) {
  return +valorA * +valorB;
}
function division(valorA, valorB) {
  return +valorA / +valorB;
}
