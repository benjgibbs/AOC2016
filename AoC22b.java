import java.util.*;

class AoC22b {
  final static class Node {
    final int x;
    final int y;
    final int size;
    final int used;
    final int avail;
    boolean isGoal;
    Node(String line){
      String[] cols = line.split("\\s+");
      String[] names = cols[0].split("-");
      x = Integer.valueOf(names[1].substring(1));
      y = Integer.valueOf(names[2].substring(1));
      size = Integer.valueOf(cols[1].replaceAll("T",""));
      used = Integer.valueOf(cols[2].replaceAll("T",""));
      avail = Integer.valueOf(cols[3].replaceAll("T",""));
    }
    Node(int x, int y, int size, int used, int avail, boolean isGoal) {
      assert avail >= 0;
      this.x = x; this. y = y; this.size = size; this.used = used; this.avail = avail; this.isGoal = isGoal;
    }
    public String toString(){
      return  String.format("[%s %2d", (isGoal ? "*" : " "), size) + "/" 
        + String.format("%2d",used) + "/" + String.format("%2d]",avail);
    }
    String pos(){ 
      return "(" + x + "," + y + ")";
    }
    public boolean equals(Object o){ 
      if (!(o instanceof Node)) return false;
      Node n = (Node)o;
      return n.x == x && n.y == y  && n.size == size && n.used == used && n.avail == avail && n.isGoal == isGoal;
    }
    public int hashCode() {
      return Arrays.hashCode(new int[]{x, y, size, used, avail, isGoal ? 1 : 0}); 
    }
  }
  
  static boolean isLogging  = false;
  
  static void log(String msg) {
    if (isLogging){
      System.out.println(msg);
    }
  }
  
  static void print(List<Node> nodes, int xLen, int yLen){
    if (!isLogging) return;
    for (int y = 0; y < yLen; y++){
      for (int x = 0; x < xLen; x++) {
        Node n = nodes.get(x * yLen + y);
        System.out.print( n.pos() + " " + n + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  static List<Node> move(List<Node> nodes, int spos, int dpos){
    List<Node> nn = new ArrayList<>(nodes);
    Node s = nodes.get(spos);
    Node d = nodes.get(dpos);
    assert (s.x == d.x || Math.abs(s.x - d.x) == 1) && (s.y == d.y || Math.abs(s.y - d.y) == 1);
    nn.set(dpos , new Node(d.x, d.y, d.size, d.used + s.used, d.size - (d.used + s.used), s.isGoal || d.isGoal));
    nn.set(spos, new Node(s.x, s.y, s.size, 0, s.size, false));
    return nn;
  }
  
  
  static List<List<Node>> moves(Set<List<Node>> seen, List<Node> nodes, Node n, int yLen){ 
    List<List<Node>> result = new ArrayList<>();
    int spos = (n.x*yLen) + n.y;
    assert nodes.get(spos).equals(n);
    
    int dpos = spos - 1;
    if ((spos % yLen) > 0 && n.used <= nodes.get(dpos).avail) {
      List<Node> nn = move(nodes, spos, dpos);
      if (!seen.contains(nn)) {
        log("Moving up: " + n.pos() + " to " + nodes.get(dpos).pos());
        result.add(nn);
      }
    }
    
    dpos = spos + 1;
    if ((spos % yLen) < (yLen - 2) && n.used <= nodes.get(dpos).avail) {
      List<Node> nn = move(nodes, spos, dpos);
      if (!seen.contains(nn)) {
        log("Moving down: " + n.pos() + " to " + nodes.get(dpos).pos());
        result.add(nn);
      }
    }
    
    dpos = spos - yLen;
    if (dpos >= 0 && n.used <= nodes.get(dpos).avail) {
      List<Node> nn = move(nodes, spos, dpos);
      if (!seen.contains(nn)) {
        log("Moving left: " + n.pos() + " to " + nodes.get(dpos).pos());
        result.add(nn);
      }
    }
    
    dpos = spos + yLen;
    if (dpos < nodes.size() && n.used <= nodes.get(dpos).avail) {
      List<Node> nn = move(nodes, spos, dpos);
      if (!seen.contains(nn)) {
        log("Moving right: " + n.pos() + " to " + nodes.get(dpos).pos());
        result.add(nn);
      }
    }
    return result;
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    List<Node> nodes = new ArrayList<>();
    int maxX = 0, maxY = 0;
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (line.startsWith("/dev")) {
        Node n = new Node(line);
        nodes.add(n);
        if (n.x > maxX) maxX = n.x;
        if (n.y > maxY) maxY = n.y;
      }
    }
    log("Max x=" + maxX + ", y=" + maxY);
    
    nodes.get(maxX  * (maxY + 1)).isGoal = true;
    
    //print(nodes, maxX+1, maxY+1);
 
    List<List<Node>> nxt = new ArrayList<>();
    nxt.add(nodes);
    
    Set<List<Node>> seen = new HashSet<>();
    int count = 0;
    do {
      count++;
      List<List<Node>> nxt2 = new ArrayList<>();
      for (List<Node> nl : nxt){
        print(nl,maxX+1, maxY+1);
        for (Node n : nl){
          if (n.used > 0) {
            for (List<Node> toAdd : moves(seen, nl, n, maxY + 1)) {
              print(toAdd,maxX+1, maxY+1);
              
              if (toAdd.get(0).isGoal) {
                print(toAdd,maxX+1, maxY+1);
                System.out.println("** Solved  ** in " + count + " moves");
                System.exit(0);
              }
              nxt2.add(toAdd);
            }
          }
        }
        log("---");
      }
      seen.addAll(nxt);
      nxt = nxt2;
      System.out.println("Considered: " + seen.size() +  ". Num moves: " + count + ". Next set: " + nxt.size());
    } while(!nxt.isEmpty());
    
    System.out.println("Performed " + count + " moves, explored " + seen.size() + " states." );
    
  }
}