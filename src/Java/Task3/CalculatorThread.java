package Java.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculatorThread extends Thread {
    InputStreamReader readConnection;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public CalculatorThread (Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Communicates with Client
            this.readConnection = new InputStreamReader(socket.getInputStream());
            this.reader = new BufferedReader(readConnection);
            this.writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Connected to Client. Type your message");

            // Reader to catch information sent by Client
            //Scanner commandReader = new Scanner(System.in);

            // Listen for inputs
            String line = reader.readLine();
            while (!line.equals("")) {
                writer.println(line);  // sender teksten til tjeneren
                String response = reader.readLine();  // mottar respons fra tjeneren
                System.out.println("Fra tjenerprogrammet: " + response);
                line = reader.readLine();
            }

            reader.close();
            writer.close();
            readConnection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List list = new ArrayList<>();

    }
}