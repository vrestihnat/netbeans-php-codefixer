package eu.janzar.php.codefixer.ui;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

public final class UiUtils {

    private UiUtils() {
    }

    public static void showWarningMessage(String message) {
        showMessage(message, NotifyDescriptor.WARNING_MESSAGE);
    }

    public static void showErrorMessage(String message) {
        showMessage(message, NotifyDescriptor.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String message) {
        showMessage(message, NotifyDescriptor.INFORMATION_MESSAGE);
    }

    public static void showMessage(String message, int messageType) {
        NotifyDescriptor.Message m = new NotifyDescriptor.Message(message, messageType);
        DialogDisplayer.getDefault().notify(m);
    }
}
