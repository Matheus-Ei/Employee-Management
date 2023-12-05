package App;

import java.util.Scanner;

// Importações de classes/objetos
import objetos.Admin;
import objetos.Funcionario;

public class Aplicativo {

    public static void main(String[] args) {
        Scanner leitorScanner = new Scanner(System.in);
        Admin adiministrador = new Admin();
        Funcionario funcionario = new Funcionario();

        System.out.println("Funcionalidades do sistema\nSelecione sua opção:\n");
        System.out.println("0-Sair do programa");
        System.out.println("1-Cadastrar novo usuário");
        System.out.println("2-Listar os usuários");
        System.out.println("3-Deletar usuário");
        System.out.println("4-Cadastrar produto");

        String opcoes;

        do {
            opcoes = leitorScanner.nextLine();
            switch (opcoes) {
                case "1":
                    adiministrador.cadastrarUsuario();
                    continuar();
                    break;

                case "2":
                    adiministrador.listarUsuarios();
                    continuar();
                    break;

                case "3":
                    adiministrador.deletarUsuario();
                    continuar();
                    break;

                case "4": {
                    funcionario.cadastrarProduto();
                    continuar();
                    break;
                }

                default:
                    break;
            }

            if (!opcoes.equals("0")) {
                System.out.println("Funcionalidades do sistema\nSelecione sua opção:\n");
                System.out.println("0-Sair do programa");
                System.out.println("1-Cadastrar novo usuário");
                System.out.println("2-Listar os usuários");
                System.out.println("3-Deletar usuário");
                System.out.println("4-Cadastrar produto");

            }

        } while (!opcoes.equals("0"));

        leitorScanner.close();
    }

    private static void continuar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPrecisone qualquer tecla para continuar");
        scanner.nextLine();
    }

}
