package de.dhbwka.exercise.classes.periodic;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Leonhard Gahr
 */
@Getter
@Setter
public class Element {
    private String name;
    private String symbol;
    @SerializedName("number")
    private int ordinal;
    private char shell;
    private Phase phase; // at 25°C (298.15 K)
    private boolean mainGroup;

    /**
     * Initialize Element with parameters
     *
     * @param name      the elements name
     * @param symbol    the elements symbol
     * @param ordinal   the ordinal of the element
     * @param shell     the shell of the element
     * @param phase     the phase of the element
     * @param mainGroup whether the element is in the main group
     */
    public Element(String name, String symbol, int ordinal, char shell, Phase phase, boolean mainGroup) {
        this.name = name;
        this.symbol = symbol;
        this.ordinal = ordinal;
        this.shell = shell;
        this.phase = phase;
        this.mainGroup = mainGroup;
    }

    /**
     * Initialize Element with some default values for phase an mainGroup (liquid and false)
     *
     * @param name    the elements name
     * @param symbol  the elements symbol
     * @param ordinal the ordinal of the element
     * @param shell   the shell of the element
     */
    public Element(String name, String symbol, int ordinal, char shell) {
        this(name, symbol, ordinal, shell, Phase.LIQUID, false);
    }

    /**
     * to string override
     *
     * @return all information about the element
     */
    @Override
    public String toString() {
        return String.format("%s (%s, %d) shell: %s, %s, group: %s", name, symbol, ordinal, shell, phase.toString().toLowerCase(), mainGroup ? "Hauptgruppe" : "Nebengruppe");
    }

    /**
     * Override of the equals method, is true if the ordinal of the two object match
     *
     * @param obj the object to compare to
     * @return whether the elements are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Element)) return false;
        return this.ordinal == ((Element) obj).ordinal;
    }
}
