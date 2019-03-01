package de.dhbwka.exercise.classes.periodic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Leonhard Gahr
 */
public class ElementHelper {
    final int[][] METAL_RANGES = {{3, 4}, {11, 13}, {19, 31}, {37, 50}, {55, 84}, {87, 116}};

    private int[] getMetalIndices() {
        Arrays.stream(METAL_RANGES).forEach(System.out::println);
        return null;
    }

    public static int[] getMetalElements() throws FileNotFoundException {
        String path = ElementHelper.class.getClassLoader().getResource("periodic_table.json").getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Type listType = new TypeToken<ArrayList<Element>>(){}.getType();
        List<Element> elements = new Gson().fromJson(bufferedReader, listType);

        System.out.println(elements);

        return null;
    }
}
