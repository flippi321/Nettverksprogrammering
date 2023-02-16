package Java.Task3.Task2;

import java.io.*;
import java.net.*;

class SocketServer2 {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 80;
    ServerSocket server = new ServerSocket(PORTNR);
    System.out.println("Server Created. Waiting for Input...\n\n");
    while(true){
      Socket connection = server.accept();  // Waiting for connection
      Thread webClient = new Thread(new SocketWebClient(connection));
      webClient.start();
    }
  }
}