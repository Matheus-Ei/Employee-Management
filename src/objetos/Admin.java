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
    String nome;
    Arquivos arquivo = new Arquivos();
    

    public void setName(String nome){
        this.nome = nome;
    }

    public String getName(){
        return this.nome;
    }

    public void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu email");
        this.setEmail(scanner.nextLine());

        String email = this.getEmail();

        while (email.length() < 7) {
            System.out.println("O email é muito curto, digite novamente:");
            this.setEmail(scanner.nextLine());
            email = this.getEmail();
        }

        if (arquivo.arquivoUsuarios.exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.arquivoUsuarios);
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.equals("email: " + email)) {
                        System.out.println("Ja tem um usuario cadastrado com esse email");
                        System.out.println("Insira o email novamente");
                        this.setEmail(scanner.nextLine());
                        email = this.getEmail();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Digite a sua senha");
        this.setSenha(scanner.nextLine());
        String senha = this.getSenha();

        while (senha.length() < 5) {
            System.out.println("A senha é muito curta, digite novamente");
            this.setSenha(scanner.nextLine());
            senha = this.getSenha();
        }

        if (!arquivo.arquivoDados.exists()) {
            try {
                arquivo.arquivoDados.createNewFile();
                arquivo.arquivoUsuarios.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String dados = "email: " + email + "    senha: " + senha + System.lineSeparator();

        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaDadosDoUsuario(), true)) {
            escritor.write(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaTodosUsuarios(), true)) {
            escritor.write("email: " + email + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listarUsuarios() {
        if (arquivo.arquivoUsuarios.exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.arquivoUsuarios);
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

    public void deletarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o email a ser deletado:");
        this.setEmail(scanner.nextLine());
        String email = this.getEmail();
        String deletarLinha = "email: " + email;

        // Configuração do arquivo temporário
        String temporarioFile = "Temporario.txt";
        String caminhoTemporario = arquivo.getDiretorioAreaDeTrabalho() + temporarioFile;
        File arquivoTemporario = new File(caminhoTemporario);

        fazerTrocaDeArquivos(arquivo.getArquivoUsuario(), arquivoTemporario, deletarLinha);
        fazerTrocaDeArquivos(arquivo.getArquivoDados(), arquivoTemporario, deletarLinha);

    }

    // usa o static para nao precisar instanciar ela dentro de onde for usar pelo
    // que eu entendi
    private static void fazerTrocaDeArquivos(File caminhoArquivo, File caminhoTemporario, String deletarLinha) {
        if (caminhoArquivo.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(caminhoArquivo));
                    BufferedWriter escritorTemporario = new BufferedWriter(new FileWriter(caminhoTemporario, true))) {

                String linha;

                // Lê cada linha do arquivo original de dados
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha
                    if (!linha.contains(deletarLinha)) {
                        // Se a linha não contiver a string a ser deletada, escreve no mesmo arquivo
                        // temporário
                        escritorTemporario.write(linha);
                        escritorTemporario.newLine();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // Substitui o arquivo original de dados pelo temporário
            substituirArquivo(caminhoArquivo, caminhoTemporario);
        } else {
            System.out.println("O arquivo de dados está vazio!!");
        }
    }

    // Método para substituir o arquivo original pelo temporário
    /*
     * private: O método só é acessível dentro da própria classe Admin. Outras
     * classes fora de Admin não podem chamar diretamente esse método.
     * 
     * static: O método pertence à classe Admin e não a instâncias específicas dessa
     * classe. Pode ser chamado diretamente usando o nome da classe
     * (Admin.substituirArquivo(...)) sem a necessidade de criar uma instância de
     * Admin.
     */
    private static void substituirArquivo(File arquivoOriginal, File arquivoTemporario) {
        // Deleta o arquivo original
        if (!arquivoOriginal.delete()) {
            System.out.println("Não foi possível deletar o arquivo original.");
            return;
        }

        // Renomeia o arquivo temporário para o nome original
        if (!arquivoTemporario.renameTo(arquivoOriginal)) {
            System.out.println("Não foi possível renomear o arquivo temporário.");
        }
    }

    public void acessarRelatorioVendas() {

    }

    public void acessarListaVendas() {

    }
}
