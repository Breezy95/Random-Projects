/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxfilesystem;

/**
 *
 * @author Fabrice Benoit ID : 109108791
 */
public class DirectoryNode {

    private String name;
    private DirectoryNode left;
    private DirectoryNode middle;
    private DirectoryNode right;
    public boolean isFile;

    /**
     * Invariants name: name of the node left: represents the left node of the
     * binary tree middle: represents the middle node of the binary tree right:
     * are used to represent the right node of the binary tree
     */
    public void setLeft(DirectoryNode left) {
        this.left = left;
    }

    public void setMiddle(DirectoryNode middle) {
        this.middle = middle;
    }

    public void setRight(DirectoryNode right) {
        this.right = right;
    }

    public void setIsFile(boolean isFile) {
        this.isFile = isFile;
    }

    /**
     * creates an instance of DirectoryNode with a string variable
     *
     * @param name used to instantiate a DirectoryNode object creates a name for
     * the directorynode
     */
    public DirectoryNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DirectoryNode getLeft() {
        return left;
    }

    public DirectoryNode getMiddle() {
        return middle;
    }

    public DirectoryNode getRight() {
        return right;
    }

    public boolean availChildren(DirectoryNode node) {
        if (node.getLeft() != null && node.getMiddle() != null && node.getRight() != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * adds a child to the directory to only one of the directory nodes left,
     * right or middle variables
     *
     * @param newChild newChild variable is of the type DirectoryNode this is
     * the object that is being added to a specified directory node
     * @throws NotADirectoryException thrown when the newChild variable has a
     * true value for isFile
     * @throws FullDirectoryException thrown when left,middle, and right are not
     * null this means that they are not empty
     */
    public void addChild(DirectoryNode newChild) throws NotADirectoryException, FullDirectoryException {
        if (newChild.isFile) {
            throw new NotADirectoryException();
        } else {

            if (this.getLeft() == null) {
                this.left = newChild;
            } else if (this.getMiddle() == null) {
                middle = newChild;
            } else if (this.getRight() == null) {
                right = newChild;
            } else {
                throw new FullDirectoryException();
            }
        }
    }

    public boolean getIsFile() {
        return isFile;
    }
}
