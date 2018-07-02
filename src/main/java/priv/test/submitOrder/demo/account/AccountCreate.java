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
        reportJson.put("reqSeqNo", "2017-11-21 14:03:36");
        reportJson.put("ts", DateUtil.unixTimestamp());
        reportJson.put("openAccountId", "2088123456789000");
        reportJson.put("source", "app");
        reportJson.put("password", "123456");
        reportJson.put("cardType", "1");
        reportJson.put("mobile", "15858225448");
        reportJson.put("userName", "zhuzh");
        reportJson.put("userLevel", 2);
        reportJson.put("creditLimit", 100);
        reportJson.put("acctType", "1");
        reportJson.put("acctNo", "1200000000111");
        reportJson.put("certNo", "33068219990307305656");
        reportJson.put("certType", "1");
        reportJson.put("subType", "1");
        reportJson.put("cityId", "330782");
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


/**
 *{"acctId":"3307000001249316","acctNo":"1200000000111","acctStatus":1,"acctType":1,"cardType":1,"merchantNo":"20000028","reqSeqNo":"2017-11-21 14:03:36","respCode":0,"respDesc":"SUCCESS","respSeqNo":"00717325511210079300281729906689","sign":"0273DE107BCB719CBDD3B1798BE62BBE","signType":"MD5","subType":1,"ts":1511244721,"userUuid":"a1732551121005851729906689"}
 */
