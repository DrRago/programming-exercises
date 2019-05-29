package de.dhbwka.exercise.classes.periodic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Leonhard Gahr
 */
public class ElementHelper {
    // the ordinal ranges of the metal elements
    final static int[][] METAL_RANGES = {{3, 5}, {11, 13}, {19, 31}, {37, 50}, {55, 83}, {87, 116}};

    private int[] getMetalIndices() {
        Arrays.stream(METAL_RANGES).forEach(System.out::println);
        return null;
    }

    public static List<Element> getAllElements() throws FileNotFoundException {
        String path = ElementHelper.class.getClassLoader().getResource("periodic_table.json").getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Gson gson = new GsonBuilder().create();
        ArrayList readElements = gson.fromJson(bufferedReader, ArrayList.class);
        List<Element> elements = new ArrayList<>();

        for (Object readElement : readElements) {
            LinkedTreeMap element = (LinkedTreeMap) readElement;

            String name = element.get("name").toString();
            String symbol = element.get("symbol").toString();
            int ordinal = (int) element.get("number");
            char shell = (char) ('J' + ((int[]) element.get("shells")).length);
            Phase phase = Phase.valueOf(element.get("phase").toString().toUpperCase());
            boolean mainGroup;
            switch ((int) element.get("xpos")) {
                case 1:
                case 2:
                case 13:
                case 18:
                    mainGroup = true;
                    break;
                default:
                    mainGroup = false;
            }

            Element curr = new Element(name, symbol, ordinal, shell, phase, mainGroup);
            elements.add(curr);
        }

        return elements;
    }

    public static List<Metal> getMetals() throws FileNotFoundException {
        Stream<Element> elements = getAllElements().stream().filter(x ->
                Arrays.stream(METAL_RANGES).anyMatch(ord -> ord[0] <= x.getOrdinal() && ord[1] >= x.getOrdinal())
        );
        Stream<Metal> metalStream = elements.map(x -> new Metal(x, false, 12));

        return metalStream.collect(Collectors.toList());
    }
}