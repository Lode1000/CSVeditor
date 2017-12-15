package lukija;

import java.util.ArrayList;
import java.util.Scanner;

public class PaaValikko {

    private TiedostonKasittelija tiedostonKasittelija;
    private Scanner input;

    private ArrayList<String> originaaliCsv;
    private ArrayList<String> kasiteltyCsv;

    /*
    Jos tekee eri toiminnallisuuksia, niin voi pistää whilen sisälle
     */
    private static boolean onKaynnissa;
    private String tiedostoPolku;
    private String komento;


    public PaaValikko() {
        this.input = new Scanner(System.in);
    }

    public void kaynnista () {
        onKaynnissa = true;
        this.tiedostonKasittelija = new TiedostonKasittelija();

        while(onKaynnissa) {
            System.out.println("Kasiteltava tiedosto: " + this.tiedostoPolku);
            System.out.print("KOMENNNOT: " +
                    "Lataa tiedosto käsiteltäväksi: 'lataa'\n" +
                    "Käsittele tiedostoa: 'kasittele'\n" +
                    "Lopeta: 'lopeta'\n" +
                    "\n> ");

            this.komento = input.nextLine();

            if (komento.equals("lataa")) {
                lataaTiedosto();
            } else if (komento.equals("kasittele")) {
                TiedonKasittelija tiedonKasittelija = new TiedonKasittelija(originaaliCsv, input);
                ArrayList<String> nimet = tiedonKasittelija.jataViimeisetSanat(kasiteltavaSarake());
            } else if (komento.equals("lopeta")) {
                onKaynnissa = false;
            }

        }

    }

    /**
     *
     * @return Palauttaan käsiteltävän sarakkeen numeron
     */
    private int kasiteltavaSarake() {
        // Käsiteltävän sarakkeen numero
        System.out.print("Anna sarakkeen numero: ");

        return Integer.parseInt(input.nextLine());
    }

    private void lataaTiedosto() {
        System.out.print("Anna tiedostopolku: ");
        this.tiedostoPolku = input.nextLine();
        this.originaaliCsv = tiedostonKasittelija.lueCSV(tiedostoPolku);
    }

}
