import java.util.ArrayList;
import java.util.List;


public class BinaryMinHeap{

	List<Comparable> minHeap;
	
	
	public BinaryMinHeap(){
		minHeap  = new ArrayList<Comparable>();
	}
	
	public int getSize(){
		return minHeap.size();
	}

	public boolean isEmpty() {
		if(minHeap.size() == 0){
			return true;
		}
		return false;
	}

	public Comparable removeMin() {
		Comparable min = minHeap.get(0);
		if(!minHeap.isEmpty()){
			minHeap.set(0, minHeap.get(minHeap.size() - 1));
			minHeap.remove(minHeap.size() - 1);
			if(!minHeap.isEmpty()){
				minHeapify(0);
			}
		}
		return min;
	}

	public void insert(Comparable element) {
		minHeap.add(element);
		moveUp(minHeap.size() - 1);
	}
	
	private void minHeapify(int i){
		int left = 2*i;
		int right = 2*i+1;
		int min = i;
		if( left < minHeap.size() && minHeap.get(left).compareTo(minHeap.get(min)) < 0){
			min = left;
		}
		if( right < minHeap.size() && minHeap.get(right).compareTo(minHeap.get(min)) < 0){
			min = right;
		}
		if (min != i){
			switchElem(i,min);
			minHeapify(min);
		}
	}
	
	private void switchElem(int pos1,int pos2){
		Comparable temp = minHeap.get(pos1);
		minHeap.set(pos1,minHeap.get(pos2));
		minHeap.set(pos2, temp);
	}

	public void updateKey(Comparable oldElem, Comparable newElem) {
		boolean foundValue = false;
		int i = 0;
		while(i < minHeap.size() && !foundValue){
			if(minHeap.get(i).equals(oldElem)){
				foundValue = true;
				minHeap.set(i,newElem);
			} else {
				i++;
			}
		}
		if(i < minHeap.size()){
			moveUp(i);
		}
	}
	
	private void moveUp(int i){
		while(minHeap.get(i).compareTo(minHeap.get(i/2)) < 0 && i != 0){
			switchElem(i,i/2);
			moveUp(i/2);
		}	
	}

}
