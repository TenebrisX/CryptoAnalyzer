package ru.javarush.kotliarov.cryptoanalizer.cryptoanalyzer.global;

import javafx.scene.control.SpinnerValueFactory;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 *
 * @project: CryptoAnalyzer-main.
 * @author: Boris Kotliarov.
 * Created on 13.03.2022, 11:45.
 */

public class Constants {

    private static final char[] ARRAY_ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и','к',
            'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
            'э', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'К', 'Л', 'М', 'Н', 'О', 'П',
            'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1','2','3','4','5',
            '6','7','8','9','0','.', ',', '«', '»', '\'', '"', ':', '!', '?', ' ', '—', '-', '*', '/',
            '\\', '+', '=', ';', '(',')','[',']','{','}','~','`','#','$','%','^','&','№','_', '|'};

    public static final ArrayList<Character> LIST_ALPHABET = new ArrayList<>() {{
        for (char c : ARRAY_ALPHABET) {
            add(c);
        }
    }};


    public static final SpinnerValueFactory<Integer> VALUE_FACTORY =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(0, ARRAY_ALPHABET.length-1, 1);

}