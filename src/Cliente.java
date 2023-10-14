import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {

            // Declaracao do Socket Cliente
            Socket cliente = new Socket("127.0.0.1", 7000);

            Scanner teclado = new Scanner(System.in);
            Scanner chegada = new Scanner(cliente.getInputStream());

            // Fluxo de dados para enviar
            PrintStream ps = new PrintStream(cliente.getOutputStream());

            String msg = "";

            do {
                System.out.println("Informe a msg a ser enviada");
                msg = teclado.nextLine();
                ps.println(msg);
                // Msg enviada para o servidor

                String resposta = chegada.nextLine();
                System.out.println("Resposta(" + resposta + ")");
                System.out.println("===============================");
            } while (msg.length() != 0);

            System.out.println("Cliente Inicado");

            // Imprime os dados enviados
            // ps.println("Conectado por Milton!");

            cliente.close();

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a conexao");
        }
    }
}