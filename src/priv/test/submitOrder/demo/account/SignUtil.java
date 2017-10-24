package priv.test.submitOrder.demo.account;

import org.apache.commons.lang.StringUtils;
import priv.utils.MD5;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 融合支付签名工具类
 * Created by zhuzh on 2016/12/27.
 */
public class SignUtil {


    /**
     * 验签
     *
     * @param clazz       存有签名数据的对象
     * @param merchantKey 商户key
     */
    public static Boolean verifySign(Object clazz, String merchantKey) {
        Map map = SignUtil.toMap(clazz);
        String sign = map.get("sign").toString();
        map.remove("serialVersionUID");
        map.remove("sign");
        String _sign = SignUtil.getSign(map, merchantKey);
        if(sign.equals(_sign)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public static Boolean verifySign(Object clazz, String sign,String merchantKey) {
        Map map = SignUtil.toMap(clazz);
        map.remove("serialVersionUID");
        String _sign = SignUtil.getSign(map, merchantKey);
        if(sign.equals(_sign)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean verifySignWithMap(HashMap<String,Object> params, String sign,String merchantKey) {    
        String _sign = SignUtil.getSign(params, merchantKey);
        if(sign.equals(_sign)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 获取签名（二次MD5）
     */
    public static String getSign(Map<String, Object> map, String merchantKey) {
        String param   = SignUtil.toUrlStringWithKey(map, merchantKey);
        String oneStep = MD5.sign(param, "", "utf-8").toUpperCase();
        String sign    = MD5.sign(oneStep, "", "utf-8").toUpperCase();
        //System.out.println("param        -------------------->" + param);
        //System.out.println("一次MD5      -------------------->" + oneStep);
        //System.out.println("二次MD5[sign]-------------------->" + sign);
        return sign;
    }

    /**
     * 获取签名（一次MD5）
     */
    public static String getSignOneMD5(Map<String, Object> map, String merchantKey) {
        String param   = SignUtil.toUrlStringWithKey(map, merchantKey);
        String oneStep = MD5.sign(param, "", "utf-8").toUpperCase();
        return oneStep;
    }

    /**
     * 将对象中的属性转为Map
     */
    public static Map<String, String> toMap(Object clazz) {
        Map<String, String> map    = new HashMap<String, String>();
        Field[]             fields = clazz.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = null;
            try {
                obj = field.get(clazz);
                if (obj != null && StringUtils.isNotBlank(obj.toString())) {
                    map.put(field.getName(), obj.toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 将属性键值对转化为k1=v1&k2=v2形式
     *
     * @param map
     * @return
     */
    public static String toUrlString(Map<String, Object> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())) {
                list.add(entry.getKey() + "=" + entry.getValue().toString() + "&");
            }
        }
        int      size        = list.size();
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

    /**
     * 将本对象非空字段转化为key1=val1&key2=val2的格式
     *
     * @param map         本对象非空字段map
     * @param merchantKey 商户密钥
     */
    public static String toUrlStringWithKey(Map<String, Object> map, String merchantKey) {

        String result = toUrlString(map) + "&key=" + merchantKey;
        return result;
    }
    
   public static void main(String [] args){
	   HashMap<String,Object> requestParams = new HashMap<String,Object>();
	   requestParams.put("merchantNo", "10000043");
		requestParams.put("date", "20170303");
		String merchant_key = "41ee167f7fff7f099be32fdb7d3f1286";
		String sign = getSign(requestParams,merchant_key);
		System.out.println("sign:"+sign);
   }
}
