package classes.Utilitarios;

import java.io.File;

//Classe Arquivos onde contem todos diretorios criados no aplicativo
public class Arquivos {
    private String diretorioAreaDeTrabalho = System.getProperty("user.home") + "/Desktop/";
    private String dadosDoUsuario = "DadosDoUsuario.txt";
    private String todosUsuarios = "TodosUsuarios.txt";
    private String dadosCantina = "Cantina.txt";
    private String produtos = "Produtos.txt";
    private String filaDeProdutos = "filaDeProdutos.txt";
    private String relatorioVendas = "relatorioVendas.txt";

    // Caminho completo do arquivo
    private String caminhoParaDadosDoUsuario = diretorioAreaDeTrabalho + dadosDoUsuario;
    private String caminhoParaTodosUsuarios = diretorioAreaDeTrabalho + todosUsuarios;
    private String caminhoParaCantina = diretorioAreaDeTrabalho + dadosCantina;
    private String caminhoParaProdutos = diretorioAreaDeTrabalho + produtos;
    private String caminhoParaFilaDeProdutos = diretorioAreaDeTrabalho + filaDeProdutos;
    private String caminhoParaRelatorioVendas = diretorioAreaDeTrabalho + relatorioVendas;


    //criando os arquivos
    private File arquivoDados = new File(caminhoParaDadosDoUsuario);
    private File arquivoUsuarios = new File(caminhoParaTodosUsuarios);
    private File arquivoCantina = new File(caminhoParaCantina);
    private File arquivoProdutos = new File(caminhoParaProdutos);
    private File arquivoFilaDeProdutos = new File(caminhoParaFilaDeProdutos);
    private File arquivoRelatorioVendas = new File(caminhoParaRelatorioVendas);

    public String getDiretorioAreaDeTrabalho() {
        return diretorioAreaDeTrabalho;
    }

    public String getCaminhoParaRelatorioVendas() {
        criarArquivo(arquivoRelatorioVendas);
        return caminhoParaRelatorioVendas;
    }

    public String getCaminhoParaFilaDeProdutos() {
        criarArquivo(arquivoFilaDeProdutos);
        return caminhoParaFilaDeProdutos;
    }

    public String getCaminhoParaDadosDoUsuario() {
        criarArquivo(arquivoDados);
        return caminhoParaDadosDoUsuario;
    }

    public String getCaminhoParaTodosUsuarios() {
        criarArquivo(arquivoUsuarios);
        return caminhoParaTodosUsuarios;
    }

    public String getCaminhoParaCantina() {
        criarArquivo(arquivoCantina);
        return caminhoParaCantina;
    }

    public String getCaminhoParaProdutos() {
        criarArquivo(arquivoProdutos);
        return caminhoParaProdutos;
    }

    public File getArquivoProdutos() {
        criarArquivo(arquivoProdutos);
        return arquivoProdutos;
    }

    public File getArquivoCantina() {
        criarArquivo(arquivoCantina);
        return arquivoCantina;
    }

    public File getArquivoDados() {
        criarArquivo(arquivoDados);
        return arquivoDados;
    }

    public File getArquivoUsuario() {
        criarArquivo(arquivoUsuarios);
        return arquivoUsuarios;
    }

    public File getArquivoFilaDeProdutos() {
        criarArquivo(arquivoFilaDeProdutos);
        return arquivoFilaDeProdutos;
    }

    public File getArquivoRelatorioVendas() {
        criarArquivo(arquivoRelatorioVendas);
        return arquivoRelatorioVendas;
    }


    private static void criarArquivo(File arquivo) {
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

}
