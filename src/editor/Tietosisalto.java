package editor;

import java.util.ArrayList;

public class Tietosisalto {


    private ArrayList<String[]> sarakkeet;

    /**
     *
     * @param originaaliCsv OG-CSV tiedosto
     */

    public Tietosisalto(ArrayList<String> originaaliCsv) {
        eritteleSarakkeet(originaaliCsv);
    }

    /**
     *Erottelee pilkun perusteella Arrayksi, ja laittaa Arrayn listaan joka palautetaan
     *@param originaaliCsv Alkuperäinen käsiteltävä CSV
     */

    private void eritteleSarakkeet(ArrayList<String> originaaliCsv){
        ArrayList<String[]> sarakkeet = new ArrayList<>();

        for (String rivi : originaaliCsv) {
            sarakkeet.add(rivi.split(","));
        }

        this.sarakkeet = sarakkeet;
    }

    public ArrayList<String[]> getSarakkeet() {
        return sarakkeet;
    }

    /**
     *
     * @return Palauttaa muokatun CSV tiedoston ArrayList muodossa
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
}
