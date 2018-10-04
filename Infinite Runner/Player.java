import java.awt.*;

public class Player extends Sprite
{
	Img img = new Img("Ship");

	public Player(int x,int y)
	{
		super (x,y);
		height = 64;
		width = 64;
	}
	public void draw(Graphics g)
	{
		img.draw(g);
	}
	public void update()
	{
		img.setPosition(x-32,y-32);
	}
}