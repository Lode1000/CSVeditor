package edit;

import java.util.*;
import java.util.stream.Collectors;

public class TiedonKasittelija {


    /**
     * Järjesttelee sarakenumeron perusteella luonnolliseen järjestykseen
     *@param sarakkeenNumero Sarake, jonka mukaan halutaan sortata
     */

    public void jarjestaAakkosittain (Tietosisalto tietosisalto, int sarakkeenNumero, boolean nouseva) {


        if(nouseva) {
            tietosisalto.setSarakkeet(tietosisalto.getSarakkeet().stream()
                    .sorted(Comparator.comparing(rivi -> rivi[sarakkeenNumero]))
                    .collect(Collectors.toCollection(ArrayList::new)));
        } else {
            tietosisalto.setSarakkeet(tietosisalto.getSarakkeet().stream()
                    .sorted((rivi1, rivi2) -> rivi2[sarakkeenNumero].compareTo(rivi1[sarakkeenNumero]))
                    .collect(Collectors.toCollection(ArrayList::new)));
        }
    }

    /**
     * Treemappina toistaiseksi
     * Jossain vaiheessa muutos lambdaan, täytyy poistaa ne ylärakenteesta, jotta tieto pysyy koheesina
     * Myös return pohjaiseksi, jotta käyttöliittymä on helpompi toteuttaa
     */

    public void poistaDuplikaatit (List<String[]> rivit, int sarakkeenNumero) {
        //return lista.stream().distinct().collect(Collectors.toList());

        Map<String, String[]> treeMap = new TreeMap<>();
        for(String[] sarake : rivit) {
            treeMap.put(sarake[sarakkeenNumero], sarake);
        }

        int i = 0;
        rivit.clear();
        for (Map.Entry<String, String[]> entry : treeMap.entrySet()) {
            rivit.add(entry.getValue());
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


    public void jataMerkkienJalkeiset(List<String[]> sarakkeet, int sarakkeenNumero, String merkit){

        for(String[] sarake : sarakkeet) {
            sarake[sarakkeenNumero] = leikkaaLoppuMerkkijononPerusteella(sarake[sarakkeenNumero], merkit);
        }
    }

    private String leikkaaLoppuMerkkijononPerusteella(String eroteltava, String merkit) {
        String [] erotettu = eroteltava.split(" ");

        StringBuilder apu = new StringBuilder();

        boolean viimeinenMerkkijono = false;
        int alkio = erotettu.length - 1;

        while(!viimeinenMerkkijono) {
            if(!erotettu[alkio].equals(merkit)){
                apu.append(erotettu[alkio]).append(" ");
            } else{
                viimeinenMerkkijono = true;
            }
            alkio--;
        }

        return kaannaSanojenJarjestys(apu.toString());
        /*
        while(viimeinenMerkkijono == false){
            eroteltava = eroteltava.substring(eroteltava.indexOf(merkit) + 1);
            if(eroteltava.substring(eroteltava.indexOf(merkit) + 1) == )
        }
        */
    }

    private String kaannaSanojenJarjestys(String sanat){
        String [] erotettu = sanat.split(" ");
        String [] kaannetty = new String[erotettu.length];

        int index = 0;
        for(int i = erotettu.length - 1; i >= 0; i--) {
            kaannetty[index] = erotettu[i];
            index++;
        }

        StringBuilder b = new StringBuilder();
        for(int i = 0; i < erotettu.length; i++){
            if (i != erotettu.length-1){
                b.append(kaannetty[i]).append(" ");
            } else{
                b.append(kaannetty[i]);
            }
        }
        return b.toString();
    }
    /*
    Valitsee sarakkeen ja leikkaa siitä kaiken paitsi annettavan numeron jälkeiset asiat
     */
    public void jataViimeisetSanat(List<String[]> sarakkeet, int sarakkeenNumero, int jatettavaMaara) {

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
