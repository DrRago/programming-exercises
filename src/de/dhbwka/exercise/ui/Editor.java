package de.dhbwka.exercise.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Leonhard Gahr
 */
public class Editor extends JFrame {
    public Editor() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("Editor");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        this.setSize(640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        createMenuBar();

        this.setVisible(true);
    }

    private List<MyMenuItem> generateFileMenuItemList() {
        List<MyMenuItem> myMenuItemList = new ArrayList<>();
        myMenuItemList.add(new MyMenuItem("New"));
        myMenuItemList.add(new MyMenuItem("Open", true));
        myMenuItemList.add(new MyMenuItem("Close", true));
        myMenuItemList.add(new MyMenuItem("Save"));
        myMenuItemList.add(new MyMenuItem("Save as"));
        myMenuItemList.add(new MyMenuItem("Save as website"));
        myMenuItemList.add(new MyMenuItem("Search", true));
        myMenuItemList.add(new MyMenuItem("Versions", true));
        myMenuItemList.add(new MyMenuItem("Websitepreview", true));
        myMenuItemList.add(new MyMenuItem("Print"));
        myMenuItemList.add(new MyMenuItem("Properties", true));
        myMenuItemList.add(new MyMenuItem("Exit", e -> System.exit(0), 'Q'));

        return myMenuItemList;
    }

    private List<MyMenuItem> generateEditMenuItemList() {
        List myMenuItemList = new ArrayList<MyMenuItem>();

        return myMenuItemList;
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuEdit = new JMenu("File");
        addToMenu(menuEdit, generateFileMenuItemList().stream());
        menuBar.add(menuEdit);

        this.setJMenuBar(menuBar);
    }

    private void addToMenu(JMenu menu, Stream<MyMenuItem> itemStream) {
        itemStream.forEach(item -> {
            menu.add(item.toJMenuItem());
            if (item.isFollowSpacer()) {
                menu.addSeparator();
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Editor editor = new Editor();
    }
}
