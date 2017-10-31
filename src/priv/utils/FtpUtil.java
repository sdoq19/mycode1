package priv.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 用来操作ftp的综合类。<br/>
 * 主要依赖jar包commons-net-3.1.jar。
 *
 * @author
 * @version 1.0
 */
public class FtpUtil {
    //ftp 地址
    private String url;
    //ftp端口
    private int    port;
    //用户名
    private String userName;
    //密码
    private String password;

    /**
     * 构造函数
     *
     * @param url      ftp地址
     * @param port     ftp端口
     * @param userName 用户名
     * @param password 密码
     */
    public FtpUtil(String url, int port, String userName, String password) {
        this.url = url;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    /**
     * 从FTP服务器下载指定文件名的文件。
     *
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return 成功下载返回true，否则返回false。
     * @throws IOException
     */
    public boolean downFile(String remotePath, String fileName, String localPath) throws IOException {
        boolean   success = false;
        FTPClient ftp     = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(userName, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            // 这个方法的意思就是每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据,
            // 因为ftp server可能每次开启不同的端口来传输数据，但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞。
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            FTPFile   ff;
            for (int i = 0; i < fs.length; i++) {
                ff = fs[i];
                if (null != ff && null != ff.getName() && ff.getName().equals(fileName)) {
                    File         localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is        = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

    /**
     * 从FTP服务器列出指定文件夹下文件名列表。
     *
     * @param remotePath FTP服务器上的相对路径
     * @return List<String> 文件名列表，如果出现异常返回null。
     * @throws IOException
     */
    public List<String> getFileNameList(String remotePath) throws IOException {
        //目录列表记录
        List<String> fileNames = new ArrayList<String>();
        FTPClient    ftp       = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(userName, password);//登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
            // 这个方法的意思就是每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据,
            // 因为ftp server可能每次开启不同的端口来传输数据，但是在linux上，由于安全限制，可能某些端口没有开启，所以就出现阻塞。
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile file : fs) {
                fileNames.add(file.getName());
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return fileNames;
    }


}
