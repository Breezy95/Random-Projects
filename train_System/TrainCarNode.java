package train_System;


public class TrainCarNode {
private TrainCarNode prev;
private TrainCarNode next;
private TrainCar car;
private int placement;


public TrainCarNode() {
	
}

public TrainCarNode(TrainCar car) {
this.car = car;	
}


public String toString() {
	return (placement+1) + car.toString();
}

public int getPlacement() {
	return placement;
}

public void setPlacement(int placement) {
	this.placement = placement;
}

public TrainCarNode getPrev() {
	return prev;
}

public void setPrev(TrainCarNode prev) {
	this.prev = prev;
}

public TrainCarNode getNext() {
	return next;
}

public void setNext(TrainCarNode next) {
	this.next = next;
}

public TrainCar getCar() {
	return car;
}

public void setCar(TrainCar car) {
	this.car = car;
}
}
