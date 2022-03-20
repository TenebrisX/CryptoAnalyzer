package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 18.03.2022, 12:39.
 */

public interface Action {

    void encrypt(int key, String path, String lang, TextArea textArea);

    void appendToTextArea(TextArea textArea, String path);

    void copyTextFromTextArea(TextArea textArea, Label status);
}
