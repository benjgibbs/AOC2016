import java.util.*;

class AOC2b {

  static char[][] xy = {
    {'X','X','1','X','X'},
    {'X','2','3','4','X'},
    {'5','6','7','8','9'},
    {'X','A','B','C','X'},
    {'X','X','D','X','X'}
  };

  static int[] min = {2, 1, 0, 1, 2};
  static int[] max = {2, 3, 4, 3, 2};


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    int xpos = 0, ypos = 2;
    while (scanner.hasNextLine()) {
      for (char c : scanner.nextLine().toCharArray()) { 
        switch (c) {
          case 'R':
            xpos = Math.min(max[ypos], xpos+1);
            break;
          case 'L':
            xpos = Math.max(min[ypos], xpos-1);
            break;
          case 'D':
            ypos = Math.min(max[xpos], ypos+1);
            break;
          case 'U':
            ypos = Math.max(min[xpos], ypos-1);
            break;
          default:
        }
      }
      System.out.print(xy[ypos][xpos]);
    }
    System.out.println();
  }
}
