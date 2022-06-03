$(document).ready(() => {
    fetch();
});

function fetch() {
    $("#NavBar").load("../../components/NavBar/NavBar.html");
    $("#Footer").load("../../components/Footer/Footer.html");

    let userLogado = JSON.parse(localStorage.getItem('user'));
    let msgBemVindo = document.querySelector("#msgBemVindo");
    msgBemVindo.innerHTML = `Olá, ${userLogado.nome}`;

    let sectionEstoque = document.querySelector("#sectionEstoque");
    let sectionVendas = document.querySelector("#sectionVendas");
    let sectionGerarVenda = document.querySelector("#sectionGerarVenda");

    if(userLogado.tipo.nivel === "GERENTE" || userLogado.tipo.nivel === "VENDEDOR" || userLogado.tipo.nivel === "REPOSITOR")
        sectionEstoque.setAttribute('style', 'display: block');
    if(userLogado.tipo.nivel === "GERENTE")
        sectionVendas.setAttribute('style', 'display: block');
    if(userLogado.tipo.nivel === "GERENTE" || userLogado.tipo.nivel === "CAIXA")
        sectionGerarVenda.setAttribute('style', 'display: block');
}

$("#estoque").click(() => window.location.href = '../estoque/');
$("#vendas").click(() => window.location.href = '../vendas/');
$("#gerarVenda").click(() => window.location.href = '../gerarVenda/');