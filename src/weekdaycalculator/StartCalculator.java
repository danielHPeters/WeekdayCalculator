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
        calculator.showWeekday();
        calculator.daysTillBirthday();
    }
    
}
