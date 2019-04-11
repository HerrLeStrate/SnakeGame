package me.herrlestrate.snakegame.crypto;

import java.io.File;

public abstract class Crypto {

    private final String cryptName;

    Crypto(String name){
        cryptName = name;
    }

    public abstract void encryptFile(File file);

    public abstract void decryptFile(File file,String[] args);

    String getCryptName() {
        return cryptName;
    }
}
