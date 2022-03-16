package cs.vsu.ru.kapustin;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

public class FrameMain extends JFrame {
    private JTable inputTable;
    private JButton loadFromFileButton;
    private JButton enterButton;
    private JButton saveToFileButton;
    private JPanel panelMain;
    private JTextField numberEntryField;
    private JTextField responseField;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public <T> FrameMain() {
        this.setTitle("Task 2");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600, 250, 0, 0);
        this.setResizable(false);
        this.pack();

        JTableUtils.initJTableForArray(inputTable, 30, false, true, false, true, 25, 15);
        inputTable.setRowHeight(30);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        loadFromFileButton.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    List<T> list = Utils.readListFromFile(fileChooserOpen.getSelectedFile().getPath());
                    JTableUtils.writeArrayToJTable(inputTable, Utils.convertToArray(list));
                }
            } catch (Exception e) {
                ErrorMessages.showErrorMessage(0);
            }
        });

        enterButton.addActionListener(actionEvent -> {
            try {
                List<T> data = Utils.readListFromJTable(inputTable);
                LinkedList<T> list = new LinkedList<>(data);

                int numbOfRemovedElem = Integer.parseInt(numberEntryField.getText());
                int serialNumber = list.findLast(numbOfRemovedElem);

                responseField.setText(String.valueOf(serialNumber));
            } catch (Exception e) {
                ErrorMessages.showErrorMessage(1);
            }
        });

        saveToFileButton.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int serialNumber = Integer.parseInt(responseField.getText());

                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    Utils.writeAnswerToFile(file, serialNumber);
                }
            } catch (Exception e) {
                ErrorMessages.showErrorMessage(2);
            }
        });
    }
}
