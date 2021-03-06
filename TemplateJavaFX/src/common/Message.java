/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author remi
 */
public class Message implements Serializable{
    
    
    private String content;
    private String sender;
    private String date;
    
    public Message(String sender, String content) {
        this.content = content;
        this.sender = sender;
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   this.date = dtf.format(now).toString();
    }
    
    public Message(String sender, String content, String date) {
    	this.content = content;
    	this.sender = sender;
    	this.date = date;
    }
    
    public String toString() {
        return sender + " : " + content;
    }
    
    public String getSender() {
    	return this.sender;
    }
    
    public String getContent() {
    	return this.content;
    }
    
    public String getDate() {
    	return this.date;
    }
    
}
