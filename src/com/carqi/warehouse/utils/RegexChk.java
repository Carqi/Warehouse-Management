package com.carqi.warehouse.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 正则表达式校验类
 * @author GAO
 * @date 2013-9-11
 */
public class RegexChk {

	public static boolean startCheck(String reg, String string) {
		boolean tem = false;

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);

		tem = matcher.matches();
		return tem;
	}
    /** 
     * 检验整数,适用于正整数、负整数、0，负整数不能以-0开头,  
     * 正整数不能以0开头 
     *  
     * */  
    public boolean checkNr(String nr)  
    {  
        String reg="^(-?)[1-9]+\\d*|0";  
        return startCheck(reg,nr);  
    }  
  
    /** 
     * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数 
     * */  
    public static boolean checkCellPhone(String cellPhoneNr)  
    {  
        String reg="^[1][\\d]{10}";  
        return startCheck(reg,cellPhoneNr);  
    }  
      
    /** 
     * 检验空白符 
     * */  
    public boolean checkWhiteLine(String line)  
    {  
        String regex="(\\s|\\t|\\r)+";  
          
        return startCheck(regex,line);  
    }  
      
      
    /** 
     * 检查EMAIL地址 
     * 用户名和网站名称必须>=1位字符 
     * 地址结尾必须是以com|cn|com|cn|net|org|gov|gov.cn|edu|edu.cn结尾 
     * */  
    public static boolean checkEmailWithSuffix(String email)  
    {  
        String regex="\\w+\\@\\w+\\.(com|cn|com.cn|net|org|gov|gov.cn|edu|edu.cn)";  
          
        return startCheck(regex,email);  
    }  
      
      
    /** 
     * 检查EMAIL地址 
     * 用户名和网站名称必须>=1位字符 
     * 地址结尾必须是2位以上，如：cn,test,com,info 
     * */  
    public boolean checkEmail(String email)  
    {  
        String regex="\\w+\\@\\w+\\.\\w{2,}";  
          
        return startCheck(regex,email);  
    }  
      
    /** 
     * 检查邮政编码(中国),6位，第一位必须是非0开头，其他5位数字为0-9 
     * */  
    public boolean checkPostcode(String postCode)  
    {  
        String regex="^[1-9]\\d{5}";  
        return startCheck(regex,postCode);  
    }  
      
    /** 
     * 检验用户名 
     * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 
     * 用户名有最小长度和最大长度限制，比如用户名必须是4-20位 
     * */  
    public boolean checkUsername(String username,int min,int max)  
    {  
        String regex="[\\w\u4e00-\u9fa5]{"+min+","+max+"}(?<!_)";  
        return startCheck(regex,username);  
    }  
    /** 
     * 检验用户名 
     * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 
     * 有最小位数限制的用户名，比如：用户名最少为4位字符 
     * */  
    public boolean checkUsername(String username,int min)  
    {  
        //[\\w\u4e00-\u9fa5]{2,}?  
        String regex="[\\w\u4e00-\u9fa5]{"+min+",}(?<!_)";  
          
        return startCheck(regex,username);  
    }  
      
    /** 
     * 检验用户名 
     * 取值范围为a-z,A-Z,0-9,"_",汉字 
     * 最少一位字符，最大字符位数无限制，不能以"_"结尾 
     * */  
    public boolean checkUsername(String username)  
    {  
        String regex="[\\w\u4e00-\u9fa5]+(?<!_)";  
        return startCheck(regex,username);  
    }  
      
    /** 
     *  查看IP地址是否合法 
     * */  
    public boolean checkIP(String ipAddress)  
    {  
        String regex="(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +  
                     "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +  
                     "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +  
                     "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";  
          
        return startCheck(regex,ipAddress);  
    }  
      
    /** 
     * 验证国内电话号码 
     * 格式：010-67676767，区号长度3-4位，必须以"0"开头，号码是7-8位 
     * */  
    public static boolean checkPhoneNr(String phoneNr)  
    {  
        String regex="^[0]\\d{2,3}\\-\\d{7,8}";  
          
        return startCheck(regex,phoneNr);  
    }  
      
    /** 
     * 验证国内电话号码 
     * 格式：6767676, 号码位数必须是7-19位,头一位不能是"0" 
     * */  
    public boolean checkPhoneNrWithoutCode(String phoneNr)  
    {  
        String reg="^[1-9]\\d{6,19}";  
          
        return startCheck(reg,phoneNr);  
    }  
      
    /** 
     * 验证国内电话号码 
     * 格式：0106767676，共11位或者12位，必须是0开头 
     * */  
    public boolean checkPhoneNrWithoutLine(String phoneNr)  
    {  
        String reg="^[0]\\d{10,11}";  
          
        return startCheck(reg,phoneNr);  
    }  
      
    /** 
     * 验证国内身份证号码：15或18位，由数字组成，不能以0开头 
     * */  
    public boolean checkIdCard(String idNr)  
    {  
        String reg="^[1-9](\\d{14}|\\d{17})";  
          
        return startCheck(reg,idNr);  
    }  
      
    /** 
     * 网址验证
     * 符合类型：
     *         http://www.test.com<br> 
     *         http://163.com 
     * */  
    public boolean checkWebSite(String url)  
    {  
        //http://www.163.com  
        String reg="^(http)\\://(\\w+\\.\\w+\\.\\w+|\\w+\\.\\w+)";  
          
        return startCheck(reg,url);  
    }  
    
    /**
     * 
     *  Function:
     *  取出字符串中的数字
     *  @author Yu Liu  DateTime 2013-11-7 上午9:58:02
     *  @param input
     *  @return
     */
    public static String[] matcher(String input) {
    	Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
	    Matcher matcher = pattern.matcher(input);
	    List<String> list = new ArrayList<String>();
	    while (matcher.find()) {
	        list.add(matcher.group());
	    }
	    return list.toArray(new String[0]);
	}
    
    public static boolean isPhoneNumberVaild(String num){
      String expre = "^0?(13[0-9]|15[012356789]|18[012346789]|14[57])[0-9]{8}$";
      Pattern pattern = Pattern.compile(expre);
      Matcher matcher = pattern.matcher(num);
      return matcher.matches();
  }
    
   public static boolean containCN(String str){
	   String regEx = "[\\u4E00-\\u9FA5]";
       Pattern p = Pattern.compile(regEx);

       Matcher m = p.matcher(str);
       while (m.find()) {
           return true;
       }
       return false;
   }
}
