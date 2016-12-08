import java.util.*;

class AoC7a {

  static boolean isValid(String s) {
    char[] cs = s.toCharArray();

    int braceCount = 0;
    boolean hasAbba = false;
    boolean hasAbbaInHL = false;
    for (int i = 0; i < cs.length-3; i++) {

      System.out.println("Checking: " + s.substring(i,i+4));
      if (cs[i] == '[') {
        braceCount++;
      } else if (cs[i] == ']') {
        braceCount--;
        braceCount = Math.max(0, braceCount);
      } else {
        if (cs[i] == cs[i+3] && cs[i+1] == cs[i+2] && cs[i] != cs[i+1]) {
          System.out.println("HasABBA: " + s.substring(i,i+4));
          if (braceCount == 0) {
            hasAbba = true;
          } else {
            hasAbbaInHL = true;
          }
        }
      }
    }
    return hasAbba && !hasAbbaInHL;
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
