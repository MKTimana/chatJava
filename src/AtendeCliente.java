import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        System.out.println("Conectado com Thread (" + this.getId() + ")");
        Scanner teclado = new Scanner(System.in);
        Scanner chegada; // Cliente e que vai digitar
        InputStreamReader fluxoDados;

        try {
            chegada = new Scanner(cliente.getInputStream());
            PrintStream saida = new PrintStream(cliente.getOutputStream());

            while(chegada.hasNextLine()){
                String msgChegada = chegada.nextLine();
                System.out.println(msgChegada);

                String msgResposta = teclado.nextLine();
                saida.println(msgResposta);
                System.out.println("===============================");
            }

            fluxoDados = new InputStreamReader(cliente.getInputStream());
            BufferedReader dado = new BufferedReader(fluxoDados);
            System.out.println(dado.readLine());

            // Thread.sleep(15000);

            System.out.println("Cliente finalizado: " + cliente.getInetAddress());
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        } 
        // catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
}
