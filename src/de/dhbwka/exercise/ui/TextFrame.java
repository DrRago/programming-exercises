package de.dhbwka.exercise.ui;

import org.apache.commons.cli.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

/**
 * @author Leonhard Gahr
 */
public class TextFrame extends JFrame {
    public TextFrame(String fileName, int width, int heigth) throws IOException {
        File file = new File(fileName);
        setTitle(file.getName());
        setSize(width, heigth);

        TextArea textArea = new TextArea();
        textArea.setText(new String(Files.readAllBytes(file.toPath())));
        colorChanger(textArea);

        add(textArea);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static void colorChanger(TextArea ta) {
        new Thread(() -> {
            while (true) {
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                ta.setForeground(randomColor);

                randomColor = new Color(1 - r, 1 - g, 1 - b);
                ta.setBackground(randomColor);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("f", true, "Filename");
        options.addOption("w", true, "Window width (integer)");
        options.addOption("h", true, "Windows height (integer)");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        try {
            String fileName = cmd.getOptionValue("f");
            assert fileName != null;

            int width = Integer.parseInt(cmd.getOptionValue("w"));
            int height = Integer.parseInt(cmd.getOptionValue("h"));

            new TextFrame(fileName, width, height).setVisible(true);
        } catch (NumberFormatException | AssertionError e) {
            HelpFormatter fmt = new HelpFormatter();
            fmt.printHelp("Help", options);
        } catch (IOException e) {
            System.err.println("Error: File not Found");
        }
    }
}
