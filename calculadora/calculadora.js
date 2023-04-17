console.log("fffff")
const numberButtons = document.querySelectorAll('[data-number]')
const numberOperations = document.querySelectorAll('[data-operation]')

let button= document.querySelector('input[type= button]')
let result= document.querySelector('output')
const numbers = [];
let signo=true;
let txt = "";
    numberButtons.forEach(button => {
        button.addEventListener('click', ev => {
             if(signo==true){
                numbers.push(ev.target.value);
                signo=false;
             }
           
           
        
            



            let i = 0;

            do {
                console.log(numbers[i]);
                i++;
            } while (i < numbers.length);
             
            })
    })

    numberOperations.forEach(button => {
        button.addEventListener('click', ev => {
      let resultadoOperacion=0
            switch (ev.target.value) {
                case '=':
                case '+':
                    signo=true
                  console.log("suma")
                  if(numbers.length== 2){
                    console.log("ddd")
                 resultadoOperacion= suma(numbers[0],numbers[1])
                 result.textContent= resultadoOperacion
                  }
                  break
                case '-':
                    console.log("resta")
                  break
                case 'x':
                    console.log("multi")
                  break
                case '/':
                    console.log("divi")
                  break
                  case '=':
                    console.log("igual")
                    
                  break
                default:
        
                  return
              }
        })
    })

    function suma( valorA,  valorB) {
        return +valorA + +valorB
    }

  