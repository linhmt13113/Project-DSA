
package Lines;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//Tao bang luu nhung nguoi choi co so diem cao nhat 
public class TopScores extends JFrame{
	
	public Player  player[] =new Player[10];
	public JButton  number []=new JButton[10];
	
	public TopScores(){
		
		for(int i=0;i<10;i++){
			this.player[i]=new Player();
			this.player[i].name= "player"+(i+1);
		}
		//Bang hien ten va so diem dat duoc cua nguoi choi 
		for(int i=0;i<10;i++){
			number[i]=new JButton((i+1)+". "+this.player[i].name+"  :   "+this.player[i].scores);
			add(number[i]);
		}
		setTitle(" TopScores of Player");
		setSize(200,320);
		setLayout(new GridLayout(10,1));
		setResizable(false);		
	}
	
	//Sap xep tu thap den cao cua cac Player
	public void sortPlayer(){
		for(int i=0;i<10;i++)
			for(int j=i;j>0;j--)
				if(player[j].scores>player[j-1].scores){
					Player tmp = this.player[j];
					this.player[j]  = this.player[j-1];
					this.player[j-1]= tmp;
				}		
	}
    
    //Chuc nang them Player moi 
	public void add(Player newPlayer)throws IOException{
		readFile();
		if(this.player[9].scores<newPlayer.scores){
		    this.player[9] = newPlayer;
			sortPlayer();
			for(int i=0;i<10;i++){
				this.number[i].setText((i+1)+". "+player[i].name+"  :   "+player[i].scores);
			}
			writeFile();//ghi ra file TopScores.dat
		}
	}
	
	//Luu thong tin Player vao file TopScores.dat
	public void writeFile()throws IOException {
	 	DataOutputStream out = new DataOutputStream(new FileOutputStream("TopScores.dat"));
		for(int i=0;i<10;i++)
			this.player[i].writeData(out);
        out.close();
	}
	
	//doc thong tin Player tu file TopScores.dat
	public void readFile()throws IOException {	
		RandomAccessFile in = new RandomAccessFile("TopScores.dat","r");
		for(int i=0;i<10;i++)
			this.player[i].readData(in);
		sortPlayer();
		for(int i=0;i<10;i++){
			this.number[i].setText((i+1)+". "+player[i].name+"  :   "+player[i].scores);
		}
	}
	
	//hien thi bang TopScores
	public void showTopScores()throws IOException{
		readFile();
		show();
	}
	
	//reset la TopScores
	public void resetTopScores()throws IOException{
		new TopScores();
		writeFile();
	}	
	

}