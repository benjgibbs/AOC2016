import java.util.*;

class AoC13b {
  
  int favNum = 1352;
  
  int[][] mins = new int[100][100]; 
  Set<Node> visited = new HashSet<Node>();
  
  void run() {
    initMins();
    mins[1][1] = 0;
    //print(15);

    final int X = 50, Y = 50;
    
    Queue<Node> q = new LinkedList<Node>();
    q.add(new Node(1,1));
    while (!q.isEmpty()) {
      Node cur = q.poll();
      int d = mins[cur.y][cur.x];
      for (Node nb : getNbs(cur)) {
        q.add(nb);
        if(mins[nb.y][nb.x] > d+1) {
          mins[nb.y][nb.x] = d+1;
        }
      }
      visited.add(cur);
      if (cur.x == X && cur.y == Y) {
        System.out.println("Min distance: " + mins[cur.y][cur.x]);
        break;
      }
    }
    //print(15);
    int count = 0;
    for (int i = 0; i < mins.length; i++) {
      for (int j = 0; j < mins[i].length; j++) {
        if(mins[i][j] <= 50) count++;
      }
    }
    System.out.println("Can reach: " + count);

  }

  void checkedAdd(Set<Node> result, Node toAdd) {
    if (visited.contains(toAdd)) 
      return;
    if (isWall(toAdd.x, toAdd.y))
      return;
    result.add(toAdd);
  }

  Set<Node> getNbs(Node n) {
    Set<Node> result = new HashSet<>();
    if (n.x > 0) checkedAdd(result, new Node(n.x-1, n.y));
    if (n.y > 0) checkedAdd(result, new Node(n.x, n.y-1));
    checkedAdd(result, new Node(n.x+1, n.y));
    checkedAdd(result, new Node(n.x, n.y+1));
    return result; 
  }

  void initMins() {
    for (int i = 0; i < mins.length; i++) {
      for (int j = 0; j < mins[i].length; j++) {
        mins[i][j] = Integer.MAX_VALUE;
      }
    }
  }

  void print(int upto) {
    for (int y = 0; y < upto; y++) {
      StringBuilder row = new StringBuilder();
      for (int x = 0; x < upto; x++) {
        
        String dist = "  .";
        if (mins[y][x] != Integer.MAX_VALUE) {
          dist = String.format("%3d", mins[y][x]);
        }
        row.append(isWall(x,y) ? "  #" : dist);
      }
      System.out.println(row.toString());
    }
  }
  

  boolean isWall(int x, int y) {
    long sum = x*x + 3L*x +  2L*x*y + y + y*y + favNum;
    String binaryRep = Long.toString(sum, 2);
    int num1s = 0;
    for (char c : binaryRep.toCharArray()) {
      if (c == '1') {
        num1s++;
      }
    }
    return num1s % 2 == 1;
  } 

  public static void main(String[] args) {
    new AoC13a().run();
  }
  
  final class Node {
    final int x;
    final int y;
    Node(int x, int y) {
      this.x = x; this.y = y;
    }
    public int hashCode() {
      return x << 16 + y;
    }
    public boolean equals(Object other) {
      if (!(other instanceof Node)) {
        return false;
      }
      Node that = (Node)other;
      return this.x == that.x && this.y == that.y;
    }
  };
}
