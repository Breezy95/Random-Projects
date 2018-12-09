/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packet;
import java.util.ArrayList;
import java.util.Queue;
/**
 *
 * @author Fabrice Benoit
 */
public class NetworkQueue implements Queue {
ArrayList<Packet> netQueue = new ArrayList<>();


    public boolean add(Object e) {
        if(e instanceof Packet){
        netQueue.add((Packet) e);
        return true;
        }
        else
            return false;
    }

    public Object remove() {
    Packet r = netQueue.remove(netQueue.size()-1);
    return r;
    }

    @Override
    public Object peek() {
        return netQueue.get(0);}

    @Override
    public int size() {
       return netQueue.size();
    }

    public boolean isEmpty() {
        return netQueue.isEmpty();
    }

    public boolean contains(Object o) {
        return netQueue.contains()}

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
