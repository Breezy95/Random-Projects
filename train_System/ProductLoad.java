package train_System;


public class ProductLoad {
String name = "Empty";
double weight= 0;
double value = 0;
boolean dangerous = false;


public ProductLoad() {
	
}

public ProductLoad(String name, double weight, double value, boolean dangerous) {
this.name = name;	
this.weight = weight;
this.value = value;
this.dangerous = dangerous;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public double getWeight() {
	return weight;
}


public void setWeight(double weight) {
	this.weight = weight;
}


public double getValue() {
	return value;
}


public void setValue(double value) {
	this.value = value;
}


public boolean isDangerous() {
	return dangerous;
}


public void setDangerous(boolean dangerous) {
	this.dangerous = dangerous;
}

public String toString() {
	return String.format("%8s%,20.1f%,20.2f%10s",name,weight,value, (dangerous ? "yes" : "no"));
}


}


