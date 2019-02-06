package de.dhbwka.exercise.classes;

/**
 * @author Leonhard Gahr
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
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
