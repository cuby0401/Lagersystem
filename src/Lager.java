import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lager {
    private final List<Produkt> produkte = new ArrayList<>();
    private final String dateiName = "lager.txt";

    // Datei beim Start laden
    public void ladeDatei() {
        try (BufferedReader br = new BufferedReader(new FileReader(dateiName))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                String[] teile = zeile.split(";");
                if (teile.length == 2) {
                    try {
                        String name = teile[0];
                        int menge = Integer.parseInt(teile[1]);
                        produkte.add(new Produkt(name, menge));
                    } catch (NumberFormatException e) {
                        System.out.println("Fehlerhafte Zahl in Datei ignoriert: " + zeile);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden, es wird eine neue angelegt.");
        } catch (IOException e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }
    }

    // Speichern
    public void speichereDatei() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateiName))) {
            for (Produkt p : produkte) {
                bw.write(p.getName() + ";" + p.getMenge());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    // Produkte verwalten
    public void produktEinlagern(String name, int menge) {
        Produkt p = findeProdukt(name);
        if (p != null) {
            p.setMenge(p.getMenge() + menge);
        } else {
            produkte.add(new Produkt(name, menge));
        }
    }

    public void produktAuslagern(String name, int menge) {
        Produkt p = findeProdukt(name);
        if (p != null) {
            int neu = p.getMenge() - menge;
            if (neu < 0) {
                System.out.println("Nicht genug Bestand. Aktion abgebrochen.");
            } else {
                p.setMenge(neu);
            }
        } else {
            System.out.println("Produkt nicht gefunden.");
        }
    }

    public void produktEntfernen(String name) {
        produkte.removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    public Produkt findeProdukt(String name) {
        for (Produkt p : produkte) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public void bestandAnzeigen() {
        if (produkte.isEmpty()) {
            System.out.println("Lager ist leer.");
        } else {
            for (Produkt p : produkte) {
                System.out.println(p);
            }
        }
    }

    public void bestandPruefen() {
        //Prüfen ob es den Namen bereits gibt
        if (produkte.isEmpty()) {
            throw new ExitException("Füge zuerst Bestand hinzu.");
        }
    }

    public void nameCheck(Scanner scan) {
        if (!produkte.contains(scan)) {
            throw new ExitException("Produkt nicht gefunden.");
        }
    }
}