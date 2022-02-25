/**
 *
 * @author Gabriel Rosa
 */
package br.com.mmcs.cct.imp.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Gabriel Rosa
 */
public class WebServiceUtils {

public static void criarArquivo(String conteudoDoNovoArquivo, String nomeDoArquivo, String url) {
        FileWriter arquivo;

        try {
            arquivo = new FileWriter(new File(url + "\\" + nomeDoArquivo));
            arquivo.write(conteudoDoNovoArquivo);
            arquivo.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
