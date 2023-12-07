package App;

import java.util.Scanner;

// Importações de classes/objetos
import objetos.Admin;
import objetos.Cantina;
import objetos.Funcionario;
import objetos.Usuario;

public class Aplicativo {

    public static void main(String[] args) {
        Scanner leitorScanner = new Scanner(System.in);
        Admin adiministrador = new Admin();
        Cantina cantina = new Cantina();
        Funcionario funcionario = new Funcionario();
        Usuario usuario = new Usuario();

        while (!cantina.cantinaExist().exists() || cantina.cantinaExist().length() == 0) {
            cantina.criarCantina();
            continuar();
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

        do {
            opcoes = leitorScanner.nextLine();
            if (opcoes.equals("1")) {
                usuario.fazerLogin();
                // parei aquiii continuar aquiiiii
                // parei aquiii continuar aquiiiii
                // parei aquiii continuar aquiiiii
                // parei aquiii continuar aquiiiii
                // parei aquiii continuar aquiiiii
                // parei aquiii continuar aquiiiii
                if (usuario.getIsAdmin() == true) {

                } else {

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

        leitorScanner.close();
    }

    private static void continuar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPrecisone qualquer tecla para continuar");
        scanner.nextLine();
    }

}
