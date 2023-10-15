import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class GuiCliente extends JFrame implements ActionListener {
    private JScrollPane scrText;
    private static JTextArea txA;
    private JTextField txtMsg;
    private JButton btnSend;

    static Socket s;
    static DataOutputStream dout;
    static DataInputStream din;

    GuiCliente() {

        this.setTitle("Chat-Cliente");
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
        new GuiCliente().setVisible(true);

        try {
            s = new Socket("127.0.0.1", 7000); // Comunicacao com o server IP e porta
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msgIn = "";

            while (!msgIn.equals("exit")) {
                msgIn = din.readUTF();
                txA.setText(txA.getText().trim() + "\n Server:\t" + msgIn);
            }
        } catch (Exception e) {

        }
    }
}
