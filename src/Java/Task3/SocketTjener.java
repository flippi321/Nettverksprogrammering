import java.io.*;
import java.net.*;

class SocketTjener {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;

    ServerSocket tjener = new ServerSocket(PORTNR);
    System.out.println("Logg for tjenersiden. Nå venter vi...");
    Socket forbindelse = tjener.accept();  // venter inntil noen tar kontakt

    /* Åpner strømmer for kommunikasjon med klientprogrammet */
    InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
    BufferedReader leseren = new BufferedReader(leseforbindelse);
    PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

    /* Sender innledning til klienten */
    skriveren.println("Hei, du har kontakt med tjenersiden!");
    skriveren.println("Skriv hva du vil, så skal jeg gjenta det, avslutt med linjeskift.");

    /* Mottar data fra klienten */
    String enLinje = leseren.readLine();  // mottar en linje med tekst
    while (enLinje != null) {  // forbindelsen på klientsiden er lukket
      System.out.println("En klient skrev: " + enLinje);
      skriveren.println("Du skrev: " + enLinje);  // sender svar til klienten
      enLinje = leseren.readLine();
    }

    /* Lukker forbindelsen */
    leseren.close();
    skriveren.close();
    forbindelse.close();
  }
}
