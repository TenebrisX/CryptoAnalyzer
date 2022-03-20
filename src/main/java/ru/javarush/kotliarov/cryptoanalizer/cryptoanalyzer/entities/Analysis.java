package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.exceptions.AppException;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 18.03.2022, 12:40.
 */

public class Analysis extends Encrypter {
    private final TextFileManager textFileManager;
    private Map<Character, Double> frequencyAnalysisMap;
    private ArrayList<Character> alphabet;

    public Analysis(TextFileManager textFileManager) {
        super(ANALYSIS_IMPORTED_FILE_PATH);
        this.textFileManager = textFileManager;
    }

    public long importAndAnalyze(TextArea textAreaA, TextArea textAreaD, Label statusLabel, String lang) {
        textAreaA.clear();
        alphabet = lang.equalsIgnoreCase("english") ? ENG_FREQUENCIES : RU_FREQUENCIES;
        textFileManager.saveToResources(statusLabel, textFileManager.importFile(), Path.of(Constants.ANALYSIS_IMPORTED_FILE_PATH).toFile());
        long startTime = System.nanoTime();
        performAnalysis(alphabet);
        frequencyAnalysisMap = sortMapByValue(frequencyAnalysisMap);
        long stopTime = System.nanoTime();

        for (Map.Entry<Character, Double> entry : frequencyAnalysisMap.entrySet()) {
            textAreaA.appendText("'" + entry.getKey() + "' _____ " + DECIMAL_FORMAT.format(entry.getValue()) + "%" + '\n');
//          textAreaA.appendText("add('" + entry.getKey() + "'); ");
        }

        appendToTextArea(textAreaD, this.getDestPath());

        statusLabel.setText("Analysis Complete!");
        statusLabel.setTextFill(Color.GREEN);

        return stopTime - startTime;
    }

    public void decryptViaAnalysis(TextArea textArea, Label statusLabel) {
        if (textArea.getText().isEmpty()) {
            statusLabel.setText("Import a file first!");
            return;
        }
        textArea.clear();
        int x = 0;

        try (LineNumberReader reader = new LineNumberReader(new FileReader(ANALYSIS_IMPORTED_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ANALYSIS_DECRYPTED_FILE_PATH))) {
            while (reader.ready()) {
                String temp = swapChar(reader.readLine() + '\n');
                writer.write(temp);
                writer.newLine();
                if (x < 50) {
                    textArea.appendText(temp + '\n');
                }
                x++;
            }

        } catch (IOException e) {
            throw new AppException("IOException at Analysis.decryptViaAnalysis()", e.getCause());
        }

        statusLabel.setText("Analysis Complete!");
        statusLabel.setTextFill(Color.GREEN);
    }

    private String swapChar(String text) {
        ArrayList<Character> list = new ArrayList<>(frequencyAnalysisMap.keySet());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (list.contains(text.charAt(i))) {
                int src = list.indexOf(text.charAt(i));
                result.append(alphabet.get(src));
            }
        }
        return result.toString();
    }

    private void performAnalysis(ArrayList<Character> alphabet) {
        initMap(alphabet);

        try (LineNumberReader reader = new LineNumberReader(new FileReader(ANALYSIS_IMPORTED_FILE_PATH))) {
            String temp;
            int count = 0;
            while ((temp = reader.readLine()) != null) {
                for (Character c : alphabet) {
                    for (int i = 0; i < temp.length(); i++) {
                        if (temp.charAt(i) == c) {
                            count++;
                        }
                    }
                    frequencyAnalysisMap.put(c, frequencyAnalysisMap.get(c) + count);
                    count = 0;
                }
            }
        } catch (IOException e) {
            throw new AppException("IOException in Analysis.performAnalysis()" + e.getCause());
        }

        for (Character c : alphabet) {
            frequencyAnalysisMap.put(c, 100 * frequencyAnalysisMap.get(c) / Path.of(ANALYSIS_IMPORTED_FILE_PATH).toFile().length());
        }
    }

    private void initMap(ArrayList<Character> alphabet) {
        frequencyAnalysisMap = new LinkedHashMap<>() {{
            for (Character character : alphabet) {
                put(character, 0.0);
            }
        }};
    }

    // magic
    private Map<Character, Double> sortMapByValue(Map<Character, Double> map) {
        Stream<Map.Entry<Character, Double>> sorted = map.entrySet().stream().sorted(Collections.reverseOrder(
                Map.Entry.comparingByValue()));

        return sorted.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void manualCharacterSwap(String fromChar, String toChar, Label status, TextArea textArea) {
        if (fromChar.length() != 1 || toChar.length() != 1) {
            status.setText("There should be only one character to swap!");
            return;
        }

        char from = fromChar.charAt(0);
        char to = toChar.charAt(0);
        char placeHolder = '$';

        if (!alphabet.contains(from) || !alphabet.contains(to)) {
            status.setText("Cannot find inserted characters, please insert valid characters!");
            return;
        }

        try (LineNumberReader reader = new LineNumberReader(new FileReader(ANALYSIS_DECRYPTED_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(ANALYSIS_COMPLETE_FILE_PATH))) {
            while (reader.ready()) {
                String s = reader.readLine() + '\n';
                char[] tempArray = s.toCharArray();
                for (int i = 0; i < tempArray.length; i++) {
                    if (tempArray[i] == from) {
                        tempArray[i] = placeHolder;

                    }
                }

                for (int i = 0; i < tempArray.length; i++) {
                    if (tempArray[i] == to) {
                        tempArray[i] = from;
                    }
                }

                for (int i = 0; i < tempArray.length; i++) {
                    if (tempArray[i] == placeHolder) {
                        tempArray[i] = to;
                    }
                }

                writer.write(String.valueOf(tempArray));
            }

        } catch (IOException e) {
            throw new AppException("IOException at Analysis.manualCharacterSwap() swapping", e.getCause());
        }

        textFileManager.copyFiles(Path.of(ANALYSIS_COMPLETE_FILE_PATH), Path.of(ANALYSIS_DECRYPTED_FILE_PATH));

        appendToTextArea(textArea, ANALYSIS_COMPLETE_FILE_PATH);

        status.setText(from + " to " + to + " swap complete!");
        status.setTextFill(Color.GREEN);
    }
}