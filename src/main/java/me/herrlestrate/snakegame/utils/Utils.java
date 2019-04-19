package me.herrlestrate.snakegame.utils;

import java.awt.*;

public class Utils {

    private static final String TEST_PASS = "Science_Yeah!";

    public static Dimension getScreenSize(){
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static String getTestPass(){
        return TEST_PASS;
    }

}
