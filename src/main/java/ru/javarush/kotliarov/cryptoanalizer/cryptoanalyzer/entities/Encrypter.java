package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.exceptions.AppException;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 18.03.2022, 12:40.
 */

public class Encrypter implements Action {
    private int key = 0;
    private final String destPath;

    public Encrypter(String destPath) {
        this.destPath = destPath;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getDestPath() {
        return destPath;
    }

    @Override
    public void encrypt(int key, String path, String lang, TextArea textArea) {
        ArrayList<Character> alphabet;
        if (lang.equalsIgnoreCase("english")) {
            alphabet = Constants.LIST_ENG_ALPHABET;
        } else {
            alphabet = Constants.LIST_RU_ALPHABET;
        }

        this.key = key;
        //TextArea Handling here
        if (!textArea.getText().isEmpty()) {
            File file = new File(path);
            try (FileWriter writer = new FileWriter(file)) {
                for (CharSequence paragraph : textArea.getParagraphs()) {
                    writer.write(paragraph.toString() + '\n');
                }
            } catch (IOException e) {
                throw new AppException("Exception at Encrypter.encrypt()", e.getCause());
            }
        }
        performEncryption(path, this.destPath, alphabet);
    }

    @Override
    public void copyTextFromTextArea(TextArea textArea, Label status) {
        if (!textArea.getText().isEmpty()) {
            textArea.selectAll();
            textArea.copy();
        } else {
            status.setText("TextArea is empty!");
        }
    }

    @Override
    public void appendToTextArea(TextArea textArea, String path) {
        textArea.clear();
        try (Scanner scanner = new Scanner(Path.of(path))) {
            for (int i = 0; i < 50; i++) {
                if (scanner.hasNextLine()) {
                    textArea.appendText(scanner.nextLine() + '\n');
                }
            }
        } catch (IOException e) {
            throw new AppException("IOException at Encrypter.appendToTextArea()", e.getCause());
        }
    }

    private void performEncryption(String srcPath, String destPath, ArrayList<Character> alphabet) {
        int offset = this.key % alphabet.size();

        try (LineNumberReader reader = new LineNumberReader(new FileReader(srcPath));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destPath)))) {
            String temp;
            while (reader.ready()) {
                temp = reader.readLine() + '\n';
                for (Character c : temp.toCharArray()) {
                    if (alphabet.contains(c)) {
                        int originalPosition = alphabet.indexOf(c);
                        int newPosition = (originalPosition + offset) % alphabet.size();
                        writer.write(alphabet.get(newPosition));
                    } else if (c == '\n') {
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new AppException("IOException at Encrypter.performEncryption()" + e.getCause());
        }
    }
}
