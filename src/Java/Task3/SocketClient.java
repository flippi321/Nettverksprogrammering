package Java.Task3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1001;

    // Setup connection to Server
    Socket connection = new Socket("LAPTOP-AKTCRTBG", PORTNR);
    System.out.println("Created connection... \n");

    // Open communication with Server
    InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
    BufferedReader reader = new BufferedReader(streamReader);
    PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);

    // Write welcome-message from Thread
    System.out.println("Welcome Message:");
    System.out.println(reader.readLine());

    //Create Scanner to read from Commandline
    Scanner commandReader = new Scanner(System.in);
    String line = commandReader.nextLine();

    // Continuously reads from command line
    while (!line.equals("")) {
      writer.println(line);  // sender teksten til tjeneren
      String response = reader.readLine();  // mottar respons fra tjeneren
      System.out.println("Fra tjenerprogrammet: " + response);
      line = commandReader.nextLine();
    }

    // Close connection
    reader.close();
    writer.close();
    connection.close();
  }
}
