package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import classes.Utilitarios.Arquivos;
import classes.Utilitarios.FuncaoUtilitaria;

public class AdminLogado {
    Scanner scannerString = new Scanner(System.in);
    Arquivos arquivo = new Arquivos();

    FuncaoUtilitaria utilitaria = new FuncaoUtilitaria();

    public void opcaoDoAdm() {
        System.out.println("Bem vindo! As funcionalidades estão abaixo:");
        System.out.println("0 - Voltar");
        System.out.println("1 - Cadastrar funcionario:");
        System.out.println("2 - Listar funcionarios");
        System.out.println("3 - Exluir funcionario ");

        String opcao = "-1";

        do {
            opcao = scannerString.nextLine();
            switch (opcao) {
                case "1":
                    this.cadastrarUsuario();
                    utilitaria.continuar();
                    break;
                case "2":
                    this.listarUsuarios();
                    utilitaria.continuar();
                    break;
                case "3":
                    this.deletarUsuario();
                    utilitaria.continuar();
                    break;

                default:
                    break;
            }

            if (!opcao.equals("0")) {
                System.out.println("0 - Voltar");
                System.out.println("1 - Cadastrar funcionario:");
                System.out.println("2 - Listar funcionarios");
                System.out.println("3 - Exluir funcionario ");
            }
        } while (!opcao.equals("0"));

    }

    public void cadastrarUsuario() {

        System.out.println("Digite o seu nome:");
        String userName = utilitaria.validadorDeDados(scannerString.nextLine());
        System.out.println("Digite o seu email:");
        String userEmail = utilitaria.validadorDeDados(scannerString.nextLine());

        if (arquivo.arquivoUsuarios.exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.arquivoUsuarios);
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.equals("email: " + userEmail)) {
                        System.out.println("Ja tem um usuario cadastrado com esse email");
                        System.out.println("Insira o email novamente");
                        userEmail = scannerString.nextLine();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Digite a sua senha");
        String userPassword = utilitaria.validadorDeDados(scannerString.nextLine());

        Funcionario funcionario = new Funcionario(userName, userEmail, userPassword);

        if (!arquivo.arquivoDados.exists()) {
            try {
                arquivo.arquivoDados.createNewFile();
                arquivo.arquivoUsuarios.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String dados = funcionario.getName() + " " + funcionario.getEmail() + " " + funcionario.getSenha();

        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaDadosDoUsuario(), true)) {
            escritor.write(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaTodosUsuarios(), true)) {
            escritor.write("Email: " + funcionario.getEmail() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listarUsuarios() {
        if (arquivo.arquivoUsuarios.exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.arquivoUsuarios);
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                int count = 1;
                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    System.out.println(count + " - " + linha);
                    count++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo esta vazio!!");
        }

    }

    public void deletarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o email a ser deletado:");
        String email = scanner.nextLine();
        String deletarLinha = "Email: " + email;

        // Configuração do arquivo temporário
        String temporarioFile = "Temporario.txt";
        String caminhoTemporario = arquivo.getDiretorioAreaDeTrabalho() + temporarioFile;
        File arquivoTemporario = new File(caminhoTemporario);

        fazerTrocaDeArquivos(arquivo.getArquivoUsuario(), arquivoTemporario, deletarLinha);
        fazerTrocaDeArquivos(arquivo.getArquivoDados(), arquivoTemporario, deletarLinha);

    }

    // usa o static para nao precisar instanciar ela dentro de onde for usar pelo
    // que eu entendi
    private static void fazerTrocaDeArquivos(File caminhoArquivo, File caminhoTemporario, String deletarLinha) {
        if (caminhoArquivo.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(caminhoArquivo));
                    BufferedWriter escritorTemporario = new BufferedWriter(new FileWriter(caminhoTemporario, true))) {

                String linha;

                // Lê cada linha do arquivo original de dados
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha
                    if (!linha.contains(deletarLinha)) {
                        // Se a linha não contiver a string a ser deletada, escreve no mesmo arquivo
                        // temporário
                        escritorTemporario.write(linha);
                        escritorTemporario.newLine();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // Substitui o arquivo original de dados pelo temporário
            substituirArquivo(caminhoArquivo, caminhoTemporario);
        } else {
            System.out.println("O arquivo de dados está vazio!!");
        }
    }

    // Método para substituir o arquivo original pelo temporário
    /*
     * private: O método só é acessível dentro da própria classe Admin. Outras
     * classes fora de Admin não podem chamar diretamente esse método.
     * 
     * static: O método pertence à classe Admin e não a instâncias específicas dessa
     * classe. Pode ser chamado diretamente usando o nome da classe
     * (Admin.substituirArquivo(...)) sem a necessidade de criar uma instância de
     * Admin.
     */
    private static void substituirArquivo(File arquivoOriginal, File arquivoTemporario) {
        // Deleta o arquivo original
        if (!arquivoOriginal.delete()) {
            System.out.println("Não foi possível deletar o arquivo original.");
            return;
        }

        // Renomeia o arquivo temporário para o nome original
        if (!arquivoTemporario.renameTo(arquivoOriginal)) {
            System.out.println("Não foi possível renomear o arquivo temporário.");
        }
    }

    public void acessarRelatorioVendas() {

    }

    public void acessarListaVendas() {

    }
}
