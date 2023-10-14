package Serializado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AtendeCliente extends Thread {
    Socket cliente;

    AtendeCliente(Socket cli) {
        this.cliente = cli;
    }

    @Override
    public void run() {
        try {
            System.out.println("Conectado com Thread (" + this.getId() + ")");

            // Fluxo de dados para objecto

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            Mensagem objecto = (Mensagem) entrada.readObject();
            System.out.println(objecto.toString());
            objecto.TokenServer();
            
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            saida.writeObject(objecto);

            cliente.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // catch (InterruptedException e) {
        // e.printStackTrace();
        // }
    }
}
