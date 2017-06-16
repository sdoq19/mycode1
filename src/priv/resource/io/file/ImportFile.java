package priv.resource.io.file;

import priv.utils.FileUtil;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


/**
 * 读取文件操作
 *
 * @author zcr
 */
public class ImportFile {
    /**
     * 读取文件，将文件中的电话号码读取出来，保存在Set中。
     *
     * @param filePath 文件的绝对路径
     * @return 文件中包含的电话号码
     */
    public static Set<String> getPhoneNumFromFile(String filePath) {
        Set<String> phoneSet = new HashSet<String>();
        StringBuffer content = new StringBuffer();

        try {
            String encoding = "UTF-8";
            File   file     = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格
                BufferedReader bufferedReader = new BufferedReader(read);
                String         lineTxt        = null;

                int lineNum = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //读取文件中的一行，将其中的电话号码添加到phoneSet中
                    CheckIfIsPhoneNumber.getPhoneNumFromStrIntoSet(lineTxt, phoneSet, content);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        // 输出到文件
        try {
            FileUtil.save(content.toString().getBytes(), new File("C:\\Users\\admin\\Desktop\\cc10.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return phoneSet;
    }

}