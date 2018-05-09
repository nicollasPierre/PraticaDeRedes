package exemplos;
import java.io.*;
import java.net.*;

class ClienteTCP {
  public static void main(String args[]) {
    try {
      // Cria um socket TCP para conexão com localhost:8000
      Socket sock = new Socket("larc.inf.furb.br", 1012);

      // Coloca os dados em um buffer e envia para o servidor
      DataOutputStream d = new DataOutputStream(sock.getOutputStream());
      String sBuf = "GET MESSAGE 2420:vobun\n\r";
      d.write(sBuf.getBytes("UTF-8"));

      // Prepara um buffer para receber a resposta do servidor
      InputStreamReader s = new InputStreamReader(sock.getInputStream());
      BufferedReader rec = new BufferedReader(s);

      // Lê os dados enviados pela aplicação servidora
      String rBuf = rec.readLine();

      // Apresenta a resposta recebida
      InetAddress ip = sock.getInetAddress();
      int port = sock.getPort();
      System.out.println(ip + ":" + port + ": " + rBuf);

      // Encerra a conexão com o servidor
      sock.close();
    } catch (Exception e) {}
  }
}
