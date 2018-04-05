/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 *
 * @author Marcel
 */
public class Diretorios {

    private String str = "";

    public void ajustaDiretorio(File dir) {

        List<File> dirList = new ArrayList<>(
                FileUtils.listFiles(dir, new NotFileFilter(TrueFileFilter.TRUE),
                        TrueFileFilter.TRUE));
        List<File> filesList = new ArrayList<>(FileUtils.listFiles(dir,
                TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE));

        System.out.println(dirList);
        try {
            for (File f : filesList) {
                str += "Movendo " + f.getAbsolutePath() + " para " + f.getName();

            }

            for (File f : dirList) {
                str += "Removendo : " + f.getAbsolutePath();
                FileUtils.deleteDirectory(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
            str += e.getMessage();
        }
    }

    private void moveArquivo(File origem, File destino) throws IOException {
        
        if (!origem.renameTo(destino)) {
            throw new IOException("Não foi possível mover o arquivo");
        }
    }

    public boolean validaEntrada(File entrada) {
        if (!entrada.isDirectory()) {
            str += "O caminho de entrada não é um diretório!";
        }
        return true;
    }

    public String getStr() {
        return str;

    }

}
