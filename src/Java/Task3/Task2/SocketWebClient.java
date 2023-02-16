package Java.Task3.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWebClient implements Runnable{
    Socket socket;

    public SocketWebClient (Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Communicates with the Web-Client
            InputStreamReader readConnection = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(readConnection);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Send introduction to Client
            String response = """
                    HTTP/1.1 200 OK\r
                    Content-Type: text/html\r
                    \r
                    """;
            writer.println(response);

            // Add text to website
            String html = "<HTML><BODY>";
            html += "<H1> Hilsen. Du har koblet deg opp til min enkle web-tjener </H1>";
            html += " </H1>Header fra klient er:";
            html += "<UL>";

            // Recieve Message from Browser
            String input = reader.readLine();

            // Listen for inputs
            while (input!=null && !input.equals("")) {
                html += "<LI>";
                html += input;
                html += "</LI>";
                input = reader.readLine();
            };
            html += "</UL>";
            html += "</BODY></HTML>";
            writer.println(html);

            Thread.sleep(1000);
            socket.close();
            System.out.println("Ending a connection.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
