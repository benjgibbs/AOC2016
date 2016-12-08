import java.util.*;

class AOC1a {
  

  public static void main(String[] args) {
    System.out.println("aoc1a");
    int x = 0,  y = 0;
    int heading = 0;
    
    Scanner scanner = new Scanner(System.in).useDelimiter(", ");
    while (scanner.hasNext()) {
      String dir = scanner.next();
      char t = dir.charAt(0);
      int n = Integer.parseInt(dir.substring(1).trim());
      if (t == 'R') {
        heading += 1;
      } else {
        heading -= 1;
      }
      if (heading == -1) heading = 3;
      if (heading == 4) heading = 0;
      switch (heading) {
        case 0: y += n; break;
        case 1: x += n; break;
        case 2: y -= n; break;
        case 3: x -= n; break;
      }
    }

    System.out.println("Distance is: " + (Math.abs(x) + Math.abs(y)));

  }
}
