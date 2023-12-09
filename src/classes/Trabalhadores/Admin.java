package classes.Trabalhadores;

import java.util.Scanner;

import classes.Utilitarios.Arquivos;
public class Admin extends Usuario {
    Arquivos arquivo = new Arquivos();
    Scanner scannerString = new Scanner(System.in);

    public Admin(String nome, String email, String senha) {
        super(nome, email, senha);
    }

}
