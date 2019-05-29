package de.dhbwka.exercise.classes.periodic;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Leonhard Gahr
 */
@Getter
@Setter
public class Metal extends Element {
    private boolean metalloid;
    private double conductivity;

    /**
     * Initialize Element with parameters
     *
     * @param name         the elements name
     * @param symbol       the elements symbol
     * @param ordinal      the ordinal of the element
     * @param shell        the shell of the element
     * @param phase        the phase of the element
     * @param mainGroup    whether the element is in the main group
     * @param metalloid    whether the metal is a metalloid
     * @param conductivity the conductivity of the metal
     */
    public Metal(String name, String symbol, int ordinal, char shell, Phase phase, boolean mainGroup, boolean metalloid, double conductivity) {
        super(name, symbol, ordinal, shell, phase, mainGroup);
        this.metalloid = metalloid;
        this.conductivity = conductivity;
    }

    /**
     * Initialize the metal with default values for the phase, the main group and whether it is a metalloid (solid, false, false)
     *
     * @param name         the elements name
     * @param symbol       the elements symbol
     * @param ordinal      the ordinal of the element
     * @param shell        the shell of the element
     * @param conductivity the conductivity of the metal
     */
    public Metal(String name, String symbol, int ordinal, char shell, double conductivity) {
        this(name, symbol, ordinal, shell, Phase.SOLID, false, false, conductivity);
    }

    /**
     * Initialize the metal with an element
     *
     * @param element      the element
     * @param metalloid    whether the metal is a metallloid
     * @param conductivity the conductivity
     */
    public Metal(Element element, boolean metalloid, double conductivity) {
        this(element.getName(), element.getSymbol(), element.getOrdinal(), element.getShell(), element.getPhase(), element.isMainGroup(), metalloid, conductivity);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", %s\u03C3: %f", metalloid ? "metalloid, " : "", conductivity);
    }
}
