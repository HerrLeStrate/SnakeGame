package me.herrlestrate.snakegame.components.MainMenu;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import me.herrlestrate.snakegame.Game;
import me.herrlestrate.snakegame.utils.Sound;
import me.herrlestrate.snakegame.utils.Utils;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private JPanel rootPanel;
    private JLabel Background;

    public MainMenu() {
        add(rootPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Snake Game");
        setUndecorated(true);
        setSize(Utils.getScreenSize());
        Image image = null;

        try {
            image = ImageIO.read(getClass().getResource("/foregrounds/main_menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image == null) {
            System.err.println("Image not found!");
            return;
        }

        Image finalImage = image;
        File music = null;
        try {
            music = File.createTempFile("msuic", "main_theme");
            FileUtils.copyURLToFile(getClass().getResource("/music/main_theme.wav"), music);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sound sound = new Sound(music);
        sound.setRepeated(true);
        sound.play();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                ImageIcon icon = new ImageIcon(
                        finalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)
                );
                Background.setIcon(icon);
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setEnabled(true);
        Background = new JLabel();
        Background.setIcon(new ImageIcon(getClass().getResource("/foregrounds/main_menu.png")));
        Background.setText("");
        rootPanel.add(Background, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
