package editor;

import java.util.*;
import java.util.stream.Collectors;

public class TiedonKasittelija {


    /**
    Treenap sorttaa aakkosiin ja poistaa myös duplikaatit
    Tehoton viritys, väliaikainen for the job, evvk
    -> Täytyy järjestellä ylärakenteesta, jotta tieto pysyy koheesina
     *@param sarakkeenNumero Sarake, jonka mukaan halutaan sortata
     */

    public void jarjestaAakkosittain (ArrayList<String[]> sarakkeet, int sarakkeenNumero) {

        Map<String, String[]> treeMap = new TreeMap<>();
        for(String[] sarake : sarakkeet) {
            treeMap.put(sarake[sarakkeenNumero], sarake);
        }


        int i = 0;
        sarakkeet.clear();
        for (Map.Entry<String, String[]> entry : treeMap.entrySet()) {
            sarakkeet.add(entry.getValue());
        }

    }

    //Tarvitaan myöhemmin, kun aakkostus on erotettu duplikaattien poistosta
    // DUPLIKAATTIEN POISTAJAN täytyy poistaa ne ylärakenteesta, jotta tieto pysyy koheesina
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
