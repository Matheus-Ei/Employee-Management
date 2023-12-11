package classes.Trabalhadores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Utilitarios.Arquivos;
import classes.Utilitarios.FuncaoUtilitaria;

//Aqui temos a classe AdminLogado que vai ser chamada assim que o email e senha 
//estiverem corretos
public class AdminLogado {
    Scanner scannerString = new Scanner(System.in);
    Arquivos arquivo = new Arquivos();

    FuncaoUtilitaria utilitaria = new FuncaoUtilitaria();

    // metodo opcaoDoAdm mostra todas as coisa que o amd pode fazer, e de acordo com
    // o numero que ele digitar
    // vai executar uma metodo diferente
    public void opcaoDoAdm() {
        System.out.println("Bem vindo! As funcionalidades estão abaixo:");
        System.out.println("0 - Voltar");
        System.out.println("1 - Cadastrar funcionario:");
        System.out.println("2 - Listar funcionarios");
        System.out.println("3 - Exluir funcionario ");
        System.out.println("4 - Exibir relatorio de vendas");
        System.out.println("5 - Ver o total de dinheiro em vendas");
        System.out.println("6 - Ordenar preços");
        System.out.println("7 - Excluir cantina");

        String opcao = "-1";
        // optamos por usar a opção como string mesmo sendo um numero para nao ficar
        // dando
        // erro caso alguem digite um valor que nao seja numerico na entra de valors

        // o switch-case chamando os metodos conforme a opção selecionada
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
                case "4":
                    this.relatorioVendas();
                    utilitaria.continuar();
                    break;
                case "5":
                    this.acessarTotalEmVendas();
                    utilitaria.continuar();
                    break;
                case "6":
                    this.ordernarPrecos();
                    utilitaria.continuar();
                    break;
                case "7":
                    this.excluirCantina();
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
                System.out.println("4 - Exibir relatorio de vendas");
                System.out.println("5 - Ver o total de dinheiro em vendas");
                System.out.println("6 - Ordenar preços");
                System.out.println("7 - Excluir cantina");

            }
        } while (!opcao.equals("0"));

    }

    public void cadastrarUsuario() {

        //aqui eu leio o valor do nome e do email para realizar o login
        //o valor digitado vai para um metodo chamado validorDeDados
        //se passar do validador ai é atribuido a variavel
        System.out.println("Digite o seu nome:");
        String userName = utilitaria.validadorDeDados(scannerString.nextLine());
        System.out.println("Digite o seu email:");
        String userEmail = utilitaria.validadorDeDados(scannerString.nextLine());

        
        //aqui eu faço outra validação, onde se o email ja existir nao podera ser criado a conta
        //e verifica o email de feuncionarios e o email de administrador e me retorna em boolean
        while (!this.emailExiste(userEmail)) {
            System.out.println("Ja tem um usuario cadastrado com esse email");
            System.out.println("Insira o email novamente");
            userEmail = utilitaria.validadorDeDados(scannerString.nextLine());
        }

        //leio a senha do usuario
        System.out.println("Digite a sua senha");
        String userPassword = utilitaria.validadorDeDados(scannerString.nextLine());

        //instancio um funcionario passando os valores obtidos acima
        Funcionario funcionario = new Funcionario(userName, userEmail, userPassword);

        //crio oque será escrito no arquivo
        String dados = funcionario.getName() + " " + funcionario.getEmail() + " " + funcionario.getSenha()
                + System.lineSeparator();

        //crio um objeto escritor da classe FileWriter, o seu contrutor recebe 2 valores, o primeiro 
        //é o caminho completo para o arquivo e o segundo é uma valor boolean para append, 
        //que serve para continuar escrevendo ao fim do arquivo e nao sobreescrever oque esta lá
        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaDadosDoUsuario(), true)) {
            escritor.write(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //a criação de um novo funcionario é resulta em 2 arquivos, um com nome, email, senha
        //e outro somente com nome e email que o que esta abaixo
        try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaTodosUsuarios(), true)) {
            escritor.write("Funcionario: " + funcionario.getName() + "\tEmail: " + funcionario.getEmail()
                    + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*aqui é feito a listagem de funcionarios que estao esritos no segundo
    arquivo que eu crio no metodo de cima*/
    public void listarUsuarios() {
        if (arquivo.getArquivoUsuario().exists()) {
            /*aqui é feito a leitura do aquivo, onde eu uso a classe FileRader
            em seu contrutor ele espera receber um dado do tipo File, que é o arquivo que tem os dados
            após isso é usado a classe BufferedReader, que melhora a eficiencia pois ele vai ler 
            blocos de informações e nao caractere por caractere
            o seu construtur espera seceber um leitor, que é onde eu passo o leitorArquivo criado
            anteriormente

            o try com () é chamado de try-with-resources e serve para fechar as coisa que abriu
            dentro dos ()

            Explicação da documentação: 
            A instrução try-with-resources é uma instrução try que declara um ou mais recursos. 
            Um recurso é um objeto que deve ser fechado após a conclusão do programa. 
            A instrução try-with-resources garante que cada recurso seja fechado no final da instrução. 
            Qualquer objeto que implemente java.lang.AutoCloseable, que inclui todos os objetos 
            que implementam java.io.Closeable, pode ser usado como recurso.
            */
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoUsuario());
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

    //função para deletar a conta de um usuario
    public void deletarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o email a ser deletado:");
        this.listarUsuarios();
        //leio o email a ser deletado
        String email = scanner.nextLine();
        String deletarLinha = email;

        // Configuração do arquivo temporário
        String temporarioFile = "Temporario.txt";
        String caminhoTemporario = arquivo.getDiretorioAreaDeTrabalho() + temporarioFile;
        File arquivoTemporario = new File(caminhoTemporario);

        /*chamo um metodo static para fazer a toca de arquivos, se ele encontar o email a ser
        deletado ai ele remove dos dois aquivos que for criado, passa os valores para um arquivo 
        temporario e renomia o arquivo temporario
        */
        fazerTrocaDeArquivos(arquivo.getArquivoUsuario(), arquivoTemporario, deletarLinha);
        fazerTrocaDeArquivos(arquivo.getArquivoDados(), arquivoTemporario, deletarLinha);

    }

    // usa o static para nao precisar instanciar ela dentro de onde for usar pelo que eu entendi
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

    //aqui eu mostro o relatorio de vendas 
    //é semelhante as metodos anteriores, 
    //eu leio o arquivo com bufferedReader e printo na tela os valores
    private void relatorioVendas() {
        if (arquivo.getArquivoRelatorioVendas().exists() && arquivo.getArquivoRelatorioVendas().length() != 0) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoRelatorioVendas());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                System.out.println(System.lineSeparator() + "------------------------------------------------------"
                        + System.lineSeparator());
                while ((linha = bufferedReader.readLine()) != null) {
                    System.out.println("\t" + linha);
                }
                System.out.println(System.lineSeparator() + "------------------------------------------------------"
                        + System.lineSeparator());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nenhuma venda realizada.");
        }

    }

    //retorno a data do dia de hoje formatada para como usamos no brasil
    private static String dataHoje() {
        //busca a data do dia de hoje
        LocalDate dataAtual = LocalDate.now();
        // Define o formato desejado para a data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converte a data para uma string formatada
        return dataAtual.format(formatter);
    }

    public void acessarTotalEmVendas() {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo.getArquivoRelatorioVendas()))) {
            String linha;

            double totalEmVendas = 0;

            while ((linha = leitor.readLine()) != null) {
                if (linha.contains(dataHoje())) {
                    // Encontrar a posição de "R$"
                    // se encontrar o valor na linha, retorna o indice dele, caso contrario retorna
                    // -1;
                    int indiceR = linha.indexOf("R$");

                    // Se "R$" for encontrado na linha
                    if (indiceR != -1) {
                        // Extrair o valor que está após "R$"
                        /*
                         linha.indexOf("R$"): Retorna o índice da primeira ocorrência da string "R$"
                         na linha. Se "R$" não for encontrado, indiceR será igual a -1.
                         
                         linha.substring(indiceR + 2): Retorna uma substring da linha começando a
                         partir da posição indiceR + 2. O + 2 é usado para pular os caracteres "R$" e
                         começar a partir do valor.
                         
                         .trim(): Remove espaços em branco adicionais antes e depois da substring.
                         */
                        String valor = linha.substring(indiceR + 4).trim();
                        totalEmVendas += Double.parseDouble(valor);
                    }
                }
            }
            System.out.println("O total em vendas no dia de hoje foi R$: " + totalEmVendas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //aqui eu leio os dois arquivos que contem dados dos usuarios, e caso o email ja axistir
    //ele vai retornar um valor booleano true, caso nao existir ele retorna false
    //entao pode ser criado uma nova conta com o email
    public boolean emailExiste(String userEmail) {
        boolean emailFuncionario = false;
        boolean emailAdmin = false;

        if (arquivo.getArquivoUsuario().exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoUsuario());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.contains(userEmail)) {
                        emailFuncionario = true;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (arquivo.getArquivoCantina().exists()) {
            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoCantina());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo
                while ((linha = bufferedReader.readLine()) != null) {
                    // Processa cada linha conforme necessário
                    if (linha.contains(userEmail)) {
                        emailAdmin = true;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!emailAdmin && !emailFuncionario) {
            return true;
        } else {
            return false;
        }
    }

    //aqui eu tenho a função de ordenação, que foi solicitada no trabalho, 
    //ela ordena os preços do menor para o maior e altera o arquivo com os preços em ordem 
    public void ordernarPrecos() {
        //verefico se o arquivo existe
        if (arquivo.getArquivoProdutos().exists() && arquivo.getArquivoProdutos().length() != 0) {
            //crio uma lista de dados, é algo semelhante a um array
            List<String> listaDeStrings = new ArrayList<>();
            List<Double> listaPrecos = new ArrayList<>();

            try (FileReader leitorArquivo = new FileReader(arquivo.getArquivoProdutos());
                    BufferedReader bufferedReader = new BufferedReader(leitorArquivo)) {

                String linha;

                // Lê cada linha do arquivo e adiciona ao final do array
                while ((linha = bufferedReader.readLine()) != null) {
                    listaDeStrings.add(linha);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //caso a lista tenha mais de 1 dado ele continua com a ordenação
            if (listaDeStrings.size() >= 2) {
                //para valor da lista de strings vou remover somente o valor contido na linha
                //e adicionar a uma outra lista somente com os valore
                for (String linhaDoArquivo : listaDeStrings) {
                    //verefico se na linha tem escrito R$, caso tiver ele retorna o index
                    //se nao encontrar retorna -1
                    int indiceR = linhaDoArquivo.indexOf("R$");
                    if (indiceR != -1) {
                        //crio substrings com o valor
                        String valor = linhaDoArquivo.substring(indiceR + 4).trim();
                        //adiciono os valores a lista
                        listaPrecos.add(Double.parseDouble(valor));
                    }
                }

                //faço a ordenação dos valores, listaPrecos.size retona o tamanho da lista
                //bouble sort
                for (int i = 0; i < listaPrecos.size(); i++) {
                    for (int j = 0; j < listaPrecos.size() - 1; j++) {
                        if (listaPrecos.get(j) > listaPrecos.get(j + 1)) {
                            //crio uma varivel temporaria para o valor
                            Double temporaria = listaPrecos.get(j);
                            //crio uma varivel temporaria para a linha
                            String linhaTemporaria = listaDeStrings.get(j);
                            //nas linhas abaixo faço a troca dos valores
                            listaPrecos.set(j, listaPrecos.get(j + 1));
                            listaDeStrings.set(j, listaDeStrings.get(j + 1));
                            listaPrecos.set(j + 1, temporaria);
                            listaDeStrings.set(j + 1, linhaTemporaria);
                        }
                    }
                }
                System.out.println("A lista ordenada ficou assim: ");

                //limpo o arquivo de produtos
                try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaProdutos())) {
                    escritor.write("");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //reescrevo os valores ordenados no arquivo e printo na tela
                for (int i = 0; i < listaDeStrings.size(); i++) {
                    System.out.println(listaDeStrings.get(i));
                    try (FileWriter escritor = new FileWriter(arquivo.getCaminhoParaProdutos(), true)) {
                        escritor.write(listaDeStrings.get(i) + System.lineSeparator());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("A lista ordenada ficou assim: ");
                for (int i = 0; i < listaDeStrings.size(); i++) {
                    System.out.println(listaDeStrings.get(i));
                }
            }

        } else {
            System.out.println("Nenhum produto cadastrado");
        }
    }

    //aqui eu excluo todos os arquivos da cantina
    public void excluirCantina() {
        String opcao;
        System.out.println("Tem certeza que dejeza exluir a cantina?");
        System.out.println("Digite 1 para: SIM TENHO CERTEZA");
        System.out.println("Qualquer outra tecla para: NÃO, NÃO QUERO EXCLUIR");
        opcao = scannerString.nextLine();
        if (opcao.equals("1")) {
            deletarArquivo(arquivo.getArquivoCantina());
            deletarArquivo(arquivo.getArquivoDados());
            deletarArquivo(arquivo.getArquivoFilaDeProdutos());
            deletarArquivo(arquivo.getArquivoProdutos());
            deletarArquivo(arquivo.getArquivoRelatorioVendas());
            deletarArquivo(arquivo.getArquivoUsuario());
        }
    }

    //função deletarArquivo, primeiro verefica se o arquivo existe
    //para nao gerar nenhum erro, após isso ele tenda deletar o arquivo
    private static void deletarArquivo(File nomeDoArquivo) {
        if (nomeDoArquivo.exists()) {
            if (!nomeDoArquivo.delete()) {
                System.out.println("Não foi possível deletar o arquivo original.");
                return;
            }
        }
    }
}
