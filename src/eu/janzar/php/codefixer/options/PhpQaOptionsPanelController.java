/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/template_mypluginOptionsPanelController.java to edit this template
 */
package eu.janzar.php.codefixer.options;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.netbeans.modules.php.api.util.UiUtils;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.SubRegistration(
        location = "org-netbeans-modules-php-project-ui-options-PHPOptionsCategory",
        displayName = "#AdvancedOption_DisplayName_PhpQa",
        keywords = "#AdvancedOption_Keywords_PhpQa",
        keywordsCategory = "org-netbeans-modules-php-project-ui-options-PHPOptionsCategory/PhpQa"
)
@org.openide.util.NbBundle.Messages({"AdvancedOption_DisplayName_PhpQa=PHP codefixer/sniffer/stan", "AdvancedOption_Keywords_PhpQa=codefixer, sniffer, stan"})
public final class PhpQaOptionsPanelController extends OptionsPanelController {

  public static final String ID = "PHP-CODE-Fixer"; // NOI18N
  public static final String OPTIONS_SUBPATH = "PHPCodeFixer"; // NOI18N
  private PhpQaPanel panel;
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  private boolean changed;

  
  public static String getOptionsPath() {
        return UiUtils.FRAMEWORKS_AND_TOOLS_OPTIONS_PATH;
  }
  
  public void update() {
    getPanel().load();
    changed = false;
  }

  public void applyChanges() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        getPanel().store();
        changed = false;
      }
    });
  }

  public void cancel() {
    // need not do anything special, if no changes have been persisted yet
  }

  public boolean isValid() {
    return getPanel().valid();
  }

  public boolean isChanged() {
    return changed;
  }

  public HelpCtx getHelpCtx() {
    return null; // new HelpCtx("...ID") if you have a help set
  }

  public JComponent getComponent(Lookup masterLookup) {
    return getPanel();
  }

  public void addPropertyChangeListener(PropertyChangeListener l) {
    pcs.addPropertyChangeListener(l);
  }

  public void removePropertyChangeListener(PropertyChangeListener l) {
    pcs.removePropertyChangeListener(l);
  }

  private PhpQaPanel getPanel() {
    if (panel == null) {
      panel = new PhpQaPanel(this);
    }
    return panel;
  }

  void changed() {
    if (!changed) {
      changed = true;
      pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
    }
    pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
  }

}
