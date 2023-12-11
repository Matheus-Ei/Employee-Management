package classes.Trabalhadores;

import java.util.Scanner;

import classes.Utilitarios.Arquivos;

//Aqui é criado uma classe Admin que extends o Usuario, ou seja
//está fazendo um herança de Usuario
public class Admin extends Usuario {
    Arquivos arquivo = new Arquivos();
    Scanner scannerString = new Scanner(System.in);

    // a classe Admin bem simples, onde tem somente um construtur que espara
    // os 3 valores pata ser criado, onde passa esses 3 valores para o construtur da
    // sua super classe

    public Admin(String nome, String email, String senha) {
        // super é o contrutor da classe Usuario
        super(nome, email, senha);
    }

}
