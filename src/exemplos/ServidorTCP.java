package exemplos;
import java.io.*;
import java.net.*;

class ServidorTCP {
  public static void main(String args[]) {
    try {
      // Cria um socket TCP para pedidos de conex�o na porta 8000
      ServerSocket listenSocket = new ServerSocket(8000);

      // Aguarda at� que um cliente pe�a por uma conex�o
      Socket sock = listenSocket.accept();

      // Prepara um buffer para receber dados de um cliente
      InputStreamReader s = new InputStreamReader(sock.getInputStream());
      BufferedReader rec = new BufferedReader(s);

      // L� os dados enviados pela aplica��o cliente
      String rBuf = rec.readLine();

      // Apresenta os dados recebidos
      InetAddress ip = sock.getInetAddress();
      int port = sock.getPort();
      System.out.println(ip + ":" + port + ": " + rBuf);

      // Coloca a resposta em um buffer e envia para o cliente
      DataOutputStream d = new DataOutputStream(sock.getOutputStream());
      String sBuf = "Ok!\n";
      d.write(sBuf.getBytes("UTF-8"));

      // Encerra a conex�o com o cliente
      sock.close();
    } catch (Exception e) {}
  }
}
