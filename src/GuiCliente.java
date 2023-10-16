import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuiCliente extends JFrame implements ActionListener {
    static Socket s;
    static DataOutputStream dout;
    static DataInputStream din;

    private JScrollPane scrText;
    private static JTextArea txA;
    private JTextField txtMsg;
    private JButton btnSend;
    private JPanel panText;
    private JPanel panAnswers;
    private JPanel panImage;
    private JLabel lblImage;
    private ImageIcon img;
    private ImageIcon imgSend;
    private JButton btnOk;
    private JButton btnHello;
    private JButton btnUnderstood;
    private JLabel lblAnswers;
    private JLabel lblPowered;

    private static String currentTime = new SimpleDateFormat("HH:mm").format(new Date());

    Color c = new Color(54, 75, 110);

    GuiCliente() {

        this.setTitle("Chat-Cliente");
        this.setSize(700, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));
        this.setBackground(c);

        lblAnswers = new JLabel("Respostas rapidas");
        lblPowered = new JLabel("ChatApp Powered by @MKTECH");
        lblPowered.setForeground(Color.white);

        btnHello = new JButton("Hello");
        btnHello.addActionListener(this);
        btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        btnUnderstood = new JButton("Understood");
        btnUnderstood.addActionListener(this);

        panText = new JPanel();
        panImage = new JPanel();
        panAnswers = new JPanel(new GridLayout(2, 1));
        panAnswers.setBackground(c);
        lblAnswers.setForeground(Color.white);
        panText.setBackground(c);
        panImage.setBackground(c);

        img = new ImageIcon("src/image/pc2.png");
        imgSend = new ImageIcon("src/image/send.png");
        lblImage = new JLabel(img);

        txA = new JTextArea(15, 25);
        txA.setEditable(false);

        txA.setForeground(Color.black);
        scrText = new JScrollPane(txA) {
            @Override
            public void setBorder(Border border) {
                // No!
            }
        };

        txtMsg = new JTextField(20);

        btnSend = new JButton(imgSend);
        btnSend.setOpaque(false);
        btnSend.setBorderPainted(false);

        btnSend.addActionListener(this);

        panText.add(scrText);
        panAnswers.add(lblAnswers);
        panAnswers.add(btnHello);
        panAnswers.add(btnOk);
        panAnswers.add(btnUnderstood);
        panText.add(panAnswers);
        panText.add(txtMsg);
        panText.add(btnSend);
        panImage.add(lblImage);
        panImage.add(lblPowered);

        this.add(panText);
        this.add(panImage);
        // this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSend) {
            try {
                String msgOut = "";
                msgOut = txtMsg.getText().trim();
                txA.setText(txA.getText().trim() + "\nMe:\t" + msgOut+". Enviado às "+currentTime);
                dout.writeUTF(msgOut);// enviando a msg
                txtMsg.setText("");
            } catch (Exception err) {
                System.out.println("Erro de envio de msg" + err);
            }
        } else if (e.getSource() == btnHello) {
            txtMsg.setText("Hello");
        } else if (e.getSource() == btnOk) {
            txtMsg.setText("OK");
        } else if (e.getSource() == btnUnderstood) {
            txtMsg.setText("Understood");
        }
    }

    public static void main(String[] args) {
        new GuiCliente().setVisible(true);

        try {
            s = new Socket("172.20.10.3", 7000); // Comunicacao com o server IP e porta
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msgIn = "";

            while (!msgIn.equals("exit")) {
                msgIn = din.readUTF();
                txA.setText(txA.getText().trim() + "\nDELL MK:\t" + msgIn+". Recebido às "+currentTime);
            }
        } catch (Exception e) {

        }
    }
}
