import java.util.*;

class AoC16a {

  static String dragonCurve(int length, String s0) {
    while (s0.length() < length) {
      StringBuilder sb = new StringBuilder(s0);
      sb.append("0");
      char[] cs = s0.toCharArray();
      for (int i = cs.length - 1; i >= 0; i--) {
        if (cs[i] == '0') {
          sb.append('1');
        } else {
          sb.append('0');
        }
      }
      s0 = sb.toString();
    }
    return s0.substring(0, length); 
  }

  static String checksum(String s1) {
    while (s1.length() % 2 == 0) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s1.length()-1; i+=2) {
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

  static void run(int l, String s0) {
    String s1 = dragonCurve(l, s0);
    System.out.println("Checksum:" + checksum(s1));
  }

  public static void main(String[] args) {
    System.out.println("Given:   01100");
    run(20, "10000");
    run(272, "10111011111001111");
    run(35651584, "10111011111001111");
  }
}
