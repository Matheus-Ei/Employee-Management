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

    Queue<String> filaDePedidos = new LinkedList<>();

    private Produto newProduto;
    Scanner scannerString = new Scanner(System.in);
    Scanner scannerDouble = new Scanner(System.in);

    Arquivos arquivo = new Arquivos();

    FuncaoUtilitaria utilitaria = new FuncaoUtilitaria();

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

        System.out.println("Digite o nome do produto");
        String nome = scannerString.nextLine();
        while (nome.length() <= 2) {
            System.out.println("Nome invalido, digite novamente");
            nome = scannerString.nextLine();
        }

        boolean valorValido = false;
        double valorNumber = 0;

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
        newProduto = new Produto(nome, valorNumber);

        String produto = "Nome: " + newProduto.getNOme() + "    R$: " + newProduto.getValor() + System.lineSeparator();

        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaProdutos(), true)) {
            escritor.write(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listarProdutos() {
        int numeroDeLinhas = 0;
        if (arquivo.getArquivoProdutos().exists()) {
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
        if (arquivo.getArquivoProdutos().exists() && arquivo.getArquivoProdutos().length() != 0) {
            this.buscarDadosDaFila();
            Scanner scannerInt = new Scanner(System.in);
            boolean valorValido = false;

            while (!valorValido) {
                System.out.println("Digite o nome correspondente ao produto:");
                this.listarProdutos();
                String pedido = scannerInt.nextLine();

                while (!this.produtoExiste(pedido)) {
                    System.out.println("Não temos produtos com esse nome");
                    System.out.println("Digite novamente:");
                    pedido = scannerInt.nextLine();
                }

                this.filaDePedidos.offer(pedido);
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

        if (arquivo.getArquivoProdutos().exists() && arquivo.getArquivoProdutos().length() != 0) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
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

        return produto;
    }

    private void marcarComoPronto() {
        if (arquivo.getArquivoFilaDeProdutos().exists() && arquivo.getArquivoFilaDeProdutos().length() != 0) {
            this.filaDePedidos.poll();
            this.escreverArquivoDaFila();
        }
    }

    private void proximoPedidoAFazer() {
        System.out.println("O proximo produto da fila de pedidios é: ");
        System.out.println(this.filaDePedidos.peek());
    }

    private void buscarDadosDaFila() {
        if (arquivo.getArquivoFilaDeProdutos().exists() && arquivo.getArquivoFilaDeProdutos().length() != 0) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoFilaDeProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;
                this.filaDePedidos.clear();

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    this.filaDePedidos.offer(linha);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void escreverArquivoDaFila() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo.getArquivoFilaDeProdutos()))) {
            for (String elemento : this.filaDePedidos) {
                writer.write(elemento);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }

    private static String dataHoje() {
        LocalDate dataAtual = LocalDate.now();
        // Define o formato desejado para a data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converte a data para uma string formatada
        return dataAtual.format(formatter);
    }

    public void ordernarPrecos() {
        if (arquivo.getArquivoProdutos().exists() && arquivo.getArquivoProdutos().length() != 0) {
            List<String> listaDeStrings = new ArrayList<>();
            List<Double> listaPrecos = new ArrayList<>();

            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    listaDeStrings.add(linha);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (listaDeStrings.size() >= 2) {
                for (String linhaDoArquivo : listaDeStrings) {
                    int indiceR = linhaDoArquivo.indexOf("R$");
                    if (indiceR != -1) {
                        String valor = linhaDoArquivo.substring(indiceR + 4).trim();
                        listaPrecos.add(Double.parseDouble(valor));
                    }
                }

                for (int i = 0; i < listaPrecos.size(); i++) {
                    for (int j = 0; j < listaPrecos.size() - 1; j++) {
                        if (listaPrecos.get(j) > listaPrecos.get(j + 1)) {
                            Double temporaria = listaPrecos.get(j);
                            String linhaTemporaria = listaDeStrings.get(j);
                            listaPrecos.set(j, listaPrecos.get(j + 1));
                            listaDeStrings.set(j, listaDeStrings.get(j + 1));
                            listaPrecos.set(j + 1, temporaria);
                            listaDeStrings.set(j + 1, linhaTemporaria);
                        }
                    }
                }
                System.out.println("A lista ordenada ficou assim: ");

                try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaProdutos())) {
                    escritor.write("");
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

        if (arquivo.getArquivoRelatorioVendas().exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoRelatorioVendas());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

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
