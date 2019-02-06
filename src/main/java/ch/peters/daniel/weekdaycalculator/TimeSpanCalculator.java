package ch.peters.daniel.weekdaycalculator;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

/**
 * Class TimeSpanCalculator.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class TimeSpanCalculator {
  private InputReader inputReader;

  /**
   * @param inputReader
   */
  public TimeSpanCalculator(InputReader inputReader) {
    this.inputReader = inputReader;
  }

  /**
   *
   */
  public void daysTillBirthday() {
    System.out.println("Please enter your birthday:");

    var day = inputReader.getDateNumberByMeasurement(TimeMeasurements.DAY);
    var month = inputReader.getDateNumberByMeasurement(TimeMeasurements.MONTH);
    var year = Year.now(Config.TIME_ZONE).getValue();
    var daysCount = ChronoUnit.DAYS.between(LocalDate.now(Config.TIME_ZONE), LocalDate.of(year, month, day));

    System.out.println("Your birthday is in " + daysCount + " days.");
  }
}
