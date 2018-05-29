package lab05;

import java.util.Scanner;

public class Jogo {

    public static void main(String[] args) {

        try {
            Scanner start = new Scanner(System.in);
            System.out.println("Informe sue ID");
            int usuario = Integer.parseInt(start.nextLine());
            //int usuario = 4356;
            System.out.println("Informe sua senha");
            //xwojw
            String senha = start.nextLine();

            TCP tcp = new TCP(usuario, senha);
            UDP udp = new UDP(usuario, senha);

            udp.sendMessage("SEND GAME", "ENTER");

            Thread keepAlive = new Thread() {
                boolean jogar = true;

                @Override
                public void run() {
                    while (jogar) {
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

                public boolean toogleJogar() {
                    return jogar = !jogar;
                }
            };

            Thread run = new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(1000);
                            String playerList = tcp.sendAndReceiveMessage("GET PLAYERS ");
                            if (playerList.contains(usuario + ":GETTING")) {
                                String mensagem = "";
                                while (!mensagem.equals("2")) {
                                    System.out.println("1 - Comprar carta");
                                    System.out.println("2 - Parar de comprar carta");
                                    System.out.println("M - Enviar uma menssagem para um usario");
                                    System.out.println("Q - Sair do JOGO");
                                    Scanner rec = new Scanner(System.in);
                                    mensagem = rec.nextLine();

                                    if (mensagem.equals("1")) {
                                        System.out.println(tcp.sendAndReceiveMessage("GET CARD"));
                                    }
                                    if (mensagem.equalsIgnoreCase("M")) {
                                        Scanner scanner = new Scanner(System.in);
                                        System.out.println("Informe o numero do usuario que deseja enviar a mensagem\n"
                                                + "Caso deseje enviar a todos informe o numero ZERO");
                                        int num = Integer.parseInt(scanner.nextLine());
                                        System.out.println("Escreve sua mensagem");
                                        String msg = scanner.nextLine();
                                        udp.sendMessageToPlayers(num, msg);
                                    }
                                    if (mensagem.equalsIgnoreCase("Q")) {
                                        udp.sendMessage("SEND GAME", "QUIT");
                                        break;
                                    } else {
                                        udp.sendMessage("SEND GAME", "STOP");
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
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
