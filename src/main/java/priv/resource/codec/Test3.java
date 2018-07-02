package priv.resource.codec;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test3 {

	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "小明");
		map.put("age", 12);
		map.put("sex", "男");
		map.put("school", "xxx中学");
		map.put("address", "xxx小区");
		/*** MD5签名与验签 **/
		String key = "123456ADSEF";
		String sign = MD5sign(map, key);
		System.out.println("生成的MD5签名：" + sign);
		vlidateMD5sign(map, key, sign);
		/*** RSA签名与验签 **/
		String prikey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOq30rck7L3FshHVYWJK59sTToGMAn7WfYdrFN60AmPPyiMcIFXe3ZAxf7SWNbaQOPUz/xYr+oAXUBK17bykS/E2+Xa74wdN2VNbc7cZIggAjP9tGN0qhYTclbtC3pchcU8TVccrlVUN2lzJDLBHhPBDBFXzsQx9Vwtm2qjf2GcrAgMBAAECgYEAsHnz4aXOpkTNRSFVbiz5tLsIbNjTS4CDs1ysvWFE5rzls45DNa0yk2bUKPhDfHdli99DbO02FDbzCo5lKE+zlEHaC/WTp6guEe7jj5dwMl3shBZmgITCTk1/MQ46gGRG4RRADbQT/Y7tENp/GF3y9oJyJ+LmHFvfdEjSuY1/QzECQQD6aKqYFO8wuhLhy1fTvjMwlzok0szT9wTp+l6E7Ct9+csvdwaYjJrGsr6kUv+6YUwieSJ41lVtGnRy1oXEQG2TAkEA7/V35kYG+FMwYq/DOrBNaomRQGJVAOLzGRoK2dkjAkpoUAfzk4TTQ0KdJJ3T6mzF/6IQY+1oFDD42kNKJklfCQJARiya0i/bsC4VKI3RuRcuRUm8E6G3oRcym1d8sYd10MH1/QFAKfQNU+23m1lfLR4jNe34iSCXpBGr3JrdtdfQXQJAXgWRkGHZ800tRU3XMlTIULlMd6zP38QNOsWwgMGK7SfYjZs//opp+Q3N4v4QfedXAZ4vy+fHAzpZF7SMBkpzeQJALlMaKKeqKvPr8abXSRjW8u6s8tHaHX6CRV/1fGDX1bkUByqdFMO5CqIHn7isK2dHXI42bJVz63/d2Aax3lTbkA==";
		String pubkey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqt9K3JOy9xbIR1WFiSufbE06BjAJ+1n2HaxTetAJjz8ojHCBV3t2QMX+0ljW2kDj1M/8WK/qAF1ASte28pEvxNvl2u+MHTdlTW3O3GSIIAIz/bRjdKoWE3JW7Qt6XIXFPE1XHK5VVDdpcyQywR4TwQwRV87EMfVcLZtqo39hnKwIDAQAB";
		String rsaSign = RSAsign(map, prikey);
		System.out.println("生成的RSA签名：" + rsaSign);
		vlidateRSAsign(map, rsaSign, pubkey);

	}

	/**
	 * RSA生成签名字符串
	 * 
	 * @param map
	 *            需签名参数
	 * @param prikey
	 *            rsa私钥
	 * @return
	 */
	public static String RSAsign(Map<String, Object> map, String prikey) {
		String genSign = "";
		try {

			String[] signFields = new String[5];
			signFields[0] = "name";
			signFields[1] = "age";
			signFields[2] = "sex";
			signFields[3] = "school";
			signFields[4] = "address";
			JSONObject param = (JSONObject) JSONObject.toJSON(map);
			// 生成签名原文
			String src = orgSignSrc(signFields, param);
			genSign = RsaUtil.sign(src, prikey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genSign;
	}

	/**
	 * RSA生成签名字符串
	 * 
	 * @param map
	 *            需签名参数
	 * @param key
	 *            MD5key
	 * @return
	 */
	public static String vlidateRSAsign(Map<String, Object> map, String sign,
			String publickey) {
		String genSign = "";
		try {

			String[] signFields = new String[5];
			signFields[0] = "name";
			signFields[1] = "age";
			signFields[2] = "sex";
			signFields[3] = "school";
			signFields[4] = "address";
			JSONObject param = (JSONObject) JSONObject.toJSON(map);
			// 生成签名原文
			String signSrc = orgSignSrc(signFields, param);
			// 调用工具类验签
			boolean bool = RsaUtil.verify(signSrc, sign, publickey);
			System.out.println("验证签名生成的签名与原签名是否一致： true?false:" + bool);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return genSign;
	}

	/**
	 * MD5生成签名字符串
	 * 
	 * @param map
	 *            需签名参数
	 * @param key
	 *            MD5key
	 * @return
	 */
	public static String MD5sign(Map<String, Object> map, String key) {
		String genSign = "";
		try {

			String[] signFields = new String[5];
			signFields[0] = "name";
			signFields[1] = "age";
			signFields[2] = "sex";
			signFields[3] = "school";
			signFields[4] = "address";
			JSONObject param = (JSONObject) JSONObject.toJSON(map);
			// 生成签名原文
			String signSrc = orgSignSrc(signFields, param);
			// MD5的方式签名
			signSrc += "&KEY=" + key;
			genSign = MD5Encrypt.getMessageDigest(signSrc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return genSign;
	}

	/**
	 * MD5验证签名
	 * 
	 * @param map
	 * @param key
	 * @param sign
	 * @return
	 */
	public static void vlidateMD5sign(Map<String, Object> map, String key,
			String sign) {
		String vsign = MD5sign(map, key);
		System.out.println("MD5验证签名生成的签名：" + vsign);
		System.out.println("MD5验证签名生成的签名与原签名是否一致：sign=vsign true?false:"
				+ (vsign.equals(sign)));
	}

	/**
	 * 构建签名原文
	 * 
	 * @param signFilds
	 *            参数列表
	 * @param param
	 *            参数与值的jsonbject
	 * @return
	 */
	private static String orgSignSrc(String[] signFields, JSONObject param) {
		if (signFields != null) {
			Arrays.sort(signFields); // 对key按照 字典顺序排序
		}

		StringBuffer signSrc = new StringBuffer("");
		int i = 0;
		for (String field : signFields) {
			signSrc.append(field);
			signSrc.append("=");
			signSrc.append((StringUtil.isEmpty(param.getString(field)) ? ""
					: param.getString(field)));
			// 最后一个元素后面不加&
			if (i < (signFields.length - 1)) {
				signSrc.append("&");
			}
			i++;
		}
		return signSrc.toString();
	}

}
