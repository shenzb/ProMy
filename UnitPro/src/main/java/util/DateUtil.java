package util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA. User: liuzz Date: 2010-3-31 Time: 11:13:16
 */
public final class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static final int INSTANCE_COUNT = 20;

	public static final SimpleDateFormat[] formatYYYYMMDD = createDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat[] formatLong = createDateFormat("yyyy-MM-dd HH:mm:MerchantResult");

	private static final SimpleDateFormat[] insureFormat = createDateFormat("yyyy-MM-dd|HH:mm:MerchantResult");
	private static final SimpleDateFormat[] formatShort = createDateFormat("yyyyMMdd");
	private static final SimpleDateFormat[] formatTime = createDateFormat("HH:mm:MerchantResult");
	private static final SimpleDateFormat[] formatYYYYMMDDSSS = createDateFormat("yyMMddHHmmssSSS");
	private static final SimpleDateFormat[] formatYYYYMMDDHHMMSS = createDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat[] formatYYYYMMDDHHMMSSS = createDateFormat("yyyyMMddHHmmsss");
	private static final SimpleDateFormat[] formatYYYYMMDDHH = createDateFormat("yyyyMMddHHmmss");
	

	private static final SimpleDateFormat[] formatMMDD = createDateFormat("MMdd");
	private static final SimpleDateFormat[] formatYYYYMMDDHHMM = createDateFormat("yyyy-MM-dd HH:mm");

	private static final String parseYYYYMMDD = "yyyy-MM-dd HH:mm:MerchantResult";

	private static int currentIndex = 0; // 不需要考虑多线程问题，节省性能开销。

	private DateUtil() {
	}

	private static SimpleDateFormat[] createDateFormat(String fmt) {
		SimpleDateFormat[] ret = new SimpleDateFormat[INSTANCE_COUNT];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new SimpleDateFormat(fmt);
			ret[i].setLenient(false);
		}
		return ret;
	}

	private final static int getIndex() {
		int n = currentIndex++;
		if (n < 0) { // 处理溢出
			currentIndex = 0;
			n = 0;
		}
		return n % INSTANCE_COUNT;
	}

	public static String formatYYYYMMDDHHMM() {
		SimpleDateFormat fmt = formatYYYYMMDDHHMM[getIndex()];
		synchronized (fmt) {
			return fmt.format(new Date());
		}
	}

	public static Date paseYYYYMMDDHHMM(String strDate) {
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;

		Date date = null;
		try {
			SimpleDateFormat fmt = formatYYYYMMDDHHMM[getIndex()];
			synchronized (fmt) {
				date = fmt.parse(strDate);
			}
		} catch (Exception e) {
			logger.error("formatYYYYMMDDHHMM error: date=" + strDate, e);
			return null;
		}

		return date;
	}

	public static String formatMMDD() {
		SimpleDateFormat fmt = formatMMDD[getIndex()];
		synchronized (fmt) {
			return fmt.format(new Date());
		}
	}
	
	public static String formatYYYYMMDDHH(){
		SimpleDateFormat fmt = formatYYYYMMDDHH[getIndex()];
		Date d = new Date();
		d.setMinutes(0);
		d.setSeconds(0);
		synchronized (fmt) {
			return fmt.format(d);
		}
	}

	public static String formatCurTime() {
		SimpleDateFormat fmt = formatYYYYMMDDSSS[getIndex()];
		synchronized (fmt) {
			return fmt.format(new Date());
		}
	}

	public static String formatCurTimeLong() {
		SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
		synchronized (fmt) {
			return fmt.format(new Date());
		}
	}

	public static String formatYYYYMMDDHHMMSS(Date date) {
		SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
		synchronized (fmt) {
			return fmt.format(date);
		}
	}

	public static String formatYYYYMMDDHHMMSSS(Date date) {
		SimpleDateFormat fmt = formatYYYYMMDDHHMMSSS[getIndex()];
		synchronized (fmt) {
			return fmt.format(date);
		}
	}

	public static Date convertYYYYMMDDHHMMSS(String strDate) {
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;

		Date date = null;
		try {
			SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
			synchronized (fmt) {
				date = fmt.parse(strDate);
			}
		} catch (Exception e) {
			logger.error("convertYYYYMMDDHHMMSS error: date=" + strDate, e);
			return null;
		}

		return date;
	}

	public static Date convertYYYYMMDD(String strDate) {
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;

		Date date = null;
		try {
			SimpleDateFormat fmt = formatYYYYMMDD[getIndex()];
			synchronized (fmt) {
				date = fmt.parse(strDate);
			}
		} catch (Exception e) {
			logger.error("convertYYYYMMDD error: date=" + strDate, e);
			return null;
		}

		return date;
	}
	
	public static Date convertYYYYMMDDHH(String strDate) {
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;

		Date date = null;
		try {
			SimpleDateFormat fmt = formatYYYYMMDDHH[getIndex()];
			synchronized (fmt) {
				date = fmt.parse(strDate);
			}
		} catch (Exception e) {
			logger.error("convertYYYYMMDDHH error: date=" + strDate, e);
			return null;
		}

		return date;
	}

	public static Date convertShort(String strDate) {
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;

		Date date = null;
		try {
			SimpleDateFormat fmt = formatShort[getIndex()];
			synchronized (fmt) {
				date = fmt.parse(strDate);
			}
		} catch (Exception e) {
			logger.error("convertShort error: date=" + strDate, e);
			return null;
		}

		return date;
	}

	public static Date convertLong(String strDate) {
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;

		Date date = null;
		try {
			SimpleDateFormat fmt = formatLong[getIndex()];
			synchronized (fmt) {
				date = fmt.parse(strDate);
			}
		} catch (Exception e) {
			logger.error("convertLong error: date=" + strDate, e);
			return null;
		}

		return date;
	}

	public static String formatYYYYMMDD(Date date) {
		SimpleDateFormat fmt = formatYYYYMMDD[getIndex()];
		synchronized (fmt) {
			return fmt.format(date);
		}
	}

	public static String insureFormat(Date date) {
		SimpleDateFormat fmt = insureFormat[getIndex()];
		synchronized (fmt) {
			return fmt.format(date).replaceAll("\\|", "T");
		}
	}

	public static String formatTime(Date date) {
		SimpleDateFormat fmt = formatTime[getIndex()];
		synchronized (fmt) {
			return fmt.format(date);
		}
	}

	public static String formatShort(Date date) {
		SimpleDateFormat fmt = formatShort[getIndex()];
		synchronized (fmt) {
			return fmt.format(date);
		}
	}

	public static String formatLong(Date date) {
		SimpleDateFormat fmt = formatLong[getIndex()];
		synchronized (fmt) {
			return fmt.format(date);
		}
	}

	public static String formatDDMMMYY(Date date) {
		return String.format(Locale.US, "%1$td%1$tb%1$ty", date);
	}

	public static String convertYYYYMMDDToDDMMMYY(String date) {
		Date d = convertYYYYMMDD(date);
		return null == d ? "" : formatDDMMMYY(d);
	}

	public static String formatTodyDate(String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date());
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Date parse(String date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			logger.error("pase error: date=" + date + ", pattern=" + pattern, e);
			return null;
		}
	}

	public static String formatTime(Time time) {
		if (null == time) {
			return "";
		} else {
			return time.toString().substring(0, 4);
		}
	}

	/**
	 * 取得两个日期的时间间隔,相差的天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getDayBetween(Date d1, Date d2) {
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		if (d1.before(d2)) {
			before.setTime(d1);
			after.setTime(d2);
		} else {
			before.setTime(d2);
			after.setTime(d1);
		}
		int days = 0;

		int startDay = before.get(Calendar.DAY_OF_YEAR);
		int endDay = after.get(Calendar.DAY_OF_YEAR);

		int startYear = before.get(Calendar.YEAR);
		int endYear = after.get(Calendar.YEAR);
		before.clear();
		before.set(startYear, 0, 1);

		while (startYear != endYear) {
			before.set(startYear++, Calendar.DECEMBER, 31);
			days += before.get(Calendar.DAY_OF_YEAR);
		}
		return days + endDay - startDay;
	}

	/**
	 * 取得两个日期的时间间隔,相差的天数
	 *
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getHourBetween(Date start, Date end) {
		String str="";
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		if (end.after(start)) {
			before.setTime(start);
			after.setTime(end);
			long aftertime=after.getTimeInMillis();
			long befortime=before.getTimeInMillis();
			long betweentime=aftertime-befortime;
			long day=betweentime/(24*60*60*1000);
			long hour=(betweentime/(60*60*1000)-day*24);
			long min=((betweentime/(60*1000))-day*24*60-hour*60);
			long s=(betweentime/1000-day*24*60*60-hour*60*60-min*60);
			str=day+"天"+hour+"小时"+min+"分"+s+"秒";
		}else{
			str="0";
		}
		return str;
	}
	/**
	 * 取得两个日期的时间间隔,相差的天数,后面减前面，可能为负
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getDayBetweenD(Date d1, Date d2) {
		if (d1.before(d2)) {
			return getDayBetween(d1, d2);
		} else {
			return -getDayBetween(d1, d2);
		}
	}

	/**
	 * 取得时间间隔,相差的时间，XX小时XX分钟
	 * 
	 * @return 不会超过24小时
	 */
	public static String getTimeBetween(String time1, String time2) {
		String[] t1 = time1.split(":");
		String[] t2 = time2.split(":");

		int minute = Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]);
		int hour = Integer.parseInt(t2[0]) - Integer.parseInt(t1[0]);

		if (minute < 0) {
			minute += 60;
			hour -= 1;
		}
		if (hour < 0) {
			hour += 24;
		}

		if (hour == 0) {
			return minute + "分钟";
		}
		if (minute == 0) {
			return hour + "小时";
		}
		return hour + "小时" + minute + "分钟";
	}

	public static Date addDay(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}

	public static Date addMinute(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.MINUTE, amount);
		return cal.getTime();
	}

	public static Date addYear(Date myDate, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.YEAR, amount);
		return cal.getTime();
	}

	public static String dateFormatStr(String dateByyyyyMMddStr) {
		Preconditions.checkArgument(dateByyyyyMMddStr != null && dateByyyyyMMddStr.length() == 8, dateByyyyyMMddStr + " is error format");

		String year = dateByyyyyMMddStr.substring(0, 4);
		String month = dateByyyyyMMddStr.substring(4, 6);
		String day = dateByyyyyMMddStr.substring(6, 8);
		StringBuilder date = new StringBuilder();
		date.append(year).append("-").append(month).append("-").append(day);
		return String.valueOf(date);
	}

	public static String timeFormat2HHmmss(String timeHHmm) {
		Preconditions.checkArgument(timeHHmm != null && timeHHmm.length() == 5, timeHHmm + " is error format");

		StringBuilder time = new StringBuilder();
		time.append(timeHHmm.substring(0, 2)).append(timeHHmm.substring(3, 5)).append("00");
		return String.valueOf(time);
	}

	public static String timeFormat2HHmm(String timeHHmmss) {
		Preconditions.checkArgument(timeHHmmss != null && timeHHmmss.length() == 6, timeHHmmss + " is error format");

		StringBuilder time = new StringBuilder();
		time.append(timeHHmmss.substring(0, 2)).append(timeHHmmss.substring(2, 4));
		return String.valueOf(time);
	}

	public static String timeFormat2HHmm(Time time) {
		return time.toString().substring(0, 5);
	}

	public static String dateFormat2YYYYMMDD(String inputDate) {
		Preconditions.checkArgument(inputDate != null && inputDate.length() == 10, inputDate + " is error format");
		StringBuilder date = new StringBuilder();
		date.append(inputDate.substring(0, 4)).append(inputDate.substring(5, 7)).append(inputDate.substring(8, 10));
		return String.valueOf(date);
	}

	public static String long2DateStr(long msec, String pattern) {
		Date date = new Date();
		date.setTime(msec);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static boolean beforeCurrentTime(String param) {
		Calendar nowcal = Calendar.getInstance();
		Calendar paramcal = Calendar.getInstance();
		nowcal.setTime(new Date());
		paramcal.setTime(parse(param, parseYYYYMMDD));
		return nowcal.after(paramcal);
	}

	/**
	 * 返回不包含时分秒的日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date trimDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		Date trimDate = null;
		try {
			trimDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			logger.error("pase error: date=" + date + ", pattern=yyyy-MM-dd", e);
		}
		return trimDate;
	}

	public static Date addHour(Date date, int h) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, h);
		return cal.getTime();
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.format(date);
		} catch (Exception e) {
			logger.error("format error: date=" + date + ", pattern=" + pattern, e);
			return null;
		}
	}

	public static String getWeekOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (Calendar.MONDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return "星期一";
		}
		if (Calendar.TUESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return "星期二";
		}
		if (Calendar.WEDNESDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return "星期三";
		}
		if (Calendar.THURSDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return "星期四";
		}
		if (Calendar.FRIDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return "星期五";
		}
		if (Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return "星期六";
		}
		if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
			return "星期日";
		}
		return "";
	}

    //获取当前时间距离明天凌晨还有多少毫秒
    public static long getRemainTime(){
        Date afterDate = DateUtils.addDays(new Date(), 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(sdf.format(afterDate));
            return date.getTime() - new Date().getTime();
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    public static int getRemainSeconds(long dayTime) {
        int result = 0;
        try {
            long time = getRemainTime() + dayTime;
            result = (int) (time/1000);
        } catch (Exception e) {
            logger.error("获取剩余时间异常", e);
        }
        return result;
    }


	public static void main(String[] args) {
		String date="2016-04-30 12:30:23";
		Date endDate=convertLong(date);
		System.out.println(getHourBetween(new Date(),endDate));
    }
}