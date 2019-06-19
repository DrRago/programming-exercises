package de.dhbwka.exercise.ui;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author Leonhard Gahr
 */

@Getter
@Setter
public class MyMenuItem {
    private String title;
    private ActionListener actionListener;
    private char mnemonic;
    private boolean followSpacer = false;

    public MyMenuItem(String title, ActionListener actionListener, char mnemonic, boolean followSpacer) {
        this.title = title;
        this.actionListener = actionListener;
        this.mnemonic = mnemonic;
        this.followSpacer = followSpacer;
    }
    public MyMenuItem(String title, ActionListener actionListener, char mnemonic) {
        this.title = title;
        this.actionListener = actionListener;
        this.mnemonic = mnemonic;
    }

    public MyMenuItem(String title, ActionListener actionListener, boolean followSpacer) {
        this.title = title;
        this.actionListener = actionListener;
        this.followSpacer = followSpacer;

    }
    public MyMenuItem(String title, ActionListener actionListener) {
        this.title = title;
        this.actionListener = actionListener;

    }

    public MyMenuItem(String title, boolean followSpacer) {
        this.title = title;
        this.followSpacer = followSpacer;
    }

    public MyMenuItem(String title) {
        this.title = title;
    }

    public JMenuItem toJMenuItem() {
        JMenuItem item = new JMenuItem(title);
        item.addActionListener(actionListener);
        item.setMnemonic(mnemonic);

        return item;
    }
}
