package br.com.mmcs.cct.imp.utils;

import br.com.mmcs.cct.imp.model.enumeration.TpCertificadoEnum;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 *
 * @author Gabriel Rosa
 */
public class CertificadoUtils {

    private static KeyStore.PrivateKeyEntry cert;
    public static String SUNPKCS11 = "";

    /**
     * Realiza conexão com o certificado plugado na máquina
     *
     * @param tpCertificado
     * @throws IOException
     */
    public static void criarConexaoComOCertificado(TpCertificadoEnum tpCertificado) throws IOException {

//        System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
        switch (tpCertificado) {
            case TOKEN:
//                System.setProperty("javax.net.ssl.keyStoreType", "PKCS11");
//                System.setProperty("javax.net.ssl.keyStoreProvider", "SunPKCS11-eToken");
//                System.setProperty("javax.net.ssl.keyStore", "NONE");
//                System.setProperty("javax.net.ssl.keyStorePassword", ConfiguracaoUtils.getSenhaCertificado());
//                System.setProperty("javax.net.ssl.trustStoreType", "JKS");
//                System.setProperty("javax.net.ssl.trustStore", ConfiguracaoUtils.URL_CONF + "Cacerts");
//                break;
            case SMART_CARD:
                System.setProperty("https.protocols", "SSLv3,TLSv1");
                System.setProperty("jdk.tls.client.protocols", "TLSv1");
//                System.setProperty("javax.net.ssl.keyStoreType", "PKCS11");
//                System.setProperty("javax.net.ssl.keyStoreProvider", "SunPKCS11-SmartCard");
//                System.setProperty("javax.net.ssl.keyStore", "NONE");
//                System.setProperty("javax.net.ssl.keyStorePassword", ConfiguracaoUtils.getSenhaCertificado());
//                System.setProperty("javax.net.ssl.trustStoreType", "JKS");
//                System.setProperty("javax.net.ssl.trustStore", ConfiguracaoUtils.URL_CONF + "Cacerts");
//                break;
            case A1:
//                System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
//                System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
//                System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
//                System.setProperty("javax.net.ssl.keyStore", ConfiguracaoUtils.getCaminhoCertificado());
//                System.setProperty("javax.net.ssl.keyStorePassword", ConfiguracaoUtils.getSenhaCertificado());
//                break;
            default:
                break;
        }

    }

    /**
     * Carrega o certificado digital com base no arquivo token ou smartCard cfg
     * localizado em C:\nfcheetah\conf
     *
     * @return
     */
    public static ArrayList<String> carregaCertificadoDoProvider() {
        ArrayList<String> certificados = new ArrayList<>();
        Provider p;
        if (Security.getProvider(SUNPKCS11) == null) {
            p = new sun.security.pkcs11.SunPKCS11((ConfiguracaoUtils.getTipoCertificado().equals(TpCertificadoEnum.TOKEN)
                    ? ConfiguracaoUtils.URL_CONF + "token.cfg"
                    : ConfiguracaoUtils.URL_CONF + "smartCard.cfg"));
            Security.addProvider(p);
            SUNPKCS11 = p.getName();
        } else {
            p = Security.getProvider(SUNPKCS11);
        }

        try {
            KeyStore ks = KeyStore.getInstance("pkcs11", p);

            ks.load(null, ConfiguracaoUtils.getSenhaCertificado().toCharArray());
            Enumeration<String> al = ks.aliases();

            while (al.hasMoreElements()) {
                certificados.add(al.nextElement());
            }
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | java.security.cert.CertificateException ex) {
            ex.printStackTrace();
        }

        return certificados;

    }

    /**
     * Carrega os certificado do repositório do Windows
     *
     * @return
     */
    public static ArrayList<String> carregaCertificadoRepWindows() {
        ArrayList<String> certificados = new ArrayList<>();
        KeyStore ks;
        try {
            ks = KeyStore.getInstance("Windows-MY", "SunMSCAPI");

            ks.load(null, null);
            Enumeration<String> al = ks.aliases();

            while (al.hasMoreElements()) {
                certificados.add(al.nextElement());
            }
        } catch (KeyStoreException | NoSuchProviderException | IOException | NoSuchAlgorithmException | java.security.cert.CertificateException ex) {
            ex.printStackTrace();
        }
        return certificados;
    }

    /**
     * Carrega o certificado plugado na máquina
     *
     * @param certificado
     * @throws Exception
     */
    public static void carregarCertificadosNaKeyStore(TpCertificadoEnum certificado) throws Exception {
//        Provider provider = null;
//        KeyStore.PrivateKeyEntry pkEntry = null;
//        KeyStore ks = null;
        if (cert == null) {

//            if (!certificado.equals(TpCertificado.A1)) {
//                provider = new sun.security.pkcs11.SunPKCS11(certificado.equals(TpCertificado.TOKEN)
//                        ? ConfiguracaoUtils.URL_CONF + "token.cfg"
//                        : ConfiguracaoUtils.URL_CONF + "smartCard.cfg");
//                Security.addProvider(provider);
//                ks = KeyStore.getInstance("pkcs11", provider);
//                ks.load(null, ConfiguracaoUtils.getSenhaCertificado().toCharArray());
//
//                Enumeration<String> aliasesEnum = ks.aliases();
//                while (aliasesEnum.hasMoreElements()) {
//                    String alias = (String) aliasesEnum.nextElement();
//                    if (ks.isKeyEntry(alias)) {
//                        pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
//                                new KeyStore.PasswordProtection(ConfiguracaoUtils.getSenhaCertificado().toCharArray()));
//                        break;
//                    }
//                }
//                cert = pkEntry;
            if (!TpCertificadoEnum.A1.equals(certificado)) {
                carregarDadosA3();
            } else {
                carregarDadosA1();
            }
        } else {
//                Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//                ks = KeyStore.getInstance(("PKCS12"));
//                ks.load(new FileInputStream(CertificadoUtils.getCaminhoCertificado()), CertificadoUtils.getSenhaCertificado().toCharArray());

        }

    }

    public static KeyStore.PrivateKeyEntry getCert() {
        return cert;
    }

    public static void setCert(KeyStore.PrivateKeyEntry cert) {
        CertificadoUtils.cert = cert;
    }

    /**
     * Carrega o certificado do tipo A3 com base no arquivo localizado em
     * C:\nfcheetah\conf token ou smartCard cfg
     */
    private static void carregarDadosA3() {
        Provider p;
        try {
            if (Security.getProvider(SUNPKCS11) == null) {
                p = new sun.security.pkcs11.SunPKCS11((ConfiguracaoUtils.getTipoCertificado().equals(TpCertificadoEnum.TOKEN)
                        ? ConfiguracaoUtils.URL_CONF + "token.cfg"
                        : ConfiguracaoUtils.URL_CONF + "smartCard.cfg"));
                Security.addProvider(p);
                SUNPKCS11 = p.getName();
            } else {
                p = Security.getProvider(SUNPKCS11);
            }
            char[] pin = ConfiguracaoUtils.getSenhaCertificado().toCharArray();
            KeyStore ks;
            ks = KeyStore.getInstance("pkcs11", p);

            ks.load(null, pin);

            String alias = "";
            Enumeration<String> aliasesEnum = ks.aliases();
            while (aliasesEnum.hasMoreElements()) {
                alias = (String) aliasesEnum.nextElement();
                if (ks.isKeyEntry(alias)) {
                    cert = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
                            new KeyStore.PasswordProtection(ConfiguracaoUtils.getSenhaCertificado().toCharArray()));

                    break;
                }
            }
            X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
            PrivateKey privateKey = (PrivateKey) ks.getKey(alias, ConfiguracaoUtils.getSenhaCertificado().toCharArray());
            SocketFactoryDinamico socketFactoryDinamico = new SocketFactoryDinamico(certificate, privateKey);
            socketFactoryDinamico.setFileCacerts(ConfiguracaoUtils.URL_CONF + "Cacerts");

            Protocol protocol = new Protocol("https", socketFactoryDinamico, ConfiguracaoUtils.SSL_PORT);
            Protocol.registerProtocol("https", protocol);
        } catch (IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableEntryException | KeyStoreException ex) {
            JOptionPane.showMessageDialog(null, "Certificado não encontrado na máquina!");
            ex.printStackTrace();
        }
    }

    /**
     * Carrega ao arquivo .pfx que se refere ao certificado de arquivo que
     * representa o tipo A1
     *
     * @throws FileNotFoundException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws java.security.cert.CertificateException
     * @throws Exception
     */
    private static void carregarDadosA1() throws FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, java.security.cert.CertificateException, Exception {
        InputStream entrada = new FileInputStream(ConfiguracaoUtils.getCaminhoCertificado());
        KeyStore ks = KeyStore.getInstance("pkcs12");
        try {
            ks.load(entrada, ConfiguracaoUtils.getSenhaCertificado().toCharArray());
        } catch (IOException e) {
            throw new Exception("Senha do Certificado Digital esta incorreta ou Certificado inválido.");
        }

        String alias = "";
        Enumeration<String> aliasesEnum = ks.aliases();
        while (aliasesEnum.hasMoreElements()) {
            alias = (String) aliasesEnum.nextElement();
            if (ks.isKeyEntry(alias)) {
                cert = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
                        new KeyStore.PasswordProtection(ConfiguracaoUtils.getSenhaCertificado().toCharArray()));
                break;
            }
        }
        X509Certificate certificate = (X509Certificate) ks.getCertificate(alias);
        PrivateKey privateKey = (PrivateKey) ks.getKey(alias, ConfiguracaoUtils.getSenhaCertificado().toCharArray());
        SocketFactoryDinamico socketFactoryDinamico = new SocketFactoryDinamico(certificate, privateKey);
        socketFactoryDinamico.setFileCacerts(ConfiguracaoUtils.URL_CONF + "Cacerts");

        Protocol protocol = new Protocol("https", socketFactoryDinamico, ConfiguracaoUtils.SSL_PORT);
        Protocol.registerProtocol("https", protocol);

    }
}
