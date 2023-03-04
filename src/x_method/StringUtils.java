package x_method;

import org.junit.Test;

public class StringUtils {
    public static void main(String[] args) {
        System.out.println(safeEqual("aaaa", "vvvv"));
//        System.out.println(equal("aaaa", "aaaa"));
    }

/*
* 防止计时攻击，详情百度
* safeEqual： 可以防止黑客猜到密码（加密文），因为每次比较不管两个字符串从第几位开始不同的，
* 都要比较整个字符串，这样的话黑客就无法根据每次比较的时间长短从而暴力破解出密码信息
*
* */
    public static boolean safeEqual(String str1, String str2){
        if (str1.length() != str2.length())
            return false;
        int equal = 0;
        for (int i = 0; i < str1.length(); i++) {
            equal |= str1.charAt(i) ^ str2.charAt(i);
        }
        return equal == 0;
    }

    public static boolean equal(String str1, String str2){
        if (str1.length() != str2.length())
            return false;
        int equal = 0;
        for (int i = 0; i < str1.length(); i++) {
            equal |= str1.charAt(i) ^ str2.charAt(i);
            if (equal != 0)
                return false;
        }
        return true;
    }

    @Test
    public void Xor(){
        // 以后 同时为真是结果为假，一真一假时结果为真
        System.out.println('a' ^ 'a');
        System.out.println(true ^ false);
        System.out.println(false ^ false);
        System.out.println(true ^ true);
        System.out.println(0 ^ 1);
        System.out.println(1 ^ 1);
        System.out.println(0 ^ 0);
    }


    @Test
    public void or(){
        // 以后 同时为真是结果为假，一真一假时结果为真
        System.out.println(0 | 1);
        System.out.println(true | false);
    }
}
