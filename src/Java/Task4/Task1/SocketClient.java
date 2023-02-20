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
  DatagramPacket packet;
  static DatagramSocket connection;

  public static void main(String[] args) throws IOException {
    final int PORTNR = 4555;
    String question;
    String response;
    byte[] buffer;

    connection = new DatagramSocket();
    System.out.println("Created connection...");

    // Send handshake to Thread
    sendMessage("Handshake");

    // Read Introduction from Server
    System.out.println("Waiting for connection...");
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

  public static void sendMessage(String msg) throws IOException {
    byte[] buffer = (msg).getBytes(StandardCharsets.UTF_8);
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("127.0.0.1"), 4555);
    connection.send(packet);
  }

  public static String recieveMessage() throws IOException {
    byte[] buffer = new byte[1500]; // Creates a new buffer to read messages into.
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    connection.receive(packet); // Ready to receive a packet. Stores the packet into the packet-object.
    return new String(packet.getData()).trim().replace(";", "");
  }
}




