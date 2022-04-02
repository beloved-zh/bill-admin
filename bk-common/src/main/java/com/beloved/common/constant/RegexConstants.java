package com.beloved.common.constant;

/**
 * 正则 常量
 *
 * @author beloved
 */
public class RegexConstants {

    /**
     * 邮箱
     */
    public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 域名
     */
    public static final String DOMAIN_NAME_REGEX = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";

    /**
     * URL
     */
    public static final String URL_REGEX = "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";

    /**
     * 电话号码
     */
    public static final String PHONE_NUMBER_REGEX = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";

    /**
     * 身份证号码
     */
    public static final String ID_NUMBER_REGEX = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";


    /**
     * IPv4
     */
    public static final String IPV4_REGEX = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";

    /**
     * 密码
     *      以字母开头，长度在6~18之间，只能包含字母、数字和下划线
     */
    public static final String PASSWORD_REGEX = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 强密码
     *      必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间
     */
    public static final String POWERFUL_PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
}
