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
 * @author Daniel Peters
 * @version 1.0
 */
public class WeekdayCalculator {
  private final Scanner scanner = new Scanner(System.in);

  /**
   * Get input as number.
   */
  private int readNumber(TimeMeasurements measurements) {
    int input = 0;

    try {
      switch (measurements) {
        case DAY:
          System.out.println("Please enter valid day (1 - 31)");
          input = read(1, 31);
          break;

        case MONTH:
          System.out.println("Please enter valid month (1 - 12)");
          input = read(1, 12);
          break;

        case YEAR:
          System.out.println("Please enter valid year (1 - 9999)");
          input = read(1, 9999);
          break;

        default:
          break;
      }
    } catch (InputMismatchException e) {
      System.out.println("Invalid input!");
    }

    return input;
  }

  /**
   * Read until valid input is given.
   *
   * @param min minimum value
   * @param max maximum value
   * @return input number
   */
  private int read(int min, int max) {
    int input;

    do {
      input = scanner.nextInt();
    } while (input < min && input > max);

    return input;
  }

  /**
   * Jeder Monat hat eine Monatsziffer die im Wikipediaartikel gegeben wird.
   * Man könnte sie auch berechnen, doch dann wird der Code unnötg Länger Die
   * untenstehende Methode erhält den eingegeben Monat als Parameter und sucht
   * dann im Switch nach der entsprechenden Monatsziffer, die dann
   * zurückgegeben wird.
   */
  private int monatszifferFinden(int month) {
    int monz = 0;
    switch (month) {
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
   * @param year year
   * @return
   */
  private int jahreszifferBerechnen(int year) {
    int jz = year % 100;
    return (jz + (jz / 4)) % 7;
  }

  /**
   * Die Formel zur Berechnung der Jahrhundertziffer wird hier ausgeführt
   */
  private int jahrhundertzifferBerechnen(int jahr) {
    int jhz = jahr / 100;
    return (3 - (jhz % 4)) * 2;
  }

  /**
   * Da es Schaltjahre gibt, würde die Berechnung nicht ganz aufgehen. In
   * einem schaltjahr würden die Wochentage im Januar und Februar nicht
   * stimmen. Deshalb muss bei jedem Schaltjahr entweder -1 oder +6 zur
   * Gesamtberechnung hinzugefügt werden. Dazu wird überprüft, ob das jahr ein
   * Schaltjahr ist, und dann ob es ein Januar oder Februar ist.
   *
   * @param month
   * @param year
   * @return
   */
  private int leapYearCorrection(int month, int year) {
    int leapYear = 0;
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
      if (month < 3 && month > 0) {
        leapYear = -1;
      }
    }
    return leapYear;
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
  private String searchDay(int tz, int mz, int jz, int jhz, int sjk) {

    // Ausführung der Formel gemäss Wikipediaartikel
    int wochentagsziffer = (tz + mz + jz + jhz + sjk) % 7;
    String day = "";

    // Select the correct day
    switch (wochentagsziffer) {
      case 0:
        day = "Sunday";
        break;
      case 1:
        day = "Monday";
        break;
      case 2:
        day = "Tuesday";
        break;
      case 3:
        day = "Wednesday";
        break;
      case 4:
        day = "Thursday";
        break;
      case 5:
        day = "Friday";
        break;
      case 6:
        day = "Saturday";
        break;
    }

    return day;
  }

  public void showTime() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();

    System.out.println("The current date and time is: " + dateFormat.format(cal.getTime()));
    System.out.println();
  }

  public void daysTillBirthday() {
    int day;
    int month;
    int year;

    System.out.println("Please enter your next birthday:");
    day = readNumber(TimeMeasurements.DAY);
    month = readNumber(TimeMeasurements.MONTH);
    year = readNumber(TimeMeasurements.YEAR);

    // Get difference between days
    long daysCount = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(year, month, day));
    System.out.println("Your birthday is in " + daysCount + " days.");
  }

  public void showWeekday() {
    // Get user input
    int day = readNumber(TimeMeasurements.DAY);
    int month = readNumber(TimeMeasurements.MONTH);
    int year = readNumber(TimeMeasurements.YEAR);

    //Alle benötigten Ziffern berechnen.
    int tagesziffer = day % 7;
    int monatsziffer = monatszifferFinden(month);
    int jahresziffer = jahreszifferBerechnen(year);
    int jahrhundertziffer = jahrhundertzifferBerechnen(year);
    int schaltjahrkorrektur = leapYearCorrection(month, year);

    //Wochentagsziffer Berechnen und Wochentag Holen.
    String weekDay = searchDay(tagesziffer, monatsziffer, jahresziffer, jahrhundertziffer, schaltjahrkorrektur);

    System.out.println();
    System.out.println("Entered date:");
    System.out.println("Day: " + day + " Month: " + month + " Year: " + year);
    System.out.println("The given date is a " + weekDay);
  }
}
