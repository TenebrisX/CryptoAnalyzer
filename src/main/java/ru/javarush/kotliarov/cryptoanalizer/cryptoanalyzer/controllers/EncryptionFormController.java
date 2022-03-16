package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 12.03.2022, 16:30.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.logic.Logic;

import java.net.URL;
import java.util.*;

public class EncryptionFormController implements Initializable {

    @FXML
    private Label statusMessage;

    @FXML
    private Spinner<Integer> keySpinner = new Spinner<>();

    @FXML
    private TextArea textArea;

    @FXML
    void copyTextButtonOnAction() {
        Logic.copyText(textArea, statusMessage);
    }

    @FXML
    void encryptButtonOnAction() {
        Logic.encrypt(textArea, statusMessage, keySpinner.getValue());
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