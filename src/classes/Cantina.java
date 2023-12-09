package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import classes.Trabalhadores.Admin;
import classes.Utilitarios.*;

public class Cantina {
    public String nome;

    String diretorioAreaDeTrabalho = System.getProperty("user.home") + "/Desktop/";

    // Nome do arquivo
    String dadosCantina = "Cantina.txt";

    // Caminho completo do arquivo
    String caminhoParaCantina = diretorioAreaDeTrabalho + dadosCantina;

    File arquivoCantina = new File(caminhoParaCantina);
    Arquivos arquivo = new Arquivos();
    FuncaoUtilitaria validacao = new FuncaoUtilitaria();

    public void criarCantina() {
        Admin adiministraor;
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
        // usuario tem nome, email e senha;
        System.out.println("Digite o seu nome:");
        String userName = validacao.validadorDeDados(scannerString.nextLine());
        System.out.println("Digite o seu email:");
        String userEmail = validacao.validadorDeDados(scannerString.nextLine());
        System.out.println("Digite a sua senha:");
        String userPassword = validacao.validadorDeDados(scannerString.nextLine());
        adiministraor = new Admin(userName, userEmail, userPassword);

        String dados = this.nome + " " + adiministraor.getName() + " " + adiministraor.getEmail() + " "
                + adiministraor.getSenha();

        try (FileWriter escritor = new FileWriter(caminhoParaCantina)) {
            escritor.write(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Adiministrador adicionado com sucesso");
    }

    public boolean loginAdm(String email, String senha) {
        return login(arquivo.getArquivoCantina(), email, senha);
    }

    public boolean loginFuncionario(String email, String senha) {
        return login(arquivo.getArquivoDados(), email, senha);
    }

    private static boolean login(File nomeDoArquivo, String email, String senha) {
        if (nomeDoArquivo.exists()) {
            String linha;

            Boolean achouEmail = false;
            Boolean achouSenha = false;
            try (FileReader leitorArquivo = new FileReader(nomeDoArquivo);
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {
                // Lê cada linha do arquivo e verefica se é um adm ou fucnionario que fez login
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.equals("Email: " + email)) {
                        achouEmail = true;
                    }
                    if (linha.equals("Senha: " + senha)) {
                        achouSenha = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (achouEmail && achouSenha) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

}
