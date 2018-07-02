package priv.test.test;

import org.json.JSONObject;

/**
 * Created by zhuzh on 2017-6-27.
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "\"{\" +\n" +
                "                \"      \\\"trade_list\\\":[{\" +\n" +
                "                \"        \\\"user_id\\\":\\\"2088011211223344\\\",\" +\n" +
                "                \"\\\"out_trade_no\\\":\\\"LINE1_POS1_20160425120102\\\",\" +\n" +
                "                \"\\\"actual_order_time\\\":\\\"2016-11-11 11:11:11\\\",\" +\n" +
                "                \"\\\"subject\\\":\\\"984路公交\\\",\" +\n" +
                "                \"\\\"amount\\\":2,\" +\n" +
                "                \"\\\"order_biz_context\\\":\\\"{\\\\\\\"line\\\\\\\":\\\\\\\"2\\\\\\\", \\\\\\\"start_pos_id\\\\\\\":\\\\\\\"01\\\\\\\", \\\\\\\"start_station\\\\\\\":\\\\\\\"松江大学城\\\\\\\",\\\\\\\"sys_service_provider_id\\\\\\\":\\\\\\\"2088000011112222\\\\\\\"}\\\",\" +\n" +
                "                \"          \\\"records\\\":[\" +\n" +
                "                \"            \\\"0a3feae2ad6d7c.....\\\"\" +\n" +
                "                \"          ],\" +\n" +
                "                \"\\\"seller_login_name\\\":\\\"pc@alipay.com\\\"\" +\n" +
                "                \"        }]\" +\n" +
                "                \"  }\"";
        System.out.println(a);

        String b = "{\"trade_list\":\"[{\\\"actual_order_time\\\":\\\"2017-06-27 10:20:00\\\",\\\"amount\\\":\\\"0.01\\\",\\\"out_trade_no\\\":\\\"o171786025600923009416664\\\",\\\"records\\\":\\\"[\\\\\\\"1\\\\\\\",\\\\\\\"4\\\\\\\"]\\\",\\\"subject\\\":\\\"desc\\\",\\\"user_id\\\":\\\"2088102035812323\\\"}]\"}";
        System.out.println(b);

        AlipayOfflineTrade trade = new AlipayOfflineTrade();
        trade.setActual_order_time("2017-06-27 10:20:00");
        trade.setAmount("0.01");
        trade.setOrder_biz_context("");
        trade.setOut_trade_no("osdf");
        String records = "[\"123\", \"456\"]";
        trade.setRecords(records);
        trade.setSubject("subject");

        JSONObject jsonObject = new JSONObject();
    }
}
