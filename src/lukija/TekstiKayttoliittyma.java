package lukija;

import java.util.ArrayList;
import java.util.Scanner;

public class TekstiKayttoliittyma {

    private TiedostonKasittelija tiedostonKasittelija;
    private Scanner input;

    private ArrayList<String> originaaliCsv;
    private ArrayList<String> kasiteltyCsv;

    /*
    Jos tekee eri toiminnallisuuksia, niin voi pistää whilen sisälle
     */
    private boolean onKaynnissa;


    public TekstiKayttoliittyma() {
        this.input = new Scanner(System.in);

        System.out.print("Anna tiedostopolku: ");
        this.tiedostonKasittelija = new TiedostonKasittelija(input.nextLine());
        this.onKaynnissa = true;
    }

    public void kaynnista () {
        this.originaaliCsv = tiedostonKasittelija.lueCSV();
        ArrayList<String> nimet = jataViimeisetSanat();
    }

    /*
    Valitsee sarakkeen ja leikkaa siitä kaiken paitsi annettavan numeron jälkeiset asiat
     */

    private void leikkaaAlkuPois() {
        System.out.print("Anna sarakkee numero: ");
        int sarakkeenNumero = Integer.parseInt(input.nextLine());
        System.out.print("Anna leikattava kohta: ");
        int leikkaus = Integer.parseInt(input.nextLine());

        ArrayList <String[]> sarakkeet = eritteleSarakkeet();
        for(String[] sarake : sarakkeet) {
            System.out.println(sarake[sarakkeenNumero].length());
            sarake[sarakkeenNumero] = sarake[sarakkeenNumero].substring(leikkaus);
        }

    }

    private int kasiteltavaSarake() {
        // Käsiteltävän sarakkeen numero
        System.out.print("Anna sarakkee numero: ");

        return Integer.parseInt(input.nextLine());
    }

    private ArrayList<String> jataViimeisetSanat() {
        int sarakkeenNumero = kasiteltavaSarake();

        ArrayList <String[]> sarakkeet = eritteleSarakkeet();
        ArrayList <String> nimet = new ArrayList<>();

        for(String[] sarake : sarakkeet) {
            sarake[sarakkeenNumero] = eritteleValilyonnilla(sarake[sarakkeenNumero]);
            nimet.add(eritteleValilyonnilla(sarake[sarakkeenNumero]));
            System.out.println(sarake[sarakkeenNumero]);
        }

        return nimet;
    }

    /*
    Splittaa ',' perusteella CSV sarakkeet
     */
    private ArrayList<String[]> eritteleSarakkeet(){
        ArrayList<String[]> sarakkeet = new ArrayList<>();

        for(int i = 0; i < originaaliCsv.size(); i++) {
            sarakkeet.add(originaaliCsv.get(i).split(","));
        }

        return sarakkeet;
    }

    /**
     *Erottelee välilyönnin perusteella Arrayksi
     *@param eroteltava Stringi joka erotellaan
     *@return kaksi viimeistä sanaa annetusta parametrista
     */
    private String eritteleValilyonnilla(String eroteltava) {
        String [] erotettu = eroteltava.split(" ");

        return erotettu[erotettu.length -1] + " " + erotettu[erotettu.length -2];
    }



}
