package cs.vsu.ru.kapustin;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static <T> List<T> readListFromJTable(JTable table) {
        StringBuilder sb = new StringBuilder();
        TableModel tableModel = table.getModel();

        int numberOfColumns = tableModel.getColumnCount();

        for (int i = 0; i < numberOfColumns; i++) {
            sb.append(tableModel.getValueAt(0, i).toString());
            sb.append(" ");
        }

        return convertToList(sb.toString());
    }

    public static <T> List<T> readListFromFile(String fileName) throws FileNotFoundException {
        Scanner scn = new Scanner(new File(fileName));
        String unsortedList;
        unsortedList = scn.nextLine();

        return convertToList(unsortedList);
    }

    private static <T> List<T> convertToList(String unsortedList) {
        List<T> sortedList = new ArrayList<>();

        if (unsortedList != null) {
            Scanner scn = new Scanner(unsortedList);
            scn.useDelimiter("(\\s|,)+");

            while (scn.hasNext()) {
                sortedList.add((T) scn.next());
            }
        }
        return sortedList;
    }

    public static void writeAnswerToFile(String fileName, int number) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(fileName);

        out.print(number);
        out.close();
    }

    public static <T> String[] convertToArray(List<T> list) {
        String[] arr = new String[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i).toString();
        }

        return arr;
    }
}
