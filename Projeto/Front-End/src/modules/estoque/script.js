$("#NavBar").load("../../components/NavBar/NavBar.html");
$("#Footer").load("../../components/Footer/Footer.html");

$(document).ready(() => {
    $.get('http://localhost:8082/sistemaestoque/produtos', (data) =>{
        console.log(data);
    });
});