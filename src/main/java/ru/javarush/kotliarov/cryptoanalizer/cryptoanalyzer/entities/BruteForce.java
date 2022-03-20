package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities;

import javafx.scene.control.TextArea;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.exceptions.AppException;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 18.03.2022, 12:40.
 */

public class BruteForce extends Encrypter {

    public BruteForce(String destPath) {
        super(destPath);
    }

    @Override
    public void encrypt(int key, String path, String lang, TextArea textArea) {
        StringBuilder stringBuilder;
        ArrayList<Character> alphabet;
        if (lang.equalsIgnoreCase("english")) {
            alphabet = Constants.LIST_ENG_ALPHABET;
        } else {
            alphabet = Constants.LIST_RU_ALPHABET;
        }

        stringBuilder = getUpToFiftyLines();

        for (int i = 0; i < alphabet.size(); i++) {
            if (getMostCommonChar(encLogic(stringBuilder.toString(), i, alphabet).toString()) == ' ') {
                key = i;
            }
        }

        super.encrypt(key, path, lang, textArea);
    }

    private char getMostCommonChar(String str) {
        int[] count = new int[10000];
        int len = str.length();
        for (int i = 0; i < len; i++) {
            count[str.charAt(i)]++;
        }
        int max = Integer.MIN_VALUE;
        char result = '$';
        for (int i = 0; i < len; i++) {
            if (max < count[str.charAt(i)]) {
                max = count[str.charAt(i)];
                result = str.charAt(i);
            }
        }
        return result;
    }

    private StringBuilder encLogic(String text, int key, ArrayList<Character> alphabet) {
        int offset = key % alphabet.size();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (alphabet.contains(c)) {
                int originalPosition = alphabet.indexOf(c);
                int newPosition = (originalPosition + offset) % alphabet.size();
                result.append(alphabet.get(newPosition));
            }
        }
        return result;
    }

    private StringBuilder getUpToFiftyLines() {
        StringBuilder stringBuilder = new StringBuilder();
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(Constants.ENCRYPTED_FILE_PATH))) {
            for (int i = 0; i < 50; i++) {
                if (lineNumberReader.ready()) {
                    stringBuilder.append(lineNumberReader.readLine());
                }
            }
        } catch (IOException e) {
            throw new AppException("Exception in BruteForce.encrypt()", e.getCause());
        }

        return stringBuilder;
    }
}
