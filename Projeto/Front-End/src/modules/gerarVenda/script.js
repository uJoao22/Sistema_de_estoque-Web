let prodsVenda = [];

$(document).ready(() => {
    validation();

    $("#formFiltro").submit((e) => {
        e.preventDefault()
        buscarProduto();
    });

    $("#formGerarVenda").submit((e) => {
        e.preventDefault();

        if(prodsVenda.length > 0) {
            const formaPag = document.querySelector("#pagamento").value;
            const valTot = somaValorTotal();

            const sendObj = {
                pagamento: formaPag,
                valorTotal: valTot,
                produtos: prodsVenda,
            };

            gerarVenda(sendObj);
        } else {

        }
    });
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

function buscarProduto() {
    const filtro = document.querySelector("#filtro").value;

    if(filtro == '') {
        closeAlertErro();
        return;
    }

    $.get(`http://localhost:8082/sistemaestoque/produtos/${filtro}`).then((data) => {
        closeAlertErro();

        const temEstoque = verificaEstoque(data);
        const isAdd = verificaExistsProduto(data);

        if(temEstoque && !isAdd) {
            if(prodsVenda.length == 0)
                $("#produtosVenda>tbody").empty();

            const item = {
                produto: {...data},
                quantidade: 1,
            }

            prodsVenda.push(item);
            insereProduto([data]);
        } else if(isAdd) {
            document.querySelector("#msgDanger").innerHTML = "Produto já adicionado";
            document.querySelector("#alertDanger").setAttribute('style', 'display: flex');
        } else {
            document.querySelector("#msgDanger").innerHTML = "Produto sem estoque.";
            document.querySelector("#alertDanger").setAttribute('style', 'display: flex');
        }

    }).fail(() => {document.querySelector("#alertErro").setAttribute('style', 'display: flex')});

    document.querySelector("#filtro").value = '';
}

function insereProduto(produto) {
    if(produto.length == 1) {
        produto.forEach((prod, idx) => {
            $("#produtosVenda>tbody").append(`<tr>
            <th scope='row'>${prod.codigo}</th>
            <td>${prod.nome.toUpperCase()}</td>
            <td>${formatMoeda(prod.preco)}</td>
            <td><input type="number" class="form-control form-control-sm quantPro${prod.codigo}" onblur="updateQuant(${prod.codigo})" value="1" min="1" max="${prod.quantidade}"></input></td>
            <td>
            <button type="button" class="btn btn-danger btn-sm" onclick="removerProduto(${prod.codigo})">
            <i class="fa-solid fa-xmark"></i>
            </button>
            </td>
            </tr>`);
        });
        atualizaValorTotal();
    }
}

function verificaEstoque(produto) {
    return produto.quantidade > 0 && produto.quantidade != 0;
}

function verificaExistsProduto(produto) {
    let temPro = false;
    prodsVenda.forEach((proVen) => {
        if(proVen.produto.codigo == produto.codigo)
            temPro = true;
    });
    return temPro;
}

function removerProduto(codigo){
    const novaLista = prodsVenda.filter((pro) => pro.produto.codigo != codigo);
    prodsVenda = novaLista;
    atualizaLista(novaLista)
}

function atualizaLista(listaProdutos) {
    $("#produtosVenda>tbody").empty();
    if(listaProdutos.length > 0) {
        listaProdutos.forEach((prod) => {
            $("#produtosVenda>tbody").append(`<tr>
            <th scope='row'>${prod.produto.codigo}</th>
            <td>${prod.produto.nome.toUpperCase()}</td>
            <td>${formatMoeda(prod.produto.preco)}</td>
            <td><input type="number" class="form-control form-control-sm quantPro${prod.codigo}" onblur="updateQuant(${prod.codigo})" value="${prod.quantidade}" min="1" max="${prod.quantidade}"></input></td>
            <td>
            <button type="button" class="btn btn-danger btn-sm" onclick="removerProduto(${prod.produto.codigo})">
            <i class="fa-solid fa-xmark"></i>
            </button>
            </td>
            </tr>`);
        });
    } else
        $("#produtosVenda>tbody").append('<tr><td colspan="6" class="table-light text-center">Sem dados</td></tr>');
    atualizaValorTotal();
}

function gerarVenda(obj) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8082/sistemaestoque/vendas',
        data: JSON.stringify(obj),
        headers: {
            'Content-Type': 'application/json',
        }
    }).done(() => {
        limpaForm();
    });
}

function limpaForm() {
    prodsVenda = [];
    document.querySelector("#alertSuccess").setAttribute('style', 'display: flex');
    document.querySelector("#total").value = "";
    $("#produtosVenda>tbody").empty();
    $("#produtosVenda>tbody").append('<tr><td colspan="6" class="table-light text-center">Sem dados</td></tr>');
    document.querySelector("#pagamento").value = '';
}

function updateQuant(codigo) {
    const quant = $(`.quantPro${codigo}`).val()
    prodsVenda.forEach((proVen) => {
        if(proVen.produto.codigo == codigo) {
            proVen.quantidade = quant;
        }
    });
    atualizaValorTotal();
}

function atualizaValorTotal() {
    if(prodsVenda.length > 0)
        document.querySelector("#total").value = formatMoeda(somaValorTotal());
    else
        document.querySelector("#total").value = "";
}

function somaValorTotal() {
    let soma = 0;
    prodsVenda.forEach((prod) => soma += (prod.produto.preco*prod.quantidade));
    return soma;
}

function closeAlertErro() {
    document.querySelector("#alertErro").setAttribute('style', 'display: none');
}

function closeAlertSuccess() {
    document.querySelector("#alertSuccess").setAttribute('style', 'display: none');
}

function closeAlertDanger() {
    document.querySelector("#alertDanger").setAttribute('style', 'display: none');
}

function formatMoeda(valor) {
    return valor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})
}