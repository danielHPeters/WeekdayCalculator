/*
 * Hier wird die WeekdayCalculator Gestartet und die Methoden Aufgerufen aus der Klasse WeekdayCalculator
 */
package weekdaycalculator;

/**
 *
 * @author Daniel
 */
public class StartCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        WeekdayCalculator calculator = new WeekdayCalculator();
        
        calculator.showTime();
        System.out.println();
        calculator.showWeekday();
        calculator.daysTillBirthday();
    }
    
}
