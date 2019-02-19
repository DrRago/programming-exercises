package de.dhbwka.exercise.classes.swapgame;

import static de.dhbwka.exercise.utility.ConsoleColors.*;

/**
 * Helper for SwapGame console colors
 * @author Leonhard Gahr
 */
class ColorHelper {
    /**
     * get a string as specific color string based on an integer from 0 to n (14+ will return always white)
     *
     * @param i the color indicator
     * @return the color code string
     */
    private static String getColor(int i) {
        switch (i) {
            case 0:
                return BLACK_BACKGROUND_BRIGHT;
            case 1:
                return RED_BACKGROUND_BRIGHT;
            case 2:
                return GREEN_BACKGROUND_BRIGHT;
            case 3:
                return YELLOW_BACKGROUND_BRIGHT;
            case 4:
                return BLUE_BACKGROUND_BRIGHT;
            case 5:
                return PURPLE_BACKGROUND_BRIGHT;
            case 6:
                return CYAN_BACKGROUND_BRIGHT;
            case 7:
                return BLACK_BACKGROUND;
            case 8:
                return RED_BACKGROUND;
            case 9:
                return GREEN_BACKGROUND;
            case 10:
                return YELLOW_BACKGROUND;
            case 11:
                return BLUE_BACKGROUND;
            case 12:
                return PURPLE_BACKGROUND;
            case 13:
                return CYAN_BACKGROUND;
            default:
                return WHITE_BACKGROUND_BRIGHT;
        }
    }

    static String getColorString(int i) {
        return getColor(i) + String.format("%2d ", i) + RESET;
    }
}