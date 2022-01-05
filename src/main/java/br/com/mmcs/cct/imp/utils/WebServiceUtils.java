/**
 *
 * @author Gabriel Rosa
 */
package br.com.mmcs.cct.imp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author suporte
 */
public class WebServiceUtils {

    public static final String FORMATO_ANO_MES_DIA_T_HORA_MIN_SEG = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String AMBIENTE = "1";
    public static final String URL_ENVIADO_NFSE = "C:\\nfcheetah\\nfse\\enviados";
    public static final String URL_CANCELADO_NFSE = "C:\\nfcheetah\\nfse\\cancelados";
    public static final String URL_ASSINADO_NFSE = "C:\\nfcheetah\\nfse\\assinados";

    public static final String URL_CANCELADO_CTE = "C:\\nfcheetah\\cte\\cancelados";
    public static final String URL_ENVIADO_CTE = "C:\\nfcheetah\\cte\\enviados";
    public static final String URL_ASSINADO_CTE = "C:\\nfcheetah\\cte\\assinados";

    public static final String URL_ENVIADO_MDFE = "C:\\nfcheetah\\mdfe\\enviados";
    public static final String URL_CANCELADO_MDFE = "C:\\nfcheetah\\mdfe\\cancelados";
    public static final String URL_ASSINADO_MDFE = "C:\\nfcheetah\\mdfe\\assinados";
    public static final String URL_ENCERRADO_MDFE = "C:\\nfcheetah\\mdfe\\encerrados";

    public static String lerArquivo(String caminhoArquivo) throws IOException {
        String linha;
        StringBuilder xml = new StringBuilder();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(caminhoArquivo)));
        while ((linha = in.readLine()) != null) {
            xml.append(linha);
        }
        in.close();

        return xml.toString();
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String normalize(String str) {
        String xml = str;
        if ((xml != null) && (!"".equals(xml))) {
            xml = xml.replaceAll("\\r\\n", "");
            xml = xml.replaceAll("\\r", "");
            xml = xml.replaceAll("\\n", "");
            xml = xml.replaceAll("\\>\\s+\\<", "><");
            xml = xml.replaceAll("(\\s\\s)", "");
            xml = xml.replaceAll(" standalone=\"no\"", "");
        }
        return xml;
    }

    public static Document stringXmlParaDocument(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xml)));
    }

    public static String documentParaString(Document document) throws ParserConfigurationException, TransformerException {
        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }

    public static void criarArquivoComBaseEmOutro(String urlArquivo, String complementoDoNomeDoArquivo, String conteudoDoNovoArquivo, String urlPasta) {
        FileWriter arquivo;
        String nomeDoArquivo = urlArquivo.substring(urlArquivo.lastIndexOf("\\") + 1);

        try {
            arquivo = new FileWriter(new File(urlPasta + "\\" + complementoDoNomeDoArquivo + nomeDoArquivo));
            arquivo.write(conteudoDoNovoArquivo);
            arquivo.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void deletarArquivo(String url) {
        File file = new File(url);
        file.delete();
    }

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

    /**
     * Normaliza o xml para não ter espaços nem caracters especiais que
     * atrapalham durante a assinatura
     *
     * @param xml
     * @return
     */
    public static Document prepararaXmlParaAssinar(String xml) {
        String xmlSemAssinatura;
        Document docXml = null;
        try {
            xmlSemAssinatura = WebServiceUtils.normalize(WebServiceUtils.lerArquivo(xml));
        } catch (IOException ex) {
            xmlSemAssinatura = WebServiceUtils.normalize(xml);
        }

        try {
            docXml = WebServiceUtils.stringXmlParaDocument(WebServiceUtils.removerAcentos(xmlSemAssinatura));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(WebServiceUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return docXml;
    }

    public static String pegarTextoEntreTags(String xml, String tag) {
        String texto = null;
        Pattern pattern = Pattern.compile("<" + tag + ">(.*?)</" + tag + ">");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            texto = matcher.group();
            texto = texto.replace("<" + tag + ">", "");
            texto = texto.replace("</" + tag + ">", "");
        }
        return texto;
    }

    public static String pegarPrimeiroTextoEntreTags(String xml, String tag) {
        String texto = null;
        Pattern pattern = Pattern.compile("<" + tag + ">(.*?)</" + tag + ">");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            texto = matcher.group();
            texto = texto.replace("<" + tag + ">", "");
            texto = texto.replace("</" + tag + ">", "");
            break;
        }
        return texto;
    }

    public static String pegarPrimeiroTextoEntreTags(String xml, String initTag, String lastTag) {
        String texto = null;
        Pattern pattern = Pattern.compile("<" + initTag + ">(.*?)</" + lastTag + ">");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            texto = matcher.group();
            texto = texto.replace("<" + initTag + ">", "");
            texto = texto.replace("</" + lastTag + ">", "");
            break;
        }
        return texto;
    }

    public static List<String> pegarTodosDadosEntreTags(String xml, String tag) {
        String texto = null;
        List<String> resultado = new ArrayList<>();
        Pattern pattern = Pattern.compile("<" + tag + ">(.*?)</" + tag + ">");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            texto = matcher.group();
            texto = texto.replace("<" + tag + ">", "");
            texto = texto.replace("</" + tag + ">", "");
            resultado.add(texto);
        }
        return resultado;
    }

    public static String pegarTodosDadosEntreTagsPorContains(String xml, String tag) {
        if (xml.contains(tag)) {
            int startTag = xml.indexOf("<" + tag + ">");
            int endTag = xml.indexOf("</" + tag + ">");

            return xml.substring(startTag + tag.length() + 2, endTag);
        } else {
            return "";
        }
    }

    public static List<String> pegarTodosDadosEntreTags(String xml, String initTag, String lastTag) {
        String texto = null;
        List<String> resultado = new ArrayList<>();
        Pattern pattern = Pattern.compile("<" + initTag + ">(.*?)</" + lastTag + ">");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            texto = matcher.group();
            texto = texto.replace("<" + initTag + ">", "");
            texto = texto.replace("</" + lastTag + ">", "");
            resultado.add(texto);
        }
        return resultado;
    }

    public static List<File> listaArquivosDeUmDiretorio(String urlDiretorio) {
        File diretorio = new File(urlDiretorio);
        if (diretorio.isDirectory()) {
            return Arrays.asList(diretorio.listFiles());
        } else {
            return null;
        }
    }

    public static String pegarAtributoDoXml(String xml, String attr) {
        String texto = null;
        Pattern pattern = Pattern.compile(attr + "=\"(.*?)\"");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            texto = matcher.group();
            texto = texto.replace(attr + "=\"", "");
            texto = texto.replaceAll("\"", "");
        }
        return texto;
    }

    public static String formataDataAnoMesDiaTHoraMinSeg(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_ANO_MES_DIA_T_HORA_MIN_SEG);
        return dateFormat.format(date);
    }

    public static String removePontoEBarraETraco(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replaceAll("[./-]", "");
    }

    /**
     * Converte um object para String a partir do JaxB
     *
     * @param clazz Classe utilizada como referencia para conversão
     * @param object Objeto a ser convertido
     * @return
     * @throws JAXBException
     */
    public static String converteClassAPartirDoJaxbParaString(Class clazz, Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw);
        return sw.toString();
    }

    public static XMLGregorianCalendar transformaDateEmXmlGregorianDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        XMLGregorianCalendar xmlDate = null;

        if (date == null) {
            return null;
        }

        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(sdf.format(date));
        } catch (DatatypeConfigurationException ex) {
            System.out.println("ERRO DURANTE A CONVERSÃO DAS DATAS");
        }
        return xmlDate;
    }

    public static XMLGregorianCalendar transformaDateEmXmlGregorianDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        XMLGregorianCalendar xmlDate = null;

        if (date == null) {
            return null;
        }

        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(sdf.format(date));
        } catch (DatatypeConfigurationException ex) {
            System.out.println("ERRO DURANTE A CONVERSÃO DAS DATAS");
        }
        return xmlDate;
    }

    public static Date pegarData(List<Object[]> dados, boolean pegarMaiorData, int index) {
        Date date = null;
        Date retornoData = null;

        for (Object[] dado : dados) {
            date = (Date) dado[index];
            if (date != null) {
                if (retornoData == null) {
                    retornoData = date;
                    continue;
                }

                if (pegarMaiorData) {
                    if (date.after(retornoData)) {
                        retornoData = date;
                    }
                } else if (date.before(retornoData)) {
                    retornoData = date;
                }
            }
        }
        return retornoData == null ? new Date() : retornoData;
    }

    /**
     * Transforma o xml novamente em um objecto a partir da classe informada
     *
     * @param <T> Classe a ser transformado
     * @param xml xml a ser convertido
     * @param clazz Classe a ser transformado
     * @return Retorna um objeto preenchido a partir do xml
     */
    public static <T> T unmarshall(String xml, Class<T> clazz) {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            T obj = clazz.cast(unmarshaller.unmarshal(new StringReader(xml)));
            return obj;
        } catch (JAXBException ex) {
            Logger.getLogger(WebServiceUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
