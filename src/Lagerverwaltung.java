import java.util.Scanner;

public class Lagerverwaltung {
    public static void main(String[] args) {
        Lager lager = new Lager();
        lager.ladeDatei();

        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                    \n=== Lagerverwaltung ===
                    1. Produkt einlagern
                    2. Produkt auslagern
                    3. Bestand anzeigen
                    4. Neues Produkt anlegen
                    5. Produkt entfernen
                    6. Beenden
                    """);
            String eingabe = getProduct("Auswahl: ", scan);

            switch (eingabe) {
                case "1" -> {
                    if (bestandCheck(lager)) break;
                    String name = getProduct("Produktname: ", scan);
                    try {
                        lager.nameCheck(name);
                        System.out.print("Menge: ");
                        int menge = Integer.parseInt(scan.nextLine());
                        lager.produktEinlagern(name, menge);
                    } catch (NumberFormatException e) {
                        System.out.println("Ungültige Zahl eingegeben!");
                    } catch (ExitException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "2" -> {
                    if (bestandCheck(lager)) break;
                    String name = getProduct("Produktname: ", scan);
                    System.out.print("Menge: ");
                    try {
                        int menge = Integer.parseInt(scan.nextLine());
                        lager.produktAuslagern(name, menge);
                    } catch (NumberFormatException e) {
                        System.out.println("Ungültige Zahl eingegeben!");
                    }
                }
                case "3" -> lager.bestandAnzeigen();
                case "4" -> {
                    String name = getProduct("Produktname: ", scan);
                    System.out.print("Menge: ");
                    try {
                        int menge = Integer.parseInt(scan.nextLine());
                        lager.produktEinlagern(name, menge);
                    } catch (NumberFormatException e) {
                        System.out.println("Ungültige Zahl eingegeben!");
                    }
                }
                case "5" -> {
                    if (bestandCheck(lager)) break;
                    String name = getProduct("Produktname: ", scan);
                    lager.produktEntfernen(name);
                }
                case "6" -> {
                    running = false;
                    lager.speichereDatei();
                    System.out.println("Programm beendet. Daten gespeichert.");
                }
                default -> System.out.println("Ungültige Auswahl!");
            }
        }
        scan.close();
    }

    private static boolean bestandCheck(Lager lager) {
        try {
            lager.bestandPruefen();
        }
        catch (ExitException e) {
            System.out.println(e.getMessage());
            return true;
        }
        return false;
    }

    private static String getProduct(String s, Scanner scan) {
        System.out.print(s);
        return scan.nextLine();
    }
}
