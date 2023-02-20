package Java.Task4.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

class SocketClient {
  final int PORTNR = 4555;
  String question;
  String response;
  byte[] buffer;
  DatagramPacket packet;
  DatagramSocket connection;

  public void run() throws IOException {
    // Setup connection to Server
    connection = new DatagramSocket(PORTNR);
    System.out.println("Created connection...");

    // Read Introduction from Server
    System.out.println(recieveMessage());

    // Return Success
    sendMessage("Message Recieved");

    // Create scanner
    Scanner commandReader = new Scanner(System.in);

    // Read Question form Thread
    question = recieveMessage();             // Read question from Thread

    while(!question.equals("")){
      System.out.println(question);         // Display Question
      response = commandReader.nextLine();  // Get Response from User
      sendMessage(response);                // Send Response to Server
      question = recieveMessage();          // Read next question from Thread
    }

    // Close connection when done
    connection.close();
  }

  private void sendMessage(String msg) throws IOException {
    buffer = (msg).getBytes(StandardCharsets.UTF_8);
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), PORTNR);
    connection.send(packet);
  }

  private String recieveMessage() throws IOException {
    buffer = new byte[1500]; // Creates a new buffer to read messages into.
    packet = new DatagramPacket(buffer, buffer.length);
    connection.receive(packet); // Ready to receive a packet. Stores the packet into the packet-object.
    return Arrays.toString(packet.getData()).replace(";", "");
  }

  public static void main(String[] args) throws IOException {
    SocketClient client = new SocketClient();
    client.run();
  }
}




