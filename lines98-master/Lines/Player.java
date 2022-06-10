

package Lines;

import java.awt.*;
import javax.swing.*;
import java.io.*;
//Tao class Player de nhap thong tin cua nguoi choi 
public class Player{
 //khai bao bien name va scores 
	public String name;
	public int  scores;
	
	public Player(){
		this.name = " ";
		this.scores=0;
	}
	public Player(String name,int scores){
		this.name = name;
		this.scores=scores;
	}
	public void setName(){
		 String a;
 		 a = JOptionPane.showInputDialog("nhap ten cua ban")+"";
 		 this.name=a;
	}	
	//luu thong tin cua Player vao doi tuong in 
	public void writeData(DataOutput out)throws IOException{
		writeFixedString(this.name, 20 , out);
        out.writeInt(this.scores);

	}
	
	//doc thong tin vao Player tu doi tuong in
	public void readData( DataInput in)throws IOException{
		this.name = readFixedString(20, in);
        this.scores = in.readInt();
      	
	}	
		//doc xau do dai size của nguoi nhap 
	public  String readFixedString(int size, DataInput in)
	  	throws IOException{
    	
    	StringBuilder b = new StringBuilder(size);
      	int i = 0;
      	boolean more = true;
      	while (more && i < size){
        	char ch = in.readChar();
         	i++;
         	if (ch == 0)
         		more = false;
         	else b.append(ch);
      	}
      	
      	in.skipBytes(2 * (size - i));
      	return b.toString();
 	}
	
	//ghi size ki tu cua xau s vao doi tuong out 
   	public  void writeFixedString(String s, int size, DataOutput out)
      throws IOException{
      	int i;
      	for (i = 0; i < size; i++){
        	char ch = 0;
         	if (i < s.length()) ch = s.charAt(i);
         	out.writeChar(ch);
      	}
   	}
}