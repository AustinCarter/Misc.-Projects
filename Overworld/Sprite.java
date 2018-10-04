import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class Sprite
{
	int height,width;
	int x, y;
	double vx = 0, vy = 0 ;
	Color color = Color.GRAY;

	public Sprite(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public boolean contains(int x, int y)
	{
		Rectangle r =
			new Rectangle(this.x-(width >> 1),this.y-(height >> 1),width,height);
		return r.contains(x,y);
	}

	public void setSpeed(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	public double dist(double x1, double y1)
	{
		return dist(x, y, x1, y1);
	}

	public double dist(double x1,double y1,double x2, double y2)
	{
		double diffx = x2 - x1;
		double diffy = y2 - y1;
		return Math.sqrt(diffx * diffx + diffy * diffy);
	}
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x-(width >> 1),y-(height >> 1),width,height);
	}

	public void update()
	{
		x += vx; y += vy;
	}
}