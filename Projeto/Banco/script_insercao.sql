INSERT INTO categoria (nome)
VALUES ('Revestimentos'), ('Argamassa'), ('Sanitários');

insert into tipo_usuario (nivel)
values ('GERENTE'), ('CAIXA'), ('VENDEDOR'), ('REPOSITOR');

insert into usuarios (nome, cpf, email, id_tipo, senha)
values 
('Andrade', '123.456.789-00', 'andrade@eletroandrade.com', 1, 'andrade123'),
('Janaina', '789.654.321-11', 'janaina@eletroandrade.com', 2, 'janaina123'),
('Claudio', '444.444.444-88', 'claudio@eletroandrade.com', 3, 'claudio123'),
('Roberto', '888.555.222-77', 'roberto@eletroandrade.com', 4, 'roberto123');

insert into produtos (nome, id_categoria, preco, quantidade, marca, modelo, adicionais)
values
('Vaso Sanitário', 3, 350.00, 5, 'Celite', 'Caixa Acoplada', '6 litros'),
('Cerâmica', 1, 48.90, 100, 'Embramaco', '', 'Tamanho da peça: 33x60; Metro por caixa: 2,43m'),
('Rejunte Branco', 2, 15.00, 50, 'Precon', '', '1kg');

INSERT into vendas (id_pedido, codigo_produto, quantidade, data_venda, pagamento)
values
('1111', 1, 20, '2022-05-10 13:27:48', 'dinheiro'),
('1111', 2, 4, '2022-05-10 13:27:48', 'dinheiro'),
('1111', 3, 1, '2022-05-10 13:27:48', 'dinheiro');