package objetos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import objetos.Utilitarios.Arquivos;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private Boolean isAdmin;

    Cantina cantina = new Cantina();
    Arquivos arquivo = new Arquivos();

    // construtur, sempre que for instanciar um usuario tera que ter esse 3
    // elementos
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    Scanner scannerString = new Scanner(System.in);

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

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }
}
