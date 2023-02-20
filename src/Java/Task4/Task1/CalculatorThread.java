package Java.Task4.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CalculatorThread extends Thread {
    int PORTNR;
    InetAddress address;
    DatagramSocket socket;
    byte[] buffer;
    DatagramPacket packet;

    public CalculatorThread(DatagramSocket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Recieve Handshake
            buffer = new byte[1500]; // Creates a new buffer to read messages into.
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet); // Ready to receive a packet. Stores the packet into the packet-object.

            // Acquire information from handshake
            address = packet.getAddress();
            PORTNR = packet.getPort();


            // Send introduction to Client
            sendMessage("Welcome to the Calculator Thread");

            // Receiving Affirmative
            // Inform Server
            System.out.println("Thread Has sent intro message:");
            System.out.println(recieveMessage());

            // Listen for inputs
            while (packet.getData().length!=0) {
                // Get first number
                int num1 = getNumber("Write your first number:");
                int num2 = getNumber("Write your second number:");

                int operator = getNumber("Type 1 for Addition and 2 for Substitution");

                // Do the Quick Maths
                String message = "[ERROR: INCORRECT OPERATOR INPUT]";
                if(operator == 1){
                    message = (num1 + " + " + num2 + " = " + (num1+num2));
                } else if(operator == 2){
                    message = (num1 + " - " + num2 + " = " + (num1-num2));
                }

                // Send Response
                sendMessage(message);
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String msg) throws IOException {

        buffer = (msg).getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORTNR);
        socket.send(packet);
    }

    private String recieveMessage() throws IOException {
        buffer = new byte[1500]; // Creates a new buffer to read messages into.
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet); // Ready to receive a packet. Stores the packet into the packet-object.
        return new String(packet.getData()).trim().replace(";", "");
    }

    private int getNumber(String msg) throws IOException {
        // Send Message to Client
        sendMessage(msg);

        // Receiving Affirmative
        String message = recieveMessage();
        System.out.println(message);
        return Integer.parseInt(message);
    }
}