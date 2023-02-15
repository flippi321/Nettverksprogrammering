package Java.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CalculatorThread extends Thread {
    Socket socket;

    public CalculatorThread (Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Communicates with Client
            InputStreamReader readConnection = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(readConnection);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Send introduction to Client
            writer.println("Welcome to the Calculator Thread");
            writer.println("I will now guide you trough the math");

            // Recieve Affirmative
            String input = reader.readLine();

            // Inform Server
            System.out.println("Thread Has sent intro message");
            System.out.println(input);

            // Listen for inputs
            while (input!=null) {
                // Get first number
                writer.println("Write Your first number:");
                input = reader.readLine();
                input.replace(";", "");
                System.out.println("Got input " + input);
                int num1 = Integer.parseInt(input);

                // Get second number
                writer.println("Write Your second number:");
                input = reader.readLine();
                input.replace(";", "");
                System.out.println("Got input " + input);
                int num2 = Integer.parseInt(input);

                // Get operator
                writer.println("Type 1 for Addition and 2 for Substitution");
                input = reader.readLine();
                input.replace(";", "");
                System.out.println("Got input " + input);
                int operator = Integer.parseInt(input);

                // Do the Quick Maths
                String message = "[ERROR: INCORRECT OPERATOR INPUT]";
                if(operator == 1){
                    message = (num1 + " + " + num2 + " = " + (num1+num2));
                } else if(operator == 2){
                    message = (num1 + " - " + num2 + " = " + (num1-num2));
                }
                writer.println(message);
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}