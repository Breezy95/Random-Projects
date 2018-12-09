/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;

/**
 *
 * @author Fabrice Benoit
 */
public class Router {
    private Packet head,tail;
    private Packet current;
    private int size=0;
    private int maxPacketSize;
    
    public Router(){
        
    }
    public void enqueue(Packet P){
        if(head == null){
            current = P;
            head = P;
            tail = P;
        }
        else{
            tail.setNext(P);
            tail = tail.getNext();
        }
        size++;
    }
    
    public Packet dequeue() throws NullPointerException{
            Packet temp;
            if (head == null){
                throw new NullPointerException("List is empty");
            }
            //System.out.println(head);
            temp = head;
            //current = current.getNext();
            head = head.getNext();
            size--;
            return temp;
        }


    public Packet getHead() {
        return head;
    }

    public void setHead(Packet head) {
        this.head = head;
    }

    

    public Packet getCurrent() {
        return current;
    }

    public void setCurrent(Packet current) {
        this.current = current;
    }
    
    public int Size(){
        return size;
    }
    public Packet peek(){
        return head;
    }
    
    public boolean isEmpty(){
        return (size>0 ? false : true);
    }
    
    public static int sendPacketTo(ArrayList<Router> routers, int buffer){
        Router min = routers.get(0);
        boolean emptyRouters = true;
        for(int i = 0;i<routers.size();i++){
            if(routers.get(i).isEmpty() == false){
                emptyRouters = false;
                break;
            }
        }
        if(emptyRouters == true)
            return 0;
        else{
            int maxedOut = 0;
            for(int i = 0; i<routers.size();i++){
                if(routers.get(i).Size()>= buffer)
                maxedOut++;
            }
            if(maxedOut == routers.size())
                return -1;
            else{
                for(int i = 0; i<routers.size();i++){
                    if(min.Size()>routers.get(i).Size()){
                        if(routers.get(i).Size() < buffer)
                        min = routers.get(i);
                            
                    }
                }
                return routers.indexOf(min);
            }
        } 
    }
        
    public String toString(){
        String info = "";
        info += "{";
        if(size ==0){
            return"";
        }
        for(Packet pter = head;pter!= null; pter = pter.getNext() ){
            info +=  pter.toString();
        }
        info += "}";
        return info;
    }
}
    
