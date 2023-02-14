import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketKlient {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;

    /* Bruker en scanner til � lese fra kommandovinduet */
    Scanner leserFraKommandovindu = new Scanner(System.in);
    System.out.print("Oppgi navnet p� maskinen der tjenerprogrammet kj�rer: ");
    String tjenermaskin = leserFraKommandovindu.nextLine();

    /* Setter opp forbindelsen til tjenerprogrammet */
    Socket forbindelse = new Socket(tjenermaskin, PORTNR);
    System.out.println("N� er forbindelsen opprettet.");

    /* �pner en forbindelse for kommunikasjon med tjenerprogrammet */
    InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
    BufferedReader leseren = new BufferedReader(leseforbindelse);
    PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

    /* Leser innledning fra tjeneren og skriver den til kommandovinduet */
    String innledning1 = leseren.readLine();
    String innledning2 = leseren.readLine();
    System.out.println(innledning1 + "\n" + innledning2);

    /* Leser tekst fra kommandovinduet (brukeren) */
    String enLinje = leserFraKommandovindu.nextLine();
    while (!enLinje.equals("")) {
      skriveren.println(enLinje);  // sender teksten til tjeneren
      String respons = leseren.readLine();  // mottar respons fra tjeneren
      System.out.println("Fra tjenerprogrammet: " + respons);
      enLinje = leserFraKommandovindu.nextLine();
    }

    /* Lukker forbindelsen */
    leseren.close();
    skriveren.close();
    forbindelse.close();
  }
}
