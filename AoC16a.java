import java.util.*;

class AoC16a {

  static String dragonCurve(int length, String s0) {
    String result = s0;

    while (result.length() < length) {
      StringBuilder b = new StringBuilder(result);
      b.reverse();
      StringBuilder res = new StringBuilder(result);
      res.append("0");
      for (Character c : b.toString().toCharArray()) {
        if (c == '0') {
          res.append('1');
        } else {
          res.append('0');
        }
      }
      result = res.toString();
    }
    return result.substring(0, length); 
  }

  static String checksum(String s1) {
    while (s1.length() % 2 == 0) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s1.length() -1; i+=2) {
        if (s1.charAt(i) == s1.charAt(i+1)) {
          sb.append("1");
        } else {
          sb.append("0");
        }
      }
      s1 = sb.toString();
    }
    return s1;
  }

  public static void main(String[] args) {
    System.out.println("Starting...");
    //int length = 20;
    //int length = 272;
    int length = 35651584;
    //String s0 = "10000";
    String s0 = "10111011111001111";

    String s1 = dragonCurve(length, s0);
    //System.out.println("s1="+s1);
    System.out.println("CheckSum:" +  checksum(s1));
  }
}
