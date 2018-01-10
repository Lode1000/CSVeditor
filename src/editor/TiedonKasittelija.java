package editor;

import java.util.*;
import java.util.stream.Collectors;

public class TiedonKasittelija {


    /**
     * Järjesttelee sarakenumeron perusteella luonnolliseen järjestykseen
     *@param sarakkeenNumero Sarake, jonka mukaan halutaan sortata
     */

    public List<String[]> jarjestaAakkosittain (List<String[]> sarakkeet, int sarakkeenNumero, boolean nouseva) {

        if(nouseva) {
            return sarakkeet.stream()
                    .sorted(Comparator.comparing(rivi -> rivi[sarakkeenNumero]))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            return sarakkeet.stream()
                    .sorted((rivi1, rivi2) -> rivi2[sarakkeenNumero].compareTo(rivi1[sarakkeenNumero]))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    /**
     * Treemappina toistaiseksi
     * Jossain vaiheessa muutos lambdaan, täytyy poistaa ne ylärakenteesta, jotta tieto pysyy koheesina
     * Myös return pohjaiseksi, jotta käyttöliittymä on helpompi toteuttaa
     */

    public void poistaDuplikaatit (List<String[]> sarakkeet, int sarakkeenNumero) {
        //return lista.stream().distinct().collect(Collectors.toList());

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
    public void jataViimeisetSanat(List<String[]> sarakkeet, int sarakkeenNumero, int jatettavaMaara) {

        ArrayList <String> nimet = new ArrayList<>();

        for(String[] sarake : sarakkeet) {
            sarake[sarakkeenNumero] = eritteleValilyonnilla(sarake[sarakkeenNumero], jatettavaMaara);
        }
    }

    /**
     *Erottelee välilyönnin perusteella Arrayksi
     *@param eroteltava Stringi joka erotellaan
     *@return kaksi viimeistä sanaa annetusta parametrista
     */
    private String eritteleValilyonnilla(String eroteltava, int jatettavaMaara) {
        String [] erotettu = eroteltava.split(" ");

        StringBuilder apu = new StringBuilder();
        for(int i = jatettavaMaara; i >= 1; i--){
            if (i != 1){
                apu.append(erotettu[erotettu.length - i]).append(" ");
            } else {
                apu.append(erotettu[erotettu.length - i]);
            }

        }

        return apu.toString();
    }

}
