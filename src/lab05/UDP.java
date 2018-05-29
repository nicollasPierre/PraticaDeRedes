package lab05;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDP {

    private InetAddress ip;
    private int port;
    private String usuario;

    public UDP(int usuario, String senha) throws UnknownHostException {
        this.usuario = usuario + ":" + senha;
        ip = InetAddress.getByName("larc.inf.furb.br");
        this.port = 1011;
    }

    public void sendMessageToPlayers(int user, String msg) throws IOException {
        msg = "SEND MESSAGE " + usuario + ":" + user + ":" + msg;
        byte[] mensagemFormatadaTP = new byte[msg.length()];
        mensagemFormatadaTP = msg.getBytes("UTF-8");

        DatagramPacket packTP = new DatagramPacket(mensagemFormatadaTP, mensagemFormatadaTP.length, ip, port);
        // Cria um socket UDP e envia o pacote para localhost:8000
        DatagramSocket socket = new DatagramSocket();
        socket.send(packTP);
        // Encerra o socket
        socket.close();
    }

    public void sendMessage(String mensagem, String parametro) throws IOException {
        mensagem = mensagem + " " + usuario + ":" + parametro + "\n\r";
        byte[] mensagemFormatada = new byte[mensagem.length()];
        mensagemFormatada = mensagem.getBytes("UTF-8");

        DatagramPacket pack = new DatagramPacket(mensagemFormatada, mensagemFormatada.length, ip, port);

        // Cria um socket UDP e envia o pacote para localhost:8000
        DatagramSocket socket = new DatagramSocket();
        socket.send(pack);

        // Encerra o socket
        socket.close();
    }
}
