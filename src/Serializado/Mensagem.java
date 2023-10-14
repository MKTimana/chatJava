package Serializado;

import java.io.Serializable;
import java.util.UUID;

public class Mensagem implements Serializable{
    public String nome;
    public String msg;
    public String token;

    Mensagem (String nome, String msg){
        this.nome = nome;
        this.msg = msg;
    }

    public void TokenServer(){
        this.token = UUID.randomUUID().toString();
    }

    public String toString(){
        return this.nome +"\t"+" msg: "+this.msg+"\t"+" token: "+this.token;
    }
}
