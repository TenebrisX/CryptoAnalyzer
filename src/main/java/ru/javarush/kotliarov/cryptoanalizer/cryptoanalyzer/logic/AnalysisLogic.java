package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.logic;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.exceptions.AppException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 14.03.2022, 22:49.
 */

public class AnalysisLogic {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.####");
    private static long fileLength = 0;
    private static String chooser;

    public static Map<Character, Double> importAnalysisMap;

    public static void importAndAnalyze(TextArea textAreaA, TextArea textAreaD, Label timeLabel, Label statusLabel,
                                        ChoiceBox<String> choiceBox) {
        textAreaA.clear();
        File file = Logic.importFile();
        fileLength = file.length();
        saveFileToResources(file);

        long startTime = System.nanoTime();
        performAnalysis(importAnalysisMap, file, choiceBox.getValue());
        importAnalysisMap = sortMapByValue(importAnalysisMap);

        //magic
//        importAnalysisMap.entrySet().removeIf(characterDoubleEntry -> characterDoubleEntry.getValue() < 0.000);

        for (Map.Entry<Character, Double> entry : importAnalysisMap.entrySet()) {
            textAreaA.appendText("'" + entry.getKey() + "' _____ " + decimalFormat.format(entry.getValue()) + "%" + '\n');
//          textAreaA.appendText("add('" + entry.getKey() + "'); ");
        }

        long stopTime = System.nanoTime();

        timeLabel.setText(decimalFormat.format(((double) stopTime - startTime) / 1_000_000_000) +
                " sec; character count: " + fileLength);
        timeLabel.setTextFill(Color.GREEN);

        writeToDecTextArea(Paths.get(ENCRYPTED_FILE_PATH), textAreaD);

        statusLabel.setText("Analysis Complete!");
        statusLabel.setTextFill(Color.GREEN);
    }

    public static void decryptViaAnalysis(TextArea textArea, Label statusLabel, Label timeLabel) {
        if (textArea.getText().isEmpty()) {
            statusLabel.setText("Import a file first!");
            return;
        }

        textArea.clear();

        int x = 0;


        long startTime = System.nanoTime();

        try (BufferedReader reader = new BufferedReader(new FileReader(ENCRYPTED_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(DECRYPTED_FILE_PATH))) {
            while (reader.ready()) {
                String temp = swapChar(reader.readLine());
                writer.write(temp);
                if (x < 50) {
                    textArea.appendText(temp + '\n');
                }
                x++;
            }

        } catch (IOException e) {
            throw new AppException("Error in decryptViaAnalysis", e.getCause());
        }
        long stopTime = System.nanoTime();
        timeLabel.setText(decimalFormat.format(((double) stopTime - startTime) / 1_000_000_000) +
                " sec; character count: " + fileLength);
        timeLabel.setTextFill(Color.GREEN);

        statusLabel.setText("Decryption Complete!");
        statusLabel.setTextFill(Color.GREEN);
    }

    private static String swapChar(String text) {
        ArrayList<Character> dict = chooser.equals("Русский") ? RU_FREQUENCIES : ENG_FREQUENCIES;
        ArrayList<Character> list = new ArrayList<>(importAnalysisMap.keySet());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (list.contains(text.charAt(i))) {
                int src = list.indexOf(text.charAt(i));
                result.append(dict.get(src));
            }
        }
        return result.toString();
    }

    public static void manualSwap() {

    }

    private static void performAnalysis(Map<Character, Double> map, File file, String lang) {
        ArrayList<Character> alphabet;

        if (lang.equals("English")) {
            alphabet = LIST_ENG_ALPHABET;
            map = new LinkedHashMap<>() {{
                for (Character c : LIST_ENG_ALPHABET) {
                    put(c, 0.0);
                }
            }};
        } else {
            lang = "Русский";
            alphabet = LIST_RU_ALPHABET;
            map = new LinkedHashMap<>() {{
                for (Character c : LIST_RU_ALPHABET) {
                    put(c, 0.0);
                }
            }};
        }

        chooser = lang;
        importAnalysisMap = map;

        try (LineNumberReader reader = new LineNumberReader(new FileReader(file))) {
            String temp;
            int count = 0;
            while ((temp = reader.readLine()) != null) {
                for (Character c : alphabet) {
                    for (int i = 0; i < temp.length(); i++) {
                        if (temp.charAt(i) == c) {
                            count++;
                        }
                    }
                    map.put(c, map.get(c) + count);
                    count = 0;
                }
            }
        } catch (IOException e) {
            throw new AppException("File not found error in performAnalysis" + e.getCause());
        }

        for (Character c : alphabet) {
            map.put(c, 100 * map.get(c) / file.length());
        }
    }

    private static void saveFileToResources(File file) {
        Path src = Paths.get(file.toURI());
        Path dest = Paths.get(ENCRYPTED_FILE_PATH);
        try {
            if (Files.exists(dest)) {
                Files.delete(dest);
            }
            Files.copy(src, dest);
        } catch (IOException e) {
            throw new AppException("Error in saveFileToResources" + e.getMessage(), e.getCause());
        }
    }

    // magic
    private static Map<Character, Double> sortMapByValue(Map<Character, Double> map) {
        Stream<Map.Entry<Character, Double>> sorted = map.entrySet().stream().sorted(Collections.reverseOrder(
                Map.Entry.comparingByValue()));

        return sorted.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));
    }

    private static void writeToDecTextArea(Path file, TextArea textArea) {
        textArea.clear();
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < 50; i++) {
                if (scanner.hasNextLine()) {
                    textArea.appendText(scanner.nextLine() + '\n');
                }
            }
        } catch (IOException e) {
            throw new AppException("Error in writeToDecTextArea", e.getCause());
        }
    }
}