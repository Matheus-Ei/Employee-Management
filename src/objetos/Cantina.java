package objetos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cantina {
    public String nome;

    String diretorioAreaDeTrabalho = System.getProperty("user.home") + "/Desktop/";

    // Nome do arquivo
    String dadosCantina = "Cantina.txt";

    // Caminho completo do arquivo
    String caminhoParaCantina = diretorioAreaDeTrabalho + dadosCantina;

    File arquivoCantina = new File(caminhoParaCantina);

    // com esse metodo eu posso saber se a cantina ja existe para as opções que eu
    // for mostrar para o ususario
    public File cantinaExist() {
        return arquivoCantina;
    }

    public void criarCantina() {
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        System.out.println("SEJA BEM VINDO AO GERENCIADOR DE CANTINAS");
        System.out.println("VAMOS CRIAR A SUA CONTA NO APLICATIVO!!");
        System.out.println("Digite o nome da sua cantina: ");
        this.nome = scannerString.nextLine();

        System.out.println("O nome está correto ou deseja redefinir?");
        System.out.println("Selecione a opção:" + System.lineSeparator() + "1-Está correto" + System.lineSeparator()
                + "2-Redefinir");
        int opcao = scannerInt.nextInt();
        while (opcao != 1) {
            System.out.println("Digite o nome da cantina novamente:");
            this.nome = scannerString.nextLine();
            System.out.println("O nome está correto ou deseja redefinir?");
            System.out.println("Selecione a opção:" + System.lineSeparator() + "1-Está correto" + System.lineSeparator()
                    + "2-Redefinir");
            opcao = scannerInt.nextInt();
        }

        System.out.printf("Cantina %s criada com sucesso!", this.nome);

        System.out.println("\nAGORA VAMOS CADASTRAR O ADIMINISTRADOR PARA GERENCIAR A CANTINA:");
        Admin adiministraor = new Admin();
        // usuario tem nome, email e senha;
        System.out.println("Digite o seu nome:");
        adiministraor.setName(scannerString.nextLine());
        while (adiministraor.getName().length() == 0) {
            System.out.println("Nome muito curto, digite novamente");
            adiministraor.setName(scannerString.nextLine());
        }

        System.out.println("Digite o seu email:");
        adiministraor.setEmail(scannerString.nextLine());
        while (adiministraor.getEmail().length() < 5) {
            System.out.println("Email muito curto, digite novamente");
            adiministraor.setEmail(scannerString.nextLine());
        }

        System.out.println("Digite a sua senha:");
        adiministraor.setSenha(scannerString.nextLine());
        while (adiministraor.getSenha().length() < 4) {
            System.out.println("Senha muito curta, digite novamente");
            adiministraor.setSenha(scannerString.nextLine());
        }

        if (!arquivoCantina.exists()) {
            try {
                arquivoCantina.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String dados = "Cantina: " + this.nome + System.lineSeparator() + "Nome: " + adiministraor.getName()
                + System.lineSeparator() + "Email: "
                + adiministraor.getEmail() + System.lineSeparator() + "Senha: " + adiministraor.getSenha();

        try (FileWriter escritor = new FileWriter(caminhoParaCantina)) {
            escritor.write(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Adiministrador adicionado com sucesso");
    }

}
