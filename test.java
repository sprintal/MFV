import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{

    /**
     * Constructor for objects of class test
     */
    public test()
    {

    }

    public void time()
    {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        System.out.println("当前时间：" + c);
        System.out.println("时间：" + date);
        System.out.println("当前时间：" + c);
        System.out.println("当前时间：" + c);
        System.out.println("当前时间：" + c);
        System.out.println("当前时间：" + c);
        System.out.println("当前时间：" + c);
        System.out.println("当前时间：" + c);
        System.out.println("当前时间：" + c);
    }

    public void convert()
    {
        int i = 1234;
        String a = i + "";
    }
}
