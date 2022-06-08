let vendas = [];
let selected = {};

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

    carregaVenda();
}

function carregaVenda() {
    $.get('http://localhost:8082/sistemaestoque/vendas').then((data) =>{
        vendas = data;
        listaVendas(vendas);
    }).fail(() => carregamentoFalho());;
}

function listaVendas(data) {
    if(data.length>0)
        adicionaVenda(data);
    else
        carregamentoFalho();
}

function adicionaVenda(venda) {
    $("#vendas>tbody").empty();
    venda.forEach((vend, idx) => {
        $("#vendas>tbody").append(`<tr>
        <th scope='row'>${vend.id}</th>
        <td>${formatDate(new Date(vend.dataVenda))}</td>
        <td>${vend.pagamento.toUpperCase()}</td>
        <td>${formatMoeda(vend.valorTotal)}</td>
        <td>
        <button type="button" class="btn btn-primary btn-sm" onclick="showVenda(${idx})" data-bs-toggle="modal" data-bs-target="#infoVenda">
        <i class="fa-solid fa-eye"></i>
        </button>
        </td>
        </tr>`);
    });
}

function carregamentoFalho() {
    $("#vendas>tbody").empty();
    $("#vendas>tbody").append('<tr><td colspan="6" class="table-light text-center">Sem dados</td></tr>');
}

function showVenda(idx) {
    selected = vendas[idx];
    openModal(selected);
}

function openModal(param){
    document.querySelector("#codVenda").innerHTML = param.id;
    document.querySelector("#pagamento").value = param.pagamento.toUpperCase();
    document.querySelector("#total").value = formatMoeda(param.valorTotal);
    document.querySelector("#dataVenda").value = formatDate(new Date(param.dataVenda));

    $("#produtos>tbody").empty();
    if(param.produtos.length > 0) {
        param.produtos.forEach((proVen) => {
            $("#produtos>tbody").append(`<tr>
            <th scope='row'>${proVen.produto.codigo}</th>
            <td>${proVen.produto.nome.toUpperCase()}</td>
            <td>${formatMoeda(proVen.produto.preco)}</td>
            <td>${proVen.quantidade}</td>
            </tr>`);
        });
    } else
        $("#produtos>tbody").append('<tr><td colspan="6" class="table-light text-center">Sem dados</td></tr>');
}

function formatMoeda(valor) {
    return valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})
}

function formatDate(date) {
    return addZeroDate(date.getDate()) + "/" + addZeroDate((date.getMonth()+1)) + "/" + date.getFullYear();
}

function addZeroDate(numero){
    if (numero <= 9)
        return "0" + numero;
    else
        return numero;
}