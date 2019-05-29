package de.dhbwka.exercise.ui;

import javax.swing.*;
import java.util.stream.IntStream;

/**
 * @author Leonhard Gahr
 */
public class ComponentFrame extends JFrame {
    public ComponentFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JPanel panel = new JPanel();

        JLabel label = new JLabel("JLabel");
        panel.add(label);

        JTextField txtField = new JTextField("JTextField");
        panel.add(txtField);

        JPasswordField pwField = new JPasswordField("JPasswordField");
        pwField.setColumns(10);
        panel.add(pwField);

        JButton button = new JButton("JButton");
        button.setToolTipText("I am a tooltip");
        panel.add(button);

        JToggleButton toggleButton = new JToggleButton("JToggleButton");
        panel.add(toggleButton);

        JCheckBox checkBox = new JCheckBox("JCheckBox");
        panel.add(checkBox);

        String[] items = IntStream.range(0, 5).mapToObj(i -> String.format("Item %d", i + 1)).toArray(String[]::new);
        JComboBox<String> comboBox = new JComboBox<>(items);
        panel.add(comboBox);


        ButtonGroup radioButtonGroup = new ButtonGroup();
        IntStream.range(0, 3).mapToObj(i -> new JRadioButton(String.format("Radio-Button-%d", i + 1))).forEach(btn -> {
            radioButtonGroup.add(btn);
            panel.add(btn);
        });


        add(panel);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        ComponentFrame cFrame = new ComponentFrame();
        cFrame.setSize(650, 100);
        cFrame.setTitle("Swing dein Ding");
        cFrame.setDefaultCloseOperation(ComponentFrame.EXIT_ON_CLOSE);
        cFrame.setVisible(true);
    }
}
