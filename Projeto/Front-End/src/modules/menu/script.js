$("#NavBar").load("../../components/NavBar/NavBar.html");
$("#Footer").load("../../components/Footer/Footer.html");

$("#estoque").click(() => {
    window.location.assign('../estoque/');
});

$("#vendas").click(() => {
    window.location.assign('../vendas/');
});

$("#gerarVenda").click(() => {
    window.location.assign('../gerarVenda/');
});