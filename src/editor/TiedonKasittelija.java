package editor;

import java.util.ArrayList;

public class TiedonKasittelija {


    private ArrayList<String[]> sarakkeet;

    public TiedonKasittelija(ArrayList<String> originaaliCsv) {
        this.sarakkeet = eritteleSarakkeet(originaaliCsv);

    }

    public void leikkaaAlkuPois(int sarakkeenNumero, int leikkaus) {
        for(String[] sarake : sarakkeet) {
            System.out.println(sarake[sarakkeenNumero].length());
            sarake[sarakkeenNumero] = sarake[sarakkeenNumero].substring(leikkaus);
        }

    }

    /**
     *
     * @return Palauttaa muokatun CSV tiedoston
     */

    public ArrayList<String> palautaCsvMuodossa() {
        ArrayList<String> uusiCsv = new ArrayList<>();

        for (String[] sarake: sarakkeet){
            StringBuilder tiedot = new StringBuilder();
            for(int i = 0; i < sarake.length; i++) {

                if(i < sarake.length - 1) {
                    tiedot.append(sarake[i]).append(",");
                } else {
                    tiedot.append(sarake[i]);
                }

            }
            uusiCsv.add(tiedot.toString());
        }
        return uusiCsv;
    }

    /*
    Valitsee sarakkeen ja leikkaa siitä kaiken paitsi annettavan numeron jälkeiset asiat
     */
    public void jataViimeisetSanat(int sarakkeenNumero) {

        ArrayList <String> nimet = new ArrayList<>();

        for(String[] sarake : sarakkeet) {
            sarake[sarakkeenNumero] = eritteleValilyonnilla(sarake[sarakkeenNumero]);
            System.out.println(sarake[sarakkeenNumero]);
        }

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
