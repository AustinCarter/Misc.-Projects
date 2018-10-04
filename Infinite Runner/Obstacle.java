import java.awt.*;

public class Obstacle extends Sprite
{
	Img img = new Img("Wall");

	public Obstacle(int x, int y)
	{
		super(x,y);
		color = Color.MAGENTA;
		height = 180;
		width = 40;
	}
	public void draw(Graphics g)
	{
		img.draw(g);
	}

	public void update()
	{
		x += vx; y += vy;
		img.setPosition(x-20,y-90);
	}

}