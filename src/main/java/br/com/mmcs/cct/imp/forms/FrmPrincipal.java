package br.com.mmcs.cct.imp.forms;

/**
 *
 * @author Gabriel Rosa
 */
public class FrmPrincipal extends javax.swing.JFrame {

    private jiConfiguracaoCertificado configuracaoCertificado;
    private jifEnviarCai enviarCai;

    public FrmPrincipal() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        initComponents();
        versionLabel.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        versionLabel = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuConfiguracao1 = new javax.swing.JMenu();
        imEnviarCai = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        menuConfiguracao = new javax.swing.JMenu();
        imConfigurarCertificado = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mmcs CTT-Imp");
        getContentPane().setLayout(null);

        versionLabel.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        versionLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        versionLabel.setText("OLA");
        versionLabel.setAlignmentY(100.0F);
        versionLabel.setName("versionLabel"); // NOI18N

        Desktop.setLayer(versionLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopLayout.createSequentialGroup()
                .addContainerGap(859, Short.MAX_VALUE)
                .addComponent(versionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopLayout.createSequentialGroup()
                .addContainerGap(589, Short.MAX_VALUE)
                .addComponent(versionLabel)
                .addContainerGap())
        );

        getContentPane().add(Desktop);
        Desktop.setBounds(0, 0, 1020, 610);

        menuConfiguracao1.setText("CAI");

        imEnviarCai.setText("Enviar");
        imEnviarCai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imEnviarCaiActionPerformed(evt);
            }
        });
        menuConfiguracao1.add(imEnviarCai);
        menuConfiguracao1.add(jSeparator12);

        barraMenu.add(menuConfiguracao1);

        menuConfiguracao.setText("Configuração");

        imConfigurarCertificado.setText("Configurar certificado");
        imConfigurarCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imConfigurarCertificadoActionPerformed(evt);
            }
        });
        menuConfiguracao.add(imConfigurarCertificado);
        menuConfiguracao.add(jSeparator11);

        barraMenu.add(menuConfiguracao);

        setJMenuBar(barraMenu);

        setSize(new java.awt.Dimension(1036, 670));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void imConfigurarCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imConfigurarCertificadoActionPerformed
        configuracaoCertificado = new jiConfiguracaoCertificado();
        Desktop.add(configuracaoCertificado);
        configuracaoCertificado.setPosicao();
        configuracaoCertificado.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_imConfigurarCertificadoActionPerformed

    private void imEnviarCaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imEnviarCaiActionPerformed
        enviarCai = new jifEnviarCai();
        Desktop.add(enviarCai);
        enviarCai.setPosicao();
        enviarCai.setVisible(Boolean.TRUE);
    }//GEN-LAST:event_imEnviarCaiActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem imConfigurarCertificado;
    private javax.swing.JMenuItem imEnviarCai;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JMenu menuConfiguracao;
    private javax.swing.JMenu menuConfiguracao1;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
