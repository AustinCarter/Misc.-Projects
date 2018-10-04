import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Img
{
	ImageIcon img;
	int x, y;
	
	public Img()
	{
		img = null;
		setPosition(0,0);
	}
	
	public Img(String filename)
	{
		this(filename,"png");
	}
	
	public Img(String filename, String filetype)
	{
		img = loadImage(filename + "." + filetype);
		setPosition(0,0);
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g)
	{
		img.paintIcon(null,g,x,y);
	}
	
	protected ImageIcon loadImage(String name)
	{
		java.net.URL url = this.getClass().getResource(name);
		return new ImageIcon(url);
	}
}