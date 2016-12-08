import java.util.*;

class AoC7b {

  static boolean isValid(String s) {
    char[] cs = s.toCharArray();
    int braceCount = 0;
    Set<String> s1 = new HashSet<>();
    Set<String> s2 = new HashSet<>();
    for (int i = 0; i < cs.length-2; i++) {
      //System.out.println("Checking: " + s.substring(i,i+4));
      if (cs[i] == '[') {
        braceCount++;
      } else if (cs[i] == ']') {
        braceCount--;
        braceCount = Math.max(0, braceCount);
      } else {
        if (cs[i] == cs[i+2]) {
          String sn =  s.substring(i,i+3);
          if (braceCount == 0) {
            s1.add(sn);
          } else {
            s2.add(sn);
          }
        }
      }
    }
    for (String ss : s1) {
      StringBuilder sb = new StringBuilder();
      String x = sb.append(ss.charAt(1)).append(ss.charAt(0))
                      .append(ss.charAt(1)).toString();
      if (s2.contains(x)) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int validCount = 0;
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      boolean isValid = isValid(line);
      System.out.println(line + ": " + isValid);
      if (isValid) validCount++;
    }
    System.out.println("There are " + validCount + " valids");
  }
}
