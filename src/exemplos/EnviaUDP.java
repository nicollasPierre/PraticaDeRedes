package exemplos;
import java.io.*;
import java.net.*;

class EnviaUDP {
  public static void main(String args[]) {
    try {
    // Coloca os dados em um buffer
    String buf = "Mensagem de teste!\n";
    byte[] mensagem = new byte[buf.length()];
    mensagem = buf.getBytes("UTF-8");

    // Prepara um pacote com o buffer e as informações do destinatário
    InetAddress ip = InetAddress.getByName("localhost");
    DatagramPacket pack = new DatagramPacket(mensagem, mensagem.length, ip, 8000);

    // Cria um socket UDP e envia o pacote para localhost:8000
    DatagramSocket socket = new DatagramSocket();
    socket.send(pack);

    // Encerra o socket
    socket.close();
    } catch (Exception e) {}
  }
}
