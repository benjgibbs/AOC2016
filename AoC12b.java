import java.util.*;

class AoC12b {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("#include <stdio.h>");
    System.out.println("int main(int argc, char** argv) {");
    System.out.println("int a = 0, b = 1, c = 0, d= 0;");

    int lineNum = 0;
    while (scanner.hasNextLine()) {
      System.out.print("L"+lineNum+": "); 
      String line = scanner.nextLine();
      String[] parts = line.split(" ");
      switch (parts[0]) {
        case "cpy":
          System.out.println(parts[2] + "=" + parts[1] + ";");
          break;
        case "inc":
          System.out.println(parts[1] + "++;");
          break;
        case "dec":
          System.out.println(parts[1] + "--;");
          break;
        case "jnz":
          System.out.println("if (" + parts[1] + "!= 0) goto L" + 
              (lineNum + Integer.parseInt(parts[2])+ ";"));
          break;
      }
      lineNum++;
    }
    System.out.println("L" + lineNum + ": printf(\"a=%d\\n\",a);");
    System.out.println("}");
  }
}
