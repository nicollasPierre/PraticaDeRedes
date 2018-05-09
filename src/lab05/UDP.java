package lab05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDP {
	
	private InetAddress ip;
	private int port;
	private String usuario;
	
	public UDP(String address, int port) throws UnknownHostException{
		ip = InetAddress.getByName(address);
		this.port = port;
	}
	
	public UDP(int usuario, String senha) throws UnknownHostException{
		this.usuario = usuario + ":" + senha;
		//"larc.inf.furb.br", 1012
		ip = InetAddress.getByName("larc.inf.furb.br");
		
		this.port = 1012;
	}
	
	public void sendMessage(String mensagem) throws IOException{
		sendMessage(mensagem, "");
	}
	
	public void sendMessage(String mensagem, String parametro) throws IOException{
		mensagem = mensagem + " " + usuario + ":" + parametro +"\n\r";
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
