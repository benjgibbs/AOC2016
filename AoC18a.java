
import java.util.*;

class AoC18a {
	
	public static void main(String[] args){
		run(3, "..^^.");
		run(10, ".^^.^.^^^^");
		run(40, "......^.^^.....^^^^^^^^^...^.^..^^.^^^..^.^..^.^^^.^^^^..^^.^.^.....^^^^^..^..^^^..^^.^.^..^^..^^^..");
		run(400000, "......^.^^.....^^^^^^^^^...^.^..^^.^^^..^.^..^.^^^.^^^^..^^.^.^.....^^^^^..^..^^^..^^.^.^..^^..^^^..");
	}

	static boolean isTrap(String r, int i){
		if ( i < 0) return false;
		if (i >= r.length()) return false;
		return r.charAt(i) == '^';
	}

	static void run(int rows, String r1){
		int safeCount = r1.replaceAll("\\^","").length();
		for (int i = 1; i < rows; i++){
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < r1.length(); j++) {
				if (isTrap(r1, j-1) ^ isTrap(r1, j+1)) {
					sb.append("^");
				} else {
					sb.append(".");
					safeCount++;
				}
			}
			r1 = sb.toString();
		}
		
		System.out.println("Safe count: " + safeCount);
	}
}