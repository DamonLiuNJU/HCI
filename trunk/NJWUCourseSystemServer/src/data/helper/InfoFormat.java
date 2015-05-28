package data.helper;

/**
 * 字符串数组与字符串之间的格式化转换
 * 针对数据库查找出数据的暂存压缩处理
 * @author cbb
 *
 */
public class InfoFormat {
	static String splitChar=",";
	static String blankReplacer="%&%";   //替换空白项
	
	public String encode(String[] str){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals("")) {
				sb.append(splitChar + blankReplacer);
			} else {
				sb.append(splitChar + str[i]);
			}
		}
		if (!sb.toString().equals("")) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	
	public String[] decode(String s){
		String[] str=s.split(splitChar);	
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(blankReplacer))
				str[i] = "";
		}
		return str;
	}	
}
