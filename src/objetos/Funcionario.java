package objetos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Funcionario {
    public String nome;
    Scanner scannerString = new Scanner(System.in);
    Scanner scannerDouble = new Scanner(System.in);

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

        String produto = "Nome: " + newProduto.getNOme() + "\tValor: " + newProduto.getValor() + System.lineSeparator();

        try (FileWriter escritor = new FileWriter(caminhoParaProdutos, true)) {
            escritor.write(produto);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listarProdutos() {

    }

    public void relatorioVendas() {

    }

}