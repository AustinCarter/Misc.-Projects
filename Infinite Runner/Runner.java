import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Font;

public class Runner extends JPanel implements ActionListener, KeyListener
{
	ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
	final static int UP = 0;
	final static int DOWN = 1;
	final static int ACTION = 2;

	int life= 5;
	double playSpeed = 2;
	Img death = new Img("Death");
	Img stars = new Img("Stars");
	Img start = new Img("Start");

	boolean [] key = new boolean[3];
	boolean doesSpawn = false;

	int score1=0,score2=0;
	double speed = -1;

	int vx =3,vy=1;

	//Font font = new Font(ARIAL, BOLD|ITALIC,24);

	Player player = new Player(128,300);


	public Runner()
	{
		setPreferredSize(new Dimension(800,600));
		setBackground(Color.BLACK);
		createWindow();
	}
	public void actionPerformed(ActionEvent e)
	{
		int spawn = (int)(Math.random()*1000);
		if(spawn < 10 && doesSpawn)
		{
			int y = (int)(Math.random() * getHeight());
			Obstacle obstacle = new Obstacle(800,y);
			obstacle.setSpeed(speed,0);
			speed -= .02;
			obstacles.add(obstacle);
		}else if(spawn < 11) {
			int y = (int)(Math.random() * getHeight());
			PowerUp obstacle = new PowerUp(800,y);
			obstacle.setSpeed(speed,0);
			obstacles.add(obstacle);
		}
		else if(spawn < 12) {
			int y = (int)(Math.random() * getHeight());
			LifeUp obstacle = new LifeUp(800,y);
			obstacle.setSpeed(speed,0);
			obstacles.add(obstacle);
		}


		if(key[UP])
		{
			player.y -= playSpeed;
			player.update();
		}
		if(key[DOWN])
		{
			player.y += playSpeed;
			player.update();
		}
		if(key[ACTION])
		{
			doesSpawn = true;
		}

		for(int m=0;m < obstacles.size()-1;m++)
		{
			Sprite obstaclE = obstacles.get(m);

			int tempx = obstacles.get(m+1).x;
			int tempy = obstacles.get(m+1).y;
			if(obstaclE.dist(tempx,tempy) <=50)
			{
				obstacles.remove(m);
			}
		}
		for(int i=0;i < obstacles.size();i++)

			{
				Sprite obstacle = obstacles.get(i);

				if(obstacle.contains(player.x,player.y))
				{
					obstacles.remove(i);
					if(obstacle instanceof PowerUp)
					{
						playSpeed+= .5;
						obstacle.update();
						break;
					}
					if(obstacle instanceof LifeUp)
					{
						life ++;
						obstacle.update();
						break;
					}

					if(life > 1)
						life --;
					else if (life == 1){
						doesSpawn = false;
						life --;
					}
				}
				obstacle.update();
			}

		if (player.y < 0) player.y =0;
		if (player.y > 600) player.y = 600;


		repaint();
	}
	public void process(int code, boolean pressed)
	{
		switch(code)
		{
			case 87:
				key[UP] = pressed;
				break;
			case 83:
				key[DOWN] = pressed;
				break;
			case 65:
				key[ACTION] = pressed;
				break;
			default:
				System.out.println(code);
				break;
		}
	}
	public void createWindow()
	{
		JFrame frame = new JFrame("Austin's Awesome Fall Final Infinite Runner");
		frame.getContentPane().add(this);
		frame.addKeyListener(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Timer t = new Timer(5,this);
		t.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		start.draw(g);
		if(doesSpawn){
		stars.draw(g);

		g.setColor(new Color(255,255,255,255));
		player.draw(g);


		for(Sprite obstacle : obstacles)
			obstacle.draw(g);

		g.setColor(Color.CYAN);
		//g.setFont(font);
		g.drawString("Lifes: " + life, 20,20);}

		if (life == 0)
			death.draw(g);

	}

	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key)
	{
		process(key.getKeyCode(),true);
	}
	public void keyReleased(KeyEvent key)
	{
		process(key.getKeyCode(),false);
	}
	public static void main (String [] args)
	{
		new Runner();
	}
}