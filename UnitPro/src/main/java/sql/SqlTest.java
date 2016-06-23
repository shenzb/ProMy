package sql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by zhenbiao.shen on 2016/4/19.
 */
public class SqlTest {
    public static void main(String[] args) {
        try {
            String location="D:\\sql.txt";
            File temp = new File(location);
            if (temp.exists()) {
                temp.delete();
                temp=new File(location);
            }
            FileOutputStream fos = new FileOutputStream(temp);
            OutputStreamWriter writer = new OutputStreamWriter(fos);

            writer.write("use riskmgmt;\n");
            for (int i=1;i<=410000000/50000;i++){
                writer.write("DELETE from check_log where id>"+(i-1)*50000+" and id<"+(i*50000)+";\n");
                writer.write("sleep(0.1);\n");
//                System.out.println("DELETE from check_log where id<"+(i-1)*50000+" and id>"+(i*50000)+";\n");
            }

            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
