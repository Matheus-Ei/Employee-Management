package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Funcionario extends Usuario {
    Scanner scannerString = new Scanner(System.in);
    Scanner scannerDouble = new Scanner(System.in);

    public Funcionario(String nome, String email, String senha){
        super(nome, email, senha);
    } 
    
    Produto newProduto;

    String diretorioAreaDeTrabalho = System.getProperty("user.home") + "/Desktop/";

    // Nome do arquivo
    String produtos = "Produtos.txt";

    // Caminho completo do arquivo
    String caminhoParaProdutos = diretorioAreaDeTrabalho + produtos;

    File arquivoProdutos = new File(caminhoParaProdutos);

    public void cadastrarProduto() {
        System.out.println("Digite o nome do produto");
        String nome = scannerString.nextLine();
        while (nome.length() <= 2) {
            System.out.println("Nome invalido, digite novamente");
            nome = scannerString.nextLine();
        }

        System.out.println("Digite o valor do produto");
        double valor = scannerDouble.nextDouble();

        while (valor <= 0) {
            System.out.println("Valor invalido, digite novamente");
            valor = scannerString.nextDouble();
        }

        newProduto = new Produto(nome, valor);

        if (!arquivoProdutos.exists()) {
            try {
                arquivoProdutos.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String produto = "Nome: " + newProduto.getNOme() + "    R$: " + newProduto.getValor() + System.lineSeparator();

        try (FileWriter escritor = new FileWriter(caminhoParaProdutos, true)) {
            escritor.write(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listarProdutos() {
        if (arquivoProdutos.exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivoProdutos);
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                int count = 1;
                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    System.out.println(count + " - " + linha);
                    count++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo esta vazio!!");
        }
    }

    public void relatorioVendas() {

    }

}