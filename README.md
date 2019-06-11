# Estacionamento-JAVA

## COMO FUNCIONARÁ O PROJETO:

Deverá ser criado um sistema para a gestão de um estacionamento, que cuide do Fluxo de Veículos. Assim que um veículo entrar deverá ser anotada a Placa, o Modelo e a Data e Hora de Entrada. E ao sair deverá ser anotada a Data e Hora de Saída, calcular o Tempo em Horas que o veículo ficou estacionado e calcular o Valor a Pagar.

## PERÍODO DE TOLERÂNCIA: 5 MINUTOS

VALOR DA PRIMEIRA HORA: R$6,00
VALOR DAS DEMAIS HORAS: R$2,00

Na tabela inicial serão mostrados apenas os veículos que estão estacionados.

## TELA DE ENTRADA:

Quando clicar em Gravar o veículo deverá ser gravado no banco de dados, deve ser adicionado a tela principal e setar a Data/Hora de Entrada.

## TELA DE SAÍDA:
Ao selecionar um veículo e clicar no botão Remover a Tela de Saída deverá ser acionada:

Quando realizada essa operação a Data/Hora de Saída deverá captar o horário em que o botão Remover foi acionado, o Tempo em Horas deverá ser calculado com a tolerância de 5 minutos e o Valor a Pagar deverá ser calculado baseado no Tempo em Horas.

## FECHAR SISTEMA:

Ao clicar no botão Sair do Sistema deverá ser aberta essa Janela confirmando a saída.

## CLASSES

### •	Cliente (pacote model)
Código – int
Placa – String
Modelo – String
Data e Hora de Entrada – String
Data e Hora de Saída – String
Tempo – int
Valor Pago – Double
Getters & Setters

### •	Calculos (pacote model)
valorPagar(int tempo); (Calcula o valor que deverá ser pago baseado no Tempo em Horas estacionado)

### •	ClienteDAO (pacote DAO)
gravar( ); (Grava o cliente na tbl_movimentacao)
atualizar( ); (Atualiza as informações de um cliente existente)
excluir(int codigo); (Exclui um cliente pelo código dele)
getCliente(int codigo); (Pega um cliente pelo código)
ArrayList<Cliente> listarClientes( ); (lista todos os clientes na tbl_movimentacao)
ArrayList<Cliente> listarClientesEstacionados( ); (lista apenas clientes onde a data_saida = null, ou seja, clientes ainda estacionados)
primeiraHora( ); (pega o valor da primeira hora na tbl_valor)
demaisHoras( ); (pega o valor das demais horas na tbl_valor)

### •	Conexao (pacote DAO)
getConexao( ); (Faz a conexão ao banco de dados)
fecharConexao( ); (Encerra a conexão ao banco de dados)

### •	Data (pacote utils)
converterParaBanco(String data); (converte uma data em formato comum para um formato aceito pelo banco de dados)
converterParaTela(String data); (converte uma data em formato do banco para o formato normal)
horaAtual( ); (passa a hora atual)
quantidadeHoras(String dataEntrada, String dataSaida); (calcula a quantidade de horas que o cliente esteve estacionado baseado na hora de Entrada/Saída e a Tolerância de 5 minutos)

### •	FrmEntrada (pacote view)
É a tela que aparece quando o botão “Adicionar novo Veículo” é clicado.

### •	FrmPrincipal (pacote view)
É a tela que aparece quando se inicia o sistema.

### •	FrmSaida (pacote view)
É a tela que aparece quando o botão “Remover” e o botão “Atualizar” são clicados.

