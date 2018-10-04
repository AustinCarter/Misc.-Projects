import java.awt.*;

public class LifeUp extends Sprite
{
	Img img = new Img("LifeUp");

	public LifeUp(int x, int y)
	{
		super(x,y);
		height = 40;
		width = 40;
	}
	public void draw(Graphics g)
	{
		img.draw(g);
	}

	public void update()
	{
		x += vx; y += vy;
		img.setPosition(x-20,y-20);
	}
}