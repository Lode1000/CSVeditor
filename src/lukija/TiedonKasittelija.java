package lukija;

import java.util.ArrayList;
import java.util.Scanner;

public class TiedonKasittelija {

    private final Scanner input;
    private ArrayList<String[]> sarakkeet;

    public TiedonKasittelija(ArrayList<String> originaaliCsv, Scanner input) {
        this.sarakkeet = eritteleSarakkeet(originaaliCsv);
        this.input = input;
    }

    public void leikkaaAlkuPois() {
        System.out.print("Anna sarakkee numero: ");
        int sarakkeenNumero = Integer.parseInt(input.nextLine());
        System.out.print("Anna leikattava kohta: ");
        int leikkaus = Integer.parseInt(input.nextLine());

        for(String[] sarake : sarakkeet) {
            System.out.println(sarake[sarakkeenNumero].length());
            sarake[sarakkeenNumero] = sarake[sarakkeenNumero].substring(leikkaus);
        }

    }

    /*
    Valitsee sarakkeen ja leikkaa siitä kaiken paitsi annettavan numeron jälkeiset asiat
     */

    public ArrayList<String> jataViimeisetSanat(int sarakkeenNumero) {

        ArrayList <String> nimet = new ArrayList<>();

        for(String[] sarake : sarakkeet) {
            sarake[sarakkeenNumero] = eritteleValilyonnilla(sarake[sarakkeenNumero]);
            nimet.add(eritteleValilyonnilla(sarake[sarakkeenNumero]));
            System.out.println(sarake[sarakkeenNumero]);
        }

        return nimet;
    }

    /**
     *Erottelee välilyönnin perusteella Arrayksi
     *@param originaaliCsv Alkuperäinen käsiteltävä CSV
     *@return Lista, jossa jokaisessa indeksissä on sarakkeet eroteltuina Arrayn alkioihin
     */

    private ArrayList<String[]> eritteleSarakkeet(ArrayList<String> originaaliCsv){
        ArrayList<String[]> sarakkeet = new ArrayList<>();

        for (String rivi : originaaliCsv) {
            sarakkeet.add(rivi.split(","));
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
