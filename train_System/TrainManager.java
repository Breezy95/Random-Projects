package train_System;


import java.util.Scanner;

public class TrainManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrainLinkedList list = new TrainLinkedList();
		boolean finished = false;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("Pick an option");
			String choice = input.next();
			if(choice.contentEquals("d")) {
				try {
					list.removeDangerousCars();
					} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(choice.contentEquals("i")) {
				System.out.println("enter length");
				double length = input.nextDouble();
				System.out.println("Enter weight");
				double weight = input.nextDouble();
				list.insertAfterCursor(new TrainCar(length,weight));
				//System.out.printf("New Train car %f  meters %f tons inserted into train \n",length,weight);
			}
			if(choice.contentEquals("q")) {
				finished = true;
				System.out.println("EXITING");
				break;
			}
			if(choice.contentEquals("t")) {
				System.out.println();
				if (list.size() == 0)
					System.out.println("Cannot get summary there are no trains");
				else {
				System.out.print("Train: " + list.size() + (list.size() >1? " cars, ": " car, "));
				System.out.printf("%,.1f meters, %,.1f tons, %,.2f value, ", list.getTotalLength(), list.getTotalWeight(), list.getTotalValue());
				System.out.print(list.isDangerous() ?  " DANGEROUS" : " NOT DANGEROUS");
				System.out.println();
				}
				}
			if( choice.contentEquals("f")) {
				list.cursorForward();
			}
			if(choice.contentEquals("b")) {
				list.cursorBackward();
		}
			if(choice.contentEquals("r")) {
				list.printContentDisplay();
				System.out.println(list.removeCursor().getContents().toString());
			}
			if(choice.contentEquals("l")) {
				System.out.println("Enter produce name");
				input.nextLine();
				list.getCursorData().getCar().getContents().setName(input.nextLine());
				System.out.println("Enter weight");
				list.getCursorData().getCar().getContents().setWeight(input.nextDouble());
				System.out.println("Enter value in dollars");
				double dollar = input.nextInt();
				list.addValue(dollar);
				list.getCursorData().getCar().getContents().setValue(dollar);
				
				boolean done = false;
				while(done == false) {
					System.out.println("Is product Dangerous? (y/n)");
					String x = input.next().toLowerCase();
					if(x.length()>1 && (x.compareTo("y") != 0 || x.compareTo("n") != 0))
						System.out.println("Did not enter correct input");
					else {
						if(x.equals("y")) {
							list.getCursorData().getCar().getContents().setDangerous(true);
							list.setDangerous(true);
							
						}
						else
							list.getCursorData().getCar().getContents().setDangerous(false);
						done = true;
			}
			
		} 
	}
			list.Placement();
			list.DangerSearch();
			list.printDisplay();
			System.out.println(list.toString());
}while(!finished);
	}	
}

