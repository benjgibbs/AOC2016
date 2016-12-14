import java.math.*;
import java.security.*;
import java.util.*;


class AoC14b {
  final MessageDigest md;

  AoC14b() throws Exception {
     md = MessageDigest.getInstance("MD5");
  }
  
  //String salt = "abc"; 
  String salt = "jlmsuwbz"; 
  
  String firstN (String msg, int n) throws Exception {
    byte[] bs = msg.getBytes();
    for (int i = 0; i < msg.length() - (n-1); i++) {
      boolean ok = true;
      for(int j = i+1; j < i+n && ok; j++) {
        if (bs[i] != bs[j]) ok = false;
      }
      if (ok) return msg.substring(i, i+3);
    }
    return null;
  }

  String pad(String md5) {
    int toPad = 32 - md5.length();
    if (toPad == 0) {
      return md5;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < toPad; i++) {
        sb.append("0");
    }
    sb.append(md5);
    return sb.toString();
  }
  
  Map<Integer, String> countToMd5 = new HashMap<>();
  
  String createMd5(int count) throws Exception {
    String result = countToMd5.get(count);
    if (result == null) {
      String s = salt + count;  
      for (int i = 0; i <= 2016; i++) {
        byte[] bs = s.getBytes("UTF-8");
        byte[] dg = md.digest(bs);
        BigInteger bi = new BigInteger(1, dg);
        s = pad(bi.toString(16));
      }
      result = s;
      countToMd5.put(count, result);
    }
    return result;
  }

  void run() throws Exception {
    int count = 0;
    int keyCount = 0;
    boolean keepGoing = true;
    while (keepGoing) {
      String md5 = createMd5(count);
      String tripple = firstN(md5,3);
      if (tripple != null) {
        StringBuilder sb = new StringBuilder(tripple);
        sb.append(tripple.substring(0,2));
        String quintet = sb.toString();
        boolean foundFive = false;
        for (int i = 1; i <= 1000 && !foundFive; i++) {
          String md5b = createMd5(count + i);
          if (md5b.indexOf(quintet) != -1) {
            foundFive = true;
            keyCount++;
            
            System.out.println("Found " + keyCount + " at index " + count + 
                " tripple=" + tripple + ", quintet=" + 
                quintet + "(" + (count + i) + ")");

            if (keyCount == 64) {
              keepGoing = false;
              break;
            }
          }
        }
      }
      countToMd5.remove(count);
      count++;
    }
  }
  
  public static void main(String[] args) throws Exception {
    new AoC14b().run();
  }
}
