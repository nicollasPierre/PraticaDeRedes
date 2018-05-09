package exemplos;
import java.io.*;
import java.net.*;

class ServidorTCP {
  public static void main(String args[]) {
    try {
      // Cria um socket TCP para pedidos de conexão na porta 8000
      ServerSocket listenSocket = new ServerSocket(8000);

      // Aguarda até que um cliente peça por uma conexão
      Socket sock = listenSocket.accept();

      // Prepara um buffer para receber dados de um cliente
      InputStreamReader s = new InputStreamReader(sock.getInputStream());
      BufferedReader rec = new BufferedReader(s);

      // Lê os dados enviados pela aplicação cliente
      String rBuf = rec.readLine();

      // Apresenta os dados recebidos
      InetAddress ip = sock.getInetAddress();
      int port = sock.getPort();
      System.out.println(ip + ":" + port + ": " + rBuf);

      // Coloca a resposta em um buffer e envia para o cliente
      DataOutputStream d = new DataOutputStream(sock.getOutputStream());
      String sBuf = "Ok!\n";
      d.write(sBuf.getBytes("UTF-8"));

      // Encerra a conexão com o cliente
      sock.close();
    } catch (Exception e) {}
  }
}
