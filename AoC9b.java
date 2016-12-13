
import java.util.*;

class AoC9b {
  
  static long length(String section) {
    int i = section.indexOf('(');
    if (i == -1) {
      return section.length();
    }
    int j = section.indexOf(')');

    String[] parts = section.substring(i+1, j).split("x");
    int n = Integer.parseInt(parts[0]); // this many chars
    int m = Integer.parseInt(parts[1]); // this many times
    
    String toRepeat = section.substring(j+1, j+n+1);
    long length =  i + m * length(toRepeat.toString()) + length(section.substring(j+n+1));
    return length;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      System.out.println("Length: " + length(scanner.nextLine()));
    }
  }
}
