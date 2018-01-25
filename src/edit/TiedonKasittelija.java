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
     * Kömpelö treemap toistaiseksi
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

    public void jataMerkkienJalkeiset(List<String[]> sarakkeet, int sarakkeenNumero, String merkit, boolean loppu){

        for(String[] sarake : sarakkeet) {
            sarake[sarakkeenNumero] = leikkaaMerkkijononPerusteella(sarake[sarakkeenNumero], merkit, loppu);
        }
    }

    /**
     * Jättää ainoastaan annetun merkin tai merkkijonon  ennen tai jälkeen tulevat sanat. Mikäli useita, niin viimeisestä merkistä alkaen.
     * @param eroteltava Merkkijono, josta halutaan leikata
     * @param merkit Merkki tai merkkijono, jonka perusteella leikataan tätä ennen tai sen jälkeinen sisältö pois
     * @param loppu Jos true, niin jättää merkin jälkeiset, muutoin ennen merkkiä
     * @return Leikattu merkkijono ilman alku- tai loppuosaa
     */
    private String leikkaaMerkkijononPerusteella(String eroteltava, String merkit, Boolean loppu) {
        String trimmattu = eroteltava.trim();

        // Kuinka monta kirjainta otetaan palautettavaksi 'erotettu'-Arrayn lopusta
        String [] erotettu = trimmattu.split(" ");
        int lopunPituus = 0;
        for (int i = erotettu.length - 1; !erotettu[i].equals(merkit); i--) {
            if(i != erotettu.length - 1) {
                lopunPituus += erotettu[i].length() + 1; // +1 edustaa välilyöntejä
            } else {
                lopunPituus += erotettu[i].length();
            }
        }

        int merkkiIndeksi = trimmattu.length() - lopunPituus; // Merkin indeksinumero
        if(loppu){
            return trimmattu.substring(merkkiIndeksi);
        }

        return trimmattu.substring(0, merkkiIndeksi);
    }

    /**
     *
     * @return palauttaa parametrina annetun stringin käänteisenä
     */
    public String kaannaSanojenJarjestys(String sanat){
        String [] erotettu = sanat.split(" ");

        // Kääntää sanojen järjestyksen
        for(int i = 0; i < erotettu.length / 2; i++) {
            String apu = erotettu[i];

            erotettu[i] = erotettu[erotettu.length - 1 - i];
            erotettu[erotettu.length - 1 - i] = apu;
        }

        return luoStringTaulukosta(erotettu, 0, erotettu.length - 1);
    }

    /**
     * Valitsee sarakkeen ja leikkaa joka riviltä kaiken paitsi annettavan numeron jälkeiset asiat
     * @param rivit Data listassa, jossa sarakkeiden tiedot on Arrayna
     * @param sarakkeenNumero Käsiteltävä sarake
     * @param jatettavaMaara Jätettävien sanojen määrä
     */
    public void jataViimeisetSanat(List<String[]> rivit, int sarakkeenNumero, int jatettavaMaara) {

        for(String[] sarake : rivit) {
            String [] erotettu = sarake[sarakkeenNumero].split(" ");    // Joka kierroksella tekee splitin sarakkeen sisällölle
            sarake[sarakkeenNumero] = luoStringTaulukosta(erotettu, erotettu.length - jatettavaMaara, erotettu.length -1);
        }
    }

    /**
     * @param taulukko Taulukko, jonka alkioista tehdään String välilyönnillä sanojen välissä
     * @param alkuIndeksi Leikkaa pois tätä ennen olleet sanat ja merkit
     * @param loppuindeksi Leikkaa tähän saakka
     * @return Leikattu asia String muodossa
     */
    private String luoStringTaulukosta(String[] taulukko, int alkuIndeksi, int loppuindeksi){

        StringBuilder b = new StringBuilder();
        for(int i = alkuIndeksi; i <= loppuindeksi; i++){
            if (i != loppuindeksi){
                b.append(taulukko[i]).append(" ");
            } else{
                b.append(taulukko[i]);
            }
        }
        return b.toString();
    }

}
