package priv.test.submitOrder.demo;

import org.apache.commons.lang3.StringUtils;
import priv.utils.HttpUtil;
import priv.utils.MD5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzh on 2017-12-19.
 */
public class GetConfig {
    public static void main(String[] args) {
        // 下单基本参数
        Map<String, String> map = new HashMap();
        map.put("merchantNo", "20000052");
        map.put("timestamp", "20171219155700");

        // 下单参数签名操作
        String paramStr  = toUrlString(map);
        String waitDoMD5 = paramStr + "&key=" + "5AF1964C323E945B06A24537ECC62D7B";
        String sign      = priv.utils.MD5.MD5Encode(MD5.MD5Encode(waitDoMD5).toUpperCase()).toUpperCase();
        map.put("sign", sign);

        try {
            // 向众城付系统请求下单
            Long beginTime = System.currentTimeMillis();
            String resp = HttpUtil.doPost("http://localhost:8888/dp/internal/getMerchantInfo", map);
            Long endTime = System.currentTimeMillis();

            // 下单结果
            System.out.println("耗时：" + (endTime - beginTime) + "," + resp);

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
