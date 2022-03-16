package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 12.03.2022, 17:16.
 */

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.logic.AnalysisLogic;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.logic.Logic;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalysisFormController implements Initializable {

    @FXML
    private TextArea analysisTextArea;

    @FXML
    private TextArea sampleTextArea;

    @FXML
    private Label analysisTimeLabel;

    @FXML
    private Label decryptionTimeLabel;

    @FXML
    private TextField charSwapFrom;

    @FXML
    private TextField charSwapTo;

    @FXML
    private Label statusMessage;

    @FXML
    private ChoiceBox<String> langChoice;


    @FXML
    void copyTextButtonOnAction(ActionEvent event) {
        Logic.copyText(sampleTextArea, statusMessage);
    }

    @FXML
    void decryptButtonOnAction(ActionEvent event) {
        AnalysisLogic.decryptViaAnalysis(sampleTextArea, statusMessage, decryptionTimeLabel);
    }

    @FXML
    void importAnalysisFileOnAction() {
        AnalysisLogic.importAndAnalyze(analysisTextArea, sampleTextArea, analysisTimeLabel, statusMessage, langChoice);
    }

    @FXML
    void saveFileButtonOnAction(ActionEvent event) {

    }


    @FXML
    void swapOnAction(ActionEvent event) {
        AnalysisLogic.manualSwap();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusMessage.setText("");
        analysisTimeLabel.setText("0");
        decryptionTimeLabel.setText("0");
        analysisTextArea.setWrapText(true);
        sampleTextArea.setWrapText(true);
        sampleTextArea.setTooltip(new Tooltip("This text area showing a decrypted text sample, max 50 lines of text!"));
        langChoice.setValue("Русский");
        langChoice.setItems(FXCollections.observableArrayList("Русский", "English"));
        langChoice.setTooltip(new Tooltip("Select the text language, default language is Russian."));
    }
}
