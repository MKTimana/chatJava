import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class GuiServer extends JFrame implements ActionListener {
    private JScrollPane scrText;
    private static JTextArea txA;
    private JTextField txtMsg;
    private JButton btnSend;

    static ServerSocket ss;
    static Socket s;
    static DataOutputStream dout;
    static DataInputStream din;

    GuiServer() {

        this.setTitle("Chat-Server");
        this.setSize(500, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        txA = new JTextArea(15, 30);
        txA.setEditable(false);
        scrText = new JScrollPane(txA);

        txtMsg = new JTextField(20);
        
        btnSend = new JButton("Send msg");
        btnSend.addActionListener(this);

        this.add(scrText);
        this.add(txtMsg);
        this.add(btnSend);

        // this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSend) {
            try {
                String msgOut = "";
                msgOut = txtMsg.getText().trim();
                txA.setText(txA.getText().trim()+"\n Eu: " + msgOut);
                dout.writeUTF(msgOut);// enviando a msg
                txtMsg.setText("");
            } catch (Exception err) {
                System.out.println("Erro de envio de msg" + err);
            }
        }
    }

    public static void main(String[] args) {

        new GuiServer().setVisible(true);

        String msgIn = "";
        try {
            ss = new ServerSocket(7000); // porta do servidor 7000
            s = ss.accept(); // aceita a conexao do servidor

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (!msgIn.equals("exit")) {
                msgIn = din.readUTF();
                txA.setText(txA.getText().trim() + "\n Client: \t" + msgIn); // aqui vai apresentar o texto enviado... pelo cliente
            }
        } catch (Exception e) {

        }
    }
}
