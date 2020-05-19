package com.bolly.support.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectUtils {

	public static final String EMPTY_STRING = "";

	private ObjectUtils() {

	}

	public static String toStr(Object obj) {
		return obj == null ? EMPTY_STRING : obj.toString();
	}

	public static int getValue(Integer obj) {
		return obj == null ? 0 : obj.intValue();
	}
	
	public static long getValue(Long obj) {
		return obj == null ? 0 : obj.longValue();
	}
	
	public static String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) {
			return null;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuilder buf = new StringBuilder(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	/**
	 * 
	 * 返回首字母
	 * 
	 * @param strChinese
	 * @param bUpCase
	 * @return
	 * 
	 */
	public static String getPYIndexStr(String strChinese, boolean bUpCase) {
		try {
			StringBuilder buffer = new StringBuilder();
			byte[] bytes = strChinese.getBytes("GBK");// 把中文转化成byte数组
			for (int i = 0; i < bytes.length; i++) {
				if ((bytes[i] & 255) > 128) {
					int char1 = bytes[i++] & 255;
					char1 <<= 8;// 左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
					int chart = char1 + (bytes[i] & 255);
					buffer.append(getPYIndexChar((char) chart, bUpCase));
					continue;
				}
				char c = (char) bytes[i];

				if (!Character.isJavaIdentifierPart(c)) {
					c = 'A';
					buffer.append(c);
				}
			}
			return buffer.toString();
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * 得到首字母
	 * 
	 * @param strChinese
	 * 
	 * @param bUpCase
	 * 
	 * @return
	 * 
	 */
	private static char getPYIndexChar(char strChinese, boolean bUpCase) {
		int charGBK = strChinese;
		char result;
		if (charGBK >= 45217 && charGBK <= 45252) {
			result = 'A';
		} else if (charGBK >= 45253 && charGBK <= 45760) {
			result = 'B';
		} else if (charGBK >= 45761 && charGBK <= 46317) {
			result = 'C';
		} else if (charGBK >= 46318 && charGBK <= 46825) {
			result = 'D';
		} else if (charGBK >= 46826 && charGBK <= 47009) {
			result = 'E';
		} else if (charGBK >= 47010 && charGBK <= 47296) {
			result = 'F';
		} else if (charGBK >= 47297 && charGBK <= 47613) {
			result = 'G';
		} else if (charGBK >= 47614 && charGBK <= 48118) {
			result = 'H';
		} else if (charGBK >= 48119 && charGBK <= 49061) {
			result = 'J';
		} else if (charGBK >= 49062 && charGBK <= 49323) {
			result = 'K';
		} else if (charGBK >= 49324 && charGBK <= 49895) {
			result = 'L';
		} else if (charGBK >= 49896 && charGBK <= 50370) {
			result = 'M';
		} else if (charGBK >= 50371 && charGBK <= 50613) {
			result = 'N';
		} else if (charGBK >= 50614 && charGBK <= 50621) {
			result = 'O';
		} else if (charGBK >= 50622 && charGBK <= 50905) {
			result = 'P';
		} else if (charGBK >= 50906 && charGBK <= 51386) {
			result = 'Q';
		} else if (charGBK >= 51387 && charGBK <= 51445) {
			result = 'R';
		} else if (charGBK >= 51446 && charGBK <= 52217) {
			result = 'S';
		} else if (charGBK >= 52218 && charGBK <= 52697) {
			result = 'T';
		} else if (charGBK >= 52698 && charGBK <= 52979) {
			result = 'W';
		} else if (charGBK >= 52980 && charGBK <= 53688) {
			result = 'X';
		} else if (charGBK >= 53689 && charGBK <= 54480) {
			result = 'Y';
		} else if (charGBK >= 54481 && charGBK <= 55289) {
			result = 'Z';
		} else {
			result = (char) (65 + (new Random()).nextInt(25));
		}

		if (!bUpCase) {
			result = Character.toLowerCase(result);
		}
		return result;
	}

	/**
	 * 获得电话号码的正则表达式：包括固定电话和移动电话 符合规则的号码： 1》、移动电话 86+‘-’+11位电话号码 86+11位正常的电话号码
	 * 11位正常电话号码a (+86) + 11位电话号码 (86) + 11位电话号码 2》、固定电话 区号 + ‘-’ + 固定电话 + ‘-’ +
	 * 分机号 区号 + ‘-’ + 固定电话 区号 + 固定电话
	 * 
	 * @return 电话号码的正则表达式
	 */
	public static String isPhoneRegexp() {
		String regexp = "";
		// 能满足最长匹配，但无法完成国家区域号和电话号码之间有空格的情况
		String mobilePhoneRegexp = "(?:(\\(\\+?86\\))((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|"
				+ "(?:86-?((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|"
				+ "(?:((13[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})";
		// 固定电话正则表达式
		String landlinePhoneRegexp = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([0-9][0-9]{1,7})+(\\-[0-9]{1,4})?)|"
				+ "(?:(86-?)?(0[0-9]{2,3}\\-?)?([0-9][0-9]{1,7})+(\\-[0-9]{1,4})?)";
		// 400电话正则表达式
		String freePhoneRegexp = "(?:(400{1})+([0-9]{1,3})+(\\-[0-9]{1,4})?)";
		regexp += "(?:" + freePhoneRegexp  + "|" + mobilePhoneRegexp + "|" + landlinePhoneRegexp + ")";
		return regexp;
	}

	/**
	 * 从dataStr中获取出所有的电话号码（固话和移动电话），将其放入Set
	 * 
	 * @param dataStr
	 *            待查找的字符串
	 */
	public static String getFirstPhoneNoFromStr(String dataStr) {
		// 获得固定电话和移动电话的正则表达式
		String regexp = isPhoneRegexp();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(dataStr);
		// 找与该模式匹配的输入序列的下一个子序列
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	// 判断字符串是否为数字
	public static boolean isNumeric(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ListToString
	 * 
	 * @param stringList
	 * @param separator
	 * @return
	 */
	public static String listToString(List<String> stringList, String separator) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(separator);
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}
	
	public static boolean isNullOrNullStr(String str) {
		return StringUtils.isBlank(str) || "null".equals(str.toLowerCase());
	}
	
	/**
	 * 验证身份证号是否合法
	 * @param papersNo
	 * @return
	 */
	public static boolean validePapersNo(String papersNo) {
		// 获取身份证号的正则表达式
		String regexp = isPapersNoRegexp();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(papersNo);
		return matcher.find();
	}
	
	public static String isPapersNoRegexp() {
		String regexp = "";
		// 匹配15位身份证号
		String fifteenPapersNo = "(^[1-9]\\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1|2]\\d)|(3[0-1]))\\d{3}$)";
		// 匹配18位身份证号
		String eighteenPapersNo = "(^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1|2]\\d)|(3[0-1]))((\\d{4})|\\d{3}[Xx])$)";
		regexp += "(?:" + fifteenPapersNo + "|" + eighteenPapersNo + ")";
		return regexp;
	}
}
