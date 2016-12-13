import java.util.*;


class AoC9a {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      StringBuilder out = new StringBuilder();
      int i = 0;
      while (i < line.length()) {
        if (line.charAt(i) == '(') {
          int j = i+4;
          while (line.charAt(j) != ')')
            j++;
          
          String[] parts = line.substring(i+1, j).split("x");
          int n = Integer.parseInt(parts[0]); // this many chars
          int m = Integer.parseInt(parts[1]); // this many times
          String toRepeat = line.substring(j+1, j+n+1);

          for (int k = 0; k < m; k++) {
            out.append(toRepeat);
          }
          i = j+n+1;
        } else {
          out.append(line.charAt(i));
          i++;
        } 
      }
      System.out.println("Length: " + out.length());
    }
  }
}
