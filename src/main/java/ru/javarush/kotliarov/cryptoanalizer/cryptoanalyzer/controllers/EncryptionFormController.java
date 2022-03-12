package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 12.03.2022, 16:30.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class EncryptionFormController implements Initializable {
    @FXML
    AnchorPane anchorPane;

    @FXML
    private Button copyTextButton;

    @FXML
    private Button encryptButton;

    @FXML
    private Label statusMessage;

    @FXML
    private Button importButton;

    @FXML
    private TextField keyField;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Button saveFileButton;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.statusMessage.setText("");
    }

    @FXML
    void copyTextButtonOnAction(ActionEvent event) {

    }

    @FXML
    void encryptButtonOnAction(ActionEvent event) {

    }

    @FXML
    void importButtonOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

    }

    @FXML
    void saveFileButtonOnAction(ActionEvent event) {

    }



}
