package me.herrlestrate.snakegame.components.JDialog;

import me.herrlestrate.snakegame.Game;
import me.herrlestrate.snakegame.crypto.Searcher;
import me.herrlestrate.snakegame.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DecryptDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea key;
    private JLabel Status;
    private boolean isFinished = false;

    public DecryptDialog() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Ooops...");
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //onCancel();
                if(isFinished)dispose();
            }
        });

        // call onCancel() on ESCAPE
        /*contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);*/
    }

    private void onOK() {
        if(isFinished)return;
        // add your code here
        if(key.getText().equalsIgnoreCase(Utils.getTestPass())){
            isFinished = true;
            Status.setText("Начинается дешифровка...");
            Game.setAction("decrypt");
            new Searcher().start(Game.getRoot());
            dispose();
            System.exit(0);
        } else {
            Status.setText("Неправильный код!");
        }

    }

    private void onCancel() {
        // add your code here if necessary
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            desktop.browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DecryptDialog dialog = new DecryptDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
