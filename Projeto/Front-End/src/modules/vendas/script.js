$(document).ready(() => {
    validation();

    document.querySelector("#novaVenda").addEventListener("click", () => {
        window.location.href = "../gerarVenda";
    });
});


function validation() {
    let userLogado = JSON.parse(localStorage.getItem('user'));
    if(userLogado.tipo.nivel !== "GERENTE")
        window.location.href = "../menu";
    else
        fetch();
}

function fetch() {
    $("#NavBar").load("../../components/NavBar/NavBar.html");
    $("#Footer").load("../../components/Footer/Footer.html");

    $.get('http://localhost:8082/sistemaestoque/vendas').then((data) =>{
        console.log(data);
    });
}