package priv.resource.io.file;

import java.util.Set;

/**
 * Created by zhuzh on 2017-5-11.
 */
public class Choose {
    public static void main(String argv[]) {
        String filePath = "C:\\Users\\admin\\Desktop\\串号问题\\tsmpay_log_2017-05-10\\pro3-pay-service.log_2017-05-09.log";

        Set<String> phoneSet = ImportFile.getPhoneNumFromFile(filePath);

        System.out.println(phoneSet);
    }
}
