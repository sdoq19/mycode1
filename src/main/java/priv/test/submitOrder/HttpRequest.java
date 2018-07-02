package priv.test.submitOrder;

import org.apache.commons.lang3.StringUtils;
import priv.utils.HttpUtil;
import priv.utils.MD5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class HttpRequest {
    /**
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "GBK");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

  
    /**
     * �����Լ�ֵ��ת��Ϊk1=v1&k2=v2��ʽ
     *
     * @param map
     * @return
     */
    public static String toUrlString(Map<String, Object> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue()!=null && StringUtils.isNotBlank(entry.getValue().toString())) {
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
    
    public static void main(String[] args) throws Exception {
        //���� GET ����
    	Map<String, Object> map=new HashMap();
    	map.put("merchantNo", "20000036");
    	map.put("merchantOrderNo", "201710180005");
    	map.put("orderAmt", "7");
    	map.put("orderDesc", "中文ֵ");
    	map.put("orderLife", "5");
    	map.put("backendUrl", "http://122.122.122.122");
    	map.put("terminalType", "web");
    	map.put("payChannel", "alipay_f2f");
    	//map.put("terminalType", "web");
    	//map.put("payChannel", "wechat_mp_native");
    	String param_base=toUrlString(map);
    	String param_md5=param_base+"&key=bdd63bcd00da41de8281d4599550dd2a";  	
    	String param_sign= MD5.MD5Encode(MD5.MD5Encode(param_md5).toUpperCase()).toUpperCase();
    	// String s=HttpRequest.sendGet("http://tsmpaytest.allcitygo.com/dp/tsmPay/submitOrder",
    	// 		param_base+"&sign="+param_sign);

        map.put("sign", param_sign);

        Map<String, String> newHashMap = new HashMap<>();
        for(Map.Entry<String, Object> entry: map.entrySet()) {
            newHashMap.put(entry.getKey(), entry.getValue().toString());
        }
        System.out.println(HttpUtil.doPost("http://tsmpaytest.allcitygo.com/dp/tsmPay/submitOrder", newHashMap));

    	try{
          // s=new String(s.getBytes(),"utf-8");
          // System.out.println(""+s);
    	}catch(Exception e){
    	  e.printStackTrace();
    	}
        

        	
        Map<String, Object> map1 = new HashMap<String, Object>();

		// try {
			// map1= JSONUtils.toMap(s);
			// System.out.println(map1.toString());
			// System.out.println(map1.get("code").toString());
			// System.out.println(map1.get("message").toString());
			// if(map1.get("code").toString().equals("1")){
			// 	System.out.println(map1.get("data").toString());
			// }
		// } catch (HZException e) {
		// 	e.printStackTrace();
		// }
		
    }
}