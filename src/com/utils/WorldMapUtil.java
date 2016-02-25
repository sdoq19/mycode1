package com.utils;


/** 地图工具类 */
public class WorldMapUtil {
	private static final double EARTH_RADIUS = 6378137.0;
	
	/**
	 * 计算AB两点距离(米)
	 * @param lat_a	A点纬度
	 * @param lng_a	A点经度
	 * @param lat_b	B点纬度
	 * @param lng_b	B点经度
	 * @return	AB两点距离多少米
	 */
	public static double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = (lat_a * Math.PI / 180.0);
		double radLat2 = (lat_b * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (lng_a - lng_b) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 计算角方位
	 * @param lat_a	A点纬度
	 * @param lng_a	A点经度
	 * @param lat_b	B点纬度
	 * @param lng_b	B点经度
	 * @return
	 */
	public static double gps2d(double lat_a, double lng_a, double lat_b, double lng_b) {
		double d = 0;
		lat_a=lat_a*Math.PI/180;
		lng_a=lng_a*Math.PI/180;
		lat_b=lat_b*Math.PI/180;
		lng_b=lng_b*Math.PI/180;
		
		d=Math.sin(lat_a)*Math.sin(lat_b)+Math.cos(lat_a)*Math.cos(lat_b)*Math.cos(lng_b-lng_a);
		d=Math.sqrt(1-d*d);
		d=Math.cos(lat_b)*Math.sin(lng_b-lng_a)/d;
		d=Math.asin(d)*180/Math.PI;
		
//		d = (d*10000);
		return d;
	}
	
	/***
	 * 计算人与坐标点的距离(单位：米)
	 * @param lat_a	坐标点纬度
	 * @param lng_a	坐标点经度
	 * @param userid	人的id
	 * @return	坐标点相距人的距离(单位：米)
	 * @throws Exception
	 
	public static double getRange(double lat_a, double lng_a,long userid)throws Exception {
		CacheClient cc = RmiProxy.getCacheService();
		Heartbeat hb = cc.getHeartbeat(userid);
		return WorldMapUtil.gps2m(lat_a, lng_a, hb.getLat(), hb.getLng());
	}*/
	
	/***
	 * 计算两个人相距的距离(单位：米)
	 * @param userid1	第一个人的id
	 * @param userid2	第二个人的id
	 * @return	坐标点相距人的距离(单位：米)
	 * @throws Exception
	
	public static double getRange(long userid1, long userid2)throws Exception {
		CacheClient cc = RmiProxy.getCacheService();
		Heartbeat hb1 = cc.getHeartbeat(userid1);
		Heartbeat hb2 = cc.getHeartbeat(userid2);
		return WorldMapUtil.gps2m(hb1.getLat(), hb1.getLng(), hb2.getLat(), hb2.getLng());
	} */
}
