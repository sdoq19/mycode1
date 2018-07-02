package priv.test.file;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuzh on 2016-6-26.
 * 分割云梯VPN文件
 */
public class VpnUpSplit {

    public static void main(String[] args) {
        //String str = "route add 111.67.192.0 mask 255.255.240.0 %gw% metric 5";
        //Pattern pattern = Pattern.compile("route add (.*?) mask");
        //Matcher matcher = pattern.matcher(str);
        //while (matcher.find()) {
        //    System.out.println(matcher.group(1));
        //}

        splitFile2();
    }

    public static void splitFile2() {
        String origPath = "C:\\Users\\lenovo\\Desktop\\vpnup.bat";
        File origFile = new File(origPath);
        String path = "C:\\Users\\lenovo\\Desktop\\vpn\\";
        String header =
                "@echo off\n" +
                        "    for /F \"tokens=3\" %%* in ('route print ^| findstr \"\\<0.0.0.0\\>\"') do set \"gw=%%*\"\n" +
                        "ipconfig /flushdns";

        Map<String, StringBuffer> map = new HashMap<String, StringBuffer>();
        int index = 0;

        // 输出流
        OutputStream os = null;
        // 输出流
        InputStream is = null;

        if (origFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(origFile), "utf-8"));
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    // 忽略空行及非route开头的行
                    if (!"".equals(temp) && temp.startsWith("route")) {
                        String tempStr = ipHeader(temp);
                        if (tempStr != null && tempStr.split("\\.")[0] != null) {
                            String tStr = tempStr.split("\\.")[0];
                            if (map.get(tStr) == null) {
                                StringBuffer sb = new StringBuffer(header + "\n");
                                sb.append(temp + "\n");
                                map.put(tStr, sb);
                            } else {
                                StringBuffer sb = map.get(tStr).append(temp + "\n");
                                map.put(tStr, sb);
                            }
                        }
                    }
                }

                for (Map.Entry<String, StringBuffer> item : map.entrySet()) {
                    File file = new File(path + index + ".bat");
                    index++;
                    os = new FileOutputStream(file);
                    int bytesRead = 0;
                    byte[] buffer = new byte[8192];
                    is = new ByteArrayInputStream(item.getValue().toString().getBytes());
                    while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    is.close();
                    os.close();
                }

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("file is not exists!");
        }
    }

    @Deprecated
    public static void splitFile() {
        String origPath = "C:\\Users\\lenovo\\Desktop\\vpnup.bat";
        File origFile = new File(origPath);
        String path = "C:\\Users\\lenovo\\Desktop\\vpn\\";
        String header =
                "@echo off\n" +
                        "    for /F \"tokens=3\" %%* in ('route print ^| findstr \"\\<0.0.0.0\\>\"') do set \"gw=%%*\"\n" +
                        "ipconfig /flushdns";

        StringBuffer content = new StringBuffer();
        content.append(header);
        String nowIp = "";
        int index = 0;
        File file = new File(path + index + ".bat");
        index++;
        // 输出流
        OutputStream os = null;
        // 输出流
        InputStream is = null;

        if (origFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(origFile), "utf-8"));
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    // 忽略空行及非route开头的行
                    if (!"".equals(temp) && temp.startsWith("route")) {
                        String tempstr = ipHeader(temp);
                        if (tempstr != null && !strEquals(tempstr, nowIp)) { // 与前一行IP段不同
                            nowIp = tempstr;
                            // 关闭上一个文件
                            os = new FileOutputStream(file);
                            int bytesRead = 0;
                            byte[] buffer = new byte[8192];
                            is = new ByteArrayInputStream(content.toString().getBytes());
                            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                                os.write(buffer, 0, bytesRead);
                            }
                            is.close();
                            os.close();

                            content = new StringBuffer();
                            content.append(header + "\n");
                            content.append(temp + "\n");
                            //　新建一个文件
                            file = new File(path + index + ".bat");
                            index++;

                        } else if (tempstr != null && strEquals(tempstr, nowIp)) { // 与前一行IP段相同
                            nowIp = ipHeader(temp);

                            // 将当前行加入到文件
                            content.append(temp + "\n");

                            // 文件最后一行加空行！！
                        }
                    }

                }


            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("file is not exists!");
        }
    }

    public static String ipHeader(String line) {
        Pattern pattern = Pattern.compile("route add (.*?) mask");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String temp = matcher.group(1);
            return temp;
        }

        return null;
    }

    public static boolean strEquals(String now, String before) {
        if (now != null && before != null) {
            if (now.split("\\.")[0].equals(before.split("\\.")[0])) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
