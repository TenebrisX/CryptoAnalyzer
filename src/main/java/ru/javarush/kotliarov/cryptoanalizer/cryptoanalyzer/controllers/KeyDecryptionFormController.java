package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 12.03.2022, 17:16.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.logic.Logic;

import java.net.URL;
import java.util.ResourceBundle;

public class KeyDecryptionFormController implements Initializable {
    @FXML
    private Spinner<Integer> keySpinner;

    @FXML
    private Label statusMessage;

    @FXML
    private TextArea textArea;

    @FXML
    void copyTextButtonOnAction() {
        Logic.copyText(textArea, statusMessage);
    }

    @FXML
    void decryptButtonOnAction() {
        Logic.encrypt(textArea, statusMessage, Constants.RU_FREQUENCIES.size() - keySpinner.getValue());
    }

    @FXML
    void importButtonOnAction() {
        Logic.importFileAndPrint(textArea, statusMessage);
    }

    @FXML
    void saveFileButtonOnAction() {
        Logic.saveFile(textArea, statusMessage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.statusMessage.setText("");
        textArea.setWrapText(true);
        keySpinner.setValueFactory(Constants.VALUE_FACTORY);
    }
}