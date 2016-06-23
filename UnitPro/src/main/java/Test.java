import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhenbiao.shen on 2016/5/6.
 */
public class Test {
//    private static final Log logger = LogFactory.getLog(Test.class);

    public static boolean flag=false;

    public static void main(String[] args) throws InterruptedException {
           int   MAX_VALUE = 0x7fffffff;

        System.out.println(MAX_VALUE);

    }
    synchronized private void wait1(){

        while (true) {
            try {
                if(!flag){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized private void notify1(){
        try {
            Thread.sleep(2000);
            flag=true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
