import java.io.PrintStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {

            // Declaracao do Socket Cliente
            Socket cliente = new Socket("127.0.0.1", 7000);
            System.out.println("Cliente Inicado");
            // Fluxo de dados para enviar
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            ps.println("Conectado por Milton!");

            cliente.close();
            System.out.println("Cliente Finalizado");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a conexao");
        }
    }
}