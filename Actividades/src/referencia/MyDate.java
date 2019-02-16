
package referencia;

/**
 *
 * @author Karenrgast
 */
public class MyDate {
    private int day=1;
    private int momth=1;
    private int year=2000;
    
//Se crea constructor con el generador de código
    public MyDate(int d, int m, int y){
    this.day=d;
    this.momth=m;
    this.year=y;
}
    public MyDate(MyDate date){
        this.day=date.day;
        this.momth=date.momth;
        this.year=date.year;
    }
    //paso por valor o referencia
    public static MyDate addDays(int moredays, MyDate newmydate){
        int moreDays=20;
        
        newmydate.day=newmydate.day+moreDays;
        //moreDays=20;
        newmydate=new MyDate(moreDays,11,2013);
        
        return newmydate;
    }
    
    public String getDate(){
        return day+"-"+momth+"-"+year;
    } 
}
