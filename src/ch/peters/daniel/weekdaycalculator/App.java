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
    var calculator = new WeekdayCalculator();

    calculator.showTime();
    calculator.showWeekday();
    calculator.daysTillBirthday();
  }
}
