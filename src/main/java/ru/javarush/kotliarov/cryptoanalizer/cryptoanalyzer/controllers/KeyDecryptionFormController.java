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
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.Decrypter;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.TextFileManager;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class KeyDecryptionFormController implements Initializable {
    TextFileManager textFileManager = new TextFileManager();
    Decrypter decrypter = new Decrypter(Constants.DECRYPTED_FILE_PATH);

    @FXML
    private Label decryptTimeLabel;

    @FXML
    private TextArea destTextArea;

    @FXML
    private Spinner<Integer> keySpinner = new Spinner<>();

    @FXML
    private ChoiceBox<String> langChoice;

    @FXML
    private TextArea srcTextArea;

    @FXML
    private Label statusMessage;

    @FXML
    void copyTextButtonOnAction() {
        decrypter.copyTextFromTextArea(destTextArea, statusMessage);
    }

    @FXML
    void decryptButtonOnAction() {
        long startTime = System.nanoTime();
        if (langChoice.getValue().equalsIgnoreCase("english")) {
            decrypter.encrypt(Constants.LIST_ENG_ALPHABET.size() - keySpinner.getValue(),Constants.ENCRYPTED_FILE_PATH,
                    langChoice.getValue(), srcTextArea);
        } else {
            decrypter.encrypt(Constants.LIST_RU_ALPHABET.size() - keySpinner.getValue(),Constants.ENCRYPTED_FILE_PATH,
                    langChoice.getValue(), srcTextArea);
        }

        decrypter.appendToTextArea(destTextArea, Constants.DECRYPTED_FILE_PATH);

        long stopTime = System.nanoTime();
        decryptTimeLabel.setText(Constants.DECIMAL_FORMAT.format(((double) stopTime - startTime) / 1_000_000_000) + " sec, " +
                "character count: " + Path.of(Constants.DECRYPTED_FILE_PATH).toFile().length());
        decryptTimeLabel.setTextFill(Color.GREEN);
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
        keySpinner.setValueFactory(Constants.VALUE_FACTORY);
        langChoice.setValue("Русский");
        langChoice.setItems(FXCollections.observableArrayList("Русский", "English"));
        langChoice.setTooltip(new Tooltip("Select the text language, default language is Russian."));
    }
}