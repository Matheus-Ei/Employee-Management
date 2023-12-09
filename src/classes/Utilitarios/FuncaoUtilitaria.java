package classes.Utilitarios;

import java.util.Scanner;

public class FuncaoUtilitaria {

    public String validadorDeDados(String dado) {
        Scanner scanner = new Scanner(System.in);
        while (dado.length() < 4) {
            System.out.println("O dado informado acima é muito curto, digite novamente: ");
            dado = scanner.nextLine();
        }
        return dado;
    }

    public void continuar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPrecisone qualquer tecla para continuar");
        scanner.nextLine();
    }

}
