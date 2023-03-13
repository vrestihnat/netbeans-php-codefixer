/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/template_mypluginPanel.java to edit this template
 */
package eu.janzar.php.codefixer.options;

import java.io.File;
import java.util.logging.Logger;
import javafx.beans.binding.SetExpression;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;

final class PhpQaPanel extends javax.swing.JPanel {

  private final PhpQaOptionsPanelController controller;
  private static final String PHPCODEFIXER_LAST_FOLDER_SUFFIX = ".phpcodefixer"; // NOI18N
  private static final String PHPCODESNIFFER_LAST_FOLDER_SUFFIX = ".phpcodesniffer"; // NOI18N
  private static final String PHPSTAN_LAST_FOLDER_SUFFIX = ".phpstan";
  private static final Logger LOGGER = Logger.getLogger(PhpQaPanel.class.getName());

  PhpQaPanel(PhpQaOptionsPanelController controller) {
    this.controller = controller;
    initComponents();
    // TODO listen to changes in form fields and call controller.changed()
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    TFPathFixer = new javax.swing.JTextPane();
    BBtPhpcbf = new javax.swing.JButton();
    BBtPhpcs = new javax.swing.JButton();
    jScrollPane2 = new javax.swing.JScrollPane();
    TFPathSniffer = new javax.swing.JTextPane();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    TFExtensions = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    TFEncoding = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    CBVerbose = new javax.swing.JCheckBox();
    TFCustom = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jScrollPane3 = new javax.swing.JScrollPane();
    TFPathStan = new javax.swing.JTextPane();
    BBtPhpstan = new javax.swing.JButton();
    jLabel9 = new javax.swing.JLabel();
    TFConfiguration = new javax.swing.JTextField();
    jLabel10 = new javax.swing.JLabel();
    TFAutoload = new javax.swing.JTextField();
    SLevel = new javax.swing.JSpinner();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    TFCustomStan = new javax.swing.JTextField();

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel1.text")); // NOI18N
    jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel1.toolTipText")); // NOI18N

    TFPathFixer.setName(""); // NOI18N
    jScrollPane1.setViewportView(TFPathFixer);

    org.openide.awt.Mnemonics.setLocalizedText(BBtPhpcbf, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.text")); // NOI18N
    BBtPhpcbf.setLabel(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.BBtPhpcbf.label")); // NOI18N
    BBtPhpcbf.setName(""); // NOI18N
    BBtPhpcbf.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BBtPhpcbfActionPerformed(evt);
      }
    });

    org.openide.awt.Mnemonics.setLocalizedText(BBtPhpcs, "Browse");
    BBtPhpcs.setName(""); // NOI18N
    BBtPhpcs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BBtPhpcsActionPerformed(evt);
      }
    });

    TFPathSniffer.setName(""); // NOI18N
    jScrollPane2.setViewportView(TFPathSniffer);

    org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel2.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel3.text")); // NOI18N

    TFExtensions.setText("php,phtml,phpt");
    TFExtensions.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.toolTipText")); // NOI18N
    TFExtensions.setName(""); // NOI18N
    TFExtensions.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        TFExtensionsActionPerformed(evt);
      }
    });

    org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel4.text")); // NOI18N

    TFEncoding.setText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFEncoding.text")); // NOI18N
    TFEncoding.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFEncoding.toolTipText")); // NOI18N
    TFEncoding.setName(""); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel5.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(CBVerbose, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.CbVerbose.text")); // NOI18N
    CBVerbose.setLabel(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.CBVerbose.label")); // NOI18N
    CBVerbose.setName(""); // NOI18N

    TFCustom.setText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFCustom.text")); // NOI18N
    TFCustom.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFCustom.toolTipText")); // NOI18N
    TFCustom.setName(""); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel6.text")); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(22, 22, 22)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel2)
              .addComponent(jLabel3)
              .addComponent(jLabel4))
            .addGap(6, 6, 6)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BBtPhpcs))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BBtPhpcbf))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(TFExtensions, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(TFEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(CBVerbose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addComponent(TFCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(BBtPhpcbf)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel2))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(BBtPhpcs)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel3))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(TFExtensions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4)
          .addComponent(TFEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(CBVerbose)
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(TFCustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel6)))
        .addContainerGap(135, Short.MAX_VALUE))
    );

    BBtPhpcs.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.BBtPhpcs.AccessibleContext.accessibleName")); // NOI18N

    jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel7.text")); // NOI18N
    jLabel7.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel7.toolTipText")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel8.text")); // NOI18N

    TFPathStan.setName(""); // NOI18N
    jScrollPane3.setViewportView(TFPathStan);

    org.openide.awt.Mnemonics.setLocalizedText(BBtPhpstan, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.text")); // NOI18N
    BBtPhpstan.setName(""); // NOI18N
    BBtPhpstan.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BBtPhpstanActionPerformed(evt);
      }
    });

    org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel9.text")); // NOI18N

    TFConfiguration.setText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFConfiguration.text")); // NOI18N
    TFConfiguration.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFConfiguration.toolTipText")); // NOI18N
    TFConfiguration.setName(""); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel10.text")); // NOI18N

    TFAutoload.setText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFAutoload.text")); // NOI18N
    TFAutoload.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFAutoload.toolTipText")); // NOI18N
    TFAutoload.setName(""); // NOI18N

    SLevel.setMinimumSize(new java.awt.Dimension(18, 18));
    SLevel.setName(""); // NOI18N
    SLevel.setValue(5);

    org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel11.text")); // NOI18N

    org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.jLabel12.text")); // NOI18N

    TFCustomStan.setText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFCustomStan.text")); // NOI18N
    TFCustomStan.setToolTipText(org.openide.util.NbBundle.getMessage(PhpQaPanel.class, "PhpQaPanel.TFCustomStan.toolTipText")); // NOI18N
    TFCustomStan.setName(""); // NOI18N

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGap(21, 21, 21)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel7)
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BBtPhpstan))
              .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(jLabel9)
                  .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(TFConfiguration)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel10))
                  .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(SLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12)))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(TFCustomStan)
                  .addComponent(TFAutoload, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))))))
        .addContainerGap(35, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel7)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(BBtPhpstan)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel8))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(TFConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel9)
          .addComponent(TFAutoload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel10))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(SLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel11)
          .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(TFCustomStan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel12)))
        .addContainerGap(151, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(23, 23, 23)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(24, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(29, 29, 29)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(7, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents
  @NbBundle.Messages("LBL_SelectPhpCodefixer=Select PHP Codefixer")
  private void BBtPhpcbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBtPhpcbfActionPerformed
    File source = new FileChooserBuilder(PhpQaPanel.class.getName() + PHPCODEFIXER_LAST_FOLDER_SUFFIX)
            .setTitle(Bundle.LBL_SelectPhpCodefixer())
            .setFilesOnly(true)
            .showOpenDialog();
    if (source != null) {
      source = FileUtil.normalizeFile(source);
      setPathFixer(source.getAbsolutePath());
    }
  }//GEN-LAST:event_BBtPhpcbfActionPerformed
  @NbBundle.Messages("LBL_SelectPhpSniffer=Select PHP CodeSniffer")
  private void BBtPhpcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBtPhpcsActionPerformed
    File source = new FileChooserBuilder(PhpQaPanel.class.getName() + PHPCODESNIFFER_LAST_FOLDER_SUFFIX)
            .setTitle(Bundle.LBL_SelectPhpSniffer())
            .setFilesOnly(true)
            .showOpenDialog();
    if (source != null) {
      source = FileUtil.normalizeFile(source);
      setPathSniffer(source.getAbsolutePath());
    }
  }//GEN-LAST:event_BBtPhpcsActionPerformed
  @NbBundle.Messages("LBL_SelectPhpStan=Select PHP CodeSniffer")
  private void BBtPhpstanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBtPhpstanActionPerformed
    File source = new FileChooserBuilder(PhpQaPanel.class.getName() + PHPSTAN_LAST_FOLDER_SUFFIX)
            .setTitle(Bundle.LBL_SelectPhpSniffer())
            .setFilesOnly(true)
            .showOpenDialog();
    if (source != null) {
      source = FileUtil.normalizeFile(source);
      setPathStan(source.getAbsolutePath());
    }
  }//GEN-LAST:event_BBtPhpstanActionPerformed

  private void TFExtensionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFExtensionsActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_TFExtensionsActionPerformed

  private PhpCodefixerOptions getOptions() {
    return PhpCodefixerOptions.getInstance();
  }

  void load() {
    PhpCodefixerOptions options = getOptions();
    setPathFixer(options.getPhpCodeFixerPath());
    setPathSniffer(options.getPhpCodeSnifferPath());
    setPathSniffer(options.getPhpStanPath());
    SetExtensions(options.getExtensions());
    SetEncoding(options.getEncoding());
    SetStanConfiguration(options.getStanConfiguration());
    SetStanAutoload(options.getStanAutoload());
    SetStanCustom(options.getStanCustom());
    SetFixerCustom(options.getFixerCustom());
    SetVerbose(options.getVerbose());
    SetLevel(options.getLevel());

  }

  void store() {
    // TODO store modified settings
    // Example:
    // Preferences.userNodeForPackage(PhpQaPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
    // or for org.openide.util with API spec. version >= 7.4:
    // NbPreferences.forModule(PhpQaPanel.class).putBoolean("someFlag", someCheckBox.isSelected());
    // or:
    // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
    PhpCodefixerOptions options = getOptions();
    options.setPhpCodeFixerPath(getPhpCodeFixerPath());
    options.setPhpCodeSnifferPath(getPhpCodeSnifferPath());
    options.setPhpStanPath(getPhpStanPath());
    options.setExtensions(getExtensions());
    options.setEncoding(getEncoding());
    options.setStanConfiguration(getStanConfiguration());
    options.setStanAutoload(getStanAutoload());
    options.setStanCustom(getStanCustom());
    options.setFixerCustom(getFixerCustom());
    options.setVerbose(getVerbose());
    options.setLevel(getLevel());

  }

  boolean valid() {
    // TODO check whether form is consistent and complete
    return true;
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton BBtPhpcbf;
  private javax.swing.JButton BBtPhpcs;
  private javax.swing.JButton BBtPhpstan;
  private javax.swing.JCheckBox CBVerbose;
  private javax.swing.JSpinner SLevel;
  private javax.swing.JTextField TFAutoload;
  private javax.swing.JTextField TFConfiguration;
  private javax.swing.JTextField TFCustom;
  private javax.swing.JTextField TFCustomStan;
  private javax.swing.JTextField TFEncoding;
  private javax.swing.JTextField TFExtensions;
  private javax.swing.JTextPane TFPathFixer;
  private javax.swing.JTextPane TFPathSniffer;
  private javax.swing.JTextPane TFPathStan;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  // End of variables declaration//GEN-END:variables

  private void setPathFixer(String absolutePath) {
    TFPathFixer.setText(absolutePath);
  }

  private void setPathSniffer(String absolutePath) {
    TFPathSniffer.setText(absolutePath);
  }

  private void setPathStan(String absolutePath) {
    TFPathStan.setText(absolutePath);
  }

  private void SetExtensions(String extensions) {
    TFExtensions.setText(extensions);
  }

  private void SetEncoding(String encoding) {
    TFEncoding.setText(encoding);
  }

  private void SetStanConfiguration(String stanConfiguration) {
    TFConfiguration.setText(stanConfiguration);
  }

  private void SetStanAutoload(String stanAutoload) {
    TFAutoload.setText(stanAutoload);
  }

  private void SetStanCustom(String stanCustom) {
    TFCustomStan.setText(stanCustom);
  }

  private void SetFixerCustom(String fixerCustom) {
    TFCustom.setText(fixerCustom);
  }

  private void SetVerbose(Boolean verbose) {
    CBVerbose.setSelected(verbose);
  }

  private void SetLevel(int level) {
    SLevel.setValue(level);
  }

  private String getPhpCodeFixerPath() {
    return TFPathFixer.getText();
  }

  private String getPhpCodeSnifferPath() {
    return TFPathSniffer.getText();
  }

  private String getPhpStanPath() {
    return TFPathStan.getText();
  }

  private String getExtensions() {
    return TFExtensions.getText();
  }

  private String getEncoding() {
    return TFEncoding.getText();
  }

  private String getStanConfiguration() {
    return TFConfiguration.getText();
  }

  private String getStanAutoload() {
    return TFAutoload.getText();
  }

  private String getStanCustom() {
     return TFCustomStan.getText();
  }

  private String getFixerCustom() {
    return TFCustom.getText();
  }

  private Boolean getVerbose() {
    return CBVerbose.isSelected();
  }

  private int getLevel() {
    return (int) SLevel.getValue();
  }
}
