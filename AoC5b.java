import java.util.*;
import java.math.*;
import java.security.*;

class AoC5b {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder(8);
    for (int i = 0; i < 8; i++) {
      result.append("-");
    }
    MessageDigest md = MessageDigest.getInstance("MD5");

    String base = "reyedfim";
    long count = 1L;
    for (int i = 0; i < 8; i++) {
      for (; ; count++) {
        byte[]  bytes = (base + count).getBytes("UTF-8");
        byte[] dg = md.digest(bytes);
        BigInteger bi = new BigInteger(1, dg);
        String s = bi.toString(16);
        if (s.length() == 27) {
          int idx = Integer.parseInt(s.substring(0,1), 16);
          if (idx < 8 && result.charAt(idx) == '-') {
            char c = s.toCharArray()[1];
            result.setCharAt(idx, c);
            count++; break;
          }
        } else if (s.length() == 26 && result.charAt(0) == '-') {
          char c = s.toCharArray()[0];
          result.setCharAt(0, c);
          count++; break;
        } else if (s.length() < 26 && result.charAt(0) == '-')  {
          result.setCharAt(0, '0');
          count++; break;
        }
      }
    }
    System.out.println("Password: " + result.toString());
  }

}
