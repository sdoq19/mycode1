/**
 * 
 */
package priv.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 *
 */
public class Map2Xml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void mapToXmlCore(Map<String,String> map,StringBuffer strBuf){
		Set set = map.keySet();  
        for (Iterator it = set.iterator(); it.hasNext();) {  
            String key = (String) it.next();  
            Object value = map.get(key);  
            if (null == value){
            	 value = "";    
            }         
            else {  
                	strBuf.append("<" + key + ">" + value + "</" + key + ">");  
                }  
            }  
  
	}
	
	public static String wechatRequestMapToXml(Map<String,String> request){
		StringBuffer retXml = new StringBuffer();  
		retXml.append("<xml>");
		mapToXmlCore(request,retXml);
		retXml.append("</xml>");
		return retXml.toString();
	}
}
