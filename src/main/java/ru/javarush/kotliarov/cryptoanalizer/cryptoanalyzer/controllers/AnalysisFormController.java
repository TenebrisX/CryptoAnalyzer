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
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.Analysis;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities.TextFileManager;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global.Constants;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class AnalysisFormController implements Initializable {
    TextFileManager textFileManager = new TextFileManager();
    Analysis analysis = new Analysis(textFileManager);

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
    void decryptButtonOnAction() {
        long startTime = System.nanoTime();
        analysis.decryptViaAnalysis(sampleTextArea, statusMessage);
        long stopTime = System.nanoTime();
        decryptionTimeLabel.setText(Constants.DECIMAL_FORMAT.format(((double) stopTime - startTime) / 1_000_000_000) + " sec. " +
                "character count: " + Path.of(Constants.ANALYSIS_DECRYPTED_FILE_PATH).toFile().length());
        decryptionTimeLabel.setTextFill(Color.GREEN);
    }

    @FXML
    void importAnalysisFileOnAction() {
        long time = analysis.importAndAnalyze(analysisTextArea,sampleTextArea,statusMessage, langChoice.getValue());
        analysisTimeLabel.setText(Constants.DECIMAL_FORMAT.format(((double) time) / 1_000_000_000) + " sec.");
        analysisTimeLabel.setTextFill(Color.GREEN);
    }

    @FXML
    void saveFileButtonOnAction() {
        textFileManager.saveFile(statusMessage, Constants.ANALYSIS_DECRYPTED_FILE_PATH);
    }


    @FXML
    void swapOnAction() {
        analysis.manualCharacterSwap(charSwapFrom.getText(), charSwapTo.getText(), statusMessage, sampleTextArea);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusMessage.setText("Choose the language first!");
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
