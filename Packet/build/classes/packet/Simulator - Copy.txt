/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
4 .5 10 500 1500 25
 */
package packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabrice Benoit
 */
public class Simulator {
    /*
    Router dispatcher = new Router();
    ArrayList<Router> routers;
    Router destination = new Router();
    int totalServicetime; //time the packet arrives minus the time the packet was created
    int packetsDropped; //happens when send to throws an exception
    //these are set by user
    double arrivalProb;
    int numIntRouters;
    int maxBufferSize;
    int minPacketSize;
    int maxPacketSize;
    int bandwidth;
    int duration;
    */
static class packetsServed{
    int packetsServed = 0;
} 
   
    public static double simulate(Router destination,ArrayList<Router> routers,Router dispatcher,double ap, int nIR, int mBS,
                       int mPS, int maxPS, int B, int d){
        int droppedPackets = 0;
        int totalTimeServed = 0;
        packetsServed packetsserved = new packetsServed();
        int packetNum = 1;
        for(int i = 0;i<d;i++){
            int Time = i+1;
            System.out.println("Time: " + Time);
            
            for(int k = 0; k< 3;k++){
            int packetSize = randInt(mPS,maxPS);
            try {
                packetNum =enqueueDispatch(Time,packetNum,ap,B,dispatcher, packetSize);
            } catch (fullQueueException e){System.out.println("dispatcher queue cannot be filled"); }
            }
            
            
            try {
                deqDisp(mBS,dispatcher,routers);
            } catch (fullQueueException ex) {
                Packet p = dispatcher.dequeue();
                System.out.println("Network was congested packet " + p.getId() + " was dropped");
                droppedPackets++;
            }
        
            
           //  to timeToDest (original time to arrive - time of creation )
           //use method += sendtoDest(routers,)
           totalTimeServed += sendtoDest(routers,packetsserved, B);
            printIntRouter(routers);
        }
           System.out.println("Simulation ending...");
           System.out.println("Total service time: " + totalTimeServed);
           System.out.println("Total packets served: " + packetsserved.packetsServed);
           System.out.println("Average service time per packet: " + (totalTimeServed/packetsserved.packetsServed));
                   
           
   return droppedPackets; 
    }

    
//generates packet if less than probabilit    
    //enqueues dispatcher method
    public static int enqueueDispatch(int Time,int packetNum,double ap,int B,Router dispatcher, int packetSize)
    throws fullQueueException{
        int num = packetNum;
        if(dispatcher.Size()>3){
                System.out.println(dispatcher.Size());
                throw new fullQueueException();
        }
        if(Math.random()<ap){ 
            Packet packet = new Packet(packetNum,packetSize,Time,packetSize/100);
            
        
            dispatcher.enqueue(packet);
            num++;
            System.out.println("Packet " + packet.getId() + " arrives at dispatcher"
                + " with size " + packetSize);
            
       }
        return num;
    }
    
    
    //dequeues dispatcher and sends to free-est int router
    public static void deqDisp(int bufferSize,Router dispatcher, ArrayList<Router> routers)throws fullQueueException{
        int index = 0;
        while(!dispatcher.isEmpty()){
            index = Router.sendPacketTo(routers,bufferSize);
            System.out.println("Index of dispatcher to be dequeued: " + index );
            if(index == -1){
                throw new fullQueueException();
            }
            else{
                Packet pac = dispatcher.dequeue();
                System.out.println(index + ":" + pac );
                System.out.println(routers.get(index));
            routers.get(index).enqueue(pac);
            }
        }
    }
    
        
    
    //decrements packet in intermediate router
    public static int sendtoDest(ArrayList<Router> routers, packetsServed packet , int bandwidth){
        int numAdded =0;
        int result = 0;
        for(int i = 0; i<routers.size();i++){
            if(numAdded == bandwidth)
                break;
            if(routers.get(i).isEmpty())
                continue;
            if(routers.get(i).peek().getTimeToDest() > 0){
                routers.get(i).peek().decrementTimeToArrival();
            }
            else if (routers.get(i).peek().getTimeToDest() <=0){
                Packet p = routers.get(i).dequeue();
                System.out.println("Packet " + p.getId() + 
                        " has successfully reached its destination +" +
                        (p.getTimeArrive()- p.getTimeToDest()));
                result += (p.getTimeArrive() - p.getTimeToDest()+1);
                packet.packetsServed++;
                numAdded++;
                System.out.println("PACKETS SERVED ARE: " +packet.packetsServed);
            }
        }
        
    
    return result;
    }
    //
    private static int randInt(int minVal, int maxVal){
        return (int)( minVal + Math.random()*(maxVal-minVal+1)); 
    }
    public static void printIntRouter(ArrayList<Router> arr){
        for(int i = 0;i<arr.size();i++){
            System.out.println("R" + (i+1) + ": " +
                    arr.get(i).toString());
    }
    }
    
    public static void main(String[] args){
    Router dispatcher = new Router();
    ArrayList<Router> routers;
    Router destination = new Router();
    int totalServicetime; //time the packet arrives minus the time the packet was created
    int packetsDropped; //happens when send to throws an exception
    //these are set by user
    double arrivalProb;
    int numIntRouters;
    int maxBufferSize;
    int minPacketSize;
    int maxPacketSize;
    int bandwidth;
    int duration;
    char loopVal = 'y';
    do{
        try{
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of intermediate routers");
        numIntRouters = input.nextInt();
        routers = new ArrayList<>();
        for(int i = 0; i<numIntRouters;i++){
            routers.add(new Router());
        }
        System.out.println("Enter the arrival probability of a packet");
        arrivalProb = input.nextDouble();
        System.out.println("Enter the maximum buffer size of a router");
        maxBufferSize = input.nextInt();
        System.out.println("Enter the minimum size of the packet");
        minPacketSize = input.nextInt();
        System.out.println("Enter the maximum size of the packet");
        maxPacketSize = input.nextInt();
        System.out.println("Enter bandwidth size");
        bandwidth = input.nextInt();
        System.out.println("Enter simulation duration");
        duration = input.nextInt();
        if(arrivalProb >=1 || arrivalProb<0){
            System.out.println("Enter a probability "
                    + "less than 1 and greater than 0");
            continue;
        }
        if(arrivalProb<0 || maxPacketSize<0 ||maxBufferSize<0||
                minPacketSize<0 ||bandwidth<0 || duration <0){
            System.out.println("Do not enter any negative values");
                    continue;
        }
        if(minPacketSize>maxPacketSize){
            System.out.println("Do not set min greater than max");
            continue;
        }
        
        System.out.println("Total Packets dropped: " + simulate(destination,routers,dispatcher,arrivalProb,numIntRouters,maxBufferSize,minPacketSize
                ,maxPacketSize,bandwidth,duration));
        System.out.println("Do you want to try another simulation? (y/n)");
        input.next();
        loopVal = input.next().charAt(0);
        if(loopVal == 'n'){
            System.out.println("Program terminated successfully...");
            break;}
        else if(loopVal == 'y')
            continue;
        }
        catch(InputMismatchException e){System.out.println("Enter the correct values");}
        //catch(Exception ex){}    
    } while(loopVal == 'y');
    }
}
