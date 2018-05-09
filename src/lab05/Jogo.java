package lab05;

import java.io.IOException;
import java.util.Scanner;

public class Jogo {
	public static void main(String[] args) {
		//2420:vobun
		try {
			int usuario = 2420;
			String senha = "vobun";
			TCP tcp = new TCP(usuario, senha);
			UDP udp = new UDP(usuario, senha);
			
			udp.sendMessage("SEND GAME", "ENTER");
			
			Thread keepAlive = new Thread(){
				boolean jogar = true;
				
				@Override
				public void run() {
					while(jogar){
						tcp.keepAlive();
						tcp.getMessage();
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				public boolean toogleJogar(){
					return jogar = !jogar;
				}
			};
			
			Thread run = new Thread(){
				@Override
				public void run() {
					try {
						tcp.sendAndReceiveMessage("GET PLAYERS").contains(usuario+":GETTING");
						String mensagem = ""; 
						while (!mensagem.equals("2"));{
							System.out.println("1 - Comprar carta");
							System.out.println("2 - Parar de comprar carta");
							Scanner rec = new Scanner(System.in);
							mensagem = rec.nextLine();
							if(mensagem.equals("1")){
								tcp.sendAndReceiveMessage("GET CARD");
							}
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			
			keepAlive.start();
			run.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
