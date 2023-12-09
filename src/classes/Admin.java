package classes;

import java.util.Scanner;

import classes.Utilitarios.Arquivos;
import classes.Utilitarios.*;

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
    Arquivos arquivo = new Arquivos();
    Scanner scannerString = new Scanner(System.in);

    public Admin(String nome, String email, String senha) {
        super(nome, email, senha);
    }

}
