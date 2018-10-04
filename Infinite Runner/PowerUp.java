import java.awt.*;

public class PowerUp extends Sprite
{
	Img img = new Img("PowerUp");

	public PowerUp(int x, int y)
	{
		super(x,y);
		height = 32;
		width = 32;
	}
	public void draw(Graphics g)
	{
		img.draw(g);
	}

	public void update()
	{
		x += vx; y += vy;
		img.setPosition(x-16,y-16);
	}
}