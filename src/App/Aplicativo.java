package App;

import java.util.Scanner;

import classes.AdminLogado;
import classes.Cantina;
import classes.FazerLogin;
import classes.Utilitarios.*;

public class Aplicativo {

    public static void main(String[] args) {
        Scanner leitorScanner = new Scanner(System.in);
        Cantina cantina = new Cantina();
        FuncaoUtilitaria utilitaria = new FuncaoUtilitaria();
        Arquivos arquivo = new Arquivos();

        while (!arquivo.arquivoCantina.exists() || arquivo.arquivoCantina.length() == 0) {
            cantina.criarCantina();
            utilitaria.continuar();
        }

        System.out.println(System.lineSeparator() + "Selecione sua opção: " + System.lineSeparator());

        System.out.println("0-Sair do programa");
        System.out.println("1-Realizar login");

        /*
         * System.out.println("Funcionalidades do sistema\nSelecione sua opção:\n");
         * System.out.println("0-Sair do programa");
         * System.out.println("1-Cadastrar novo usuário");
         * System.out.println("2-Listar os usuários");
         * System.out.println("3-Deletar usuário");
         * System.out.println("4-Cadastrar produto");
         * System.out.println("5-Listar Produtos");
         */
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
                } else {
                    System.out.println("Email ou senha incorreta, aperte 1 para tentar novamete ou 0 para sair");
                }
            }
            /*
             * switch (opcoes) {
             * case "1":
             * adiministrador.cadastrarUsuario();
             * continuar();
             * break;
             * 
             * case "2":
             * adiministrador.listarUsuarios();
             * continuar();
             * break;
             * 
             * case "3":
             * adiministrador.deletarUsuario();
             * continuar();
             * break;
             * 
             * case "4": {
             * funcionario.cadastrarProduto();
             * continuar();
             * break;
             * }
             * case "5":
             * funcionario.listarProdutos();
             * continuar();
             * break;
             * 
             * default:
             * break;
             * }
             * 
             * if (!opcoes.equals("0")) {
             * System.out.println("Funcionalidades do sistema\nSelecione sua opção:\n");
             * System.out.println("0-Sair do programa");
             * System.out.println("1-Cadastrar novo usuário");
             * System.out.println("2-Listar os usuários");
             * System.out.println("3-Deletar usuário");
             * System.out.println("4-Cadastrar produto");
             * System.out.println("5-Listar Produtos");
             * 
             * }
             */

        } while (!opcoes.equals("0"));
    }
}
