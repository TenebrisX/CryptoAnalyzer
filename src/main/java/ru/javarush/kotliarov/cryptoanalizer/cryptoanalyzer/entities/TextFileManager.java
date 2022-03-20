package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.exceptions.AppException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 18.03.2022, 13:11.
 */

public class TextFileManager implements FileManager {
    private final FileChooser fileChooser = new FileChooser();
    private final FileChooser.ExtensionFilter extensionFilter =
            new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

    public TextFileManager() {
    }

    @Override
    public File importFile() {
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file;
        try {
            file = fileChooser.showOpenDialog(new Stage());
        } catch (NullPointerException e) {
            throw new AppException("NullPointerException at TextFileManager.importFile()", e.getCause());
        }
        return file;
    }

    @Override
    public void saveToResources(Label status, File source, File destination) {
        try {
        if (Files.exists(Path.of(destination.toURI()))) {
            Files.delete(Path.of(destination.toURI()));
        }
        Path src = Path.of(source.toURI());
        Path dest = Path.of(destination.toURI());
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
            status.setText("Import complete!");
            status.setTextFill(Color.GREEN);
        } catch (IOException e) {
            status.setText("Failed to import the file!");
            throw new AppException("IOException at TextFileManager.saveToResources()", e.getCause());
        }
    }

    @Override
    public void saveFile(Label status, String path) {
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().add(extensionFilter);
        fileChooser.setInitialFileName("file");
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file);
            LineNumberReader reader = new LineNumberReader(new FileReader(path))) {
                while (reader.ready()) {
                    writer.write(reader.readLine() + '\n');
                }
                status.setText("File saved!");
                status.setTextFill(Color.GREEN);
            } catch (IOException e) {
                status.setText("Failed to save file!");
                throw new AppException("IOException at TextFileManager.saveFile()", e.getCause());
            }
        }
    }

    @Override
    public void copyFiles(Path src, Path dest) {
        try {
            Files.copy(src,dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new AppException("IOException at TextFileManager.copyFiles()", e.getCause());
        }
    }
}
