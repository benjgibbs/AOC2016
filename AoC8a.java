import java.util.*;


class AoC8a {
  static void print(boolean[][] disp) {
    System.out.println();
    for (int y = 0; y < disp.length; y++) {
      for (int x = 0; x < disp[y].length; x++) {
        System.out.print(disp[y][x] ? "#" : " ");
      }
      System.out.println();
    }
  }

  static int countLit(boolean[][] disp) {
    int cnt = 0;
    for (int y = 0; y < disp.length; y++) {
      for (int x = 0; x < disp[y].length; x++) {
        if (disp[y][x]) cnt++;
      }
    }
    return cnt;
  }

  public static void main(String[] args) {
    boolean[][] display = new boolean[6][50];
    //boolean[][] display = new boolean[3][7];
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.startsWith("rect ")) {
        line = line.substring("rect ".length()); 
        String[] dims = line.split("x");
        int X = Integer.parseInt(dims[0]);
        int Y = Integer.parseInt(dims[1]);
        for (int x = 0; x < X; x++) {
          for (int y = 0; y < Y; y++) {
            display[y][x] = true;
          }
        }
      } else if (line.startsWith("rotate")) {
        line = line.substring("rotate ".length());
        if (line.startsWith("row y=")) {
          line = line.substring("row y=".length());
          String[] parts = line.split(" by ");
          int y = Integer.parseInt(parts[0]);
          int by = Integer.parseInt(parts[1]);
          boolean[] buf = new boolean[display[y].length];
          for (int i = 0; i < display[y].length; i++) {
            int j = (i + by) % display[y].length;
            buf[j] = display[y][i];
          }
          display[y] = buf;

        } else if (line.startsWith("column x=")) {
          line = line.substring("column x=".length());
          String[] parts = line.split(" by ");
          int x = Integer.parseInt(parts[0]);
          int by = Integer.parseInt(parts[1]);
          boolean[] buf = new boolean[display.length];
          for (int i = 0; i < display.length; i++) {
            int j = (i + by) % display.length;
            buf[j] = display[i][x];
          }
          for (int i = 0; i < buf.length; i++) {
            display[i][x] = buf[i];
          }
        } else {
          System.err.println("Unknown command: " + line);
        }
      } else {
        System.err.println("Unknown command: " + line);
      }
    }
    print(display);
    System.out.println("\nNum lit: " + countLit(display));
  }
}
