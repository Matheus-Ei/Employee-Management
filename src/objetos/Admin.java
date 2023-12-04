package objetos;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

/*
GRAVAR DADOS EM ARQUIVOS

import java.io.FileWriter;
import java.io.IOException;  

OU 

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

1. **Definir os dados a serem gravados.**
2. **Especificar o caminho do arquivo.**
3. **Utilizar as classes `FileWriter` ou `BufferedWriter` para escrever os dados no arquivo.**
4. **Fechar o escritor para liberar os recursos.**
 */

public class Admin extends Usuario {
    public String nome;

    String diretorioAreaDeTrabalho = System.getProperty("user.home") + "/Desktop/";

    // Nome do arquivo
    String dadosDoUsuario = "DadosDoUsuario.txt";
    String todosUsuarios = "TodosUsuarios.txt";

    // Caminho completo do arquivo
    String caminhoParaDadosDoUsuario = diretorioAreaDeTrabalho + dadosDoUsuario;
    String caminhoParaTodosUsuarios = diretorioAreaDeTrabalho + todosUsuarios;

    File arquivoDados = new File(caminhoParaDadosDoUsuario);
    File arquivoUsuarios = new File(caminhoParaTodosUsuarios);

    public void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu email");
        String email = scanner.nextLine();

        if (arquivoUsuarios.exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivoUsuarios);
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.equals("email: " + email)) {
                        System.out.println("Ja tem um usuario cadastrado com esse email");
                        System.out.println("Insira o email novamente");
                        email = scanner.nextLine();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Digite a sSua senha");
        String senha = scanner.nextLine();
        this.fazerLogin(email, senha);
        // Obtendo o diretório da área de trabalho do usuário

        if (!arquivoDados.exists()) {
            try {
                arquivoDados.createNewFile();
                arquivoUsuarios.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String dados = "email: " + email + "\nsenha: " + senha + "\n";

        try (FileWriter escritor = new FileWriter(caminhoParaDadosDoUsuario, true)) {
            escritor.write(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter escritor = new FileWriter(caminhoParaTodosUsuarios, true)) {
            escritor.write("email: " + email + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listarUsuarios() {
        if (arquivoUsuarios.exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivoUsuarios);
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    System.out.println(linha);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo esta vazio!!");
        }

    }

    public void deletarUsuario() {

    }

    public void acessarRelatorioVendas() {

    }

    public void acessarListaVendas() {

    }
}
