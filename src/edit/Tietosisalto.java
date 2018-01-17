package edit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tietosisalto {


    private List<String[]> sarakkeet;

    /**
     *
     * @param originaaliCsv OG CSV-muotoinen tiedosto
     */
    public Tietosisalto(List<String> originaaliCsv) {
        eritteleSarakkeet(originaaliCsv);
    }

    /**
     *Erottelee pilkun perusteella Arrayksi ja laittaa  listaan
     *@param originaaliCsv Alkuperäinen käsiteltävä CSV
     */
    private void eritteleSarakkeet(List<String> originaaliCsv){

        this.sarakkeet = originaaliCsv.stream()
                .map(sarake -> sarake.split(","))
                .collect(Collectors.toCollection(ArrayList::new));
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

                if(i < sarake.length - 1) { // Viimeinen sarake, joten ei pilkkua
                    tiedot.append(sarake[i]).append(",");
                } else {
                    tiedot.append(sarake[i]);
                }

            }

            uusiCsv.add(tiedot.toString());
        }
        return uusiCsv;
    }

    public List<String[]> getSarakkeet() {
        return sarakkeet;
    }
    public void setSarakkeet(List<String[]> sarakkeet) {
        this.sarakkeet = sarakkeet;
    }

}
