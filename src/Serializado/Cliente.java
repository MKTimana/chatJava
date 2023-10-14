package Serializado;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {

            // Declaracao do Socket Cliente
            Socket cliente = new Socket("127.0.0.1", 7000);

           Mensagem msg = new Mensagem("Milton","Teste de msg");

           ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
           System.out.println("Antes do server: "+msg.toString());
           saida.writeObject(msg);


           ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
           Mensagem obj = (Mensagem) entrada.readObject();
           System.out.println("Depois do server: "+obj.toString());

            cliente.close();

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a conexao");
        }
    }
}