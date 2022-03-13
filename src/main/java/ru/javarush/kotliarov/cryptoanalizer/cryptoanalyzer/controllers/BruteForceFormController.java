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
import javafx.scene.control.TextArea;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.operations.Operations;

import java.net.URL;
import java.util.ResourceBundle;

public class BruteForceFormController implements Initializable {
    @FXML
    private Label statusMessage;

    @FXML
    private TextArea textArea;

    @FXML
    void copyTextButtonOnAction() {
        Operations.copyText(textArea, statusMessage);
    }

    @FXML
    void decryptButtonOnAction() {
        Operations.bruteForceDecryption(textArea, statusMessage);
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
    }
}
