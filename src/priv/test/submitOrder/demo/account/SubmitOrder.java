package priv.test.submitOrder.demo.account;

import org.apache.commons.lang3.StringUtils;
import priv.utils.HttpUtil;
import priv.utils.MD5;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhuzh on 2017-10-19.
 */
public class SubmitOrder {

    // 商户号
    public static final String merchantNo = "20000028";

    // 商户key
    public static final String key = "795acbb78c20487583ce5aaeaa80a2c2";

    public static void main(String[] args) {

        String timestamp =  DateUtil.sdfTimes.format(new Date());
        Long timestampl = Long.valueOf(timestamp);
        Long acctId = 3301000001249290l;
        String transportParams = "{\"source\":\"alipay\",\"user_id\":\"2088102607638638\",\"subType\":1,\"transTime\":\"20170816174300\",\"record\":\"10ff0201007532303838313032363037363338363338599561d5007807d000000000000000000000000003df953fcfbc8720946bd77ee496c6fc46f8a5f89cb0488562543033323032303010333030313031303130303030303034301e0101214005f5e12d20270809000064000000000027101157254f24cd47e747304502203204c27aacb18c145ccc2b9637049f7264276a2c37e8b19480e9c8cf728f261002210098f3e59c3a3e62ab22f4a4b9dae59102d9cb83894941215281c4d37418ea83020459941055383036021900b868c70aec0152f23ea858089b092f2b004348829d345e820219008e00d1beb735de8b4072bbf634cf574b3485e7221bbfd6bf00677b22706f735f6964223a223230303231303032222c2274797065223a2253494e474c45222c227375626a656374223a22323031373031222c227265636f72645f6964223a2232303032313030325f32303137303831363137323533315f3030303030313536227d000459940f8b0010acdc2dc75f5a23f789ed6f488b2611c9\",\"city_code\":\"3610\"}";


        // 下单基本参数
        Map<String, String> map = new HashMap();
        map.put("merchantNo", merchantNo); // 商户号
        map.put("merchantOrderNo", merchantNo + System.currentTimeMillis()); // 订单号
        map.put("orderAmt", "1"); // 订单金额
        map.put("terminalType", "wap"); // 终端类型
        map.put("payChannel", "tsmpay_barcode"); // 支付方式
        map.put("orderLife", "5"); // 订单有效期
        map.put("backendUrl", "http://backendUrl"); // 支付结果异步通知地址
        map.put("orderDesc", "账户消费ֵ"); // 订单描述
        map.put("terminalNo", "001");
        map.put("timestamp", timestamp);
        map.put("auth_code", "84" + String.valueOf(acctId^timestampl));

        map.put("transportParams", transportParams);

        // 下单参数签名操作
        String paramStr  = toUrlString(map);
        String waitDoMD5 = paramStr + "&key=" + key;
        String sign      = MD5.MD5Encode(MD5.MD5Encode(waitDoMD5).toUpperCase()).toUpperCase();

        // 将签名加入待传参数
        map.put("sign", sign);

        try {
            // 向众城付系统请求下单
            String resp = HttpUtil.doPost("http://10.0.0.105:8080/dp/tsmPay/submitOrder", map);

            // 下单结果
            System.out.println(resp);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String toUrlString(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())) {
                list.add(entry.getKey() + "=" + entry.getValue().toString() + "&");
            }
        }
        int size = list.size();

        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
