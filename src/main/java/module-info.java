module ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer to javafx.fxml;
    exports ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer;
    exports ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers;
    opens ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.controllers to javafx.fxml;
}