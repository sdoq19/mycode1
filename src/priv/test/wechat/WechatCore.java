/**
 * 
 */
package priv.test.wechat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import priv.utils.MD5;

import java.lang.reflect.Field;
import java.util.*;


/**
 * @author beam
 *
 */
public class WechatCore {

	 public static String getMapMD5Sign(Map<String,String> requestParams,String key){
	        ArrayList<String> list = new ArrayList<String>();
	        for(Map.Entry<String,String> entry:requestParams.entrySet()){
	            if(entry.getValue()!=""){
	                list.add(entry.getKey() + "=" + entry.getValue() + "&");
	            }
	        }
	        int size = list.size();
	        String [] arrayToSort = list.toArray(new String[size]);
	       
	        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
	        
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < size; i ++) {
	            sb.append(arrayToSort[i]);
	        }
	        String result = sb.toString();
	        result += "key=" + key;
	        result = MD5.MD5Encode(result).toUpperCase();
	        return result;
	    }
	 
	 /**
	     * 签名算法
	     * @param o 要参与签名的数据对象
	     * @return 签名
	     * @throws IllegalAccessException
	     */
	    public static String getMD5Sign(Object o,String key) throws IllegalAccessException {
	        ArrayList<String> list = new ArrayList<String>();
	        Class cls = o.getClass();
	        Field[] fields = cls.getDeclaredFields();
	        for (Field f : fields) {
	            f.setAccessible(true);
	            if (f.get(o) != null && f.get(o) != "") {
	            	if("sign".equals(f.getName())){
	            		continue;
	            	}
	                list.add(f.getName() + "=" + f.get(o) + "&");
	            }
	        }
	        int size = list.size();
	        String [] arrayToSort = list.toArray(new String[size]);
	        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < size; i ++) {
	            sb.append(arrayToSort[i]);
	        }
	        String result = sb.toString();
	        result += "key=" + key;
	        result = MD5.MD5Encode(result).toUpperCase();
	        return result;
	    }
	    
	    public static String getMD5Sign(String waitStr,String key){
	    	String tmpStr = waitStr+"&key="+key;
	        String result = MD5.MD5Encode(tmpStr).toUpperCase();
	        return result;
	    }
	    
	    /*
	     * 从响应xml串中获取待签名的字符串。
	     * */
	    public static String getSignSorceData(String xmlResponse){
	    	String sourceData = "";
	    	
	    	if(null == xmlResponse||"".equals(xmlResponse)){
	    		return null;
	    	}
	    	
	    	Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlResponse);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				return null;
			}
			if(null == doc){
				return null;
			}
			
			HashMap<String,String> params   = new HashMap<String,String>();
			Element                root     = doc.getRootElement();
			Iterator               iterator = root.elementIterator();
			while ( iterator.hasNext()) {
				Element curItem   = (Element) iterator.next();
				String  nodeName  = curItem.getName();
				String  nodeValue = curItem.getText();
				if("sign".equals(nodeName)||"".equals(nodeValue)){//过滤掉sign字段和空值字段
					continue;
				}
				params.put(nodeName, nodeValue);
			}
			
	    	return getJointString(params);
	    	
	    }
	    
	    /**
	     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	     *
	     * @param params 需要排序并参与字符拼接的参数组
	     * @return 拼接后字符串
	     */
	    public static String getJointString(Map<String, String> params) {

	        List<String> keys = new ArrayList<String>(params.keySet());
	        Collections.sort(keys);

	        String prestr = "";

	        for (int i = 0; i < keys.size(); i++) {
	            String key   = keys.get(i);
	            String value = params.get(key);

	            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
	                prestr = prestr + key + "=" + value;
	            } else {
	                prestr = prestr + key + "=" + value + "&";
	            }
	        }

	        return prestr;
	    }

        /*验证http响应的签名
         * 
         * @param calcSign 计算出的签名
         * @param responseStr 接受到的http xml响应字符串
         * */
	    public static boolean verfiySign(String calcSign,String xmlResponse){
	    	if(null == xmlResponse||"".equals(xmlResponse)){
	    		return false;
	    	}
	    	
	    	Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlResponse);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				return false;
			}
			if(null == doc){
				return false;
			}
			
			HashMap<String,String> params   = new HashMap<String,String>();
			Element                root     = doc.getRootElement();
			Iterator               iterator = root.elementIterator();
			String                 recvSign = "";
			while ( iterator.hasNext()) {
				Element curItem   = (Element) iterator.next();
				String  nodeName  = curItem.getName();
				String  nodeValue = curItem.getText();
				if("sign".equals(nodeName)){//过滤掉sign字段和空值字段
					recvSign = nodeValue;
					break;
				}
			}
			return calcSign.equals(recvSign)?true:false;
	    }
	    
	    //获取随机串
	    public static String getNonceStr(){
	    	 java.util.Random r=new java.util.Random(); 
	    	 Long random = r.nextLong();
	    	 return random.toString();
	    }
	    
	    public static boolean verifySign(Map<String,String>respParams,String key,String signType){
	    	
	    	Map<String,String> filterParams = new HashMap<String,String>();
	    	String sign = "";
	    	for(String keyVal:respParams.keySet()){
	    		if("".equals(respParams.get(keyVal))){
	    			continue;
	    		}
	    		if("sign".equals(keyVal)){
	    			sign = respParams.get(keyVal);
	    		}else{
	    			if(!filterParams.containsKey(keyVal)){
	    				filterParams.put(keyVal, respParams.get(keyVal));
	    			}
	    		}
	    	}
	    
	    	
	    	if("".equals(sign)){
	    		return false;
	    	}
	    	
	    	String waitStr = getJointString(filterParams);
	    	
	    	String calcedSign = "";
	    	if(null==signType||"MD5".equals(signType)||"".equals(signType)){
	    		calcedSign = getMD5Sign(waitStr,key);
	    	}
	    	
	    	if(calcedSign.equals(sign)){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
	    
	    public static Map<String,String> parseFromXml(String xmlResponse){
	    	if(null == xmlResponse||"".equals(xmlResponse)){
	    		return null;
	    	}
	    	
	    	Document doc = null;
			try {
				doc = DocumentHelper.parseText(xmlResponse);
				if(null == doc){
					return null;
				}
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				return null;
			}
			
			
			HashMap<String,String> params   = new HashMap<String,String>();
			Element                root     = doc.getRootElement();
			Iterator               iterator = root.elementIterator();
			
			while ( iterator.hasNext()) {
				Element curItem   = (Element) iterator.next();
				String  nodeName  = curItem.getName();
				String  nodeValue = curItem.getText();
				params.put(nodeName,nodeValue);
			}
			return params;
	    }
}
