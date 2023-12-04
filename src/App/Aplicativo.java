package App;

import java.util.Scanner;

// Importações de classes/objetos
import objetos.Admin;

public class Aplicativo {

    public static void main(String[] args) {
        Scanner leitorScanner = new Scanner(System.in);
        Admin teste = new Admin();

        System.out.println("Funcionalidades do sistema\nSelecione sua opção:\n");
        System.out.println("0-Sair do programa");
        System.out.println("1-Cadastrar novo usuário");
        System.out.println("2-Listar os usuários");
        System.out.println("3-Deletar usuário");

        String opcoes;

        do {
            opcoes = leitorScanner.nextLine();
            switch (opcoes) {
                case "1":
                    teste.cadastrarUsuario();
                    System.out.println("Precisone qualquer tecla para continuar");
                    leitorScanner.nextLine();

                    break;

                case "2":
                    teste.listarUsuarios();
                    System.out.println("Precisone qualquer tecla para continuar");
                    leitorScanner.nextLine();
                    break;

                case "3":
                    teste.deletarUsuario();
                    System.out.println("Precisone qualquer tecla para continuar");
                    leitorScanner.nextLine();
                    break;

                default:
                    break;
            }

            if (!opcoes.equals("0")) {
                System.out.println("Funcionalidades do sistema\nSelecione sua opção:\n");
                System.out.println("0-Sair do programa");
                System.out.println("1-Cadastrar novo usuário");
                System.out.println("2-Listar os usuários");
                System.out.println("3-Deletar usuário");
            }

        } while (!opcoes.equals("0"));

        leitorScanner.close();
    }
}
