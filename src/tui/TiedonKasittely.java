package tui;

import edit.TiedonKasittelija;
import edit.TiedostonKasittelija;
import edit.Tietosisalto;

import java.util.List;

public class TiedonKasittely implements Kayttoliittyma{

    private Paavalikko paavalikko;

    private TiedonKasittelija tiedonKasittelija;
    private Tietosisalto tietosisalto;
    private TiedostonKasittelija tiedostonKasittelija;

    private List<String> originaaliCsv;
    private List<String> kasiteltyCsv;

    private boolean onKaynnissa;
    private String komento;

    //----------------------Var end----------------------

    TiedonKasittely(Paavalikko paavalikko){
        this.paavalikko = paavalikko;
    }

    @Override
    public void tulostaKomentolista() {
        System.out.print("KOMENNNOT: " +
                "Suorita kaikki toimenpiteet: '1'\n" +
                "Tallenna tiedosto: '2'\n" +
                "Järjestä aakkosittain: '3'\n" +
                "Jätä viimeiset sanat: '4'\n" +
                "Palaa päävalikkoon: '0'\n");
    }

    @Override
    public void kaynnista() {
        this.onKaynnissa = true;

        this.tiedostonKasittelija = new TiedostonKasittelija();
        lataaTiedosto();    // Lataa originaaliCsv-muuttujan seuraavaa riviä varten
        this.tietosisalto = new Tietosisalto(originaaliCsv);
        this.tiedonKasittelija = new TiedonKasittelija();

        while(onKaynnissa) {
            tulostaKomentolista();
            this.komento = paavalikko.kirjoitaKomento();

            if (komento.equals("1")) {
                suoritaKaikkiToimenpiteet();
                tulostaKasiteltyCsv();
            } else if (komento.equals("2")) {
                System.out.print("Anna tiedostonimi: ");
                String tiedostonimi = paavalikko.kirjoitaKomento() + ".csv";
                tiedostonKasittelija.kirjoitaUusiCsv(tiedostonimi, kasiteltyCsv);
            } else if (komento.equals("0")) {
                onKaynnissa = false;
            } else if (komento.equals("3")) {
                System.out.println("Valitse käsiteltävä sarake");
                tiedonKasittelija.jarjestaAakkosittain(tietosisalto, 2,true);
                //tietosisalto.setSarakkeet(tiedonKasittelija.jarjestaAakkosittain(tietosisalto.getSarakkeet(),2, true));
                this.kasiteltyCsv = tietosisalto.palautaCsvMuodossa();
            }else if (komento.equals("4")) {
                // tiedonKasittelija.jataViimeisetSanat(tietosisalto.getSarakkeet(),2);
                this.kasiteltyCsv = tietosisalto.palautaCsvMuodossa();
            }
        }
    }

    private void lataaTiedosto(){
        this.originaaliCsv = tiedostonKasittelija.lueCsvTiedosto(Paavalikko.getTiedostoPolku() + Paavalikko.getTiedostoNimi());
        if(originaaliCsv.isEmpty()) {
            Paavalikko.setTiedostoPolku("");
            Paavalikko.setTiedostoNimi("");
            System.out.println("Tiedostoa ei ole olemassa");
            onKaynnissa = false;
        }
    }

    private void suoritaKaikkiToimenpiteet(){
        tiedonKasittelija.jataMerkkienJalkeiset(tietosisalto.getSarakkeet(),2,"-");
        // tiedonKasittelija.jataViimeisetSanat(tietosisalto.getSarakkeet(),2, 2);
        tiedonKasittelija.jarjestaAakkosittain(tietosisalto, 2,true);
        tiedonKasittelija.poistaDuplikaatit(tietosisalto.getSarakkeet(),2);

        this.kasiteltyCsv = tietosisalto.palautaCsvMuodossa();
    }

    private void tulostaKasiteltyCsv(){
        for (String tieto : this.kasiteltyCsv) {
            System.out.println(tieto);
        }
    }


}
