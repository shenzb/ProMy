package infoEncrypt;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import util.HttpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Random;

/**
 * Created by zhenbiao.shen on 2016/5/6.
 */
public class InfoEncrypt {

    public static void main(String[] args) {
        try {
            List<String> phones = Lists.newArrayList();
            List<String> machines = Lists.newArrayList();
            int i = 1;
            System.out.println("List size:" + phones.size());

            File temp = new File("D://data.txt");
            FileOutputStream fos = new FileOutputStream(temp);
            OutputStreamWriter writer = new OutputStreamWriter(fos);


            for (String phone : phones) {
                Random random = new Random();
                int index = random.nextInt(4);
                String machiine = machines.get(index);
                String url = "http://" + machiine + ":8080/smart/testjm?key=" + phone.trim() + "&type=1";
                String ph = HttpUtil.get(url);
                if (i % 4 == 0) {
                    Thread.sleep(1500);
                }
                System.out.println(i + " " + phone + " " + ph);
                if (StringUtils.isEmpty(ph)) {
                    ph = phone + ",null";
                }
                writer.write(ph);
                writer.write("\n");
                i++;
            }
            writer.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String context, String type) {
        List<String> machines = Lists.newArrayList("");
        int i = 1;
        Random random = new Random();
        int index = random.nextInt(4);
        String machiine = machines.get(index);
        String url = "http://" + machiine + ":8080/smart/testjm?key=" + context.trim() + "&type=" + type;
        String ph = HttpUtil.get(url);
        return ph;
    }

}
