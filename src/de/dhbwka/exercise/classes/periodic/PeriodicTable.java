package de.dhbwka.exercise.classes.periodic;

import java.io.FileNotFoundException;

import static de.dhbwka.exercise.classes.periodic.ElementHelper.getMetalElements;

/**
 * Handle a periodic table
 *
 * @author Leonhard Gahr
 */
public class PeriodicTable {
    Element[] elements;

    /**
     * Initialize the periodic table with elements
     *
     * @param elements the elements
     */
    public PeriodicTable(Element[] elements) {
        this.elements = elements;
    }

    /**
     * Initialize the periodic table with all elements
     */
    public PeriodicTable() {
        this(new Element[118]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        getMetalElements();
    }
}
