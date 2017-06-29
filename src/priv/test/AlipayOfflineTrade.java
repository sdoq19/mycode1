package priv.test;

/**
 * 支付宝待结算脱机交易
 * Created by zhuzh on 2017-6-8.
 */
public class AlipayOfflineTrade {

    private String user_id;
    private String out_trade_no;
    private String actual_order_time;
    private String subject;
    private String amount;
    private String order_biz_context;
    private String records;
    private String seller_login_name;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getActual_order_time() {
        return actual_order_time;
    }

    public void setActual_order_time(String actual_order_time) {
        this.actual_order_time = actual_order_time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrder_biz_context() {
        return order_biz_context;
    }

    public void setOrder_biz_context(String order_biz_context) {
        this.order_biz_context = order_biz_context;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getSeller_login_name() {
        return seller_login_name;
    }

    public void setSeller_login_name(String seller_login_name) {
        this.seller_login_name = seller_login_name;
    }
}
