package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.logic;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.exceptions.AppException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 13.03.2022, 15:14.
 */

public class Logic {
    private static final FileChooser fileChooser = new FileChooser();
    private static final FileChooser.ExtensionFilter extensionFilter =
            new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

    public static void importFileAndPrint(TextArea textArea, Label statusMessage) {
        statusMessage.setText("");
        File file = importFile();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                textArea.appendText(scanner.nextLine() + '\n');
            }

        } catch (FileNotFoundException e) {
            statusMessage.setText("File not found!");
            throw new AppException("File not found: " + file.getName());
        }
    }

    protected static File importFile() {
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file;
        try {
            file = fileChooser.showOpenDialog(new Stage());
        } catch (NullPointerException e) {
            throw new AppException(e.getMessage());
        }
        return file;
    }

    public static void saveFile(TextArea textArea, Label statusMessage) {
        Text text = new Text(textArea.getText());
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println(text.getText());
                statusMessage.setText("File saved!");
                statusMessage.setTextFill(Color.GREEN);
            } catch (FileNotFoundException e) {
                statusMessage.setText("Failed to save file!");
                throw new AppException("Failed to save file!", e.getCause());
            }
        }
    }

    public static void copyText(TextArea textArea, Label statusMessage) {
        if (!textArea.getText().isEmpty()) {
            textArea.selectAll();
            textArea.copy();
            statusMessage.setText("Great! Now paste it anywhere you want!");
            statusMessage.setTextFill(Color.GREEN);
        } else {
            statusMessage.setText("No text found!");
        }
    }

    public static void encrypt(TextArea textArea, Label statusMessage, int key) {
        if (!textArea.getText().isEmpty()) {
            statusMessage.setText("");
            StringBuilder result = encLogic(textArea.getText(), key);
            textArea.clear();
            textArea.appendText(result.toString());
            statusMessage.setText("Complete!");
            statusMessage.setTextFill(Color.GREEN);
        } else {
            statusMessage.setText("No text found!");
        }
    }

    public static void bruteForceDecryption(TextArea textArea, Label statusMessage) {
        if (!textArea.getText().isEmpty()) {
            int key = 0;
            for (int i = 0; i < Constants.RU_FREQUENCIES.size(); i++) {
                if (getMostCommonChar(encLogic(textArea.getText(), i).toString()) == ' ') {
                    key = i;
                    break;
                }
            }
            encrypt(textArea, statusMessage, key);
        } else {
            statusMessage.setText("No text found!");
        }
    }

    private static char getMostCommonChar(String str) {
        int[] count = new int[10000];
        int len = str.length();
        for (int i = 0; i < len; i++) {
            count[str.charAt(i)]++;
        }
        int max = Integer.MIN_VALUE;
        char result = ' ';
        for (int i = 0; i < len; i++) {
            if (max < count[str.charAt(i)]) {
                max = count[str.charAt(i)];
                result = str.charAt(i);
            }
        }
        return result;
    }

    private static StringBuilder encLogic(String text, int key) {
        int offset = key % Constants.RU_FREQUENCIES.size();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Constants.RU_FREQUENCIES.contains(c)) {
                int originalPosition = Constants.RU_FREQUENCIES.indexOf(c);
                int newPosition = (originalPosition + offset) % Constants.RU_FREQUENCIES.size();
                result.append(Constants.RU_FREQUENCIES.get(newPosition));
            }
        }
        return result;
    }
}