package Serializado;
import java.net.ServerSocket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket servidor = new ServerSocket(7000);
            System.out.println("Servidor iniciado...");

            while (true) {


                AtendeCliente ac = new AtendeCliente(servidor.accept());
                ac.start();

                // Socket cliente = servidor.accept();

                // System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // InputStreamReader fluxoDados = new InputStreamReader(cliente.getInputStream());
                // BufferedReader dado = new BufferedReader(fluxoDados);
                // System.out.println(dado.readLine());

                // Thread.sleep(7000);
                // cliente.close();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro no servidor!");
        }
    }
}
