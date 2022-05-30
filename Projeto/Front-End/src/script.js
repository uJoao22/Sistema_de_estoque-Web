$("#NavBar").load("./components/NavBar/NavBar.html");
$("#Footer").load("./components/Footer/Footer.html");

document.querySelector("#login").addEventListener("click", () => {
    window.location.assign("./modules/menu/index.html");
    // $.get('http://localhost:8082/sistemaestoque/produtos', (data) =>{
    //     console.log(data);
    // });
});


