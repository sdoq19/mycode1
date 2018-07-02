package priv.test.test;

import priv.utils.FtpUtil;

import java.io.IOException;

/**
 * Created by zhuzh on 2017-10-31.
 */
public class FtpTest {

    public static void main(String[] args) {
        FtpUtil ftp = new FtpUtil("120.27.175.1", 5001, "320200", "Pf2j9YiG");
        try {
            System.out.println(ftp.getFileNameList(""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ftp.downFile("", "CJ3202002000001120171025.TXT", "d:/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
