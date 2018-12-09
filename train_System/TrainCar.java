package train_System;

public class TrainCar {
private double length; //in meters
private double weight; //in tons
private ProductLoad contents = new ProductLoad();
public TrainCar() {
	
}
public TrainCar(double length, double weight) {
this.length = length;
this.weight = weight;	
}
public double getLength() {
	return length;
}
public double getWeight() {
	return weight;
}
public ProductLoad getContents() {
	return contents;
}
public void setContents(ProductLoad contents) {
	this.contents = contents;
}

public boolean isEmpty() {
	if(contents == null) {
	System.out.println("jhhj");
		return true;
	}
	else
		return false;
}

public String toString() {
	return String.format("%,10.1f%,12.1f%9s%10s%,10.1f%,14.2f%9b",length,weight,"|",contents.getName(),contents.getWeight(),contents.getValue(),contents.isDangerous());
}



}


