package classes.Trabalhadores;

import java.util.Scanner;

import classes.Utilitarios.Arquivos;

public class Funcionario extends Usuario {
    Scanner scannerString = new Scanner(System.in);
    Scanner scannerDouble = new Scanner(System.in);

    Arquivos arquivo = new Arquivos();

    public Funcionario(String nome, String email, String senha) {
        super(nome, email, senha);
    }
}