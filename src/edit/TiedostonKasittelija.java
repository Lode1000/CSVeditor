package edit;

import tui.Paavalikko;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TiedostonKasittelija {

    private String csv;

    /*
    Lukee tiedoston, ja palauttaa ArrayListina indeksi per rivi
     */
    public List<String> lueCsvTiedosto(String tiedostopolku) {
        this.csv = tiedostopolku;

        try {
            return Files.lines(Paths.get(tiedostopolku)).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        System.out.println("CSV " + tiedostopolku + " lukeminen epäonnistui!");
        return new ArrayList<>();
    }

    public void kirjoitaUusiCsv(String tiedostoNimi, List<String> uusiCsv) {
        try {
            Files.write(Paths.get(Paavalikko.getTiedostoPolku() + tiedostoNimi), uusiCsv, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Tiedoston " + tiedostoNimi + " kirjoittaminen ei onnistunut.");
        }
    }
}
