package ch.peters.daniel.weekdaycalculator;

public interface InputReader {
  int readNumberInRange(int min, int max);

  int getDateNumberByMeasurement(TimeMeasurements measurements);
}
