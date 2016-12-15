import java.util.*;

class AoC15a {

    

    void run() {
        System.out.println("Starting...");
        int[] numPos = new int[10];
        int[] curPos = new int[10];
        
        int numDiscs = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                numPos[numDiscs] = Integer.parseInt(parts[3]);
                curPos[numDiscs] = Integer.parseInt(parts[11].replace(".",""));
				numDiscs++;
            }
        }
		
		System.out.println("NumPos: " + Arrays.toString(numPos));
		System.out.println("CurPos: " + Arrays.toString(curPos));
		int time = 0;
		while (true) {
			int startTime = time;
			int runTime = time;
			boolean ok = true;
			for (int i = 0; i < numDiscs && ok; i++){
				runTime++;
				if ((curPos[i] + runTime) % numPos[i] != 0){
					ok = false;
				}
			}
			if (ok){
				System.out.println("Start Time: " + startTime);
				break;
			}
			time++;
		}

    }

    public static void main(String[] args) {
        new AoC15a().run();
    }
}