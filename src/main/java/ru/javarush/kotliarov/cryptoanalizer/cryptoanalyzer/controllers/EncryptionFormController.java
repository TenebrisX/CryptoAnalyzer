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
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.operations.Operations;

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
        Operations.copyText(textArea, statusMessage);
    }

    @FXML
    void encryptButtonOnAction() {
        Operations.encrypt(textArea, statusMessage, keySpinner.getValue());
    }

    @FXML
    void importButtonOnAction() {
        Operations.importFile(textArea, statusMessage);
    }

    @FXML
    void saveFileButtonOnAction() {
        Operations.saveFile(textArea, statusMessage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.statusMessage.setText("");
        textArea.setWrapText(true);
        keySpinner.setValueFactory(Constants.VALUE_FACTORY);
    }
}