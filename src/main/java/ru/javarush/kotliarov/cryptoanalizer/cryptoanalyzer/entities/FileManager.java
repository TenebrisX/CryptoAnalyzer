package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.entities;

import javafx.scene.control.Label;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 18.03.2022, 12:54.
 */

public interface FileManager {

    File importFile();

    void saveToResources(Label status, File source, File destination);

    void saveFile(Label status, String path);

    void copyFiles(Path src, Path dest);
}
