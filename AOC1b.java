
import java.util.*;

class AOC1b {
 
  final static class Point {
    final int x, y;
    Point(int x, int y){
      this.x = x; this.y = y;
    }
    public int hashCode() {
      return Integer.hashCode(x) + (Integer.hashCode(y) << 16);
    }
    public boolean equals(Object o) {
      if (!(o instanceof Point)) return false;
      Point p = (Point)o;
      if (p.x == x && p.y == y) {
        return true;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    System.out.println("aoc1a");
    int x = 0,  y = 0;
    int heading = 0;
    Set<Point> seen = new HashSet<>();
    seen.add(new Point(x,y));
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
      int xdiff = 0, ydiff = 0;
      switch (heading) {
        case 0: ydiff = 1; break;
        case 1: xdiff = 1; break;
        case 2: ydiff = -1; break;
        case 3: xdiff = -1; break;
      }
      for (int i = 0; i < n; i++) {
        x += xdiff; 
        y += ydiff;
        if (!seen.add(new Point(x,y))) {
          System.out.println("Distance is: " + (Math.abs(x) + Math.abs(y)));
          System.exit(0);
        }
      }
    }
  }
}
