import java.util.*;

class AoC20b {
  public static void main(String[] args) {
    List<List<Long>> ranges = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split("-");
      long a = Long.parseLong(parts[0]);
      long b = Long.parseLong(parts[1]);

      List<Long> range = new ArrayList<>();
      range.add(a);
      range.add(b);

      ranges.add(range);
    }

    ranges.sort((a,b) -> {
      if (a.get(0) < b.get(0)) {
        return -1;
      } else if (a.get(0) > b.get(0)) {
        return 1;
      }
      return Long.signum(a.get(1) - b.get(1));
    });

    System.out.println(ranges);
    
    long blocked = 0;
    long a = Long.MAX_VALUE; // move a along to the min of the blocked range
    long b = Long.MIN_VALUE; // move b along to 
    for (List<Long> r : ranges) {
      long lb = r.get(0);
      long ub = r.get(1);
      boolean overlaps = (lb >= a && lb <= b);
      if (overlaps || a == Long.MAX_VALUE) {
        if (lb < a) {
          a = lb;
        } 
        if (ub > b) {
          b = ub;
        }
      } else {
        long n = (1 + (b-a));
        System.out.println("1. Adding " + n + " range: " + a + "-" + b);
        blocked += n;
        a = lb;
        b = ub;
      }
    }
    long total = 4294967296L;

    long n = (1 + (b-a));
    System.out.println("2. Adding " + n + " range: " + a + "-" + b);
    blocked += n;
    System.out.println("Blocked: " + blocked);
    System.out.println("Allowed: " + (total - blocked));
  }
}

//4294967177
//3649329161
//118
