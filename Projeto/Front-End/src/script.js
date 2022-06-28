$("#NavBar").load("./components/NavBar/NavBar.html");
$("#Footer").load("./components/Footer/Footer.html");

if(localStorage.getItem('token') != null) {
    window.location.href = "./modules/menu/index.html";
}

$('#formLogin').submit((e) => {
    e.preventDefault();
    logar();
});

function logar() {
    let usuario = document.querySelector("#usuario");
    let userLabel = document.querySelector("#userLabel");

    let senha = document.querySelector("#senha");
    let senhaLabel = document.querySelector("#senhaLabel");

    let msgError = document.querySelector("#msgError");

    let userValid = {
        cpf: '',
        email: '',
        id: '',
        nome: '',
        senha: '',
    };

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8082/sistemaestoque/usuarios/login',
        contentType : 'application/json;charset=UTF-8',
        dataType : 'json',
        data : `email=${usuario.value}&senha=${senha.value}`
    }).then((data) => {
        if(data) {
            userValid = Object.assign(userValid, data);
            let token = Math.random().toString(16).substr(2) + Math.random().toString(16).substr(2);

            localStorage.setItem('token', token);
            localStorage.setItem('user', JSON.stringify(userValid));

            window.location.href = "./modules/menu/index.html";
        } else {
            userLabel.setAttribute('style', 'color: red');
            usuario.setAttribute('style', 'border-color: red');
            senhaLabel.setAttribute('style', 'color: red');
            senha.setAttribute('style', 'border-color: red');
            msgError.setAttribute('style', 'display: block');
            usuario.focus();
        }
    });
};
