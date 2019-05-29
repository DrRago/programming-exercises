package de.dhbwka.exercise.classes.periodic;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Handle a periodic table
 *
 * @author Leonhard Gahr
 */
public class PeriodicTable {
    List<Element> elements;

    /**
     * Initialize the periodic table with elements
     *
     * @param elements the elements
     */
    public PeriodicTable(Element[] elements) {
        this(Arrays.asList(elements));
    }

    /**
     * Initialize the periodic table with elements
     *
     * @param elements the elements
     */
    public PeriodicTable(List<Element> elements) {
        this.elements = elements;
    }

    /**
     * Initialize the periodic table with all elements
     */
    public PeriodicTable() throws FileNotFoundException {
        this(ElementHelper.getAllElements());
    }

    public boolean hasElement(int o) {
        return elements.stream().anyMatch(element -> element.getOrdinal() == o);
    }

    public Element getElement(int o) {
        return elements.stream().filter(el -> el.getOrdinal() == o).findFirst().orElse(null);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ElementHelper.getMetals().forEach(System.out::println);
    }
}
