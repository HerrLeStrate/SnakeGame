package me.herrlestrate.snakegame.components.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecryptButton extends JButton {

    public DecryptButton(){
        setText("Decrypt Button");
        setPreferredSize(new Dimension(300,200));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });
    }
}
