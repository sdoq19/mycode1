package priv.resource.io.file;

/**
 * Created by zhuzh on 2017-5-11.
 */
public class Choose {
    public static void main(String argv[]) {
        String filePath = "C:\\Users\\admin\\Desktop\\fivesort.txt";

        // Set<String> phoneSet = ImportFile.getPhoneNumFromFile(filePath);
        // System.out.println(phoneSet);

        ImportFile.getTimestamp(filePath);
    }
}
