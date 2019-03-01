package de.dhbwka.exercise.classes.periodic;

import com.google.gson.annotations.SerializedName;

/**
 * @author Leonhard Gahr
 */
public enum Phase {
    @SerializedName("Solid") SOLID, @SerializedName("Liquid") LIQUID, @SerializedName("Gas") GAS;

}