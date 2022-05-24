$("#NavBar").load("./components/NavBar/NavBar.html");
$("#Footer").load("./components/Footer/Footer.html");

document.querySelector("#login").addEventListener("click", () => {
    window.location.assign("./modules/menu/index.html");
});

// Access-Control-Allow-Origin: *;

$.ajax({
    methot: 'GET',
    url: 'http://127.0.0.1:8082/sistemaestoque/produtos',
    dataType: 'json',
    // headers: {
    //     'Access-Control-Allow-Origin': '*',
    //     contentType: 'application/json',
    // }
}).done((data) =>{
    console.log(data);
}).fail((erro) => {
    console.log(erro);
});

// $.get('http://localhost:8082/sistemaestoque/produtos', (data) =>{
//     console.log(data);
// });
