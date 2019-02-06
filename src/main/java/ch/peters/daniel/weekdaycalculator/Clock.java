package ch.peters.daniel.weekdaycalculator;

import java.time.LocalDateTime;

/**
 * Clock class.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public class Clock {
  /**
   *
   */
  public void showCurrentTime() {
    var date = LocalDateTime.now(Config.TIME_ZONE);

    System.out.println("The current date and time is: " + date.toString());
  }
}
