package br.com.mmcs.cct.imp.forms;

import br.com.mmcs.cct.imp.model.enumeration.TpCertificadoEnum;
import br.com.mmcs.cct.imp.utils.CertificadoUtils;
import br.com.mmcs.cct.imp.utils.ConfiguracaoUtils;
import br.com.mmcs.cct.imp.utils.WebServiceUtils;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Rosa
 */
public class jiConfiguracaoCertificado extends javax.swing.JInternalFrame {

    public jiConfiguracaoCertificado() {
        initComponents();
        carregarConfiguracao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgOpcoesCertificado = new javax.swing.ButtonGroup();
        jFileChooser1 = new javax.swing.JFileChooser();
        txtSenhaCertificado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        opToken = new javax.swing.JRadioButton();
        opLeitora = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        opA1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtCaminho = new javax.swing.JTextField();
        btnCaminhoCertificado = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtValidade = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Configuração");

        jLabel2.setText("Senha do Certificado");

        bgOpcoesCertificado.add(opToken);
        opToken.setText("Token");

        bgOpcoesCertificado.add(opLeitora);
        opLeitora.setText("SmartCard");

        jLabel3.setText("Certificado");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        bgOpcoesCertificado.add(opA1);
        opA1.setText("Digital - A1");

        jLabel1.setText("Caminho do Certificado");

        btnCaminhoCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/7715_16x16.png"))); // NOI18N
        btnCaminhoCertificado.setToolTipText("");
        btnCaminhoCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaminhoCertificadoActionPerformed(evt);
            }
        });

        jLabel7.setText("Validade do Certificado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(45, 45, 45)
                        .addComponent(opToken)
                        .addGap(18, 18, 18)
                        .addComponent(opLeitora)
                        .addGap(18, 18, 18)
                        .addComponent(opA1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSenhaCertificado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(txtCaminho, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValidade))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCaminhoCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(btnSalvar)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(opToken)
                        .addComponent(opLeitora)
                        .addComponent(opA1)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCaminhoCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtSenhaCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnSalvar)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (!verificaCamposPreenchidos()) {
            return;
        }
        StringBuilder conteudoConfiguracao = new StringBuilder();
        conteudoConfiguracao.append("prop.certificado=");
        if (opToken.isSelected()) {
            conteudoConfiguracao.append("TOKEN");
        } else if (opLeitora.isSelected()) {
            conteudoConfiguracao.append("SMART_CARD");
        } else if (opA1.isSelected()) {
            conteudoConfiguracao.append("A1");
        }
        conteudoConfiguracao.append(System.getProperty("line.separator"));
        conteudoConfiguracao.append("prop.senha=");
        conteudoConfiguracao.append(txtSenhaCertificado.getText());
        conteudoConfiguracao.append(System.getProperty("line.separator"));
        conteudoConfiguracao.append("prop.caminho=");
        conteudoConfiguracao.append(txtCaminho.getText());
        conteudoConfiguracao.append(System.getProperty("line.separator"));

        WebServiceUtils.criarArquivo(conteudoConfiguracao.toString(), ConfiguracaoUtils.ARQUIVO_CONFIGURACAO, ConfiguracaoUtils.URL_CONF);

        JOptionPane.showMessageDialog(null, "Configurações salvas!", "Salvar Configurações", 2);

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCaminhoCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaminhoCertificadoActionPerformed
        if (opA1.isSelected()) {
            JFileChooser chooserArquivo = new JFileChooser();
            chooserArquivo.showOpenDialog(getParent());
            txtCaminho.setText(chooserArquivo.getSelectedFile().getAbsolutePath());
        } else {
            StringBuilder certificadosConcatenados = new StringBuilder();
            int contador = 1;
            ArrayList<String> certificados = CertificadoUtils.carregaCertificadoDoProvider();
            for (String cert : certificados) {
                certificadosConcatenados.append(contador++).append(" - ").append(cert).append(System.getProperty("line.separator"));
            }
            String opcao = JOptionPane.showInputDialog(null, certificadosConcatenados.toString(), "Selecione número do certificado", 1);
            txtCaminho.setText(certificados.get(Integer.parseInt(opcao) - 1));
        }
    }//GEN-LAST:event_btnCaminhoCertificadoActionPerformed

    public void setPosicao() {
        Dimension d = this.getParent().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    public boolean verificaCamposPreenchidos() {
        if (txtSenhaCertificado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha a senha do certificado", "Erro", 1);
            return false;
        } else if (!opToken.isSelected() && !opLeitora.isSelected() && !opA1.isSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione o tipo de certificado", "Erro", 1);
            return false;
        } else {
            return true;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOpcoesCertificado;
    private javax.swing.JButton btnCaminhoCertificado;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton opA1;
    private javax.swing.JRadioButton opLeitora;
    private javax.swing.JRadioButton opToken;
    private javax.swing.JTextField txtCaminho;
    private javax.swing.JTextField txtSenhaCertificado;
    private javax.swing.JTextField txtValidade;
    // End of variables declaration//GEN-END:variables

    public void carregarConfiguracao() {
        txtSenhaCertificado.setText(ConfiguracaoUtils.getSenhaCertificado());
        txtCaminho.setText(ConfiguracaoUtils.getCaminhoCertificado());

        if (ConfiguracaoUtils.getTipoCertificado() != null) {
            if (ConfiguracaoUtils.getTipoCertificado().equals(TpCertificadoEnum.TOKEN)) {
                opToken.setSelected(Boolean.TRUE);
            } else if (ConfiguracaoUtils.getTipoCertificado().equals(TpCertificadoEnum.SMART_CARD)) {
                opLeitora.setSelected(Boolean.TRUE);
            } else {
                opA1.setSelected(Boolean.TRUE);
            }
        }
    }
}
