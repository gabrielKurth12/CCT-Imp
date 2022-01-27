package br.com.mmcs.cct.imp.forms;

import br.com.mmcs.cct.imp.service.ShipmentHouseService;
import br.com.mmcs.cct.imp.utils.CertificadoUtils;
import br.com.mmcs.cct.imp.utils.ConfiguracaoUtils;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Gabriel Rosa
 */
public class jifEnviarCai extends javax.swing.JInternalFrame {

//    private RpsDao rpsDao;
//    private NfseControl nfseControl;
//    private NfseBHControl bHControl;
//    private NfsePortoAlegreControl portoAlegreControl;
//    private NfseGinfesControl nfseGinfesControl;
//    private NfseVilaVelhaControl nfseVilaVelhaControl;
    private String TAG_NFE = "NumeroNFe";
    private String TAG_NFE_CODE = "CodigoVerificacao";
    private String TAG_LOTE = "NumeroLote";
    private String TAG_RPS = "NumeroRPS";
    private TableRowSorter sorter;
    private ShipmentHouseService shipmentHouseService;
//    private NfseBhUtils nfseBhUtils;
//    private NfseSalvadorControl salvadorControl;
//    private NfseItajaiControl itajaiControl;
//    private NfseCampControl campControl;
    private static int teste = 0;

    /**
     * Creates new form jifEnviaCte
     */
    public jifEnviarCai() {
//        rpsDao = new RpsDaoImpl();
//        nfseControl = new NfseControl();
//        nfseBhUtils = new NfseBhUtils();
//        bHControl = new NfseBHControl();
//        portoAlegreControl = new NfsePortoAlegreControl();
//        salvadorControl = new NfseSalvadorControl();
//        nfseGinfesControl = new NfseGinfesControl();
//        nfseVilaVelhaControl = new NfseVilaVelhaControl();
//        itajaiControl = new NfseItajaiControl();
//        campControl = new NfseCampControl();
        initComponents();
        jtableTtn.getColumnModel().getColumn(0).setMaxWidth(280);

        criarSorter();
        preencherTable();
    }

    private void criarSorter() {
        sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) jtableTtn.getModel());
        jtableTtn.setRowSorter(sorter);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtableTtn = new javax.swing.JTable();
        btnEnviarCte = new javax.swing.JButton();
        jtxtPesquisaProcesso = new javax.swing.JTextField();
        jtxtPesquisaCliente = new javax.swing.JTextField();
        jtxtPesquisaCodigo = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jtxtPesquisaDataEmsisao = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setTitle("Enviar ");
        getContentPane().setLayout(null);

        jtableTtn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Selecionar", "Processo", "Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtableTtn.setColumnSelectionAllowed(true);
        jtableTtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jtableTtn);
        jtableTtn.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 70, 1010, 240);

        btnEnviarCte.setText("Enviar");
        btnEnviarCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnviarCte);
        btnEnviarCte.setBounds(10, 347, 120, 36);

        jtxtPesquisaProcesso.setName(""); // NOI18N
        jtxtPesquisaProcesso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pesquisaProcessoKeyTyped(evt);
            }
        });
        getContentPane().add(jtxtPesquisaProcesso);
        jtxtPesquisaProcesso.setBounds(860, 40, 130, 30);

        jtxtPesquisaCliente.setName(""); // NOI18N
        jtxtPesquisaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pesquisaClienteKeyTyped(evt);
            }
        });
        getContentPane().add(jtxtPesquisaCliente);
        jtxtPesquisaCliente.setBounds(210, 40, 130, 30);

        jtxtPesquisaCodigo.setName(""); // NOI18N
        jtxtPesquisaCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pesquisaPeloCodigoKeyTyped(evt);
            }
        });
        getContentPane().add(jtxtPesquisaCodigo);
        jtxtPesquisaCodigo.setBounds(80, 40, 130, 30);

        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(140, 350, 200, 20);

        jLabel2.setText("Pesquisa");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 130, 20);

        jtxtPesquisaDataEmsisao.setName(""); // NOI18N
        jtxtPesquisaDataEmsisao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtPesquisaDataEmsisaopesquisaDataKeyTyped(evt);
            }
        });
        getContentPane().add(jtxtPesquisaDataEmsisao);
        jtxtPesquisaDataEmsisao.setBounds(340, 40, 130, 30);

        setSize(new java.awt.Dimension(1021, 603));
    }// </editor-fold>//GEN-END:initComponents


    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
//        PedidoEnvioLoteRPS envioLoteRPS = null;
        String response = null;
        List<Object[]> linhasSelecionadas = new ArrayList<Object[]>();
        List<Integer> linhas = getSelectRows();
        String xml;
        jProgressBar1.setValue(25);
        for (int linha : linhas) {
            linhasSelecionadas.add(pegarDadosDaLinha(linha, (DefaultTableModel) jtableTtn.getModel()));
        }
        try {
            CertificadoUtils.criarConexaoComOCertificado(ConfiguracaoUtils.getTipoCertificado());
            CertificadoUtils.carregarCertificadosNaKeyStore(ConfiguracaoUtils.getTipoCertificado());

//            xml = criarXmlParaEnvio(linhasSelecionadas);
//            if (ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.SP)) {
//                xml = NfseSPUtils.remontaTagsErradas(xml, envioLoteRPS);
//            } else if (!ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.BH)
//                    && !ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.ITAJAI)
//                    && !ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.SALVADOR)
//                    && !ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.SAOJOSEDOSCAMPOS)
//                    && !ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.PORTOALEGRE)) {
//                if (!ValidadorUtils.validarXml(ValidadorUtils.URL_XSD_GINFES, xml)) {
//                    JOptionPane.showMessageDialog(null, "Nfse não enviada", "Erro ao enviar", 0);
//                    return;
//                }
//            }
//            if (ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.SALVADOR)) {
//                xml = xml.replace("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
//            }           
//            Document doc = WebServiceUtils.prepararaXmlParaAssinar(xml);
//            System.out.println("XML preenchido:");
//            System.out.println(WebServiceUtils.documentParaString(doc));
            jProgressBar1.setValue(70);
//            assinaNfse(doc);

//            System.out.println("Documento assinado:");
//            System.out.println(WebServiceUtils.documentParaString(doc));
//            
//            response = enviarNfse(WebServiceUtils.documentParaString(doc));
//            System.out.println("Retorno envio:");
//            System.out.println(response);
//            tratarErros(response, linhasSelecionadas, WebServiceUtils.documentParaString(doc));
            jProgressBar1.setValue(100);
        } catch (DatatypeConfigurationException | JAXBException | IOException ex) {
            Logger.getLogger(jifEnviarCai.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(jifEnviarCai.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(jifEnviarCai.class.getName()).log(Level.SEVERE, null, ex);
        }
        criarSorter();
        preencherTable();
    }//GEN-LAST:event_btnEnviarActionPerformed

//    public String criarXmlParaEnvio(List<Object[]> linhasSelecionadas) throws JAXBException, DatatypeConfigurationException {
//        switch (ConfiguracaoUtils.getMunicipioEmissor()) {
//            case SP:
//                return NfseSPUtils.pedidoEnvioLoteRPSParaString(nfseControl.criaXmlParaEnvioRpsSP(linhasSelecionadas, radioDeducoes.isSelected()));
//            case BH:
//                return WebServiceUtils.converteClassAPartirDoJaxbParaString(br.com.mmcs.xsd.nfse.bh.EnviarLoteRpsEnvio.class, bHControl.criarXmlEnvioBh(linhasSelecionadas, radioDeducoes.isSelected()));
////            case SANTOS:
//            case SAOJOSEDOSCAMPOS:
//                return campControl.criarXmlParaEnvio(linhasSelecionadas, radioDeducoes.isSelected());
//            case VILAVELHA:
//                return WebServiceUtils.converteClassAPartirDoJaxbParaString(br.com.mmcs.xsd.nfse.vilavelha.EnviarLoteRpsEnvio.class, nfseVilaVelhaControl.criarXmlEnvio(linhasSelecionadas, radioDeducoes.isSelected()));
//            case SALVADOR:
//                return salvadorControl.gerarXmlSalvador(linhasSelecionadas, radioDeducoes.isSelected());
//            case ITAJAI:
//                return itajaiControl.criarXmlEnvioItajai(linhasSelecionadas, radioDeducoes.isSelected());
//            case PORTOALEGRE:
//                return WebServiceUtils.converteClassAPartirDoJaxbParaString(br.com.mmcs.xsd.nfse.portoalegre.EnviarLoteRpsEnvio.class, portoAlegreControl.criarXmlEnvioPortoAlegre(linhasSelecionadas, radioDeducoes.isSelected()));
//            default:
//                return WebServiceUtils.converteClassAPartirDoJaxbParaString(br.com.mmcs.xsd.nfse.ginfes.EnviarLoteRpsEnvio.class, nfseGinfesControl.criaXmlParaEnvioLote(linhasSelecionadas, radioDeducoes.isSelected()));
//        }
//    }

    private void pesquisaProcessoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisaProcessoKeyTyped
        jtxtPesquisaProcesso.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                realizaFiltro();
            }
        });
    }//GEN-LAST:event_pesquisaProcessoKeyTyped

    private void pesquisaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisaClienteKeyTyped
        jtxtPesquisaCliente.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                realizaFiltro();
            }
        });
    }//GEN-LAST:event_pesquisaClienteKeyTyped

    public void realizaFiltro() {
        List<RowFilter<Object, Object>> listOfFilters = new ArrayList<>();
        listOfFilters.add(RowFilter.regexFilter("(?i)" + jtxtPesquisaCliente.getText(), new int[]{2}));
        listOfFilters.add(RowFilter.regexFilter("(?i)" + jtxtPesquisaCodigo.getText(), new int[]{1}));
        listOfFilters.add(RowFilter.regexFilter("(?i)" + jtxtPesquisaDataEmsisao.getText(), new int[]{3}));
        listOfFilters.add(RowFilter.regexFilter("(?i)" + jtxtPesquisaProcesso.getText(), new int[]{7}));

        sorter.setRowFilter(RowFilter.andFilter(listOfFilters));

    }

    private void pesquisaPeloCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisaPeloCodigoKeyTyped
        jtxtPesquisaCodigo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                realizaFiltro();
            }
        });
    }//GEN-LAST:event_pesquisaPeloCodigoKeyTyped

    private void jtxtPesquisaDataEmsisaopesquisaDataKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPesquisaDataEmsisaopesquisaDataKeyTyped
        jtxtPesquisaDataEmsisao.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                realizaFiltro();
            }
        });
    }//GEN-LAST:event_jtxtPesquisaDataEmsisaopesquisaDataKeyTyped

    private void preencherTable() {
        DefaultTableModel dtm;

        shipmentHouseService.findfrtValueAndCurrency("CAI");

        List<Object[]> resultado;
//        resultado = rpsDao.listaDadosParaEnvioRpsSP();

        dtm = (DefaultTableModel) jtableTtn.getModel();
        dtm.getDataVector().removeAllElements();
        jtableTtn.clearSelection();
//        for (Object[] linha : resultado) {
//            dtm.addRow(linha);
//        }
    }

    private Object[] pegarDadosDaLinha(int row, DefaultTableModel model) {
        Object[] result = new Object[model.getColumnCount()];

        for (int i = 0; i < model.getColumnCount(); i++) {
            result[i] = model.getValueAt(jtableTtn.convertRowIndexToModel(row), i);
        }
        return result;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarCte;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtableTtn;
    private javax.swing.JTextField jtxtPesquisaCliente;
    private javax.swing.JTextField jtxtPesquisaCodigo;
    private javax.swing.JTextField jtxtPesquisaDataEmsisao;
    private javax.swing.JTextField jtxtPesquisaProcesso;
    // End of variables declaration//GEN-END:variables

    /**
     * Realiza a assinatura do Document informado
     *
     * @param doc
     */
//    private void assinaNfse(Document doc) {
//        try {
//            if (ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.SP)) {
//                AssinaturasUtils.AssinaturaUnicaNoFinalDoXml(doc, ConfiguracaoUtils.getTipoCertificado());
//            } //            else if (ConfiguracaoUtils.getMunicipioEmissor().equals(Municipios.ITAJAI)) {
//            //                AssinaturasUtils.AssinaturaXml(doc, title, TpCertificado.SMART_CARD);
//            else {
//                NfseBHControl.assinarNfseAbrasf(doc, ConfiguracaoUtils.getTipoCertificado());
//            }
//        } catch (InvalidAlgorithmParameterException ex) {
//            Logger.getLogger(jifEnviaCAI.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (MarshalException ex) {
//            Logger.getLogger(jifEnviaCAI.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (XMLSignatureException ex) {
//            Logger.getLogger(jifEnviaCAI.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(jifEnviaCAI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    private String enviarNfse(String xml) {
//        switch (ConfiguracaoUtils.getMunicipioEmissor()) {
//            case SP:
//                return NfseSPUtils.enviarNfseSP(xml);
//            case BH:
//                return NfseBhUtils.enviarNfse(xml);
//            case ITAJAI:
//                return NfseItajaiUtils.enviarArquivoDeLoteNfseItajai(xml);
//            case SAOJOSEDOSCAMPOS:
//                return NfseSjcUtils.enviarNfseSjc(xml);
//            case SALVADOR:
//                return NfseSalvadorUtils.enviarNfse(xml);
//            case PORTOALEGRE:
//                return NfsePortoAlegreUtils.enviarNfse(xml);
//            default:
//                return NfseGinfesUtils.enviarNfseGinfesV3(xml);
//        }
//    }
//    private void tratarErros(String response, List<Object[]> linhasSelecionadas, String xml) throws ParserConfigurationException, TransformerException, InterruptedException {
//        switch (ConfiguracaoUtils.getMunicipioEmissor()) {
//            case SP:
//                nfseControl.tratarEnvio(response, linhasSelecionadas);
//                break;
//            case BH:
//                bHControl.tratarEnvioBh(xml, response, linhasSelecionadas);
//                break;
//            case ITAJAI:
//                itajaiControl.tratarEnvioItajai(xml, response, linhasSelecionadas);
//                break;
//            case SAOJOSEDOSCAMPOS:
//                campControl.tratarEnvioSjc(xml, response, linhasSelecionadas);
//                break;
//            case PORTOALEGRE:
//                portoAlegreControl.tratarEnvioPortoAlegre(xml, response, linhasSelecionadas);
//                break;
//            default:
//                NfseGinfesUtils.verificarEnvioGinfes(response);
//                break;
//        }
//    }
    private List<Integer> getSelectRows() {
        List<Integer> linhas = new ArrayList<Integer>();
        for (int i = 0; i < jtableTtn.getRowCount(); i++) {
            Boolean isChecked = Boolean.valueOf(jtableTtn.getValueAt(i, 0).toString());

            if (isChecked) {
                linhas.add(i);
            }
        }
        return linhas;
    }

    public void setPosicao() {
        Dimension d = this.getParent().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

}
