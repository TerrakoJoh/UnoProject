/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author remi
 */
public class Message implements Serializable{
    
    //public static final long serialVersionUID = 1L;
    
    private String content;
    private String sender;
    private String id;
    
    public Message(String sender, String content) {
        this.content = content;
        this.sender = sender;
    }
    
    public String toString() {
        return sender + " : " + content;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }
    
}
