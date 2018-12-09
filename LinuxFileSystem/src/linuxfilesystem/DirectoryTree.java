/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxfilesystem;

/**
 *
 * @author Fabrice Benoit ID: 109108791
 */
public class DirectoryTree {

    private DirectoryNode root;
    private DirectoryNode cursor;

    /**
     * Invariants: root: DirectoryNode object that refers to the root or the
     * beginning of the tree
     *
     * cursor: DirectoryNode object that refers to an iterator that cycles
     * through the tree
     */
    /**
     * returns an instance of Directory Tree with root variable set equal to a
     * DirectoryNode with name set to "root" after it sets cursor equal to the
     * root
     *
     */
    public DirectoryTree() {
        root = new DirectoryNode("root");
        this.cursor = root;

    }

    /**
     * sets cursor equal to root
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     * changes cursor to a child directory of the cursor node
     *
     * @param name name of the directory that the cursor will be set to
     * @throws IllegalArgumentException thrown if name variable contains a space
     * or a '/'
     * @throws NotADirectoryException thrown if the directory that the cursor is
     * attempting to go to is not a directory
     */
    public void changeDirectory(String name) throws IllegalArgumentException, NotADirectoryException {
        if (name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException();
        }
        if(name.contentEquals("...")){
            findParent(root,cursor);
            return;
        }
        if (cursor.getLeft() != null) {
            if (cursor.getLeft().getName().contentEquals(name)) {
                if (cursor.getLeft().getIsFile() != true) {
                    cursor = cursor.getLeft();
                    return;
                } else {
                    throw new NotADirectoryException("Cannot change"
                            + " directory to file");
                }
            }
        }
        if (cursor.getMiddle() != null) {
            if (cursor.getMiddle().getName().contentEquals(name)) {
                if (cursor.getMiddle().getIsFile() != true) {
                    cursor = cursor.getMiddle();
                    return;
                } else {
                    throw new NotADirectoryException("Cannot change"
                            + " directory to file");
                }
            }
        }
        if (cursor.getRight() != null) {
            if (cursor.getRight().getName().contentEquals(name)) {
                if (cursor.getRight().getIsFile() != true) {
                    cursor = cursor.getRight();
                    return;
                } else {
                    throw new NotADirectoryException("Cannot change"
                            + " directory to file");
                }
            }
        } else {
            System.out.println("could not find directory try again");
        }

    }
    private void findParent(DirectoryNode root,DirectoryNode cursor){
        if(cursor == root){
            System.out.println("Cursor is at highest level");
            return;
        }
        if(root.getLeft().getName().contentEquals(cursor.getName()))
            cursor  = root;
        
        if(root.getMiddle().getName().contentEquals(cursor.getName()))
            cursor = root;
        
        if(root.getRight().getName().contentEquals(cursor.getName()))
            cursor = root;
        if(root.getLeft()!= null)
            findParent(root.getLeft(),cursor);
        if(root.getMiddle() != null)
            findParent(root.getMiddle(),cursor);
        if(root.getRight() != null)
            findParent(root.getRight(),cursor);
    }

    /**
     * Returns a string representation of the directory from the root to the
     * cursor
     *
     * @return String representing the path from the root to the cursor
     */
    public String presentWorkingDirectory() {
        if (cursor == null) {
            return "no directory";
        }
        if (cursor == root) {
            return "root";
        }

        return printDirectorytoNode(cursor, root, "");
    }

    /**
     * helper method that finds the path from the root node to the cursor
     *
     * @param cursor
     * @param node
     * @param info
     * @return info which will be the path that the
     */
    private String printDirectorytoNode(DirectoryNode cursor, DirectoryNode node, String info) {
        String ans = "";
        if (node.equals(cursor)) {
            return info + node.getName();
        }
        if (node.getLeft() != null) {
            ans = printDirectorytoNode(cursor, node.getLeft(), info + node.getName() + "/");
        }
        if (node.getMiddle() != null && ans != null) {
            ans = printDirectorytoNode(cursor, node.getMiddle(), info + node.getName() + "/");
        }
        if (node.getRight() != null && ans != null) {
            ans = printDirectorytoNode(cursor, node.getRight(), info + node.getName() + "/");
        }
        return ans;
    }

    /**
     * returns all of the nodes in the child directory
     *
     * @return String object with the names of all of the children of the cursor
     */
    public String listDirectory() {
        String info = "";
        if (cursor.getLeft() != null) {
            info += cursor.getLeft().getName() + " ";
        }
        if (cursor.getMiddle() != null) {
            info += cursor.getMiddle().getName() + " ";
        }
        if (cursor.getRight() != null) {
            info += cursor.getRight().getName() + " ";
        }
        return info;
    }

    /**
     * method that prints the directory Tree beneath the cursor
     *
     */
    public void printdirectoryTree() {
        printTree(0, cursor);

    }

    /**
     * helper method that prints all children of all the nodes with the cursor
     * acting as root
     *
     * @param n int variable that represents the level or height that each node
     * is at with 0 being the level of the root increases as it goes through
     * each level and decreases as it goes back
     * @param cursor DirectoryNode variable that represents the node where the
     * cursor is
     */
    private void printTree(int n, DirectoryNode cursor) {
        String space = "";
        for (int i = 0; i < n; i++) {
            space += "    ";
        }
        System.out.println((cursor.getIsFile() ? space + "-" + cursor.getName() : space + "|-" + cursor.getName()));

        if (cursor.getLeft() != null) {
            printTree(n + 1, cursor.getLeft());
        }
        if (cursor.getMiddle() != null) {
            printTree(n + 1, cursor.getMiddle());
        }
        if (cursor.getRight() != null) {
            printTree(n + 1, cursor.getRight());
        }
    }

    /**
     * creates a directory with isFile variable set to off and places it in the
     * free directory if the cursor starting from the left most node
     *
     * @param name string variable that represents the name of the directory
     * @throws IllegalArgumentException thrown if name contains a whitespace("
     * ") or a forward slash "/"
     * @throws FullDirectoryException thrown if all the children of the
     * directory reference a DirectoryNode
     */
    public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException {
        if (name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException();
        }
        if (cursor.getLeft() != null && cursor.getMiddle() != null && cursor.getRight() != null) {
            throw new FullDirectoryException();
        }
        if (cursor.getLeft() == null) {
            cursor.setLeft(new DirectoryNode(name));
            cursor.getLeft().setIsFile(false);
        } else if (cursor.getMiddle() == null) {
            cursor.setMiddle(new DirectoryNode(name));
            cursor.getMiddle().setIsFile(false);
        } else if (cursor.getRight() == null) {
            cursor.setRight(new DirectoryNode(name));
            cursor.getRight().setIsFile(false);
        }

    }

    /**
     * creates a DirectoryNode object with isFile variable set to True and adds
     * it to the free spot starting from the left
     *
     * @param name String var that represents the name of the file
     * @throws FullDirectoryException thrown if all the children nodes of the
     * cursor reference a directory
     * @throws IllegalArgumentException thrown if the name contains a
     * whitespace(" ") or "/"
     */
    public void makeFile(String name) throws FullDirectoryException, IllegalArgumentException {
        if (name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException();
        }
        if (cursor.getLeft() != null && cursor.getMiddle() != null && cursor.getRight() != null) {
            throw new FullDirectoryException();
        }

        if (cursor.getLeft() == null) {
            cursor.setLeft(new DirectoryNode(name));
            cursor.getLeft().setIsFile(true);
        } else if (cursor.getMiddle() == null) {
            cursor.setMiddle(new DirectoryNode(name));
            cursor.getMiddle().setIsFile(true);
        } else if (cursor.getRight() == null) {
            cursor.setRight(new DirectoryNode(name));
            cursor.getRight().setIsFile(true);
        }

    }
}
