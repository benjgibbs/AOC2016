

import java.util.*;
import java.util.stream.*;

class AoC24a {
  
  List<Character> maze = new ArrayList<>();
  Map<Integer, List<Integer>> pos = new HashMap<>();
  int X, Y;

  int maxNum;

  AoC24a() {
    try (Scanner scanner = new Scanner(System.in)) {
      while (scanner.hasNextLine()){
        String line =  scanner.nextLine();
        if (line.length() > X) X = line.length();

        maze.addAll(line.chars()
            .mapToObj(i -> (char)i)
            .collect(Collectors.toList()));
      }
    }
    Y = maze.size() / X; 
    for (int y = 0; y < Y; y++) {
      for (int x = 0; x < X; x++) {
        Character c = at(x,y);
        if (isNum(c)) {
          int n = num(c);
          pos.put(n, point(x,y)); 
          if (n > maxNum) {
            maxNum = n;
          }
        }
      }
    }
  }

  boolean isNum(Character c) {
    return (c >= '0' && c <= '9');
  }

  int num(Character c) {
    return c - '0';
  }
  
  Character at(List<Integer> point) {
    return at(point.get(0), point.get(1));
  }

  Character at(int x, int y) {
    return maze.get(y * X + x);
  }

  List<Integer> point(int x, int y) {
    List<Integer> result = new ArrayList<>();
    result.add(x);
    result.add(y);
    return result;
  }

  Set<List<Integer>> possibles(List<Integer> p, Set<List<Integer>> seen) {
    int px = p.get(0), py = p.get(1);

    Set<List<Integer>> result = new HashSet<>();
    if (px > 0) {
      List<Integer> p2 = point(px - 1, py);
      if (!seen.contains(p2) && at(p2) != '#') {
        result.add(p2);
      }
    }
    if (px < (X-1)) {
      List<Integer> p2 = point(px + 1, py);
      if (!seen.contains(p2) && at(p2) != '#') {
        result.add(p2);
      }
    }
    
    if (py > 0) {
      List<Integer> p2 = point(px, py - 1);
      if (!seen.contains(p2) && at(p2) != '#') {
        result.add(p2);
      }
    }
    if (py < (Y-1)) {
      List<Integer> p2 = point(px, py + 1);
      if (!seen.contains(p2) && at(p2) != '#') {
        result.add(p2);
      }
    }
    return result;
  }

  int spath(List<Integer> a, List<Integer> b) {
    Set<List<Integer>> seen = new HashSet<>();
    int sp = 0;
    List<List<Integer>> n1 = new ArrayList<>();
    n1.add(a);
    while (!n1.isEmpty()) {
      List<List<Integer>> n2 = new ArrayList<>();
      for (List<Integer> p : n1) {
        if (p.equals(b)) {
          return sp;
        }
        Set<List<Integer>> ss = possibles(p, seen);
        seen.addAll(ss);
        n2.addAll(ss);
      }
      n1 = n2;
      sp++;
    }
    throw new IllegalArgumentException("No path between: " + a + " and " + b);
  }

  void run() {
    System.out.println("Maze is " + X + " by " + Y);
    System.out.println("Pos: " + pos);
    
    System.out.println("Shortest path between: 0 and 1 is: " 
        + spath(pos.get(0), pos.get(1)));

    int[][] paths = new int[maxNum+1][maxNum+1];

    for (int n1 = 0; n1 <= maxNum; n1++) {
      for (int n2 = n1+1; n2 <= maxNum; n2++) {
        int sp = spath(pos.get(n1), pos.get(n2));
        paths[n1][n2] = sp;
        paths[n2][n1] = sp;
      }
    }
    System.out.println(Arrays.deepToString(paths));
    Set<Integer> toVisit = pos.keySet();
    int len = 0, cur = 0;
    toVisit.remove(0); 
    System.out.println(toVisit);
    while (!toVisit.isEmpty()) {
      int m = Integer.MAX_VALUE;
      int n = Integer.MAX_VALUE;
      for (int i = 0; i < paths[cur].length; i++) {
        if (cur != i && toVisit.contains(i)) {
          int v = paths[cur][i];
          if (v < m) {
            m = v;
            n = i;
          }
        }
      }
      System.out.println("min between " + cur + " and " + n + " is " + m);
      cur = n;
      len += m;
      toVisit.remove(cur);
      System.out.println("Len: " + len);
      System.out.println("To visit: " + toVisit);
    }
    System.out.println("Min path: " + len);

  }
  int min(int[] arr) {
    int m = Integer.MAX_VALUE;
    for (int i : arr) {
      if (i != 0 && i < m) {
        m = i;
      }
    }
    return m;
  }


  public static void main(String[] args) {
    new AoC24a().run();
  }
}
