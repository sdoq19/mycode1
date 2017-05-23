package priv.resource.io.file;

import java.util.Set;

/**
 * Created by zhuzh on 2017-5-11.
 */
public class Choose {
    public static void main(String argv[]) {
        String filePath = "C:\\Users\\admin\\Desktop\\nohup.out";

        Set<String> phoneSet = ImportFile.getPhoneNumFromFile(filePath);

        System.out.println(phoneSet);
    }
}
