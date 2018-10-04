/*
 *Author @ Austin Carter & Thomas Schwalen
 *Version @ 9/20/14
 *
 *Jeopardy engine for use in english project
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Jeopardy extends JPanel implements ActionListener,
		MouseMotionListener, MouseListener
{
	int width = 800;
	int height = 600;
	int size;
	Font impact = new Font("Impact", Font.BOLD, 35);
	int mousex,mousey;
	int r = (int)(mousey / 5);
	int c = (int)(mousex / 4);
	//int temp;
	boolean slide, answer;
	String display;
	int tempR, tempC;
	String[][] questions = new String[5][4];
	String[][] answers = new String[5][4];
	String[] titles = new String[4];
	String [] populate = new String [4];
	
	
	boolean[][] answered = new boolean[5][4];


	public Jeopardy()
	{
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.BLUE);
		JFrame frame = new JFrame("painter");
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		addMouseMotionListener(this);
		addMouseListener(this);
		
		int i = 0;
		try{
			Scanner Jeopardy = new Scanner(new File("Jeopardy.csv"));
			
			titles = Jeopardy.nextLine().split(",");
			
			for(int k = 0;k<questions.length; k++)
			{
				populate = Jeopardy.nextLine().split(",");
				questions[k] = populate;
			}
			for(int j = 0;j<answers.length;j++)
			{
				populate = Jeopardy.nextLine().split(",");
				answers[j] = populate;
			}	
				
		}catch(IOException e) {}

		Timer timer = new Timer(5,this);
		timer.start();
	}
	public static void main (String [] args)
	{
		new Jeopardy();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.setColor(Color.MAGENTA);
		String coords = String.format("(%d,%d)", mousex, mousey);
		g.drawString(coords,20,20);
		String rowcol = String.format("(%d,%d)",c,r);
		g.drawString(rowcol,20,40);

		g.setColor(Color.BLACK);
		size = width/4;
		for(int i = 0; i <4; i++)
		{
			if(!slide)
				g.drawLine(size * i, 0, size * i, 800);
		}

		size = height/6;

		for(int i = 0; i <6; i++)
		{
			if(!slide)
				g.drawLine(0, size * i, 1000, size * i);
		}

		g.setColor(Color.YELLOW);
		g.setFont(impact);
		if(!slide)
		{
			for(int i = 0;i < titles.length;i++)
				g.drawString(titles[i], i*200 + 25,80); 
		}

		String temp;
		for(int r = 1; r < 6; r++)
		{
			for(int c = 0; c < 4; c++)
			{

				temp = String.format("$ %d", r * 100);
				if(!slide)
					g.drawString(temp,(c * 200) + 40,(r * 100) + 80);
			}
		}
		g.setColor(Color.BLUE);
		for(int r = 0; r < 5; r ++)
		{
			for(int c = 0; c < 4; c++)
			{
				if(answered[r][c] && !slide)
					g.fillRect(c*200+1,(r*100)+101,198,98);
			}
		}

		if(slide)
		{
			g.setColor(Color.BLUE);
			g.drawRect(800,600,10,10);
			g.setColor(Color.YELLOW);
			if(r>-2 && r < 6 && c > -1 && c < 5)
				g.drawString(display,0,300);
		}





		repaint();

	}




	public void mouseDragged(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent mouse)
	{

		mousex = mouse.getX();
		mousey = mouse.getY();
		r = (int)(mousey / 100) - 1;
		c = (int)(mousex / 200);
		if(r>-1 && r < 6 && c > -1 && c < 5 && !slide && !answered[r][c])
		{
			slide = true;
			display = questions[r][c];
			tempR = r;
			tempC = c;
		}
		else if(slide && !answer)
		{
			answer = true;
			display = answers[tempR][tempC];
			answered[tempR][tempC] = true;
		}
		else
		{
			slide = false;
			answer = false;
		}

		repaint();
	}
	public void mouseReleased(MouseEvent e){}
	public void mouseMoved(MouseEvent mouse)
	{
		mousex = mouse.getX();
		mousey = mouse.getY();
		r = (int)(mousey / 100) - 1;
		c = (int)(mousex / 200);


		repaint();
	}
	public void actionPerformed(ActionEvent e)
	{
		int count = 0;

		for(int r = 0; r < 5; r ++)
		{
			for(int c = 0; c < 4; c++)
			{
				if(answered[r][c])
					count ++;
			}
		}
		if (count == 20)
		{
			for(int r = 0; r < 5; r ++)
			{
				for(int c = 0; c < 4; c++)
				{
					answered[r][c] = false;
				}
			}
		}
		repaint();
	}
}
