package me.herrlestrate.snakegame;

import me.herrlestrate.snakegame.crypto.Searcher;

import java.io.File;
import java.util.Scanner;

public class Game {

    private static boolean DEBUG = true;
    private static final String ROOT = getRoot();
    private static String ACTION = "encrypt";

    public static void main(String[] args){
        if(!new File(ROOT).exists()){
            System.err.println("Not found root path: " + ROOT);
            return;
        }
        setAction(new Scanner(System.in).next());
        new Searcher().start(ROOT);
    }

    private static String getRoot(){
        if(DEBUG){
            return "F:\\CourseProject\\";
        } else {
            return new File("").getAbsolutePath();
        }
    }

    public static boolean isDebug(){
        return DEBUG;
    }

    public static String getAction() {
        return ACTION;
    }

    public static void setAction(String ACTION) {
        Game.ACTION = ACTION;
    }
}
