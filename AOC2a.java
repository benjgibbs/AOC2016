
import java.util.*;

class AOC2a {

  public static int xyToNum(int x, int y) {
    return 1 + (y * 3) + x; 
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    int xpos = 1, ypos = 1;
    while (scanner.hasNextLine()) {
      for (char c : scanner.nextLine().toCharArray()) { 
        switch (c) {
          case 'R':
            xpos = Math.min(2, xpos+1);
            break;
          case 'L':
            xpos = Math.max(0, xpos-1);
            break;
          case 'D':
            ypos = Math.min(2, ypos+1);
            break;
          case 'U':
            ypos = Math.max(0, ypos-1);
            break;
          default:
        }
      }
      System.out.print(xyToNum(xpos,ypos));
    }
    System.out.println();
  }
}
