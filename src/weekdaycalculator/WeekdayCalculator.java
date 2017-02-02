package weekdaycalculator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Diese Klasse ist der Bauplan für eine WeekdayCalculator. Momentan beinhaltet
 * sie drei Hauptmethoden. Die eine zeigt die aktuelle Zeit. Die andere
 * berechnet den Wochentag eines Datums ab 1. Januar im Jahr 1. Der Algorithmus
 * der verwendet wurde, ist auf Wikipedia ersichtlich:
 * https://de.wikipedia.org/wiki/Wochentagsberechnung Diese Methode wurde in
 * mehrere kleine Methoden aufgeteilt, wegen der Übersichtlichkeit. Die dritte
 * Methode berechnet die Tage bis zu einem eingegebenen Datum.
 *
 * @author d.peters
 */
public class WeekdayCalculator {

    /**
     *
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Hier werden die Eingaben des Benutzers eingelesen und weitergeleitet.
     */
    private int readNumber(TimeMeasurements einlesetyp) {

        int eingabe = 0;

        try {

            switch (einlesetyp) {
                case DAY:
                    System.out.println("Bitte gültigen Tag eingeben (1 - 31)");
                    eingabe = read(1, 31);
                    break;

                case MONTH:
                    System.out.println("Bitte gültigen Monat eingeben (1 - 12)");
                    eingabe = read(1, 12);
                    break;

                case YEAR:
                    System.out.println("Bitte Jahr eingeben (1 - 9999)");
                    eingabe = read(1, 9999);
                    break;

                default:
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("Ungültige Eingabe!");
        }

        return eingabe;
    }

    /**
     * So lange durchlaufen, bis eine gültige eingabe gegeben wird.
     *
     * @param min
     * @param max
     * @return
     */
    public int read(int min, int max) {

        int eingabe = 0;

        do {
            eingabe = scanner.nextInt();
        } while (eingabe < min && eingabe > max);

        return eingabe;
    }

    /**
     * Jeder Monat hat eine Monatsziffer die im Wikipediaartikel gegeben wird.
     * Man könnte sie auch berechnen, doch dann wird der Code unnötg Länger Die
     * untenstehende Methode erhält den eingegeben Monat als Parameter und sucht
     * dann im Switch nach der entsprechenden Monatsziffer, die dann
     * zurückgegeben wird.
     */
    private int monatszifferFinden(int monat) {
        int monz = 0;
        switch (monat) {
            case 1:
                monz = 0;
                break;
            case 2:
                monz = 3;
                break;
            case 3:
                monz = 3;
                break;
            case 4:
                monz = 6;
                break;
            case 5:
                monz = 1;
                break;
            case 6:
                monz = 4;
                break;
            case 7:
                monz = 6;
                break;
            case 8:
                monz = 2;
                break;
            case 9:
                monz = 5;
                break;
            case 10:
                monz = 0;
                break;
            case 11:
                monz = 3;
                break;
            case 12:
                monz = 5;
                break;
        }
        return monz;
    }

    /**
     * Die Formel zur Berechnung der Jahresziffer wird hier ausgeführt
     *
     * @param jahr
     * @return
     */
    private int jahreszifferBerechnen(int jahr) {
        int jz = jahr % 100;
        int jahrz = (jz + (jz / 4)) % 7;
        return jahrz;
    }

    /**
     * Die Formel zur Berechnung der Jahrhundertziffer wird hier ausgeführt
     */
    private int jahrhundertzifferBerechnen(int jahr) {
        int jhz = jahr / 100;
        int jahrhz = (3 - (jhz % 4)) * 2;
        return jahrhz;
    }

    /**
     * Da es Schaltjahre gibt, würde die Berechnung nicht ganz aufgehen. In
     * einem schaltjahr würden die Wochentage im Januar und Februar nicht
     * stimmen. Deshalb muss bei jedem Schaltjahr entweder -1 oder +6 zur
     * Gesamtberechnung hinzugefügt werden. Dazu wird überprüft, ob das jahr ein
     * Schaltjahr ist, und dann ob es ein Januar oder Februar ist.
     *
     * @param monat
     * @param jahr
     * @return
     */
    private int schaltJahrKorrektur(int monat, int jahr) {
        int schaltjk = 0;
        if ((jahr % 4 == 0 && jahr % 100 != 0) || jahr % 400 == 0) {
            if (monat < 3 && monat > 0) {
                schaltjk = -1;
            }

        }
        return schaltjk;
    }

    /**
     * Hier wird die Wochentagsziffer berechnet. Mit dieser kann dann der
     * richtige Wochentag ermittelt werden. Als Parameter alle vorher
     * berechneten Ziffern und die Schaltjahrkorrektur
     *
     * @param tz
     * @param mz
     * @param jz
     * @param jhz
     * @param sjk
     * @return
     */
    private String wochentagSuchen(int tz, int mz, int jz, int jhz, int sjk) {

        // Ausführung der Formel gemäss Wikipediaartikel
        int wochentagsziffer = (tz + mz + jz + jhz + sjk) % 7;
        String wt = "";

        // Zu jeder Wochentagsziffer gehört ein Wochentag
        switch (wochentagsziffer) {
            case 0:
                wt = "Sonntag";
                break;
            case 1:
                wt = "Montag";
                break;
            case 2:
                wt = "Dienstag";
                break;
            case 3:
                wt = "Mittwoch";
                break;
            case 4:
                wt = "Donnerstag";
                break;
            case 5:
                wt = "Freitag";
                break;
            case 6:
                wt = "Samstag";
                break;
        }

        return wt;
    }

    /**
     * Methode zur Anzeige der Aktuellen Zeit und des Datums
     */
    public void showTime() {

        //Anzeigeformat des Datums und der Uhrzeir hier definiert.
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        //Objekt Kalender Instanzieren
        Calendar cal = Calendar.getInstance();

        //Aktuelle Zeit holen und in Konsole ausgeben
        System.out.println("Aktuelles Datum und Uhrzeit " + dateFormat.format(cal.getTime()));
    }

    /**
     *
     */
    public void daysTillBirthday() {

        //Einlesen
        int tag, monat, jahr;

        System.out.println("Bitte gibt deinen nächsten Geburtstag ein");
        tag = readNumber(TimeMeasurements.DAY);
        monat = readNumber(TimeMeasurements.MONTH);
        jahr = readNumber(TimeMeasurements.YEAR);

        //Datum vergleichen mit ChronoUnit und anzahl Tage zwischendurch ausgeben.
        long anzahltage = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(jahr, monat, tag));
        System.out.println("Du hast in " + anzahltage + " Tagen geburtstag");
    }

    /**
     *
     */
    public void showWeekday() {

        int tag, monat, jahr;
        int tagesziffer, monatsziffer, jahresziffer, jahrhundertziffer, schaltjahrkorrektur;
        String wochentag;

        //Benuztereingaben einlesen.
        tag = readNumber(TimeMeasurements.DAY);
        monat = readNumber(TimeMeasurements.MONTH);
        jahr = readNumber(TimeMeasurements.YEAR);

        //Alle benötigten Ziffern berechnen.
        tagesziffer = tag % 7;
        monatsziffer = monatszifferFinden(monat);
        jahresziffer = jahreszifferBerechnen(jahr);
        jahrhundertziffer = jahrhundertzifferBerechnen(jahr);
        schaltjahrkorrektur = schaltJahrKorrektur(monat, jahr);

        //Wochentagsziffer Berechnen und Wochentag Holen.
        wochentag = wochentagSuchen(tagesziffer, monatsziffer, jahresziffer, jahrhundertziffer, schaltjahrkorrektur);

        //Der zum eingegebenen Datum gehörige Wochentag ausgeben in Konsole
        System.out.println();
        System.out.println("Eingegebenes Datum:");
        System.out.println("Tag: " + tag + " Monat: " + monat + " Jahr: " + jahr);
        System.out.println(wochentag);

    }
}
