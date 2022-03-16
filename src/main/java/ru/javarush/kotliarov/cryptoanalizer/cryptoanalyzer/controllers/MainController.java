package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 11.03.2022, 13:23.
 */

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.exceptions.AppException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    public Pane context;

    @FXML
    void analysisOnAction() {
        try {
            setUi("analysisForm");
        } catch (IOException e) {
            throw new AppException(e.getCause());
        }
    }

    @FXML
    void bruteForceOnAction() {
        try {
            setUi("bruteForceForm");
        } catch (IOException e) {
            throw new AppException(e.getCause());
        }
    }

    @FXML
    void encryptionOnAction() {
        try {
            setUi("encryptionForm");
        } catch (IOException e) {
            throw new AppException(e.getCause());
        }
    }

    @FXML
    void keyDecryptionOnAction() {
        try {
            setUi("keyDecryptionForm");
        } catch (IOException e) {
            throw new AppException(e.getCause());
        }
    }

    @FXML
    public void AboutOnAction() {
        try {
            setUi("aboutForm");
        } catch (IOException e) {
            throw new AppException(e.getCause());
        }
    }

    @FXML
    void exitApp() {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        encryptionOnAction();
    }

    private void setUi(String location) throws IOException {
        URL url = new File("src/main/resources/ru/javarush/kotliarov/cryptoanalizer/cryptoanalyzer/fxml/" +
                location + ".fxml").toURI().toURL();
        context.getChildren().add(FXMLLoader.load(url));
    }
}