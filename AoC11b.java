

import java.util.*;

class AoC11b {
	
	static Set<State> seen = new HashSet<>();
	
	final static class State {
		List<Set<String>> floors = new ArrayList<>();
		int elevator;
		State parent;
		State() {
			for (int i = 0; i < 4; i++){
				floors.add(new HashSet<>());
			}
		}
		State(State that) {
			for (int i = 0; i < 4; i++){
				floors.add(new HashSet<>(that.floors.get(i)));
			}
			this.elevator = that.elevator;
			this.parent = that;
		}
		
		boolean isDone() {
			for (int i = 0; i < 3; i++){
				if (floors.get(i).size() > 0) {
					return false;
				}
			}
			return true;
		}
		
		Set<String> getRtgs(int floor){
			Set<String> result = new HashSet<>();
			for (String i : floors.get(floor)){
				if (i.endsWith("G")) {
					result.add(i.substring(0,1));
				}
			}
			return result;
		}
		
		Set<String> getChips(int floor){
			Set<String> result = new HashSet<>();
			for (String i : floors.get(floor)){
				if (i.endsWith("M")) {
					result.add(i.substring(0,1));
				}
			}
			return result;
		}
		
		boolean isValid() {
			for (int i = 0; i < 4; i++){
				Set<String> rtgs = getRtgs(i);
				Set<String> chips = getChips(i);
				if (rtgs.size() > 0) {
					for (String chip : chips){
						if (!rtgs.contains(chip)) 
							return false;
					}
				}
			}
			return true;
		}
		
		
		void appendMoves(List<State> result, int oldFloor, int newFloor){
			for (String toMove : floors.get(oldFloor)) {
				State s = new State(this);
				s.elevator = newFloor;
				s.floors.get(oldFloor).remove(toMove);
				s.floors.get(newFloor).add(toMove);
				if (s.isValid()  && !seen.contains(s))
					result.add(s);
			}
			
			List<String> items = new ArrayList<>(floors.get(oldFloor));
			for (int i = 0; i < items.size() - 1; i++) {
				for (int j = i+1; j < items.size(); j++) {
					State s = new State(this);
					s.elevator = newFloor;
					
					String toMove1 = items.get(i);
					s.floors.get(oldFloor).remove(toMove1);
					s.floors.get(newFloor).add(toMove1);
					
					String toMove2 = items.get(j);
					s.floors.get(oldFloor).remove(toMove2);
					s.floors.get(newFloor).add(toMove2);
					
					if (s.isValid() && !seen.contains(s))
						result.add(s);
				}
			}
		}
		
		List<State> nextStates() {
			List<State> result = new ArrayList<State>();
			if (elevator > 0){
				appendMoves(result, elevator, elevator - 1);
			} 
			if (elevator < 3) {
				appendMoves(result, elevator, elevator + 1);
			}
			
			//System.out.println("Next states: " + result);
			return result;
		}
		
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Done: ").append(isDone()).append("\n");
			for (int i = 0; i < 4; i++){
				builder.append("F").append((4-i))
				.append( elevator == (3-i) ? " E " : "   ")
				.append(floors.get(3-i)).append("\n");
			}
			return builder.toString();
		}
		
		public boolean equals(Object that){
			if (!(that instanceof State)) return false;
			
			State s2 = (State)that;
			return elevator == s2.elevator && floors.equals(s2.floors);
		}
		
		public int hashCode(){
			return (elevator << 16 ) + floors.hashCode();
		}
	}
	
	
	public static void main(String[] args){
		State initial = new State();
		
		//initial.floors.get(0).add("HM");
		//initial.floors.get(0).add("LM");
		//initial.floors.get(1).add("HG");
		//initial.floors.get(2).add("LG");
		
		initial.floors.get(0).addAll(Arrays.asList("TG", "TM", "PG", "SG", "EG", "EM", "DM", "DG"));
		initial.floors.get(1).addAll(Arrays.asList("PM", "SM"));
		initial.floors.get(2).addAll(Arrays.asList("OG", "OM", "RM", "RG" ));
		
		System.out.println(initial.toString());
		System.out.println("Starting");
		// BFS
		Queue<State> queue = new LinkedList<>();
		queue.offer(initial);
		seen.add(initial);
		int iters = 1;
		while(!queue.isEmpty()){
			State next = queue.poll();
			if (next.isDone()) {
				int depth = 0;
				System.out.println();
				while(next != null){
					System.out.println(next.toString());
					next = next.parent;
					depth++;
				}
				System.out.println("Depth: " + (depth-1));
				break;
			} else {
				List<State> nextStates = next.nextStates();
				
				queue.addAll(nextStates);
				for (State s : nextStates){
					seen.add(s);
				}
				
				//System.out.println(iters++ + " ----------------------------------------------------");
				//System.out.println(queue);				
			}
		}
	}
	
}