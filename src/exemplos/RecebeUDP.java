package exemplos;
import java.io.*;
import java.net.*;

class RecebeUDP {
  public static void main(String args[]) {
    try {
      // Prepara um buffer para receber dados
      byte[] r = new byte[1024];
      DatagramPacket pack = new DatagramPacket(r, r.length);

      // Cria um socket UDP para receber dados escutando a porta 8000
      DatagramSocket socket = new DatagramSocket(8000);

      // Lê os dados enviados pela aplicação
      socket.receive(pack);

      // Apresenta os dados recebidos
      String buf = new String(pack.getData(), 0, pack.getLength());
      InetAddress ip = pack.getAddress();
      int port = pack.getPort();
      System.out.println(ip + ":" + port + ": " + buf);

      // Encerra o socket
      socket.close();
    } catch (Exception e) {}
  }
}
