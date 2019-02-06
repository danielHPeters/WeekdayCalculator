package ch.peters.daniel.weekdaycalculator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * InputReader implementation using the command line.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class ConsoleInputReader implements InputReader {
  private static final int MIN_DAY = 1;
  private static final int MAX_DAY = 31;
  private static final int MIN_MONTH = 1;
  private static final int MAX_MONTH = 12;
  private static final int MIN_YEAR = 1;
  private static final int MAX_YEAR = 9999;
  private static final String DAY_PROMPT = "Please enter valid day (" + MIN_DAY + " - " + MAX_DAY + ")";
  private static final String MONTH_PROMPT = "Please enter valid month (" + MIN_MONTH + " - " + MAX_MONTH + ")";
  private static final String YEAR_PROMPT = "Please enter valid year (" + MIN_YEAR + " - " + MAX_YEAR + ")";
  private static final String INVALID_INPUT_ERROR = "Invalid input!";

  private final Scanner scanner = new Scanner(System.in);

  /**
   * Read until valid input is given.
   *
   * @param min Minimum value
   * @param max Maximum value
   * @return Input number
   */
  public int readNumberInRange(int min, int max) {
    int input;

    do {
      input = scanner.nextInt();
    } while (input < min && input > max);

    return input;
  }

  /**
   * Get input as number.
   *
   * @param measurements
   * @return
   */
  public int getDateNumberByMeasurement(TimeMeasurements measurements) {
    var input = 0;

    try {
      switch (measurements) {
        case DAY:
          System.out.println(DAY_PROMPT);
          input = readNumberInRange(MIN_DAY, MAX_DAY);
          break;

        case MONTH:
          System.out.println(MONTH_PROMPT);
          input = readNumberInRange(MIN_MONTH, MAX_MONTH);
          break;

        case YEAR:
          System.out.println(YEAR_PROMPT);
          input = readNumberInRange(MIN_YEAR, MAX_YEAR);
          break;

        default:
          break;
      }
    } catch (InputMismatchException e) {
      System.out.println(INVALID_INPUT_ERROR);
    }

    return input;
  }
}
