package priv.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

/**
 * 地图工具类
 * 
 * Aug 10, 2013 10:30:54 AM
 */
public class MapUtil {

	// 地球半径 6378.137 公里
	private static double EARTH_RADIUS = 6378.137;
	
	/**
	 * 功能：根据地址得到经纬度信息,数据来源至谷歌。
	 * 
	 * Aug 10, 2013 10:33:58 AM
	 * @param address 地址
	 * @param format 只允许传入xml或者json。
	 * @return String 其中结果状态status 含义<br/>
	 * "OK" 表示未发生错误；地址成功进行了解析并且至少传回了一个地址解析结果。（判断请求是否成功响应）<br/>
	 * "ZERO_RESULTS" 表示地址解析成功，但未返回结果。如果地址解析过程中传递的偏远位置 address 或 latlng 并不存在，则会出现这种情况。<br/>
	 * "OVER_QUERY_LIMIT" 表示您超出了配额。<br/>
	 * @throws IOException 请求google api异常。
	 * @throws ClientProtocolException 请求google api异常。
	 */
	public static String getlatlngByAddress(String address,String format) throws ClientProtocolException, IOException{
		HttpCilentUtil hu=new HttpCilentUtil();
		String url="http://maps.google.com/maps/api/geocode/"+format+"?address="+address+"&language=zh-CN&sensor=false";
		return hu.requestUrl(url);
	}
	
	/**
	 * 功能：根据经纬度得到地址信息,数据来源至谷歌。
	 * 
	 * Aug 10, 2013 10:33:58 AM
	 * @param lat 纬度
	 * @param lng 经度 
	 * @param format 只允许传入xml或者json。
	 * @return String 其中结果状态status 含义<br/>
	 * "OK" 表示未发生错误；地址成功进行了解析并且至少传回了一个地址解析结果。（判断请求是否成功响应）<br/>
	 * "ZERO_RESULTS" 表示地址解析成功，但未返回结果。如果地址解析过程中传递的偏远位置 address 或 latlng 并不存在，则会出现这种情况。<br/>
	 * "OVER_QUERY_LIMIT" 表示您超出了配额。<br/>
	 * @throws IOException 请求google api异常。
	 * @throws ClientProtocolException 请求google api异常。
	 */
	public static String getAddressBylatlng(String lat ,String lng,String format) throws ClientProtocolException, IOException{
		HttpCilentUtil hu=new HttpCilentUtil();
		String url="http://maps.google.com/maps/api/geocode/"+format+"?latlng="+lat+","+lng+"&language=zh-CN&sensor=false";
		return hu.requestUrl(url);
	}
	
	/**
	 * 根据地球上两点经纬度计算两点的距离，单位为千米。
	 * @param lat1 第一个点的纬度
	 * @param lng1 第一个点的经度
	 * @param lat2 第儿个点的纬度
	 * @param lng2 第二个点的经度
	 * @return 距离单位为千米。
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		// s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 用于计算地球上两点之间的距离。
	 * @param d
	 * @return
	 */
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
}
