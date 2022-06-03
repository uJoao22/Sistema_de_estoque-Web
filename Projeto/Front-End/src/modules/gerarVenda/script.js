$(document).ready(() => {
    validation();
});

function validation() {
    let userLogado = JSON.parse(localStorage.getItem('user'));
    if(userLogado.tipo.nivel !== "GERENTE" && userLogado.tipo.nivel !== "CAIXA")
        window.location.href = "../menu";
    else
        fetch();
}

function fetch() {
    $("#NavBar").load("../../components/NavBar/NavBar.html");
    $("#Footer").load("../../components/Footer/Footer.html");
}