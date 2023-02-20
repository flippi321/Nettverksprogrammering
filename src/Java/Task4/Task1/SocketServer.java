package Java.Task4.Task1;

import Java.Task3.Task1.CalculatorThread;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class SocketServer {
  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
    final int PORTNR = 4555;
    AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
    ServerSocket server = new ServerSocket(PORTNR);
    System.out.println("Server Created. Waiting for Input...\n\n");

    while(true){
      InetSocketAddress hostAddress = new InetSocketAddress("localhost", 2020);
      Future<Void> future = channel.connect(hostAddress);

      future.get();//wait for connection
    }
  }
}
