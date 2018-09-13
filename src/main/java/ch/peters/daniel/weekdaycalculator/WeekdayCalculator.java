package ch.peters.daniel.weekdaycalculator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Weekday calculator based on Wikipedia pseudo code.
 * https://de.wikipedia.org/wiki/Wochentagsberechnung
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class WeekdayCalculator {
  private final Scanner scanner = new Scanner(System.in);

  /**
   * Get input as number.
   *
   * @param measurements
   * @return
   */
  private int readNumber(TimeMeasurements measurements) {
    var input = 0;

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
    int monz;
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
      default:
        monz = 0;
    }
    return monz;
  }

  /**
   * Die Formel zur Berechnung der Jahresziffer wird hier ausgeführt.
   *
   * @param year year
   * @return
   */
  private int jahreszifferBerechnen(int year) {
    var jz = year % 100;
    return (jz + (jz / 4)) % 7;
  }

  /**
   * Die Formel zur Berechnung der Jahrhundertziffer wird hier ausgeführt.
   */
  private int jahrhundertzifferBerechnen(int year) {
    var jhz = year / 100;
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
    var leapYear = 0;
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
    var wochentagsziffer = (tz + mz + jz + jhz + sjk) % 7;
    var day = "";

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
      default:
        day = "Unknown";
    }

    return day;
  }

  public void showTime() {
    var date = LocalDateTime.now();

    System.out.println("The current date and time is: " + date.toString());
  }

  public void daysTillBirthday() {
    System.out.println("Please enter your next birthday:");

    var day = readNumber(TimeMeasurements.DAY);
    var month = readNumber(TimeMeasurements.MONTH);
    var year = readNumber(TimeMeasurements.YEAR);
    // Get difference between days
    var daysCount = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(year, month, day));
    System.out.println("Your birthday is in " + daysCount + " days.");
  }

  public void showWeekday() {
    // Get user input
    var day = readNumber(TimeMeasurements.DAY);
    var month = readNumber(TimeMeasurements.MONTH);
    var year = readNumber(TimeMeasurements.YEAR);

    //Alle benötigten Ziffern berechnen.
    var tagesziffer = day % 7;
    var monatsziffer = monatszifferFinden(month);
    var jahresziffer = jahreszifferBerechnen(year);
    var jahrhundertziffer = jahrhundertzifferBerechnen(year);
    var schaltjahrkorrektur = leapYearCorrection(month, year);

    //Wochentagsziffer Berechnen und Wochentag Holen.
    var weekDay = searchDay(tagesziffer, monatsziffer, jahresziffer, jahrhundertziffer, schaltjahrkorrektur);

    System.out.println();
    System.out.println("Entered date:");
    System.out.println("Day: " + day + " Month: " + month + " Year: " + year);
    System.out.println("The given date is a " + weekDay);
  }
}
