/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Marcel
 */
public class Diretorios extends Observable {

    private String str;
    private final List<File> filesList = new ArrayList<>();
    
    /**
     * Dado um diretório de entrada, caso contenha subdiretório com arquivos dentro,
     * move todos para a entrada e remove todos subdiretórios
     * @param dir Diretório fornecido pelo usuário
     */
    public void ajustaDiretorio(File dir) {
        percorreArvoreDiretorios(dir);

        try {
            //Move os arquivos
            for (File f : filesList) {
                moveArquivo(f, dir);
            }
            
            //Apaga os diretórios vazios
            for (File f : dir.listFiles(
                    new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory();
                }
            })) {

                apagaDiretorio(f);
            }
        } catch (IOException e) {
            setStr(e.getMessage());
            e.printStackTrace();       
        }
    }
    
    /**
     * Move um arquivo da origem para destino
     * @param origem Arquivo a ser movido
     * @param destino Diretório para onde o arquivo de origem será movido
     * @throws IOException 
     */
    private void moveArquivo(File origem, File destino) throws IOException {

        Path movefrom = origem.toPath();
        Path target_dir = Paths.get(destino.getAbsolutePath(), origem.getName());

        //se o arquivo não estiver na raiz do diretório analisado
        if (!origem.getParent().equals(destino.getAbsolutePath())) {
            Files.move(movefrom, target_dir, REPLACE_EXISTING);
        }
        setStr(origem.getName() + "movido para " + destino.getName());
    }
    
    /**
     * Apaga o diretório passado como parâmetro e qualquer subdiretório dentro, 
     * contando que estejam vazios
     * @param dir Diretório a ser apagado
     * @throws IOException 
     */
    private void apagaDiretorio(File dir) throws IOException {
        for (File f : dir.listFiles()) {
            f.delete();
        }
        if (!dir.delete()) {
            throw new IOException();
        }
        setStr(dir.getName() + " foi apagado!");
    }
    
    /**
     * Valida a entrada do usuário para evitar problemas
     * @param entrada Entrada do usuário
     * @return 
     */
    public boolean validaEntrada(File entrada) {
        if (!entrada.isDirectory()) {
            setStr("O caminho de entrada não é um diretório!");
        }
        return true;
    }
    
    /**
     * Método para notificar os observers com mensagens referentes as
     * ações do programa
     * @param msg Mensagem que pode ser emitida por cada método
     */
    public void setStr(String msg) {
        this.str = msg + "\n";
        setChanged();
        notifyObservers();
    }

    /**
     * Dado um diretório, o método percorre recursivamente seus subdiretórios e 
     * adiciona cada arquivo encontrado na lista
     * @param dir Diretório fornecido pelo usuário
     */
    private void percorreArvoreDiretorios(File dir) {
        for (File f : dir.listFiles()) {
            if (f.isFile()) {
                filesList.add(f);
            } else if (f.isDirectory()) {
                percorreArvoreDiretorios(f);
            }
        }
    }
}
