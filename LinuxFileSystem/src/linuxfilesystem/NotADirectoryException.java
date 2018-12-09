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
class NotADirectoryException extends Exception {

    public NotADirectoryException() {
        super();
    }

    public NotADirectoryException(String mess) {
        super(mess);
    }
}
