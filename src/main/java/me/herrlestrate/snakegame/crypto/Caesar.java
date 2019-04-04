package me.herrlestrate.snakegame.crypto;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Scanner;

public class Caesar {

    public final String CRYPT_NAME = "CAESAR";

    public Caesar(File victim){
        if(!victim.exists()){
            System.err.println("Victim file not exists!");
            System.err.println("Victim: "+victim.getAbsolutePath());
            return;
        }

        if(victim.isDirectory()){
            System.err.println("Victim is directory!");
            return;
        }

        if(FilenameUtils.getExtension(victim.getName()).equalsIgnoreCase(CRYPT_NAME)){
            System.err.println("Victim already crypted!");
            return;
        }

        FileInputStream inputStream;
        FileOutputStream outputStream;

        String pathToTargetFile = victim.getAbsolutePath()
                +"."+CRYPT_NAME;

        File target = new File(pathToTargetFile);
        if(target.exists()){
            System.err.println("Target file already created!");
            return;
        }

        try{
            target.createNewFile();
        }catch (IOException ex){
            ex.printStackTrace();
            return;
        }

        try{
             inputStream = new FileInputStream(victim);
             outputStream = new FileOutputStream(target);
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            return;
        }

        try {

            int sign = -1;
            while ((sign = inputStream.read()) != -1) {
                outputStream.write(cryptByte(sign,getStep()));
            }

            inputStream.close();
            outputStream.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }

        String pathToDecodeFile =
                FilenameUtils.getFullPath(victim.getAbsolutePath())
                + "DECRTYPTED-"
                + CRYPT_NAME
                + victim.getName();

        File decode = new File(pathToDecodeFile);

        try {
            decode.createNewFile();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        try {
            inputStream = new FileInputStream(target);
            outputStream = new FileOutputStream(decode);
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

        try {

            int sign = -1;
            while ((sign = inputStream.read()) != -1) {
                outputStream.write(cryptByte(sign,-getStep()));
            }

            inputStream.close();
            outputStream.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private int cryptByte(int x,int step){
        step %= 26;//default alphabet
        return (x+step+256)%256;
    }

    private int getStep(){
        return 1;
    }
}
