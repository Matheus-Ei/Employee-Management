package classes.Trabalhadores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import classes.Produto;
import classes.Utilitarios.Arquivos;
import classes.Utilitarios.FuncaoUtilitaria;

public class FuncionarioLogado {

    /*
     * Queue é uma interface na biblioteca Java que representa uma fila,
     * seguindo o princípio de "primeiro a entrar, primeiro a sair"
     * new LinkedList<>(); inicializa a variável filaDePedidos criando uma
     * nova instância da classe LinkedList, que implementa a interface Queue.
     * A LinkedList é uma implementação eficiente de uma lista duplamente encadeada,
     * o que a torna apropriada para a implementação de uma fila.
     * Portanto, quando você cria uma instância de LinkedList e a atribui
     * a uma variável do tipo Queue, você está usando polimorfismo e tratando
     * LinkedList como uma fila genérica.
     */
    Queue<String> filaDePedidos = new LinkedList<>();

    private Produto newProduto;
    Scanner scannerString = new Scanner(System.in);
    Scanner scannerDouble = new Scanner(System.in);

    Arquivos arquivo = new Arquivos();

    FuncaoUtilitaria utilitaria = new FuncaoUtilitaria();

    // todas as funcionalidades do funcionario
    public void opcaoDoFuncionario() {
        System.out.println("Bem vindo! As funcionalidades estão abaixo:");
        System.out.println("0 - Voltar");
        System.out.println("1 - Cadastrar produto:");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Gravar pedido");
        System.out.println("4 - Marcar pedido como concluido");
        System.out.println("5 - Proximo produto a ser feito");
        System.out.println("6 - Relatiorio de vendas");
        System.out.println("7 - Ordenar preços");

        String opcao = "-1";

        do {
            opcao = scannerString.nextLine();
            switch (opcao) {
                case "1":
                    this.cadastrarProduto();
                    utilitaria.continuar();
                    break;
                case "2":
                    this.listarProdutos();
                    utilitaria.continuar();
                    break;
                case "3":
                    this.registrarPedido();
                    utilitaria.continuar();
                    break;
                case "4":
                    this.marcarComoPronto();
                    utilitaria.continuar();
                    break;
                case "5":
                    this.proximoPedidoAFazer();
                    utilitaria.continuar();
                    break;
                case "6":
                    this.relatorioVendas();
                    utilitaria.continuar();
                    break;
                case "7":
                    this.ordernarPrecos();
                    utilitaria.continuar();
                    break;
                default:
                    break;
            }

            if (!opcao.equals("0")) {
                System.out.println("0 - Voltar");
                System.out.println("1 - Cadastrar produto:");
                System.out.println("2 - Listar produtos");
                System.out.println("3 - Gravar pedido");
                System.out.println("4 - Marcar pedido como concluido");
                System.out.println("5 - Proximo produto a ser feito");
                System.out.println("6 - Relatiorio de vendas");
                System.out.println("7 - Ordenar preços");

            }
        } while (!opcao.equals("0"));

    }

    private void cadastrarProduto() {

        // leio o nome do produto e enquanto ele tiver 2 letras ou menos
        // nao será aceito
        System.out.println("Digite o nome do produto");
        String nome = scannerString.nextLine();
        while (nome.length() <= 2) {
            System.out.println("Nome invalido, digite novamente");
            nome = scannerString.nextLine();
        }

        boolean valorValido = false;
        double valorNumber = 0;

        // aqui eu leio o valor do produto e enquanto ele nao ser um valor
        // numerico o programa nao continua
        while (!valorValido) {
            try {
                System.out.println("Digite o valor do produto");
                String valor = scannerDouble.nextLine();

                valorNumber = Double.parseDouble(valor);

                valorValido = true;
            } catch (NumberFormatException e) {
                System.out.println("O valor digitado é invalido ");
            }
        }

        // crio um novo produto passando o nome e valor para o contrutor
        newProduto = new Produto(nome, valorNumber);

        // formato os dados para gravar no arquivo
        String produto = "Nome: " + newProduto.getNOme() + "    R$: " + newProduto.getValor() + System.lineSeparator();

        // gravo os dados no arquivo
        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaProdutos(), true)) {
            escritor.write(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listarProdutos() {
        int numeroDeLinhas = 0;
        // verefico se o arquivo existe
        if (arquivo.getArquivoProdutos().exists()) {
            // intancio os leitores, bufferedReader é usado para ler dados em blocos
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    System.out.println(numeroDeLinhas + " - " + linha);
                    numeroDeLinhas++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nenhum produto cadastrado.");
        }
    }

    private void registrarPedido() {
        // verefico se o arquivo existe
        if (arquivo.getArquivoProdutos().exists() && arquivo.getArquivoProdutos().length() != 0) {
            // chamo o metodo para buscar os dados da fila e atualizar ela caso tenha algo
            // em arquivo
            this.buscarDadosDaFila();
            Scanner scannerInt = new Scanner(System.in);
            boolean valorValido = false;

            // espero o usuario digitar um nome de produto valido
            while (!valorValido) {
                System.out.println("Digite o nome correspondente ao produto:");
                // chamo o metodo listaProdutos para o ususario ver oque tem
                this.listarProdutos();
                String pedido = scannerInt.nextLine();

                // enquanto o produto digitado nao existir vai continuar no loop
                while (!this.produtoExiste(pedido)) {
                    System.out.println("Não temos produtos com esse nome");
                    System.out.println("Digite novamente:");
                    pedido = scannerInt.nextLine();
                }

                // a função offer adiciona um valor ao final da fila
                this.filaDePedidos.offer(pedido);
                // chamo o metodo escreverArquivoDaFila para atualizar o arquivo com todos os
                // valores
                this.escreverArquivoDaFila();
                valorValido = true;
            }
        } else {
            System.out.println("Você não tem nenhum produtos cadastrados");
        }
    }

    private boolean produtoExiste(String nomeProduto) {
        boolean produto = false;
        String dataAtual = dataHoje();
        // pego a data atual para se caso o produto existir
        // eu registrar no arquivo a data e o produto comprado

        // verefico se o arquivo existe
        if (arquivo.getArquivoProdutos().exists() && arquivo.getArquivoProdutos().length() != 0) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // se alguma linha do arquivo conter o produto
                    // eu adiciono o produto ao arquivo de vendas
                    // e mudo o valor de produto para true, dessa forma
                    // eu retorno que sim , o produto existe e pode ser comprado
                    if (linha.contains(nomeProduto)) {
                        try (BufferedWriter writer = new BufferedWriter(
                                new FileWriter(arquivo.getArquivoRelatorioVendas(), true))) {
                            writer.write(dataAtual + " " + linha + System.lineSeparator());
                        } catch (IOException e) {
                            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
                        }
                        produto = true;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // retorno true para produto existe e false para produto nao existe
        return produto;
    }

    private void marcarComoPronto() {
        // verefico se arquivos existem
        if (arquivo.getArquivoFilaDeProdutos().exists() && arquivo.getArquivoFilaDeProdutos().length() != 0) {
            // função peek pega o primeiro elemento da fila mas não o remove
            System.out.println(this.filaDePedidos.peek() + " ficou pronto");
            // função poll remove o primeiro elemento da fila
            this.filaDePedidos.poll();
            // reescrevo o arquivo para manter atualizado
            this.escreverArquivoDaFila();
        }
    }

    private void proximoPedidoAFazer() {
        System.out.println("O proximo produto da fila de pedidios é: ");
        // função peek pega o primeiro elemento da fila mas não o remove
        System.out.println(this.filaDePedidos.peek());
    }

    private void buscarDadosDaFila() {
        // confiro se arquivo existe
        if (arquivo.getArquivoFilaDeProdutos().exists() && arquivo.getArquivoFilaDeProdutos().length() != 0) {
            // leio arquivo da fila de produtos
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoFilaDeProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;
                // função clear limpa os valores da fila
                this.filaDePedidos.clear();

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // atualizo os valores da fila com os dados mais recentes
                    this.filaDePedidos.offer(linha);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void escreverArquivoDaFila() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo.getArquivoFilaDeProdutos()))) {
            // para cada elemento da fila, vou adicionar uma linha ao arquivo
            for (String elemento : this.filaDePedidos) {
                writer.write(elemento);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }

    private static String dataHoje() {
        // pega a data atual
        LocalDate dataAtual = LocalDate.now();
        // Define o formato desejado para a data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converte a data para uma string formatada
        return dataAtual.format(formatter);
    }

    // aqui eu tenho a função de ordenação, que foi solicitada no trabalho,
    // ela ordena os preços do menor para o maior e altera o arquivo com os preços
    // em ordem
    public void ordernarPrecos() {
        // verefico se o arquivo existe
        if (arquivo.getArquivoProdutos().exists() && arquivo.getArquivoProdutos().length() != 0) {
            // crio uma lista de dados, é algo semelhante a um array
            List<String> listaDeStrings = new ArrayList<>();
            List<Double> listaPrecos = new ArrayList<>();

            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo e adiciona ao final do array
                while ((linha = bufferedReader.readLine()) != null) {
                    listaDeStrings.add(linha);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // caso a lista tenha mais de 1 dado ele continua com a ordenação
            if (listaDeStrings.size() >= 2) {
                // para valor da lista de strings vou remover somente o valor contido na linha
                // e adicionar a uma outra lista somente com os valore
                for (String linhaDoArquivo : listaDeStrings) {
                    // verefico se na linha tem escrito R$, caso tiver ele retorna o index
                    // se nao encontrar retorna -1
                    int indiceR = linhaDoArquivo.indexOf("R$");
                    if (indiceR != -1) {
                        // crio substrings com o valor
                        String valor = linhaDoArquivo.substring(indiceR + 4).trim();
                        // adiciono os valores a lista
                        listaPrecos.add(Double.parseDouble(valor));
                    }
                }

                // faço a ordenação dos valores, listaPrecos.size retona o tamanho da lista
                // bouble sort
                for (int i = 0; i < listaPrecos.size(); i++) {
                    for (int j = 0; j < listaPrecos.size() - 1; j++) {
                        if (listaPrecos.get(j) > listaPrecos.get(j + 1)) {
                            // crio uma varivel temporaria para o valor
                            Double temporaria = listaPrecos.get(j);
                            // crio uma varivel temporaria para a linha
                            String linhaTemporaria = listaDeStrings.get(j);
                            // nas linhas abaixo faço a troca dos valores
                            listaPrecos.set(j, listaPrecos.get(j + 1));
                            listaDeStrings.set(j, listaDeStrings.get(j + 1));
                            listaPrecos.set(j + 1, temporaria);
                            listaDeStrings.set(j + 1, linhaTemporaria);
                        }
                    }
                }
                System.out.println("A lista ordenada ficou assim: ");

                // limpo o arquivo de produtos
                try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaProdutos())) {
                    escritor.write("");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // reescrevo os valores ordenados no arquivo e printo na tela
                for (int i = 0; i < listaDeStrings.size(); i++) {
                    System.out.println(listaDeStrings.get(i));
                    try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaProdutos(), true)) {
                        escritor.write(listaDeStrings.get(i) + System.lineSeparator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("A lista ordenada ficou assim: ");
                for (int i = 0; i < listaDeStrings.size(); i++) {
                    System.out.println(listaDeStrings.get(i));
                }
            }

        } else {
            System.out.println("Nenhum produto cadastrado");
        }
    }

    private void relatorioVendas() {

        String dataAtual = dataHoje();
        // crio uma varivel com a data atual

        // verefico se o arquivo existe
        if (arquivo.getArquivoRelatorioVendas().exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoRelatorioVendas());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // faço a vereficação de se alinha contem a data atual, caso ela contenha eu
                // printo na tela
                System.out.println(System.lineSeparator() + "------------------------------------------------------"
                        + System.lineSeparator());
                while ((linha = bufferedReader.readLine()) != null) {
                    if (linha.contains(dataAtual)) {
                        System.out.println("\t" + linha);
                    }
                }
                System.out.println(System.lineSeparator() + "------------------------------------------------------"
                        + System.lineSeparator());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nenhuma venda realizada.");
        }

    }

}
