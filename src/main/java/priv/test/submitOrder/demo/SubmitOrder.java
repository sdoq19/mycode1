package priv.test.submitOrder.demo;

import org.apache.commons.lang3.StringUtils;
import priv.utils.HttpUtil;
import priv.utils.JsonUtil;
import priv.utils.MD5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzh on 2017-10-19.
 */
public class SubmitOrder {

    // 商户号
    public static final String merchantNo = "10000151";

    // 商户key
    public static final String key = "17831e7b9064ee4b640c5810b36b553d";

    public static void main(String[] args) {

        // 下单基本参数
        Map<String, String> map = new HashMap();
        map.put("merchantNo", merchantNo); // 商户号
        map.put("merchantOrderNo", merchantNo + System.currentTimeMillis()); // 订单号
        map.put("orderAmt", "1"); // 订单金额
        map.put("terminalType", "web"); // 终端类型
        map.put("payChannel", "alipay_f2f"); // 支付方式
        map.put("orderLife", "5"); // 订单有效期
        map.put("backendUrl", "http://backendUrl"); // 支付结果异步通知地址
        map.put("orderDesc", "中文会乱码吗?ֵ"); // 订单描述

        // 下单参数签名操作
        String paramStr  = toUrlString(map);
        String waitDoMD5 = paramStr + "&key=" + key;
        String sign      = MD5.MD5Encode(MD5.MD5Encode(waitDoMD5).toUpperCase()).toUpperCase();

        // 将签名加入待传参数
        map.put("sign", sign);

        try {
            // 向众城付系统请求下单
            Long   beginTime = System.currentTimeMillis();
            String resp      = HttpUtil.doPost("http://tsmpaytest.allcitygo.com/dp/tsmPay/submitOrder", map);
            Long   endTime   = System.currentTimeMillis();

            // 下单结果
            System.out.println("耗时：" + (endTime - beginTime) + "," + resp);

            // 验签
            Map<String, String> rtnMap = JsonUtil.jsonToStrMap(resp);

            String rtnSign = rtnMap.get("sign").toString();
            rtnMap.remove("sign");
            waitDoMD5 = toUrlString(rtnMap) + "&key=" + key;
            String verifySign = MD5.MD5Encode(MD5.MD5Encode(waitDoMD5).toUpperCase()).toUpperCase();

            System.out.println("返回的签名：" + rtnSign);
            System.out.println("验证的签名：" + verifySign);
            if (verifySign.equals(rtnSign)) {
                System.out.println("验签成功");
            } else {
                System.out.println("验签失败");
            }

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
