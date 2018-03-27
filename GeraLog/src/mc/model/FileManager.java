/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mc.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Marcel
 */
public class FileManager {

    private static final File fileDir = new File("D:\\Users\\Marcel\\Dropbox\\protocolos.txt");

    /**
     * Salva o texto no arquivo de log
     *
     * @param corporation Nome da empresa
     * @param date Data do protocolo
     * @param protocol Protocolo informado
     * @param description Descrição do ocorrido
     * @throws FileNotFoundException Lançada caso não seja encontrado
     * o arquivo de log
     * @throws IOException Lançada caso haja erro de gravação no buffer
     */
    public static void saveToFile(String corporation, String date,
            String protocol, String description) throws FileNotFoundException, IOException {

        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileDir, true), "UTF8"))) {
            out.append(String.format("%s; %s; %s; %s\n",
                    corporation, date, protocol, description));
        }
    }

    /**
     * Carrega as informações de log do arquivo texto
     *
     * @return @code{List<String>} Lista com os protocolos
     * @throws FileNotFoundException Lançada caso não seja encontrado o arquivo
     * de log
     * @throws IOException Lançada caso haja erro de gravação no buffer
     */
    public static List<String> loadFromFile() throws FileNotFoundException, IOException {
        List<String> list;
        try (FileReader fr = new FileReader(fileDir);
                FileInputStream fis = new FileInputStream(fileDir);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                
                BufferedReader br = new BufferedReader(isr)) {
            list = new LinkedList<>();
            while (br.ready()) {
                list.add(br.readLine());
            }
        }
        return list;
    }
}
