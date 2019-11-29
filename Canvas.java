import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by yijunma on 9/27/15.
 */
public class Canvas extends Window implements KeyListener, Runnable {

    // Create the variables you need below
    private String name;
    private int keyMove;
    private Box box;
    private int times;

    // Don't delete this constructor!
    public Canvas() {
        super(sFrame); //include this line in your own constructor
        initialize();  //include this line in your own constructor
    }

    // Create your constructor below that take a string as input
    public Canvas(String name){
        super(sFrame); //include this line in your own constructor
        initialize();  //include this line in your own constructor
        this.name = name;
        this.box = new Box(Color.orange, 350,250,50);
        this.keyMove = 10;  //random setup
        this.times = 0;
        drawBox(box);
    }

    // Create your drawBox method below
    public void drawBox(Box b){

        if(b.getX() == 800){
            b.setX(0);
            this.times += 1;
        }
        else if(b.getX() == 0){
           b.setX(800);
            this.times += 1;
        }
        else if(b.getY() == 600){
            b.setY(0);
            this.times += 1;
        }
        else if(b.getY() == 0){
            b.setY(600);
            this.times += 1;
        }
        else{
            drawSquare(b.getColor(), b.getX(), b.getY(), b.getWidth());
        }


    }

    private void updateBox(Box update) {
        clearSquare();
        drawBox(update);
    }

    //////////////////// add your draw method in the update method  ////////////////////
    //////////////////// so the canvas can keep updating the canvas ////////////////////
    @Override
    public void update(Graphics g) {
        //This method will automatically reload every 0.05 seconds
        drawString(Color.GRAY,this.name + "'s Canvas",20,50,50);
        drawString(Color.GRAY, "Number of times passing edges: " + times,15,50,70);
        g.drawImage(buf, 0, 0, null);//Don't delete this code!
    }

    ///////////////////// Functions / Variables you may need to use //////////////////////////

    private int canvasWidth = 800;
    private int canvasHeight = 600;
    char keyPressed;

    public void drawBackground() {
        gc.setColor(backgroundColor);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    public void clearSquare() {
        // TODO: not the best way
        drawBackground();
    }

    public void drawSquare(Color color, int xPosition, int yPosition, int width) {
        // Use gc.fillRect(...); //The arguments are xPosition, yPosition, width, length
        gc.setColor(color);
        gc.fillRect(xPosition, yPosition, width, width);
    }

    public void drawString(Color color, String str, int fontSize, int xPosition, int yPosition) {
        Font font = new Font("Times New Roman", 0, fontSize);
        gc.setFont(font);
        gc.setColor(color);
        gc.drawString(str, xPosition, yPosition);
    }
    ///////////////////////// no need to understand what's happening below ///////////////////////////
    private BufferedImage buf;
    private Graphics gc;
    private int canvasXPosition = 300;
    private int canvasYPosition = 100;

    private Color backgroundColor = Color.BLACK;

    private static JFrame sFrame = new JFrame("Canvas");

    static {
        sFrame.setVisible(true);
        sFrame.setDefaultCloseOperation(3);
    }

    private void initialize() {
        sFrame.addKeyListener(this);
        this.setBounds(canvasXPosition, canvasYPosition, canvasWidth, canvasHeight);
        this.buf = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
        gc = buf.getGraphics();
        this.setVisible(true);
        new Thread(this).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //If you press "ESC", you quit the game
        //L is 37, U is 38, R 39, D 40
        if (e.getKeyCode() == 27) {
            this.dispose();
            System.exit(0);
        } else if(e.getKeyCode() == 37){
            box.setX(box.getX() - keyMove);
            updateBox(box);
        }
        else if(e.getKeyCode() == 39){
            box.setX(box.getX() + keyMove);
            updateBox(box);
        }
        else if(e.getKeyCode() == 38){
            box.setY(box.getY() - keyMove);
            updateBox(box);
        }
        else if(e.getKeyCode() == 40){
            box.setY(box.getY() + keyMove);
            updateBox(box);
        }
       // keyPressed = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed = ' ';
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5);
                repaint();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}