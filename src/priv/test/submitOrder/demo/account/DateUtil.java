package priv.test.submitOrder.demo.account;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 说明：日期处理
 * 创建人：
 * 修改时间：2015年11月24日
 */
public class DateUtil {

    // 以下为线程不安全，慎重使用
    public final static SimpleDateFormat sdfYear  = new SimpleDateFormat("yyyy");
    public final static SimpleDateFormat sdfDay   = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat sdfDays  = new SimpleDateFormat("yyyyMMdd");
    public final static SimpleDateFormat sdfTime  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
    public final static SimpleDateFormat sdfMin   = new SimpleDateFormat("yyyyMMddHHmm");
    public final static SimpleDateFormat sdfMins  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static SimpleDateFormat sdfMilli = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // 使用threadLocal，线程安全
    /**
     * 使用方法:dateFormat.set()后，调用get()即可使用
     */
    public static final ThreadLocal<DateFormat> dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    /**
     * 字符串转时间
     * @param source
     * @return
     * @throws ParseException
     */
    public Date convert(String source) throws ParseException {
        Date d = dateFormat.get().parse(source);
        return d;
    }


    /**
     * 获取当前时间的Unix时间戳
     */
    public static Long unixTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取Unix时间戳
     */
    public static Long unixTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 获取Unix时间戳
     */
    public static Long unixTimestamp(String dateStr, DateFormat df) throws ParseException {
        Date date = df.parse(dateStr);
        return date.getTime() / 1000;
    }

    public static Date unixTimestampToDate(Long gmt) {
        return new Date(gmt * 1000);
    }

    /**
     * 获取yyyyMMddHHmmss格式
     *
     * @return
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取yyyyMMddHHmmss
     *
     * @return
     */
    public static String getSdfTimes(Date date) {
        return sdfTimes.format(date);
    }

    /**
     * 获取yyyyMMddHHmmss
     * <p>
     * gmt 秒级别
     */
    public static String getDateFromGmt(Long gmt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date             date             = new Date(gmt * 1000);
        String           retDate          = simpleDateFormat.format(date);
        return retDate;
    }
    
    /**
     * 获取yyyy-MM-dd hh:mm:ss
     * gmt 秒级别
     */
    public static String getDateFormatFromGmt(Long gmt){
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date             date             = new Date(gmt * 1000);
        String           retDate          = simpleDateFormat.format(date);
        return retDate;
    }
    


    /**
     * 获取yyyyMMdd
     * <p>
     * gmt 秒级别
     */
    public static String getDateFromGmtYYYYMMDD(Long gmt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date             date             = new Date(gmt * 1000);
        String           retDate          = simpleDateFormat.format(date);
        return retDate;
    }
    
    /**
     * 获取yyyyMMdd
     * <p>
     * gmt 秒级别
     */
    public static String getDateFromGmtYYYYMMDDGmt(Long gmt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date             date             = new Date(gmt * 1000);
        String           retDate          = simpleDateFormat.format(date);
        return retDate;
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay(String dateStr) {
        Date d = fomatDate(dateStr);
        return sdfDay.format(d);
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getYesteday() {
        Long now      = System.currentTimeMillis();
        Long yesteday = now - 24 * 3600 * 1000;
        return sdfDay.format(new Date(yesteday));
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime(Date date) {
        return sdfTime.format(date);
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomat2Date(s).getTime() >= fomat2Date(e).getTime();
    }


    /**
     * 格式化日期
     * yyyyMMdd
     *
     * @return
     */
    public static String fomatDateyyyyMMdd(String date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateTime=fmt.parse(date);
            return DateUtil.format2str(dateTime, "yyyyMMdd");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     * yyyy-MM-dd
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     * "yyyyMMdd"
     *
     * @return
     */
    public static Date fomatDateNOBar(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date fomat2Date(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date fomatDate(Date date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String time = fmt.format(date);
            return fmt.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 当天零点
     */
    public static Date todayZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //long aa=0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long                       day       = 0;
        SimpleDateFormat format    = new SimpleDateFormat("yyyy-MM-dd");
        Date             beginDate = null;
        Date             endDate   = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterSecondDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String           dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int      daysInt   = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date             date    = canlendar.getTime();
        SimpleDateFormat sdf     = new SimpleDateFormat("E");
        String           dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 得到n秒之后的日期
     *
     * @param second
     * @return
     */
    public static Date getAfterSecondDate(Date now, Integer second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static String format2String(String format) {
        Date             date = new Date();
        SimpleDateFormat sdf  = new SimpleDateFormat(format);
        String           now  = sdf.format(date);
        return now;
    }


    /**
     * 获取当天零时的秒数
     *
     * @return
     */
    public static Long getStarSecond() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    /**
     * 格式化日期到字符串
     *
     * @param date
     * @param sformat
     * @return
     */
    public static String format2str(Date date, String sformat) {
        SimpleDateFormat sdf = new SimpleDateFormat(sformat);
        return sdf.format(date);
    }

    /**
     * 格式化日期到字符串
     *
     * @param sformat
     * @return
     */
    public static String format2str(String sformat) {
        Date date = new Date();
        return format2str(date, sformat);
    }

    /**
     * 格式化日期到字符串
     *
     * @return
     */
    public static String format2str() {
        Date   date    = new Date();
        String sformat = "yyyy-MM-dd";
        return format2str(date, sformat);
    }

    /**
     * 格式化日期到日期格式
     *
     * @param date
     * @param sformat
     * @return
     */
    public static Date format2date(Date date, String sformat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(sformat);
        return sdf.parse(sdf.format(date));
    }

    /**
     * 格式化日期到日期格式
     *
     * @param sformat
     * @return
     */
    public static Date format2date(String sformat) throws ParseException {
        Date date = new Date();
        return format2date(date, sformat);
    }

    /**
     * 格式化日期到日期格式
     *
     * @return
     */
    public static Date format2date() throws ParseException {
        Date   date    = new Date();
        String sformat = "yyyy-MM-dd";
        return format2date(date, sformat);
    }

    /**
     * 增加1天
     *
     * @param date
     * @return
     */
    public static Date addday(Date date) {
        return addday(date, 1);
    }

    /**
     * 增加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addday(Date date, int days) {
        return new Date(date.getTime() + days * 60 * 60 * 24 * 1000);
    }

    /**
     * 增加天数
     *
     * @param date
     * @param days
     * @return
     * @throws ParseException
     */
    public static Date addday(String date, int days, String sformat) throws ParseException {
        SimpleDateFormat sdf   = new SimpleDateFormat(sformat);
        Date             ndate = sdf.parse(date);
        return new Date(ndate.getTime() + days * 60 * 60 * 24 * 1000);
    }

    /**
     * 增加1个月
     *
     * @param date
     * @return
     */
    public static Date addmonth(Date date) {
        return addmonth(date, 1);
    }

    /**
     * 增加月份
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addmonth(Date date, int months) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(2, months);
        return gc.getTime();
    }

    /**
     * 增加1年
     *
     * @param date
     * @return
     */
    public static Date addyear(Date date) {
        return addyear(date, 1);
    }

    /**
     * 增加年份
     *
     * @param date
     * @param years
     * @return
     */
    public static Date addyear(Date date, int years) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(1, years);
        return gc.getTime();
    }

    /**
     * 日期差(天数)，取时间绝对差
     *
     * @param statrDate
     * @param endDate
     * @return
     */
    public static Long datediff(Date statrDate, Date endDate) {
        long diff = statrDate.getTime() - endDate.getTime();
        long days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 日期差(天数)，取日期相对差
     *
     * @param startDate
     * @param endDate
     * @param sformat   按格式取相对差，默认yyyyMMdd
     * @return
     * @throws ParseException
     */
    public static Long datediff(Date startDate, Date endDate, String sformat) throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat(StringUtils.isEmpty(sformat) ? "yyyyMMdd" : sformat);
        Date             nst  = sdf.parse(sdf.format(startDate));
        Date             ned  = sdf.parse(sdf.format(endDate));
        long             diff = nst.getTime() - ned.getTime();
        long             days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 与当期时间比较
     *
     * @param dateTime 输入时间
     * @param sformat  时间格式
     * @return 大于0 当前时间已过输入时间；
     * 等于0 当前时间等于输入时间；
     * 小于0 当前时间未到输入时间；
     * @throws ParseException
     */
    public static Long timeDiffWithNow(String dateTime, String sformat) throws ParseException {
        Date             nowtime  = new Date();
        SimpleDateFormat sdf      = new SimpleDateFormat(sformat);
        Date             datetime = sdf.parse(dateTime);
        return nowtime.getTime() - datetime.getTime();
    }


    /**
     * 与当期时间比较
     *
     * @param dateTime 输入时间
     * @return 大于0 输入时间 未到 当前时间；
     * 等于0 当前时间等于输入时间；
     * 小于0 当前时间 未到 输入时间；
     * @throws ParseException
     */
    public static Long timeDiffWithNow(Date dateTime) throws ParseException {
        Date nowtime = new Date();
        return nowtime.getTime() - dateTime.getTime();
    }

    /**
     * madp:秒转换为格式化的时间字符串
     *
     * @param sc
     * @param format
     * @return
     */
    public static String secondeToStr(String sc, String format) {
        long seconds = Long.parseLong(sc);
        if (seconds == 0) {
            return "";
        }
        long             mills = seconds * 1000;
        Date             date  = new Date(mills);
        SimpleDateFormat sdf   = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    public static void main(String[] args) throws ParseException {

        // Calendar calendar = Calendar.getInstance();
        // calendar.add(Calendar.MINUTE, -1);
        // calendar.set(Calendar.SECOND, 0);
        // calendar.set(Calendar.MILLISECOND, 0);
        // System.out.println(sdfTime.format(calendar.getTime()));
        //
        // calendar.add(Calendar.MINUTE, 1);
        // System.out.println(sdfTime.format(calendar.getTime()));
        //
        // Calendar calendar1 = Calendar.getInstance();
        // calendar1.set(Calendar.HOUR_OF_DAY, 0);
        // calendar1.set(Calendar.MINUTE, 0);
        // calendar1.set(Calendar.SECOND, 0);
        // calendar1.set(Calendar.MILLISECOND, 0);
        // System.out.println(calendar1.getTime());

        // threadlocal
       /* Callable<Date> task = new Callable<Date>() {
            public Date call() throws Exception {
                dateFormat.set(new SimpleDateFormat("yyyyMMdd"));
                return dateFormat.get().parse("20101022");
            }
        };

        //pool with 5 threads
        ExecutorService    exec    = Executors.newFixedThreadPool(5);
        List<Future<Date>> results = new ArrayList<Future<Date>>();

        //perform 10 date conversions
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(task));
        }
        exec.shutdown();

        //look at the results
        for (Future<Date> result : results) {
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }*/
	System.out.println(DateUtil.format2str("yyyyMM"));
    }


}
