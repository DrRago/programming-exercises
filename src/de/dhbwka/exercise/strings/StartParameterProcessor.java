package de.dhbwka.exercise.strings;

import de.dhbwka.exercise.utility.Sort;

import java.util.Arrays;

/**
 * @author Leonhard Gahr
 */
public class StartParameterProcessor {
    StartParameterProcessor(String... strings) {
        Sort.bogoBogoSort(strings);

        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("ü", "ue")
                    .replaceAll("ä", "ae")
                    .replaceAll("ö", "oe")
                    .replaceAll("ß", "ss");
            strings[i] = strings[i].replaceAll("Ü(?=[a-z ])", "Ue")
                    .replaceAll("Ö(?=[a-z ])", "Oe")
                    .replaceAll("Ä(?=[a-z ])", "Ae")
                    .replaceAll("ẞ(?=[a-z ])", "Ss");
            strings[i] = strings[i].replaceAll("Ä", "AE")
                    .replaceAll("Ö", "OE")
                    .replaceAll("Ü", "UE")
                    .replaceAll("ẞ", "SS");
        }
        System.out.println(Arrays.toString(strings));
    }

    public static void main(String[] args) {
        new StartParameterProcessor(args);
    }
}
