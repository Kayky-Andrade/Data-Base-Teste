create database king;
use king;

create table livros (
	codLivro int primary key auto_increment not null,
    nomeLivro varchar(40) not null,
    autorLivro varchar(40),
    quantLivro int not null,
    generoLivro varchar(20) not null
    );

create table clientes (
	codCli int primary key auto_increment not null,
    nomeCli varchar(40) not null,
    telefoneCli varchar(16) not null,
    enderecoCli varchar(60) not null
    );
    
create table funcionarios (
	codFun int primary key auto_increment not null,
    nomeFun varchar(40) not null,
    telefoneFun varchar(16) not null
    );
    
create table venda (
	codVenda int primary key auto_increment not null,
    dataVenda date not null,
    codLivro int not null,
    codCli int not null,
    codFun int not null
    );

-- adicionando relacionamento entre as tabelas, venda tera relacionamento com as outras 3 chaves primarias

alter table venda
add constraint fk_venda_livro
foreign key (codLivro) references livros(codLivro);

alter table venda
add constraint fk_venda_cliente
foreign key (codCli) references clientes(codCli);

alter table venda
add constraint fk_venda_funcionario
foreign key (codFun) references funcionarios(codFun);

-- -----------------------------------------------------------------------------------------------------------

insert into livros (nomeLivro, autorLivro, quantLivro, generoLivro) values
('O Senhor dos Anéis', 'J.R.R. Tolkien', 12, 'Fantasia'),
('Dom Casmurro', 'Machado de Assis', 8, 'Romance'),
('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 15, 'Fantasia'),
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 20, 'Infantil'),
('1984', 'George Orwell', 10, 'Distopia');

insert into clientes (nomeCli, telefoneCli, enderecoCli) values
('Ana Paula', '(11) 98765-4321', 'Rua das Flores, 123 - São Paulo'),
('Carlos Eduardo', '(21) 91234-5678', 'Av. Brasil, 456 - Rio de Janeiro'),
('Fernanda Souza', '(31) 99876-5432', 'Rua Minas Gerais, 789 - Belo Horizonte'),
('João Pedro', '(41) 97654-3210', 'Rua Paraná, 321 - Curitiba'),
('Mariana Lima', '(51) 98712-3456', 'Av. Ipiranga, 654 - Porto Alegre');

insert into funcionarios (nomeFun, telefoneFun) values
('Bruno Silva', '(11) 92345-6789'),
('Camila Ferreira', '(21) 93456-7890'),
('Diego Costa', '(31) 94567-8901'),
('Larissa Alves', '(41) 95678-9012'),
('Rafael Martins', '(51) 96789-0123'),
('Kayky', '(31) 98885-6182'),
('Robert', '(31) 96573-9387');

insert into venda (dataVenda, codLivro, codCli, codFun) values
('2025-04-25', 1, 2, 1),
('2025-04-25', 3, 4, 2),
('2025-04-26', 2, 5, 3),
('2025-04-26', 5, 1, 4),
('2025-04-26', 4, 3, 5);

-- --------------- Visualização de Informações das Tabelas --------------------------------------------
describe livros;
describe clientes;
describe funcionarios;
describe venda;

select * from livros;
select * from clientes;
select * from funcionarios;
select * from venda;
-- selec from para substituir os codigos pelos nomes dos respectivos itens
select 
    venda.codVenda,
    venda.dataVenda,
    clientes.nomeCli as NomeCliente,
    livros.nomeLivro as NomeLivro,
    funcionarios.nomeFun as NomeFuncionario
from venda
inner join clientes on venda.codCli = clientes.codCli
inner join livros on venda.codLivro = livros.codLivro
inner join funcionarios on venda.codFun = funcionarios.codFun;
-- -----------------------------------------------------------------------------------------------------
