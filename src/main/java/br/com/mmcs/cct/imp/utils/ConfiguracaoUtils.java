package br.com.mmcs.cct.imp.utils;

import br.com.mmcs.cct.imp.model.enumeration.TpCertificadoEnum;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Rosa
 */
public class ConfiguracaoUtils {

    public static boolean E_PRODUCAO = false;

    public static String ARQUIVO_CONFIGURACAO = "certificado.properties";
    public static final String PROPRIEDADE_SENHA = "prop.senha";
    public static final String PROPRIEDADE_CERTIFICADO = "prop.certificado";

    public static final String URL_CONF = "C:\\nfcheetah\\conf\\";
    public static final int SSL_PORT = 443;
    private static final String PROPRIEDADE_CAMINHO_A1 = "prop.caminho";

    /**
     * Carrega o arquivo de configuração
     *
     * @return
     */
    public static Properties carregarArquivoDeConfiguracao() {
        try {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream(URL_CONF + ARQUIVO_CONFIGURACAO);
            props.load(file);
            return props;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado, sendo criado a configuração inicial", "", 0);
            WebServiceUtils.criarArquivo("", ARQUIVO_CONFIGURACAO, URL_CONF);
            return null;
        }
    }

    public static String getSenhaCertificado() {
        Properties prop = carregarArquivoDeConfiguracao();
        return prop.getProperty(PROPRIEDADE_SENHA);
    }

    public static TpCertificadoEnum getTipoCertificado() {
        Properties prop = carregarArquivoDeConfiguracao();
        return TpCertificadoEnum.valueOf(prop.getProperty(PROPRIEDADE_CERTIFICADO));
    }

    /**
     * Pega o caminho informado do certificado A1
     *
     * @return
     */
    public static String getCaminhoCertificado() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(URL_CONF + ARQUIVO_CONFIGURACAO));
            while (br.ready()) {
                String linha = br.readLine();
                if (linha.startsWith(PROPRIEDADE_CAMINHO_A1)) {
                    return linha.replace(PROPRIEDADE_CAMINHO_A1 + "=", "");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CertificadoUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CertificadoUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
