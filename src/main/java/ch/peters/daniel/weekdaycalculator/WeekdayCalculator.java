package ch.peters.daniel.weekdaycalculator;

/**
 * Weekday calculator based on Wikipedia pseudo code.
 * https://de.wikipedia.org/wiki/Wochentagsberechnung
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class WeekdayCalculator {
  private InputReader inputReader;

  /**
   * @param inputReader
   */
  public WeekdayCalculator(InputReader inputReader) {
    this.inputReader = inputReader;
  }

  /**
   * Jeder Monat hat eine Monatsziffer die im Wikipediaartikel gegeben wird.
   * Man könnte sie auch berechnen, doch dann wird der Code unnötg Länger Die
   * untenstehende Methode erhält den eingegeben Monat als Parameter und sucht
   * dann im Switch nach der entsprechenden Monatsziffer, die dann
   * zurückgegeben wird.
   *
   * @param month
   * @return
   */
  private int getMonthDigit(int month) {
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
  private int calculateYearDigit(int year) {
    var jz = year % 100;

    return (jz + (jz / 4)) % 7;
  }

  /**
   * Calculate the century digit via the formula taken from wikipedia.
   *
   * @param year
   * @return
   */
  private int calculateCenturyDigit(int year) {
    var jhz = year / 100;

    return (3 - (jhz % 4)) * 2;
  }

  /**
   * @param month
   * @param year
   * @return
   */
  private int getLeapYearCorrection(int month, int year) {
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
   * @param weekDayDigit
   * @return
   */
  private String getWeekDay(int weekDayDigit) {
    var day = "";

    // Select the correct day
    switch (weekDayDigit) {
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

  /**
   *
   */
  public void showWeekday() {
    var day = inputReader.getDateNumberByMeasurement(TimeMeasurements.DAY);
    var month = inputReader.getDateNumberByMeasurement(TimeMeasurements.MONTH);
    var year = inputReader.getDateNumberByMeasurement(TimeMeasurements.YEAR);
    var dayDigit = day % 7;
    var monthDigit = getMonthDigit(month);
    var yearDigit = calculateYearDigit(year);
    var centuryDigit = calculateCenturyDigit(year);
    var leapYearCorrection = getLeapYearCorrection(month, year);
    var weekdayDigit = (dayDigit + monthDigit + yearDigit + centuryDigit + leapYearCorrection) % 7;
    var weekDay = getWeekDay(weekdayDigit);

    System.out.println();
    System.out.println("Entered date:");
    System.out.println("Day: " + day + " Month: " + month + " Year: " + year);
    System.out.println("The given date is a " + weekDay);
  }
}
