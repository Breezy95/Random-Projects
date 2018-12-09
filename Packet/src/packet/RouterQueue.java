/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet;

/**
 *
 * @author Fabrice Benoit
 */
public class RouterQueue {
    private Packet head;
    private Packet current;
    public RouterQueue(){
        
    }
    public void enqueue( Packet P){
        if(current == null){
            current = P;
            head = current;
        }
        else{
            current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(P);
            current = head;
    }
    }
        public Packet dequeue() throws NullPointerException{
            Packet temp;
            if (head == null){
                throw new NullPointerException("List is empty");
            }
            temp = head;
            current = current.getNext();
            head = head.getNext();
            return temp;
        }

        

    public static void main(String[] args){
        RouterQueue q = new RouterQueue();
        q.enqueue(new Packet(123));
        q.enqueue(new Packet(3243));
        try{
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        }
        catch(NullPointerException n){}
    
}
}
