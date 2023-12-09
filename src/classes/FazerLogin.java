package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import classes.Utilitarios.Arquivos;

public class FazerLogin {
    private String email;
    private String senha;

    Arquivos arquivo = new Arquivos();

    public FazerLogin(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public boolean[] realizarLogin() {
        boolean admLogado = false;
        if (arquivo.arquivoCantina.exists()) {
            admLogado = leitorArquivo(arquivo.arquivoCantina, this.email, this.senha);
        }
        boolean funcionarioLogado = false;
        if (!admLogado && arquivo.arquivoDados.exists()) {
            funcionarioLogado = leitorArquivo(arquivo.arquivoDados, this.email, this.senha);
        }

        boolean[] admOuFuncionario = new boolean[] { admLogado, funcionarioLogado };

        return admOuFuncionario;
    }

    private static boolean leitorArquivo(File arquivo, String email, String senha) {
        boolean loginComSucesso = false;
        try (FileReader leitorArquivo = new FileReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

            String linha;

            // Lê cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                // Processa cada linha conforme necessário
                if (linha.contains(email + " " + senha)) {
                    loginComSucesso = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return loginComSucesso;
    }

}
