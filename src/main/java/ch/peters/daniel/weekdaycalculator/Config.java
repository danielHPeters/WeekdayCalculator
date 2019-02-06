package ch.peters.daniel.weekdaycalculator;

import java.time.ZoneId;

/**
 * App configuration.
 *
 * @author Daniel Peters
 * @version 1.0
 */
public interface Config {
  ZoneId TIME_ZONE = ZoneId.of("Europe/Zurich");
}
