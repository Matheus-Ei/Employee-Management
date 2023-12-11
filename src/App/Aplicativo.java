package App;

import java.util.Scanner;

import classes.Cantina;
import classes.FazerLogin;
import classes.Trabalhadores.AdminLogado;
import classes.Trabalhadores.FuncionarioLogado;
import classes.Utilitarios.*;

public class Aplicativo {
    //classe aplicativo onde tem a main e faz tudo acontecer

    public static void main(String[] args) {
        //classe Scanner para ler valores do teclado
        Scanner leitorScanner = new Scanner(System.in);
        //cirando um objeto cantina
        Cantina cantina = new Cantina();
        //criando um objeto utilitaria que contem algumas funções que são usadas em 
        //diversas partes de todo  o código 
        FuncaoUtilitaria utilitaria = new FuncaoUtilitaria();
        //criando um objeto arquivo qua foi uma classe criada onde tem o caminho de todos arquivos que
        //sao usados no programa
        Arquivos arquivo = new Arquivos();

        //aqui é feita a criação da cantina, e enquanto nao existir uma cantina
        //o codigo nao procegue
        while (!arquivo.getArquivoCantina().exists() || arquivo.getArquivoCantina().length() == 0) {
            cantina.criarCantina();
            utilitaria.continuar();
        }

        //printando valores na tela
        System.out.println(System.lineSeparator() + "Selecione sua opção: " + System.lineSeparator());
        System.out.println("0-Sair do programa");
        System.out.println("1-Realizar login");

        String opcoes;
        FazerLogin fazerLogin;

        do {
            opcoes = leitorScanner.nextLine();
            //opção 1 para fazer login
            if (opcoes.equals("1")) {
                System.out.println("Digite o seu email");
                String email = utilitaria.validadorDeDados(leitorScanner.nextLine());
                System.out.println("Digite a seua senha");
                String senha = utilitaria.validadorDeDados(leitorScanner.nextLine());
                //aqui eu crio um objeto fazerLogin que no construtur espera o email e a senha;
                fazerLogin = new FazerLogin(email, senha);

                //a classe FazerLogin tem um metodo que me retornana um array com dois valores booleanos
                //se o primeiro valor do array for true significa que é um administrador usando o app
                //se o segundo for true significa que é um funcionario usando o app
                //se os 2 for false significa que o email ou senha estao incorretos
                //é isso que acontece nas linhas abaixo
                boolean[] admOuFuncionario = fazerLogin.realizarLogin();
                if (admOuFuncionario[0] || admOuFuncionario[1]) {
                    if (admOuFuncionario[0]) {
                        AdminLogado adminLogado = new AdminLogado();
                        adminLogado.opcaoDoAdm();
                    }
                    if (admOuFuncionario[1]) {
                        FuncionarioLogado funcionarioLogado = new FuncionarioLogado();
                        funcionarioLogado.opcaoDoFuncionario();
                    }
                } else {
                    System.out.println("Email ou senha incorreta, aperte 1 para tentar novamete ou 0 para sair");
                }
            }
            if (!opcoes.equals("0")) {
                System.out.println("0-Sair do programa");
                System.out.println("1-Realizar login");
            }
        } while (!opcoes.equals("0"));
    }
}
