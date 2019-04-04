package me.herrlestrate.snakegame;

import me.herrlestrate.snakegame.crypto.Caesar;
import me.herrlestrate.snakegame.crypto.Searcher;

import java.io.File;
import java.util.Objects;

public class Game {

    private static boolean DEBUG = true;
    private static final String ROOT = getRoot();

    public static void main(String[] args){
        if(!new File(ROOT).exists()){
            System.err.println("Not found root path: " + ROOT);
            return;
        }

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

}
