package me.herrlestrate.snakegame.components.MainMenu;

import me.herrlestrate.snakegame.Game;
import me.herrlestrate.snakegame.utils.Sound;
import me.herrlestrate.snakegame.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame{
    private JPanel rootPanel;
    private JLabel Background;

    public MainMenu(){
        add(rootPanel);
        setTitle("Snake Game");
        setUndecorated(true);
        setSize(Utils.getScreenSize());
        Image image = null;

        try {
            image = ImageIO.read(Game.getFileFromResources("foregrounds//main_menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(image == null){
            System.err.println("Image not found!");
            return;
        }

        Image finalImage = image;

        File music = Game.getFileFromResources("music/main_theme.wav");
        Sound sound = new Sound(music);
        sound.setRepeated(true);
        sound.play();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                ImageIcon icon = new ImageIcon(
                        finalImage.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH)
                );
                Background.setIcon(icon);
            }
        });
    }

}
