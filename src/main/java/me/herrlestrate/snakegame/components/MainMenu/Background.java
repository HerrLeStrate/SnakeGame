package me.herrlestrate.snakegame.components.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Background extends JComponent {

    private Image image;
    private Dimension size;

    public Background(Image image,Dimension size){
        this.image = image;
        this.size = size;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,size.width,size.height,this);
    }
}
