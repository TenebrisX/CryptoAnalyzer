package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 12.03.2022, 17:16.
 */


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.BruteForce;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.TextFileManager;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class BruteForceFormController implements Initializable {
    BruteForce bruteForcer = new BruteForce(Constants.DECRYPTED_FILE_PATH);
    TextFileManager textFileManager = new TextFileManager();

    @FXML
    private Label decryptTimeLabel;

    @FXML
    private TextArea destTextArea;

    @FXML
    private ChoiceBox<String> langChoice;

    @FXML
    private TextArea srcTextArea;

    @FXML
    private Label statusMessage;

    @FXML
    void copyTextButtonOnAction() {
        bruteForcer.copyTextFromTextArea(destTextArea, statusMessage);
    }

    @FXML
    void decryptButtonOnAction() {
        long startTime = System.nanoTime();
        bruteForcer.encrypt(0, Constants.ENCRYPTED_FILE_PATH, langChoice.getValue(), srcTextArea);
        bruteForcer.appendToTextArea(destTextArea, Constants.DECRYPTED_FILE_PATH);
        long stopTime = System.nanoTime();
        decryptTimeLabel.setText(Constants.DECIMAL_FORMAT.format(((double) stopTime - startTime) / 1_000_000_000) + " sec, " +
                "character count: " + Path.of(Constants.DECRYPTED_FILE_PATH).toFile().length());
        decryptTimeLabel.setTextFill(Color.GREEN);
        statusMessage.setText("Decryption complete!");
        statusMessage.setTextFill(Color.GREEN);
    }

    @FXML
    void importButtonOnAction() {
        textFileManager.saveToResources(statusMessage, textFileManager.importFile(), Path.of(Constants.ENCRYPTED_FILE_PATH).toFile());
        srcTextArea.clear();
    }

    @FXML
    void saveFileButtonOnAction() {
        textFileManager.saveFile(statusMessage, Constants.DECRYPTED_FILE_PATH);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        decryptTimeLabel.setText("0");
        statusMessage.setText("Choose the language first!");
        srcTextArea.setWrapText(true);
        destTextArea.setWrapText(true);
        langChoice.setValue("Русский");
        langChoice.setItems(FXCollections.observableArrayList("Русский", "English"));
        langChoice.setTooltip(new Tooltip("Select the text language, default language is Russian."));
    }
}
