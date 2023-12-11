package classes.Trabalhadores;

//crio a classe Usuario e fiz ela ser abstrata para 
//nao poder ser instanciada, pois ninguem pode criar um ususario
//somente pode criar Admin e Funcionarios que são as classes que herdam Usuario
abstract public class Usuario {

    private String nome;
    private String email;
    private String senha;

    // construtur, sempre que for instanciar um usuario tera que ter esse 3
    // elementos
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getName() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void criarConta(String email, String senha) {

    }
}
