/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxfilesystem;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabrice Benoit
 */
public class BashTerminal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DirectoryTree tree = new DirectoryTree();
        Scanner input = new Scanner(System.in);
        String content = "pwd";
        while (!content.contentEquals("exit")) {
            System.out.print("[fbenoit@life]: $");
            content = input.nextLine();
            if (content.contentEquals("pwd")) {
                System.out.println(tree.presentWorkingDirectory());
            } else if (content.contentEquals("ls")) {
                System.out.println(tree.listDirectory());

            } else if (content.contentEquals("ls -R")) {
                tree.printdirectoryTree();
            } //else if cd dir
            else if (content.split(" ")[0].contentEquals("cd")) {

                if (content.substring(content.indexOf(" ") + 1, content.length()).contentEquals("/")) {
                    tree.resetCursor();
                    continue;
                } else {
                    boolean found = false;
                    while (!found) {
                        try {
                            String cont = content.substring(content.indexOf(" ") + 1, content.length());
                            tree.changeDirectory(cont);
                            found = true;
                        } catch (NotADirectoryException ex) {
                            System.out.println("Incorrect format enter a "
                                    + "different directory");
                            content = input.nextLine();
                        } catch (IllegalArgumentException ex) {
                            System.out.println("incorrect format "
                                    + "enter a different directory");
                            content = input.nextLine();
                        }
                    }
                }
            } else if (content.split(" ")[0].contentEquals("mkdir")) {
                try {
                    String name = content.substring(content.indexOf(" ") + 1, content.length());
                    tree.makeDirectory(name);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (FullDirectoryException ex) {
                    ex.printStackTrace();
                }
            } else if (content.split(" ")[0].contentEquals("touch")) {
                try {
                    String name = content.substring(content.indexOf(" ") + 1, content.length());
                    tree.makeFile(name);
                } catch (FullDirectoryException ex) {
                    ex.printStackTrace();
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                }
            } else if (content.contentEquals("exit")) {
                System.out.println("Program terminating normally");
            } else {
                System.out.println("Invalid input");
            }

        }
    }
}
