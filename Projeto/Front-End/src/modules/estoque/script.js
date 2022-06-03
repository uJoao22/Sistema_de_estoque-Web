$("#NavBar").load("../../components/NavBar/NavBar.html");
$("#Footer").load("../../components/Footer/Footer.html");

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
    combo();

    $.get('http://localhost:8082/sistemaestoque/produtos').then((data) =>{
        console.log(data);
        produtos = data;
        listaProdutos(produtos);
    }).fail(() => carregamentoFalho());

    // $("#filtro").keyup(() => {
    //     console.log(this.val);
    // });

    $('#formProduto').submit((e) => {
        e.preventDefault();
        onSubmitDialog();
    });

    $("#buscar").click(() => {
        const filtro = $("#filtro").val();
        console.log(produtos);
        let produtosFiltrados = [];
        produtos.forEach((prod) => {
            if(prod.codigo == filtro)
                produtosFiltrados.push(prod);
        });
        console.log(produtosFiltrados);

        filtroBusca(produtosFiltrados);
    });
});

function validation() {
    let userLogado = JSON.parse(localStorage.getItem('user'));
    if(userLogado.tipo.nivel !== "GERENTE" && userLogado.tipo.nivel !== "VENDEDOR" && userLogado.tipo.nivel !== "REPOSITOR") {
        window.location.href = "../menu";
    }
};

function filtroBusca(produtos) {
    if(produtos.length > 0) {
        // produtos.forEach((prod) => {
            const tbody = document.getElementsByTagName("tbody")[0];
            const itens = tbody.getElementsByTagName('tr');
            const itensRemove = tbody.getElementsByTagName('tr');

            console.log(itens);
            for(let i=0; i<itens.length; i++) {
                tbody.removeChild(itensRemove[i]);
                console.log(itens[i]);
            }
        // });
        // listaProdutos(produtos);
    }
};

function listaProdutos(data) {
    if(data.length>0) {
        data.forEach((prod, idx) => {
            $(".table>tbody").append(`<tr>
            <th scope='row'>${prod.codigo}</th>
            <td>${prod.nome}</td>
            <td>${prod.categoria.nome}</td>
            <td>${prod.preco}</td>
            <td>${prod.quantidade}</td>
            <td>
            <button type="button" class="btn btn-primary btn-sm" onclick="editProduto(${idx})" data-bs-toggle="modal" data-bs-target="#infoProduto">
            <i class="fa-solid fa-pencil"></i>
            </button>
            </td>
            </tr>`);
        });
    } else
        carregamentoFalho();
};


function novoProduto() {
    selected = { ...modeloProduto };
    edit = false;
    openModal(selected, "Salvar");
};

function editProduto(idx) {
    selected = produtos[idx];
    console.log(selected);
    edit = true;
    openModal(selected, "Atualizar");
};

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
};

function carregamentoFalho() {
    $(".table>tbody").append('<tr><td colspan="6" class="table-light text-center">Sem dados</td></tr>');
};

function onSubmitDialog() {
    const form = new FormData(document.querySelector("#formProduto"))

    const item = { ...modeloProduto };
    Object.keys(modeloProduto).forEach((key) => {
        item[key] = key === 'categoria' ? {id: form.get(key)} : form.get(key);
    });

    console.log(item);

    if(edit)
        update(item);
    else
        create(item);
};

function create(obj) {
    console.log(obj);
    // $.post('http://localhost:8082/sistemaestoque/produtos', obj, (data) => {
        // console.log(data);
    // });

    // $.ajax({
    //     type: 'POST',
    //     url: 'http://localhost:8082/sistemaestoque/produtos',
    //     data: obj,
    //     dataType: 'application/json;charset=UTF-8',
    //     headers: {
    //         contentType: 'application/json',
    //     }
    // }).then((data) => {
    //     console.log(data);
    // });
};

function update(obj) {
    console.log(JSON.stringify(obj));
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8082/sistemaestoque/produtos',
        data: JSON.stringify(obj),
        contentType : 'application/json',
        dataType : 'json',
    }).then((data) => {
        console.log(data);
    });

    // fetch('http://localhost:8082/sistemaestoque/produtos', {
    //     method: 'put',
    //     mode: 'no-cors',
    //     headers: {
    //       'Accept': 'application/json',
    //       'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify(obj)
    // }).then((data) => {
    //     console.log(data);
    // });
}

function combo() {
    $.get('http://localhost:8082/sistemaestoque/categorias').then((data) => {
        data.forEach((cat) => {
            $("#categoria").append(`<option value="${cat.id}">${cat.nome}</option>`);
        });
    });
}