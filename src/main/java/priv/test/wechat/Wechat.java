package priv.test.wechat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import priv.utils.Map2Xml;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhuzh on 2017-5-11.
 */
public class Wechat {

    //请求器的配置
    public RequestConfig requestConfig;
    public String key           = "11223344556677889900AABBCCDDEEFF";
    public String cert_path     = "";
    public String cert_password = "1307220701";

    //HTTP请求器
    public CloseableHttpClient httpClient;

    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;

    private void filterParams(Map<String, String> requestMap, String filterKey) {
        Set<String> keySet = requestMap.keySet();
        for (String key : keySet) {
            if (filterKey.equals(key)) {
                requestMap.remove(key);

            }
        }
    }

    private String getSign(Map<String, String> requestMap, String signType) {
        String retSign = "";
        if (requestMap.size() == 0 || "".equals(signType)) {
            return retSign;
        }
        if ("MD5".equals(signType)) {//md5签名
            this.filterParams(requestMap, "sign");
            retSign = WechatCore.getMapMD5Sign(requestMap, this.key);
        }
        return retSign;
    }

    public String httpsRequest(String requestGateway, Map<String, String> requestMap) throws Exception {


        String sign = this.getSign(requestMap, requestMap.get("sign_type"));
        if ("".equals(sign)) {
            System.out.println("error ");
        }
        requestMap.put("sign", sign);
        String   postDataXML = Map2Xml.wechatRequestMapToXml(requestMap);
        HttpPost httpPost    = new HttpPost(requestGateway);
        System.out.println(("DefaultWechatPayClient.httpsRequest,postdata:" + postDataXML));

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(requestConfig);
        //https请求
        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity              entity         = response.getEntity();
            String                  responseStr    = EntityUtils.toString(entity, "UTF-8");
            HashMap<String, String> responseParams = (HashMap<String, String>) WechatCore.parseFromXml(responseStr);
            if (null == responseParams) {
                System.out.println(("parsed http response xml failed,response:" + response + " responseParams:" + responseParams.toString()));
                throw new Exception("orr");
            }
            if ("FAIL".equals(responseParams.get("return_code"))) {//响应无sign直接返回，不验签
                return responseStr;
            }
            boolean verifyOk = WechatCore.verifySign(responseParams, this.key, "MD5");//验证签名
            if (!verifyOk) {
                System.out.println(("httpClient execute,virified sign failed:" + response));
            }

            return responseStr;

        } catch (ConnectionPoolTimeoutException e) {
            throw e;

        } catch (ConnectTimeoutException e) {
            throw e;

        } catch (SocketTimeoutException e) {
            throw e;

        } catch (Exception e) {
            throw e;

        } finally {
            httpPost.abort();
        }

    }

    public void init() throws Exception {
        this.cert_path  = "d:/cert/wechat_cert_1307220701.p12";
        this.cert_password = "1307220701";
        KeyStore        keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(this.cert_path));//加载本地的证书进行https加密传输
        try {
            keyStore.load(instream, this.cert_password.toCharArray());//设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, this.cert_password.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        //根据默认超时限制初始化requestConfig
        requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
    }

    public static void main(String[] args) throws Exception {

        Wechat wechat = new Wechat();
        wechat.init();

        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";


        Map<String, String> map = new HashMap<>();
        map.put("appid", "wx0a2dc0a78f1cbcc5");
        map.put("mch_id", "1307220701");
        map.put("nonce_str", "123");

        map.put("body", "111");
        map.put("out_trade_no", "20171215");
        map.put("total_fee", "1000");
        map.put("spbill_create_ip", "127.0.0.1");
        map.put("notify_url", "http://aa.bb.com");
        map.put("trade_type", "JSAPI");
        map.put("sign_type", "MD5");
        map.put("openid", "of8bt0F6VNc56nwpEUUOhMHmWvCo");

        System.out.println(wechat.httpsRequest(url, map));
    }
}
