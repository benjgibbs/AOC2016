import java.util.*;

class AoC22a {
  final static class Node {
    final int x;
    final int y;
    final int size;
    final int used;
    final int avail;
    Node(String line){
      String[] cols = line.split("\\s+");
      String[] names = cols[0].split("-");
      x = Integer.valueOf(names[1].substring(1));
      y = Integer.valueOf(names[2].substring(1));
      size = Integer.valueOf(cols[1].replaceAll("T",""));
      used = Integer.valueOf(cols[2].replaceAll("T",""));
      avail = Integer.valueOf(cols[3].replaceAll("T",""));
      System.out.println(this);
    }
    public String toString(){
      return "(" + x + ", " + y + "): size=" + size + ", used=" + used + ", avail= " + avail;
    }
    public boolean equals(Object o){ 
      if (!(o instanceof Node)) return false;
      Node n = (Node)o;
      return n.x == x && n.y == y;
    }
  }
  
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    List<Node> nodes = new ArrayList<>();
    while (sc.hasNextLine()){
      String line = sc.nextLine();
      if (line.startsWith("/dev"))
        nodes.add(new Node(line));
    }
    List<Node> byUsed  = new ArrayList<Node>(nodes);
    byUsed.sort((a,b) -> a.used - b.used);
    
    List<Node> byAvail = new ArrayList<Node>(nodes);
    byAvail.sort((a,b) -> b.avail - a.avail);
    int pairs = 0;
    for (Node a : byUsed){
      if (a.used == 0) continue;
      for (Node b : byAvail){
        if (a.equals(b)) continue;
        if (a.used < b.avail) {
          pairs++;
        } else {
          break;
        }
      }
    }
    System.out.println("Num Pairs: " + pairs);
  }
}