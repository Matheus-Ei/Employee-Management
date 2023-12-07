package objetos;

import java.io.File;

public class Arquivos {
    String diretorioAreaDeTrabalho = System.getProperty("user.home") + "/Desktop/";
    String dadosDoUsuario = "DadosDoUsuario.txt";
    String todosUsuarios = "TodosUsuarios.txt";
    String dadosCantina = "Cantina.txt";

    // Caminho completo do arquivo
    String caminhoParaDadosDoUsuario = diretorioAreaDeTrabalho + dadosDoUsuario;
    String caminhoParaTodosUsuarios = diretorioAreaDeTrabalho + todosUsuarios;
    String caminhoParaCantina = diretorioAreaDeTrabalho + dadosCantina;

    File arquivoDados = new File(caminhoParaDadosDoUsuario);
    File arquivoUsuarios = new File(caminhoParaTodosUsuarios);
    File arquivoCantina = new File(caminhoParaCantina);

    public String getDiretorioAreaDeTrabalho() {
        return diretorioAreaDeTrabalho;
    }

    public String getCaminhoParaDadosDoUsuario() {
        return caminhoParaDadosDoUsuario;
    }

    public String getCaminhoParaTodosUsuarios() {
        return caminhoParaDadosDoUsuario;
    }

    public String getCaminhoParaCantina() {
        return caminhoParaDadosDoUsuario;
    }

    public File getArquivoCantina() {
        return arquivoCantina;
    }

    public File getArquivoDados() {
        return arquivoDados;
    }

    public File getArquivoUsuario() {
        return arquivoUsuarios;
    }

}
