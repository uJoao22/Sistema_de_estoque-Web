$("#NavBar").load("../../components/NavBar/NavBar.html");
$("#Footer").load("../../components/Footer/Footer.html");


document.querySelector("#novaVenda").addEventListener("click", () => {
    window.location.assign("../gerarVenda");
});