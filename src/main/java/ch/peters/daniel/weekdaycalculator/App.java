package ch.peters.daniel.weekdaycalculator;

/**
 * Main app class.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class App {
  /**
   * Application entry.
   *
   * @param args The command line arguments
   */
  public static void main(String[] args) {
    var inputReader = new ConsoleInputReader();
    var clock = new Clock();
    var weekdayCalculator = new WeekdayCalculator(inputReader);
    var timespanCalculator = new TimeSpanCalculator(inputReader);

    clock.showCurrentTime();
    weekdayCalculator.showWeekday();
    timespanCalculator.daysTillBirthday();
  }
}
