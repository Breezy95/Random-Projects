package train_System;


import java.util.Scanner;

public class TrainLinkedList {
private TrainCarNode head,tail,cursor;
private int totalNum = 0;
private double totalLength = 0;
private double totalValue = 0;
private double totalWeight =0;
private boolean isDangerous = false;

	public TrainLinkedList() {
		
	}
	
	public TrainCarNode getCursorData() {
		return cursor;
	}

	public void setCursorData(TrainCarNode car) {
		this.cursor = car;
	}
	
	public int size() {
		return totalNum;
	}
	
	public void cursorForward() {
		if(cursor!=  null && cursor.getCar() != tail.getCar()) {
			TrainCarNode temp = cursor;
			cursor =cursor.getNext();
			cursor.setPrev(temp);
			
			System.out.println("Cursor has moved forward");
		}
		else
			System.out.println("Cant move forward");
	}
	
	public void cursorBackward() {
		if(cursor != null && cursor != head){
			System.out.println("BEfore move back");
			TrainCarNode temp = cursor;
			cursor = cursor.getPrev();
			cursor.setNext(temp);
			System.out.println("cursor has moved back");
		}
		else
			System.out.println("cant move back");
	}
	
	public void insertAfterCursor(TrainCar newCar) {
		
		if(totalNum == 0) {
			TrainCarNode v = new TrainCarNode(newCar);
			cursor = v; 
			head = v;
			tail = v;
		}
		else if (totalNum == 1 ) {
		TrainCarNode w = new TrainCarNode(newCar);
		tail = w;
		cursor.setNext(tail);
		
		}
		
		else {
			TrainCarNode neoNode = new TrainCarNode(newCar);
			if(cursor == head) {
				neoNode.setNext(cursor.getNext());
				cursor.setNext(neoNode);
				cursorForward();
				cursor.setPrev(head);
				
			}
			else if(cursor == tail) {
				cursor.setNext(neoNode);
				cursor.setPrev(cursor);
				tail = neoNode;
				cursorForward();
			}
			else {
				neoNode.setNext(cursor.getNext());
				cursor.setNext(neoNode);
				TrainCarNode prevCursor =  cursor;
				cursor.setPrev(prevCursor);
				cursorForward();
			}
			
		}
		totalNum++;
		totalWeight+=cursor.getCar().getWeight();
		totalLength+= cursor.getCar().getLength();
		System.out.println("Car has been set");
	}
	
	public TrainCar removeCursor() {
		TrainCarNode removedNode = cursor;
		if(totalNum == 0) {
			System.out.println("No Cars to remove");
		}
		else if(totalNum == 1) {
			head = tail = cursor = null;
			totalNum--;
		}
		else if(totalNum == 2) {
			if(cursor == head) {
				cursorForward();
				head = cursor;
				cursor.setPrev(null);
			}
			else {
				cursorBackward();
				cursor.setNext(null);
				tail = cursor;
			}
			totalNum--;
		}
		else if(totalNum>2) {
			if(cursor == head) {
				cursor=cursor.getNext();
				cursor.setPrev(null);
				head = cursor;
				totalNum--;
			}
			else if(cursor == tail) {
				cursor= cursor.getPrev();
				cursor.setNext(null);
				tail = cursor;
				totalNum--;
			}
			else {
				TrainCarNode temp = cursor.getPrev();
				cursor.getPrev().setNext(cursor.getNext());
				cursor = cursor.getNext();
				cursor.setPrev(temp);
		}
		}
		totalWeight-=removedNode.getCar().getWeight();
		totalValue-= removedNode.getCar().getContents().getValue();
		totalLength-= removedNode.getCar().getLength();
		return removedNode.getCar();
		}
	
	public void removeDangerousCars() throws Exception {
		TrainCarNode temp = cursor;
		if(head == null) {
			throw  new Exception("There are no cars to remove");
		}
		cursor = head;
		while(cursor != null) {
			if(cursor.getCar().getContents().isDangerous())
				removeCursor();
			if(cursor.getNext() == null)
				break;
			cursor = cursor.getNext();
		}
		cursor = head;
		isDangerous = false;	
		}

	public boolean isDangerous() {
		return isDangerous;
	}

	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}
	
	
	public void DangerSearch() {
		if(head == null) {
			System.out.println("Train is empty");
			return;
		}
		setDangerous(false);
		TrainCarNode temp = cursor;
		cursor = head;
		do {
			if(cursor.getCar().getContents().isDangerous()) {
				setDangerous(true);
				break;
			}
			if(cursor.getNext() == null)
				break;
			cursor = cursor.getNext();
		}
		while(!cursor.getCar().getContents().isDangerous());
		cursor = temp;
		
		
	}
	public String toString() {
		TrainCarNode cursor2 = cursor;
		String info = ""; 
		
		if(head == null) {
			return "";
		}
		cursor2 = head;
		while(cursor2 != null) {
			if(cursor.getPlacement() == cursor2.getPlacement()) {
				info += String.format("%-1s%1s","-> ",cursor.toString() );
				info += "\n";
				cursor2 = cursor2.getNext();
				continue;
			}
		info += "   " + String.format("%s\n", cursor2.toString());
		cursor2 = cursor2.getNext();
		}
		return info;
	}
	public void Placement() {
		if(head ==null)
			return;
		TrainCarNode temp = cursor;
		cursor = head;
		int i = 0;
		while(cursor!= null) {
			cursor.setPlacement(i++);
			
			cursor = cursor.getNext();
		}
		cursor = temp;
	}
	
	public void findProduct (String name) {
		TrainCarNode temp = cursor;
		double sumWeight = 0;
		double sumValue = 0;
		TrainCarNode match = null;
		boolean isDangerous = false;
		if(head == null)  {
			System.out.println("Train is empty");
			return;
			}
		while(cursor != null){
			if(cursor.getCar().getContents().getName() == name) 
				match = cursor;
			
			if(cursor.getCar().getContents().isDangerous() == match.getCar().getContents().isDangerous()) {
				sumWeight += cursor.getCar().getContents().getWeight();
				sumValue += cursor.getCar().getContents().getValue();
			} 
			
			if(cursor.getNext() == null)
				break;
			cursor = cursor.getNext();
		}
		cursor = temp;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public double getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(double totalLength) {
		this.totalLength = totalLength;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
	
	public void addValue(double value) {
		this.totalValue += value;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	
	public void printDisplay() {
		System.out.printf("%s%40s\n","CAR:","LOAD:");
		System.out.printf("%5s%10s%10s%10s%10s%10s%10s%16s\n", "num","Length","Weight","|","Name","Weight","Value","Dangerous");
		System.out.println("==================================+=============================================================");
	}
	
	public void printContentDisplay() {
		System.out.printf("%8s%20s%20s%15s\n","Name","weight","value","Dangerous");
		System.out.println("=============================================================");
	}
	
	public static void main(String[] args) {
		TrainLinkedList one = new TrainLinkedList();
		one.insertAfterCursor(new TrainCar(211,3232));
		one.insertAfterCursor(new TrainCar(2114,323));
		one.getCursorData().getCar().setContents(new ProductLoad("name", 200, 3000, true));
		one.insertAfterCursor(new TrainCar(2114,543));
		one.printDisplay();
		System.out.println(one.toString());
		one.printContentDisplay();
		System.out.println(one.getCursorData().getCar().getContents().toString());
	}
	}


