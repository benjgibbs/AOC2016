import java.util.*;
import java.math.*;
import java.security.*;

class AoC5a {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();
    MessageDigest md = MessageDigest.getInstance("MD5");

    String base = "reyedfim";
    long count = 1L; 
    for (int i = 0; i < 8; i++) {
      for (; ; count++) {
        byte[]  bytes = (base + count).getBytes("UTF-8");
        byte[] dg = md.digest(bytes);
        BigInteger bi = new BigInteger(1, dg);
        String s = bi.toString(16);
        if (s.length() < 28) {
          if (s.length() == 27) {
            result.append(s.substring(0,1));
          } else {
            result.append("0");
          }
          count++;
          break;
        }
      }
    }
    System.out.println("Password: " + result.toString());
  }

}
