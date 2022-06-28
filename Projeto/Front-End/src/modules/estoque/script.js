let produtos = [];
let selected = {};
let edit = false;
const modeloProduto = {
    nome: '',
    categoria: {
        id: ''
    },
    preco: '',
    quantidade: '',
    marca: '',
    modelo: '',
    adicionais: '',
    codigo: ''
};


$(document).ready(() => {
    validation();
    carregaProdutos();

    $('#formProduto').submit((e) => {
        e.preventDefault();
        onSubmitDialog();
    });

    $("#formFiltro").submit((e) => {
        e.preventDefault()
        buscarProduto();
    });
});

function validation() {
    let userLogado = JSON.parse(localStorage.getItem('user'));
    if(userLogado.tipo.nivel !== "GERENTE" && userLogado.tipo.nivel !== "VENDEDOR" && userLogado.tipo.nivel !== "REPOSITOR")
        window.location.href = "../menu";
    else
        fetch();
}

function fetch() {
    $("#NavBar").load("../../components/NavBar/NavBar.html");
    $("#Footer").load("../../components/Footer/Footer.html");

    combo();
    carregaProdutos();
}

function carregaProdutos() {
    $.get('http://localhost:8082/sistemaestoque/produtos').then((data) =>{
        produtos = data;
        listaProdutos(produtos);
    }).fail(() => carregamentoFalho());
}

function listaProdutos(data) {
    if(data.length>0)
        adicionaProduto(data);
    else
        carregamentoFalho();
}

function adicionaProduto(produto) {
    $(".table>tbody").empty();
    produto.forEach((prod, idx) => {
        $(".table>tbody").append(`<tr>
        <th scope='row'>${prod.codigo}</th>
        <td>${prod.nome}</td>
        <td>${prod.categoria.nome}</td>
        <td>${formatMoeda(prod.preco)}</td>
        <td>${prod.quantidade}</td>
        <td>
        <button type="button" class="btn btn-primary btn-sm" onclick="editProduto(${idx})" data-bs-toggle="modal" data-bs-target="#infoProduto">
        <i class="fa-solid fa-pencil"></i>
        </button>
        <button type="button" class="btn btn-danger btn-sm" onclick="deleteProduto(${idx})">
        <i class="fa-solid fa-trash-can"></i>
        </button>
        </td>
        </tr>`);
    });
}

function carregamentoFalho() {
    $(".table>tbody").empty();
    $(".table>tbody").append('<tr><td colspan="6" class="table-light text-center">Sem dados</td></tr>');
}

function deleteProduto(idx) {
    selected = produtos[idx];

    $.ajax({
        type: 'DELETE',
        url: `http://localhost:8082/sistemaestoque/produtos/${selected.codigo}`,
        headers: { 'Content-Type': 'application/json' },
    }).done(() => {
        carregaProdutos();
    });
}

function novoProduto() {
    selected = { ...modeloProduto };
    edit = false;
    openModal(selected, "Salvar");
}

function editProduto(idx) {
    selected = produtos[idx];
    edit = true;
    openModal(selected, "Atualizar");
}

function openModal(param, msg){
    document.querySelector("#submit").innerHTML = msg;
    document.querySelector("#codigo").value = param.codigo;
    document.querySelector("#nome").value = param.nome;
    document.querySelector("#categoria").value = param.categoria.id;
    document.querySelector("#preco").value = param.preco;
    document.querySelector("#quantidade").value = param.quantidade;
    document.querySelector("#marca").value = param.marca;
    document.querySelector("#modelo").value = param.modelo;
    document.querySelector("#adicionais").value = param.adicionais;
}

function cancelDialog() {
    $("#cancelModal").click();
    carregaProdutos();
}

function onSubmitDialog() {
    const form = new FormData(document.querySelector("#formProduto"))

    const item = { ...modeloProduto };
    Object.keys(modeloProduto).forEach((key) => {
        item[key] = key === 'categoria' ? {id: form.get(key)} : form.get(key);
    });

    if(edit)
        update(item);
    else
        create(item);
}

function create(obj) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8082/sistemaestoque/produtos',
        data: JSON.stringify(obj),
        headers: {
            'Content-Type': 'application/json',
        }
    }).done(() => {
        cancelDialog();
    });
}

function update(obj) {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8082/sistemaestoque/produtos',
        data: JSON.stringify(obj),
        headers: {
            'Content-Type': 'application/json',
        }
    }).then((data) => {
        cancelDialog();
    });
}

function buscarProduto() {
    const filtro = document.querySelector("#filtro").value;

    if(filtro == '') {
        closeAlert();
        carregaProdutos();
        return;
    }

    $.get(`http://localhost:8082/sistemaestoque/produtos/${filtro}`).then((data) => {
        closeAlert();
        listaProdutos([data]);
    }).fail(() => {
        carregamentoFalho();
        document.querySelector("#alert").setAttribute('style', 'display: flex');
    });

    document.querySelector("#filtro").value = '';
}

function closeAlert() {
    document.querySelector("#alert").setAttribute('style', 'display: none');
}

function formatMoeda(valor) {
    return valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})
}

function combo() {
    $.get('http://localhost:8082/sistemaestoque/categorias').then((data) => {
        data.forEach((cat) => {
            $("#categoria").append(`<option value="${cat.id}">${cat.nome}</option>`);
        });
    });
}
