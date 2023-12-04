package objetos;

public class Usuario {
    public String email;
    public String senha;

    public void fazerLogin(String userEmail, String userSenha) {

        this.email = userEmail;
        this.senha = userSenha;
        System.out.printf("Usuario com o email %s cadastrado com sucesso", this.email);
    }
}
