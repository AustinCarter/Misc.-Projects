import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Overworld extends JPanel implements ActionListener, KeyListener
{
    Img grass = new Img("Grassland");
    Img tree = new Img("Trees");
    Img hut = new Img("Hut");
    Img dungeon = new Img("Castle");
    Img img = new Img("Demon Knight1");

    int px = 320 - 32;
    int py = 320 - 32;
    
    
    Player player = new Player((int)px - 320,(int)py- 320);

    double speed = 1;
   
    boolean down, up, left, right, epressed;
    int key = 0;
    
    
    
    char[][] map = new char[10][10];

    public void process(int code, boolean pressed)
    {
        key = code;

        switch(code)
        {
            case 37: left = pressed; break;
            case 38: up = pressed; break;
            case 39: right = pressed; break;
            case 40: down = pressed; break;
            case 69: epressed = pressed; break; 
            default:
            System.out.println(code);
        }
    }

    public Overworld()
    {
        loadMap();
        setPreferredSize(new Dimension(640,640));
        setBackground(Color.GREEN.darker());
        JFrame frame = new JFrame("WorldMap");
        frame.getContentPane().add(this);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        javax.swing.Timer t = new javax.swing.Timer(5,this);
        t.start();
    }

    public void loadMap()
    {
        try{
            Scanner filein = new Scanner(new File("map.txt"));
            int row = filein.nextInt();
            int col = filein.nextInt();
            filein.nextLine();

            map = new char[row][col];

            for(int i = 0; i < row; i++)
            {
                String input = filein.nextLine();
                map[i] = input.toCharArray();
            }
            
            completeMap();

        } catch(IOException ioe){}
    }
    
    public void completeMap()
    {
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {
                if(map[r][c] == '?')
                {
                    int tileChance = (int)(Math.random() * 100 + 1);
                    
                    if(tileChance <= 84) map[r][c] = 'g';
                    else if(tileChance > 84 && tileChance < 99 )map[r][c] = 't';
                    else map[r][c] = 'd';
                }
            }
        }
        printMap();
    }
    
    

    public void printMap()
    {
        for(char[] row : map)
        {
            System.out.println(Arrays.toString(row));
        }
    }

    public void keyTyped(KeyEvent key) {}

    public void actionPerformed(ActionEvent e)
    {
        update();
        repaint();
    }

    public void keyPressed(KeyEvent key)
    {
        process(key.getKeyCode(),true);
    }

    public void keyReleased(KeyEvent key)
    {
        process(key.getKeyCode(),false);
    }

    public void update()
    {
        int lastpx = px;
        int lastpy = py;
        //if(epressed)interact(); 
        if(left) px -= speed;
        if(right) px += speed;
        if(down) py += speed;
        if(up) py -= speed;
        
        
        int pr = (int)(px/64);
        int pc = (int)(py/64);
        if(px < 0) px = 0;
        else if(px > map.length * 64) px =  map.length * 64;
        if(py < 0) py = 0;
        else if(py > map.length * 64) py =  map.length * 64;
        if(map[pr][pc] == 't' && down) py -= speed;
        else if(map[pr][pc] == 't' && up) py += speed;
        else if(map[pr][pc] == 't' && left) px += speed;
        else if(map[pr][pc] == 't' && right) px -= speed;
        
        System.out.printf("%d,%d \n",pc,pr);
        
        repaint();
    }
    
    /*public void interact()
    {
        int pr = (int)(py/64);
        int pc = (int)(px/64);
        
        System.out.printf("%d,%d \n",pc,pr);
        if(map[pr][pc] == 't')
        {
            
        }
    }*/

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(int r = 0; r < map.length; r++)
        {
            for(int c = 0; c < map[r].length; c++)
            {

                int x = (320 - (int)px) + c * 64;
                int y = (320 - (int)py) + r * 64;

                if(x > -63 && y > -63 && x < getWidth() + 63 && y < getHeight() + 63)
                {

                    Img ground = grass;
                    if(map[r][c] == 'g') 
                    {
                        g.setColor(Color.BLUE);
                        g.fillRect(x,y,64,64);
                        ground = grass;
                    }
                    if(map[r][c] == 't') 
                    {
                        g.setColor(Color.BLUE);
                        g.fillRect(x,y,64,64);
                        ground = tree;
                    }
                    if(map[r][c] == 'h')
                    {
                        g.setColor(Color.BLUE);
                        g.fillRect(x,y,64,64);
                        ground = hut;
                    }
                    if(map[r][c] == 'd')
                    {
                        g.setColor(Color.BLUE);
                        g.fillRect(x,y,64,64);
                        ground = dungeon;
                    }

                    ground.setPosition(x,y);
                   

                    ground.setPosition(x,y);
                    ground.draw(g);

                    tree.setPosition(x,y);
   
                }
                // after map, active

            }
        }

        int r = (int)((py) / 64);
        int c = (int)((px) / 64);
        int x = (320 - (int)px) + c * 64;
        int y = (320 - (int)py) + r * 64;

        g.setColor(Color.RED);
        g.drawRect(x,y,64,64);

        

        img.setPosition(320 - 32,320 - 32);
        img.draw(g);

    }


    public static void main(String[] args)
    {
        new Overworld();
    }
}