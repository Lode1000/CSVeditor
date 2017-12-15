package lukija;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TiedostonKasittelija {

    private String csv;

    /*
    Lukee CSV CSVn, ja palauttaa ArrayListina indeksi per rivi
     */
    public ArrayList<String> lueCSV(String csv) {
        this.csv = csv;

        try {
            return Files.lines(Paths.get(csv)).collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        System.out.println("CSV " + csv + " lukeminen epäonnistui!");
        return new ArrayList<>();
    }
}
