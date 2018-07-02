package priv.test.test;

import priv.utils.HttpCilentUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzh on 2017-3-3.
 */
public class HttpTest {
    public static void main(String[] args) {
        HttpCilentUtil      util        = new HttpCilentUtil();
        String              url         = "http://qrctest.unservice.net:80/pay/pay_backend";
        String              method      = "post";
        String              ContentType = "application/x-www-form-urlencoded";
        Map<String, Object> map         = new HashMap<>();

        map.put("tsm_c", "alipay");
        map.put("tsm_psn", "alipay_f2f");
        map.put("body", "杭州市民支付宝医疗卡扫码支付");
        map.put("open_id", "20880021075732843827430860011632");
        map.put("subject", "杭州市民支付宝医疗卡扫码支付");
        map.put("sign_type", "RSA2");
        map.put("buyer_logon_id", "nec***@gmail.com");
        map.put("auth_app_id", "2016031601217909");
        map.put("notify_type", "trade_status_sync");
        map.put("out_trade_no", "o170620816800755004315011");
        map.put("point_amount", "0.00");
        map.put("version", "1.0");
        map.put("fund_bill_list", "[{\"amount\":\"0.01\",\"fundChannel\":\"PCREDIT\"}]");
        map.put("total_amount", "0.01");
        map.put("buyer_id", "2088102035812323");
        map.put("trade_no", "2017030321001004320211420222");
        map.put("notify_time", "2017-03-03 14:20:04");
        map.put("charset", "utf-8");
        map.put("invoice_amount", "0.01");
        map.put("trade_status", "TRADE_SUCCESS");
        map.put("gmt_payment", "2017-03-03 14:20:04");
        map.put("sign", "FOSJbaBbeVwnv75EVXGvmlW8RHR27pyUZZPDokZsXIxa+271RsAXx9Fado+VzSnQ5Ozqmcq72GqsvyRkQ1SM+VMhi2z6qEnYEKx+hu+OmEOI3yBJAhIjatv+XDIVqQxk9W8CZJ6xdDHVKrWcvWxJiO6V6I3fv/cKc39wQz75AIiaXv2ApkuQTkWGfkMAsDPtSgi2DZi1kfQV12tvm0GsHmop0mWTAsr32+C90Ci7+C98z7ZGOf5j09UAqBHkg5rzuIzGE/ypB015AQQ07zdFEnJ5kJOr2AnRs+JMULsURUdCBaZM1lEEXKHe2LoCfWcoCe+ZoUxgxSX3qiaXF9Fr6w==");
        map.put("gmt_create", "2017-03-03 14:19:11");
        map.put("buyer_pay_amount", "0.01");
        map.put("receipt_amount", "0.01");
        map.put("seller_id", "2088221232481223");
        map.put("app_id", "2016031601217909");
        map.put("seller_email", "panhy@citytsm.com");
        map.put("notify_id", "568378492cb0530ed50bf9884cce034igy");

        //util.requestUrl();
    }
}
