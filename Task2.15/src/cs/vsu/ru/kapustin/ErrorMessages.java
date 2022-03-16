package cs.vsu.ru.kapustin;

import javax.swing.*;

public class ErrorMessages {
    public static void showErrorMessage(int errorCode) {
        String message;
        if (errorCode == 0) {
            message = "Unable to load data from this file.";
        } else if (errorCode == 1) {
            message = "Calculation cannot be performed because the data was entered incorrectly.";
        } else {
            message = "Unable to save data to this file.";
        }

        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
