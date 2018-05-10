package lab05;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP {

	private Socket sock;
	private String usuario;

	public TCP(int usuario, String senha) throws IOException {
		this.usuario = usuario + ":" + senha;
		// Cria um socket TCP para conexão com localhost:8000
		sock = new Socket("larc.inf.furb.br", 1012);
	}
	

	public void sendMessage(String mensagem) throws IOException {
		DataOutputStream d = new DataOutputStream(sock.getOutputStream());
		String sBuf = mensagem + " " + usuario + "\n\r";
		System.out.println(sBuf);
		d.write(sBuf.getBytes("UTF-8"));

	}
	
	public String sendAndReceiveMessage(String mensagem) throws IOException {
		sendMessage(mensagem);
		return receiveMessage();
	}

	public String receiveMessage() throws IOException {
		InputStreamReader s = new InputStreamReader(sock.getInputStream());
		BufferedReader rec = new BufferedReader(s);
		String log = rec.readLine();
		// Lê os dados enviados pela aplicação servidora
		return log;
	}
	
	public void getMessage(){
		try {
			System.out.println(sendAndReceiveMessage("GET MESSAGE"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void keepAlive(){
		try {
			System.out.println(sendAndReceiveMessage("GET USERS"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
