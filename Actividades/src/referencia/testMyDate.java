
package referencia;

/**
 *
 * @author Karenrgast
 */
public class testMyDate {
    public static void main(String[] args) {
        MyDate date1=new MyDate(22,7,1964);
        MyDate date2=new MyDate(17,7,1964);
        
        int dias=5;
        date1=MyDate.addDays(dias, date2);
        System.out.println("Date1 es: "+date1.getDate());
        System.out.println("Date2 es: "+date2.getDate());
    }
}
