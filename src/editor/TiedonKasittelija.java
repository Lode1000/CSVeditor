package editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TiedonKasittelija {


    /*
    Järkyttävä, järjetön ja tehoton tapa. Väliaikainen for the job, evvk
     */

    public void jarjestaAakkosittain (ArrayList<String[]> sarakkeet, int sarakkeenNumero) {
        List<String> sarakkeenSisalto = new ArrayList<>();
        for(String[] sarake : sarakkeet) {
            sarakkeenSisalto.add(sarake[sarakkeenNumero]);
        }
        Collections.sort(sarakkeenSisalto);

        // DUPLIKAATTIEN POISTAJAN täytyy poistaa ne ylärakenteesta, jotta tieto pysyy koheesina
        sarakkeenSisalto = poistaDuplikaatit(sarakkeenSisalto);

        for(int i = 0; i < sarakkeenSisalto.size(); i++) {
            sarakkeet.get(i)[sarakkeenNumero] = sarakkeenSisalto.get(i);
        }

    }

    public List<String> poistaDuplikaatit (List<String> lista) {
        return lista.stream().distinct().collect(Collectors.toList());
    }

    /*
    Leikkaa jokaisesta sarakkeesta tiettyyn kirjaimeen saakka kaiken pois
     */
    public void leikkaaAlkuPois(ArrayList<String[]> sarakkeet,int sarakkeenNumero, int leikkaus) {
        for(String[] sarake : sarakkeet) {
            System.out.println(sarake[sarakkeenNumero].length());
            sarake[sarakkeenNumero] = sarake[sarakkeenNumero].substring(leikkaus);
        }
    }

    /*
    Valitsee sarakkeen ja leikkaa siitä kaiken paitsi annettavan numeron jälkeiset asiat
     */
    public void jataViimeisetSanat(ArrayList<String[]> sarakkeet, int sarakkeenNumero) {

        ArrayList <String> nimet = new ArrayList<>();

        for(String[] sarake : sarakkeet) {
            sarake[sarakkeenNumero] = eritteleValilyonnilla(sarake[sarakkeenNumero]);
        }
    }

    /**
     *Erottelee välilyönnin perusteella Arrayksi
     *@param eroteltava Stringi joka erotellaan
     *@return kaksi viimeistä sanaa annetusta parametrista
     */
    private String eritteleValilyonnilla(String eroteltava) {
        String [] erotettu = eroteltava.split(" ");

        return erotettu[erotettu.length -2] + " " + erotettu[erotettu.length -1];
    }

}
