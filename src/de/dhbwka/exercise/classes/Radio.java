package de.dhbwka.exercise.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * Radio class
 *
 * @author Leonhard Gahr
 */
public class Radio {
    @Getter
    // whether radio is on
    private boolean on;

    @Getter
    @Setter
    // volume of the radio (0 - 10)
    private int volume;

    @Getter
    // frequency (85.0 - 110.0)
    private double frequency;

    /**
     * Instantiates a new Radio with default values
     *
     * @throws IllegalArgumentException the illegal argument exception
     */
    Radio() throws IllegalArgumentException {
        // initialize radio with default vars
        this(true, 5, 85.0);
    }

    /**
     * Instantiates a new Radio.
     *
     * @param on        whether the radio is on
     * @param volume    the initial volume
     * @param frequency the initial frequency
     * @throws IllegalArgumentException thrown if the volume or the frequency value is illegal
     */
    Radio(boolean on, int volume, double frequency) throws IllegalArgumentException {
        // check whether arguments are valid
        if (0 > volume || 10 < volume) {
            throw new IllegalArgumentException(String.format("'%d' is an invalid value for volume. Must be between 0 and 10", volume));
        }

        if (85.0 > frequency || 110.0 < frequency) {
            throw new IllegalArgumentException(String.format("'%f' is an invalid value for frequency. Must be between 85.0 and 110.0", frequency));
        }

        this.on = on;
        this.volume = volume;
        this.frequency = frequency;
    }

    /**
     * Increase volume.
     */
    void incVolume() {
        // do nothing if the radio is off
        if (!this.isOn()) {
            return;
        }

        // do nothing if the volume is max
        if (this.getVolume() == 10) {
            return;
        }

        this.setVolume(this.getVolume() + 1);
    }

    /**
     * Decrease volume.
     */
    void decVolume() {
        // do nothing if the radio is off
        if (!this.isOn()) {
            return;
        }

        // do nothing if the volume is min
        if (this.getVolume() == 0) {
            return;
        }

        this.setVolume(this.getVolume() - 1);
    }

    /**
     * Turn on.
     */
    void turnOn() {
        this.on = true;
    }

    /**
     * Turn off.
     */
    void turnOff() {
        this.on = false;
    }

    /**
     * Sets frequency (min 85, max 110, default 99)
     *
     * @param frequency the frequency to set
     */
    void setFrequency(double frequency) {
        // set frequency to default if it is out of range
        if (frequency < 85.0 || frequency > 110.0) {
            frequency = 99.9;
        }

        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return String.format("Radio %s; Volume %d; Frequency %.2f Mhz", this.isOn() ? "on" : "off", this.getVolume(), this.getFrequency());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Radio radio = new Radio();
        System.out.println(radio);
        radio.incVolume();
        System.out.println(radio);
        radio.decVolume();
        radio.setFrequency(200);
        System.out.println(radio);
        radio.turnOff();
        radio.decVolume();
        System.out.println(radio);
        radio.incVolume();
        System.out.println(radio);
        radio.turnOn();
        System.out.println(radio);
    }
}
