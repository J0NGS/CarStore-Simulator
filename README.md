# CarStore Simulator

O CarStore Simulator é um projeto desenvolvido como parte da disciplina de Estrutura de Dados 2. O objetivo principal do projeto é explorar e implementar estruturas de dados e algoritmos de compressão manualmente, utilizando a linguagem de programação Java. O projeto simula o cadastro de carros e motoristas de maneira básica, com foco na aplicação de estruturas de dados e algoritmos desenvolvidos por mim.

## Funcionalidades

O projeto é dividido em três versões, cada uma representada por uma branch no repositório:

1. **Main Branch: AVL Tree Version**
   - Nesta versão, o projeto utiliza uma estrutura de dados baseada em Árvore AVL para armazenar os dados.
   - A comunicação entre as aplicações cliente e servidor é realizada por meio de RMI (Remote Method Invocation).

2. **HashTable Version**
   - Na branch HashTable Version, a estrutura de dados foi alterada de Árvore AVL para uma tabela hash.
   - A implementação suporta tanto hash de endereçamento aberto quanto de encadeamento exterior.

3. **HuffmanVersion**
   - A última versão, chamada HuffmanVersion, mantém a tabela hash como estrutura de dados principal.
   - Além disso, foi adicionado um algoritmo de compressão de dados Huffman para comprimir e descomprimir as mensagens trocadas entre as aplicações cliente e servidor.

## Fluxo de Trabalho Padrão

- Todas as versões seguem um fluxo de trabalho padrão, com a comunicação entre duas aplicações: uma atuando como servidor e outra como cliente.
- O RMI em Java é utilizado para facilitar a comunicação remota entre as aplicações.

## Como Executar o Projeto

1. Clone o repositório.
2. Selecione a branch desejada (Main, HashTable Version, ou HuffmanVersion).
3. Compile e execute as aplicações servidor e cliente.
4. Explore as funcionalidades de cadastro de carros e motoristas, observando as diferenças nas estruturas de dados e algoritmos utilizados em cada versão.

## Observações

- O projeto foi desenvolvido exclusivamente em Java, sem o uso de frameworks externos.
- As implementações de estruturas de dados e algoritmos foram feitas manualmente pelos participantes do projeto.
- Qualquer dúvida ou feedback é bem-vindo. Sinta-se à vontade para contribuir para o desenvolvimento contínuo do CarStore Simulator.
