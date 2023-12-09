package App;

import java.util.Scanner;

import classes.Cantina;
import classes.FazerLogin;
import classes.Trabalhadores.AdminLogado;
import classes.Trabalhadores.FuncionarioLogado;
import classes.Utilitarios.*;

public class Aplicativo {

    public static void main(String[] args) {
        Scanner leitorScanner = new Scanner(System.in);
        Cantina cantina = new Cantina();
        FuncaoUtilitaria utilitaria = new FuncaoUtilitaria();
        Arquivos arquivo = new Arquivos();

        while (!arquivo.getArquivoCantina().exists() || arquivo.getArquivoCantina().length() == 0) {
            cantina.criarCantina();
            utilitaria.continuar();
        }

        System.out.println(System.lineSeparator() + "Selecione sua opção: " + System.lineSeparator());

        System.out.println("0-Sair do programa");
        System.out.println("1-Realizar login");

        String opcoes;
        FazerLogin fazerLogin;

        do {
            opcoes = leitorScanner.nextLine();
            if (opcoes.equals("1")) {
                System.out.println("Digite o seu email");
                String email = utilitaria.validadorDeDados(leitorScanner.nextLine());
                System.out.println("Digite a seua senha");
                String senha = utilitaria.validadorDeDados(leitorScanner.nextLine());
                fazerLogin = new FazerLogin(email, senha);

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
