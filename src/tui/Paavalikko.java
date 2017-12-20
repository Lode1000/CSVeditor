package tui;

import editor.TiedonKasittelija;
import editor.TiedostonKasittelija;
import editor.Tietosisalto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Paavalikko {

    private TiedostonKasittelija tiedostonKasittelija;
    private Scanner input;

    private List<String> originaaliCsv;
    private List<String> kasiteltyCsv;

    /*
    Muuttujat käyttöliittymälle
     */
    private static boolean onKaynnissa;
    private String tiedostoPolku;
    private String komento;


    public Paavalikko() {
        this.input = new Scanner(System.in);
        this.tiedostoPolku = "";
    }

    public void kaynnista () {
        onKaynnissa = true;
        this.tiedostonKasittelija = new TiedostonKasittelija();

        while(onKaynnissa) {
            System.out.println("Käsiteltava tiedosto: " + this.tiedostoPolku);
            System.out.print("KOMENNNOT: " +
                    "Lataa tiedosto käsiteltäväksi: 'lataa'\n" +
                    "Käsittele tiedostoa: 'kasittele'\n" +
                    "Lopeta: 'lopeta'\n" +
                    "\n> ");

            this.komento = input.nextLine();

            if (komento.equals("lataa")) {
                lataaTiedosto();
            } else if (komento.equals("kasittele")) { // KAIKKI tämän alla uuteen luokkaan. Äärimmäisen väliaikainen
                TiedonKasittelija tiedonKasittelija = new TiedonKasittelija();
                Tietosisalto tietosisalto = new Tietosisalto(originaaliCsv);

                tiedonKasittelija.jataViimeisetSanat(tietosisalto.getSarakkeet(),2);
                tietosisalto.setSarakkeet(tiedonKasittelija.jarjestaAakkosittain(tietosisalto.getSarakkeet(),2, true));

                tiedonKasittelija.poistaDuplikaatit(tietosisalto.getSarakkeet(),2);

                this.kasiteltyCsv = tietosisalto.palautaCsvMuodossa();
                for (String tieto : this.kasiteltyCsv) {
                    System.out.println(tieto);
                }
                System.out.print("Anna tiedostonimi: ");
                String tiedostonimi = input.nextLine() + ".csv";
                tiedostonKasittelija.kirjoitaUusiCsv(tiedostonimi, kasiteltyCsv);
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
        if(originaaliCsv.isEmpty()) {
            this.tiedostoPolku = "";
        }
    }

}
