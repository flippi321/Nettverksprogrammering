package Java.Task4.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class SocketClient {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 80;
    String question;
    String response;

    // Setup connection to Server
    Socket connection = new Socket("LAPTOP-AKTCRTBG", PORTNR);
    System.out.println("Created connection...");

    // Open communication with Server
    InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
    BufferedReader reader = new BufferedReader(streamReader);
    PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);
    System.out.println("Communications Open...");

    // Read Introduction from Server
    String message1 = reader.readLine();
    String message2 = reader.readLine();
    System.out.println(message1 + "\n" + message2);
    // Return Success
    writer.println("Message Recieved");

    // Create scanner
    Scanner commandReader = new Scanner(System.in);
    question = reader.readLine();             // Read question from Thread

    while(!question.equals("")){
      System.out.println(question);           // Display Question
      response = commandReader.nextLine();    // Get Response from User
      writer.println(response);               // Send Response to Server
      question = reader.readLine();           // Read next question from Thread
    }

    // Close connection
    reader.close();
    writer.close();
    connection.close();
  }
}
