package objetos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Usuario {
    Admin admin = new Admin();
    Cantina cantina = new Cantina();

    Scanner scannerString = new Scanner(System.in);

    private String email;
    private String senha;

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

    public void fazerLogin(String userEmail, String userSenha) {
        if (!admin.usuarioExiste().exists()) {
            try (FileReader leitorArquivo = new FileReader(admin.usuarioExiste());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;
                String emailDoUsuario = "";
                String password = "";

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.equals("Email: " + userEmail)) {
                        emailDoUsuario = linha;
                    }
                    if (linha.equals("Senha: " + userSenha)) {
                        password = linha;
                    }
                }
                //parei de fazer aquiiiiiiiiiiiiii
                //parei de fazer aquiiiiiiiiiiiiii
                //parei de fazer aquiiiiiiiiiiiiii
                //parei de fazer aquiiiiiiiiiiiiii
                if(emailDoUsuario.length() >= 1 && password.length() >= 1){
                    //retornar alguma coisa para dizer que foi o admin que logou
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
