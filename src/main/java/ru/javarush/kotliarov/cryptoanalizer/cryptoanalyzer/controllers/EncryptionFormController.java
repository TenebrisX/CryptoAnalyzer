package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 12.03.2022, 16:30.
 */

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.Encrypter;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.TextFileManager;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;

import java.net.URL;
import java.nio.file.Path;
import java.util.*;

public class EncryptionFormController implements Initializable {
    TextFileManager textFileManager = new TextFileManager();
    Encrypter encrypter = new Encrypter(Constants.ENCRYPTED_FILE_PATH);

    @FXML
    private Label statusMessage;

    @FXML
    private Spinner<Integer> keySpinner = new Spinner<>();

    @FXML
    private TextArea srcTextArea;

    @FXML
    private TextArea destTextArea;

    @FXML
    private ChoiceBox<String> langChoice;

    @FXML
    private Label encryptTimeLabel;

    @FXML
    void copyTextButtonOnAction() {
        encrypter.copyTextFromTextArea(destTextArea, statusMessage);
    }

    @FXML
    void encryptButtonOnAction() {
        long startTime = System.nanoTime();
        encrypter.encrypt(keySpinner.getValue(), Constants.IMPORTED_FILE_PATH, langChoice.getValue(), srcTextArea);
        encrypter.appendToTextArea(destTextArea, Constants.ENCRYPTED_FILE_PATH);
        long stopTime = System.nanoTime();
        encryptTimeLabel.setText(Constants.DECIMAL_FORMAT.format(((double) stopTime - startTime) / 1_000_000_000) + " sec, " +
                "character count: " + Path.of(Constants.ENCRYPTED_FILE_PATH).toFile().length());
        encryptTimeLabel.setTextFill(Color.GREEN);
        statusMessage.setText("Encryption complete!");
        statusMessage.setTextFill(Color.GREEN);
    }

    @FXML
    void importButtonOnAction() {
        textFileManager.saveToResources(statusMessage, textFileManager.importFile(), Path.of(Constants.IMPORTED_FILE_PATH).toFile());
        srcTextArea.clear();
        encrypter.appendToTextArea(srcTextArea, Constants.IMPORTED_FILE_PATH);
    }

    @FXML
    void saveFileButtonOnAction() {
        textFileManager.saveFile(statusMessage, Constants.ENCRYPTED_FILE_PATH);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encryptTimeLabel.setText("0");
        statusMessage.setText("Choose the language first!");
        srcTextArea.setWrapText(true);
        destTextArea.setWrapText(true);
        keySpinner.setValueFactory(Constants.VALUE_FACTORY);
        langChoice.setValue("Русский");
        langChoice.setItems(FXCollections.observableArrayList("Русский", "English"));
        langChoice.setTooltip(new Tooltip("Select the text language, default language is Russian."));
    }
}