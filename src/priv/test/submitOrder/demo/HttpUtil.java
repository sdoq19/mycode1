package priv.test.submitOrder.demo;

import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HTTP、HTTPS工具类
 *
 * @author zhuzh
 */
public abstract class HttpUtil {


    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST     = "POST";
    private static final String METHOD_GET      = "GET";

    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    private static final int DEFAULT_READ_TIMEOUT       = 30000;

    private static SSLContext ctx = null;

    private static HostnameVerifier verifier      = null;
    private static SSLSocketFactory socketFactory = null;

    private static class DefaultTrustManager implements X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
    }

    static {
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new HttpUtil.DefaultTrustManager()}, new SecureRandom());
            ctx.getClientSessionContext().setSessionTimeout(15);
            ctx.getClientSessionContext().setSessionCacheSize(1000);
            socketFactory = ctx.getSocketFactory();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        verifier = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return false;
            }
        };
    }

    /**
     * http post请求
     *
     * @param url    请求地址
     * @param params 参数键值对
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, params, DEFAULT_CHARSET, DEFAULT_CONNECTION_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    /**
     * http post请求
     *
     * @param url            请求地址
     * @param params         参数键值对
     * @param connectTimeout 链接超时
     * @param readTimeout    读超时
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params, int connectTimeout, int readTimeout) throws IOException {
        return doPost(url, params, DEFAULT_CHARSET, connectTimeout, readTimeout);
    }

    public static String doPost(String url, Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws IOException {
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;


        String query = buildQuery(params, charset);
        System.out.println("Post中 query的值" + query);

        byte[] content = new byte[0];
        if (query != null) {
            content = query.getBytes(charset);
        }

        return doPost(url, ctype, content, connectTimeout, readTimeout);
    }

    public static String doPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout) throws IOException {
        HttpURLConnection conn = null;
        OutputStream      out  = null;
        String            rsp  = null;

        try {
            Map map;
            try {
                conn = getConnection(new URL(url), METHOD_POST, ctype);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            try {
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } finally {
            if (out != null) {
                out.close();
            }

            if (conn != null) {
                conn.disconnect();
            }

        }

        return rsp;
    }


    private static byte[] getTextEntry(String fieldName, String fieldValue, String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
        entry.append(fieldValue);
        return entry.toString().getBytes(charset);
    }

    private static byte[] getFileEntry(String fieldName, String fileName, String mimeType, String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\";filename=\"");
        entry.append(fileName);
        entry.append("\"\r\nContent-Type:");
        entry.append(mimeType);
        entry.append("\r\n\r\n");
        return entry.toString().getBytes(charset);
    }

    /**
     * Get请求
     *
     * @param url    url
     * @param params 参数键值对
     * @return
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGet(url, params, "UTF-8");
    }

    public static String doGet(String url, Map<String, String> params, String charset) throws IOException {
        HttpURLConnection conn = null;
        String            rsp  = null;

        try {
            String ctype = "application/x-www-form-urlencoded;charset=" + charset;
            String query = buildQuery(params, charset);

            Map map;
            try {
                conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

            try {
                rsp = getResponseAsString(conn);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }

        }

        return rsp;
    }

    private static HttpURLConnection getConnection(URL url, String method, String ctype) throws IOException {
        Object conn = null;
        if ("https".equals(url.getProtocol())) {
            HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
            connHttps.setSSLSocketFactory(socketFactory);
            connHttps.setHostnameVerifier(verifier);
            conn = connHttps;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }

        ((HttpURLConnection) conn).setRequestMethod(method);
        ((HttpURLConnection) conn).setDoInput(true);
        ((HttpURLConnection) conn).setDoOutput(true);
        ((HttpURLConnection) conn).setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        ((HttpURLConnection) conn).setRequestProperty("User-Agent", "aop-sdk-java");
        ((HttpURLConnection) conn).setRequestProperty("Content-Type", ctype);
        return (HttpURLConnection) conn;
    }

    private static URL buildGetUrl(String strUrl, String query) throws IOException {
        URL url = new URL(strUrl);
        if (StringUtils.isEmpty(query)) {
            return url;
        } else {
            if (StringUtils.isEmpty(url.getQuery())) {
                if (strUrl.endsWith("?")) {
                    strUrl = strUrl + query;
                } else {
                    strUrl = strUrl + "?" + query;
                }
            } else if (strUrl.endsWith("&")) {
                strUrl = strUrl + query;
            } else {
                strUrl = strUrl + "&" + query;
            }

            return new URL(strUrl);
        }
    }

    /**
     * 组装参数
     *
     * @param params  参数键值对
     * @param charset 字符集
     * @return
     * @throws IOException
     */
    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params != null && !params.isEmpty()) {
            StringBuilder query    = new StringBuilder();
            Set           entries  = params.entrySet();
            boolean       hasParam = false;
            Iterator      i$       = entries.iterator();

            while (i$.hasNext()) {
                Entry  entry = (Entry) i$.next();
                String name  = (String) entry.getKey();
                String value = (String) entry.getValue();
                if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
                    if (hasParam) {
                        query.append("&");
                    } else {
                        hasParam = true;
                    }

                    query.append(name).append("=").append(URLEncoder.encode(value, charset));
                }
            }

            return query.toString();
        } else {
            return null;
        }
    }

    /**
     * 将流转化为字符串
     *
     * @param conn HttpURLConnection
     * @return
     * @throws IOException
     */
    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String      charset = getResponseCharset(conn.getContentType());
        InputStream es      = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    /**
     * 将流转化为字符串
     *
     * @param stream  流
     * @param charset 字符集
     * @return
     * @throws IOException
     */
    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter   writer = new StringWriter();
            char[]         chars  = new char[256];
            boolean        count  = false;

            int count1;
            while ((count1 = reader.read(chars)) > 0) {
                writer.write(chars, 0, count1);
            }

            String var6 = writer.toString();
            return var6;
        } finally {
            if (stream != null) {
                stream.close();
            }

        }
    }

    /**
     * 获取响应的字符集
     *
     * @param ctype
     * @return
     */
    private static String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            String[] arr$   = params;
            int      len$   = params.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String param = arr$[i$];
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2 && !StringUtils.isEmpty(pair[1])) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }

        return charset;
    }

    public static String decode(String value) {
        return decode(value, "UTF-8");
    }

    public static String encode(String value) {
        return encode(value, "UTF-8");
    }

    public static String decode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLDecoder.decode(value, charset);
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        }

        return result;
    }

    public static String encode(String value, String charset) {
        String result = null;
        if (!StringUtils.isEmpty(value)) {
            try {
                result = URLEncoder.encode(value, charset);
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        }

        return result;
    }

    /**
     * 从URL获取参数
     *
     * @param url
     * @return
     */
    private static Map<String, String> getParamsFromUrl(String url) {
        Object map = null;
        if (url != null && url.indexOf(63) != -1) {
            map = splitUrlQuery(url.substring(url.indexOf(63) + 1));
        }

        if (map == null) {
            map = new HashMap();
        }

        return (Map) map;
    }

    /**
     * 拆解参数
     *
     * @param query 参数字符串
     * @return
     */
    public static Map<String, String> splitUrlQuery(String query) {
        HashMap  result = new HashMap();
        String[] pairs  = query.split("&");
        if (pairs != null && pairs.length > 0) {
            String[] arr$ = pairs;
            int      len$ = pairs.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String   pair  = arr$[i$];
                String[] param = pair.split("=", 2);
                if (param != null && param.length == 2) {
                    result.put(param[0], param[1]);
                }
            }
        }

        return result;
    }


}
