package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global;

import javafx.scene.control.SpinnerValueFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 13.03.2022, 11:45.
 */

public class Constants {

    private static final char[] ANALYSIS_RU_ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'й' ,'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'К', 'Л',
            'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь',
            'Э', 'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private static final char[] ANALYSIS_ENG_ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k',
            'l','m','n','o','p','q','r','s','t','u','v','q','x','q','z', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z','.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static final ArrayList<Character> LIST_RU_ALPHABET = new ArrayList<>() {{
        for (char c : ANALYSIS_RU_ALPHABET) {
            add(c);
        }
    }};

    public static final ArrayList<Character> LIST_ENG_ALPHABET = new ArrayList<>() {{
        for (char c : ANALYSIS_ENG_ALPHABET) {
            add(c);
        }
    }};

    public static final ArrayList<Character> ENG_FREQUENCIES = new ArrayList<>() {{
        add(' '); add('e'); add('t'); add('a'); add('o'); add('n'); add('i'); add('s'); add('h');
        add('r'); add('d'); add('l'); add('u'); add('c'); add('m'); add('f'); add(','); add('g');
        add('p'); add('b'); add('v'); add('.'); add('k'); add('I'); add('A'); add('P'); add('T');
        add('x'); add('N'); add('M'); add('B'); add('R'); add('S'); add('H'); add('z'); add('?');
        add('!'); add('W'); add('j'); add('F'); add('D'); add('C'); add(':'); add('K'); add('O');
        add('V'); add('G'); add('Y'); add('E'); add('L'); add('X'); add('J'); add('U'); add('Z');
        add('Q'); add('q'); add('«'); add('»'); add('"'); add('\'');
    }};

    public static final ArrayList<Character> RU_FREQUENCIES = new ArrayList<>() {{
        add(' '); add('о'); add('е'); add('а'); add('и'); add('н'); add('т'); add('с'); add('л'); add('р');
        add('в'); add('к'); add('д'); add('м'); add(','); add('у'); add('п'); add('я'); add('.'); add('г');
        add('ь'); add('ы'); add('з'); add('б'); add('ч'); add('й'); add('ж'); add('ш'); add('х'); add('ц');
        add('В'); add(':'); add('щ'); add('Н'); add('э'); add('П'); add('С'); add('ф'); add('А'); add('О');
        add('К'); add('М'); add('Р'); add('?'); add('Б'); add('Д'); add('!'); add('И'); add('Т'); add('Г');
        add('«'); add('»'); add('Ч'); add('ъ'); add('Я'); add('Е'); add('Э'); add('Л'); add('У'); add('З');
        add('Ф'); add('Ж'); add('Х'); add('Ш'); add('Ц'); add('\''); add('Ь'); add('Щ'); add('Ю'); add('Ы');
        add('Ъ'); add('Ё'); add('"');
    }};


    public static final SpinnerValueFactory<Integer> VALUE_FACTORY =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, RU_FREQUENCIES.size()-1, 1);

    public static final String ENCRYPTED_FILE_PATH = "src/main/resources/ru/javarush/kotliarov/" +
            "cryptoanalizer/cryptoanalyzer/txt/encrypted.txt";
    public static final String DECRYPTED_FILE_PATH = "src/main/resources/ru/javarush/kotliarov/" +
            "cryptoanalizer/cryptoanalyzer/txt/decrypted.txt";
    public static final String IMPORTED_FILE_PATH = "src/main/resources/ru/javarush/kotliarov/" +
            "cryptoanalizer/cryptoanalyzer/txt/imported.txt";
    public static final String ANALYSIS_IMPORTED_FILE_PATH = "src/main/resources/ru/javarush/kotliarov/" +
            "cryptoanalizer/cryptoanalyzer/txt/importedAnalysis.txt";
    public static final String ANALYSIS_DECRYPTED_FILE_PATH = "src/main/resources/ru/javarush/kotliarov/" +
            "cryptoanalizer/cryptoanalyzer/txt/decryptedAnalysis.txt";
    public static final String ANALYSIS_COMPLETE_FILE_PATH = "src/main/resources/ru/javarush/kotliarov/" +
            "cryptoanalizer/cryptoanalyzer/txt/completeAnalysis.txt";

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.####");
}
