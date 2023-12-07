package objetos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {

    private String email;
    private String senha;
    private Boolean isAdmin;

    Cantina cantina = new Cantina();
    Arquivos arquivo = new Arquivos();

    // Caminho completo do arquivo

    Scanner scannerString = new Scanner(System.in);

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void criarConta(String email, String senha) {

    }

    public void fazerLogin() {
        System.out.println("Digite o seu email");
        this.email = scannerString.nextLine();
        System.out.println("Digite a seua senha");
        this.senha = scannerString.nextLine();

        if (!arquivo.getArquivoDados().exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoCantina());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.equals("Email: " + this.email)) {
                        this.email = linha;
                    }
                    if (linha.equals("Senha: " + this.senha)) {
                        this.senha = linha;
                    }
                }
                if (this.email.contains("Email:") && this.senha.contains("Senha")) {
                    this.isAdmin = true;
                } else {
                    this.isAdmin = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }
}
