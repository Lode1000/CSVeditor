package tui;

import java.util.Scanner;

public class Paavalikko implements Kayttoliittyma {

    private Scanner input;

    private boolean onKaynnissa;

    private static String tiedostoPolku;
    private static String tiedostoNimi;

    private String komento;

    public Paavalikko() {
        this.input = new Scanner(System.in);
        Paavalikko.tiedostoPolku = "e:\\id";
        Paavalikko.tiedostoNimi = "";
    }

    public void kaynnista () {
        onKaynnissa = true;

        while(onKaynnissa) {
            tulostaKomentolista();
            this.komento = kirjoitaKomento();

            if (komento.equals("lataa")) {
                valitseTiedostopolku();
            } else if (komento.equals("kasittele")) {
                TiedonKasittely tiedonKasittely = new TiedonKasittely(this);
                tiedonKasittely.kaynnista();
            } else if (komento.equals("lopeta")) {
                onKaynnissa = false;
            }
        }

    }

    @Override
    public void tulostaKomentolista() {
        System.out.println("Käsiteltävän tiedoston sijainti: " + Paavalikko.tiedostoPolku);
        System.out.println("Käsiteltava tiedosto: " + Paavalikko.tiedostoNimi);
        System.out.print("KOMENNNOT: " +
                "Lataa tiedosto käsiteltäväksi: 'lataa'\n" +
                "Käsittele tiedostoa: 'kasittele'\n" +
                "Lopeta: 'lopeta'\n");
    }

    private void valitseTiedostopolku() {
        System.out.println("Anna tiedostopolku: ");
        Paavalikko.tiedostoPolku = kirjoitaKomento();
        System.out.println("Anna tiedostonimi: ");
        Paavalikko.tiedostoNimi = kirjoitaKomento();
    }

    //---------------------------------------------------------------------------------------------
    /**
     *
     * @return Palauttaa kirjoitetun merkkijonon
     */
    public String kirjoitaKomento() {
        System.out.print("> ");

        return input.nextLine();
    }

    /**
     *
     * @return palauttaa, että polun lopussa on kauttaviiva
     */
    public static String getTiedostoPolku() {
        return tiedostoPolku + "\\";
    }
    /**
     *
     * @return palauttaa tiedostonimen sisältäen myös loppupäätteen
     */
    public static String getTiedostoNimi() {
        return tiedostoNimi + ".csv";
    }

    public static void setTiedostoPolku(String tiedostoPolku) {
        Paavalikko.tiedostoPolku = tiedostoPolku;
    }
    public static void setTiedostoNimi(String tiedostoNimi) {
        Paavalikko.tiedostoNimi = tiedostoNimi;
    }

}
