/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxfilesystem;

/**
 *
 * @author Fabrice Benoit
 */
public class bigDirectoryNode {
    private String name;
    private bigDirectoryNode left;
    private bigDirectoryNode middle;
    private bigDirectoryNode right;
    public boolean isFile;

    public void setLeft(bigDirectoryNode left) {
        this.left = left;
    }

    public void setMiddle(bigDirectoryNode middle) {
        this.middle = middle;
    }

    public void setRight(bigDirectoryNode right) {
        this.right = right;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    public bigDirectoryNode(){
        
    }
    public bigDirectoryNode(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public bigDirectoryNode getLeft() {
        return left;
    }

    public bigDirectoryNode getMiddle() {
        return middle;
    }

    public bigDirectoryNode getRight() {
        return right;
    }
    
    public boolean availChildren(bigDirectoryNode node){
        if(node.getLeft() != null  && node.getMiddle() != null && node.getRight() != null)
            return false;
        else 
            return true;
    }
    
    public void addChild(bigDirectoryNode newChild)throws NotADirectoryException,FullDirectoryException{
        if(newChild.isFile){
            throw new NotADirectoryException();
        }
        else{
            
        if(this.getLeft() == null){
            this.left = newChild;
        }
        else if(this.getMiddle() == null){
          middle = newChild;  
        }
        else if(this.getRight() == null){
            right = newChild;
        }
        else
            throw new FullDirectoryException();
        }     
    }

    public boolean getIsFile() {
        return isFile;
    }
}
