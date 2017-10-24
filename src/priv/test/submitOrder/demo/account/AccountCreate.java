package priv.test.submitOrder.demo.account;

import com.alibaba.fastjson.JSONObject;
import priv.test.submitOrder.demo.HttpUtil;
import priv.utils.JsonUtil;

import java.io.IOException;

/**
 * Created by zhuzh on 2017-10-24.
 */
public class AccountCreate {

    public static void main(String[] args) {

        String url = "http://10.0.0.105:8080/dp/tsmAcct/accountCreate";

        JSONObject reportJson = new JSONObject();
        reportJson.put("signType", "MD5");
        reportJson.put("merchantNo", "20000028");
        reportJson.put("reqSeqNo", "2017-10-24 15:57:24");
        reportJson.put("ts", DateUtil.unixTimestamp());
        reportJson.put("openAccountId", "2088102607638638");
        reportJson.put("source", "app");
        reportJson.put("password", "123456");
        reportJson.put("cardType", "1");
        reportJson.put("mobile", "15858225448");
        reportJson.put("userName", "wdd");
        reportJson.put("userLevel", 2);
        reportJson.put("creditLimit", 100);
        reportJson.put("acctType", "1");
        reportJson.put("acctNo", "1209800000113");
        reportJson.put("certNo", "33068219990307305656");
        reportJson.put("certType", "1");
        reportJson.put("subType", "1");
        reportJson.put("cityId", "3301");
        String sign = SignUtil.getSign(reportJson, "795acbb78c20487583ce5aaeaa80a2c2");
        reportJson.put("sign", sign);

        String return_report = null;
        try {
            return_report = HttpUtil.doPost(url, JsonUtil.jsonToStrMap(reportJson.toJSONString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务端返回：" + return_report);
    }

}
