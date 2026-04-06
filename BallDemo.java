import java.awt.Color;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
        Box box2 = new Box(myCanvas);
        box2.draw();
        
    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int numofBalls)
    {
        // you must implement this
        if(numofBalls < 5) numofBalls = 5;
        if(numofBalls > 50) numofBalls = 50;
        
        myCanvas.setVisible(true);
        
        //Array of balls
        BoxBall[] balls = new BoxBall[numofBalls];
        
        //Balls created at random position and color
        for(int i = 0; i < balls.length; i++) {
            int x = (int)(Math.random() * (box.getRightWall() - box.getLeftWall() - 20)) 
            + box.getTopWall();
            int y = (int)(Math.random() * (box.getBottomWall() - box.getTopWall() - 20))
            + box.getTopWall();
            Color randomColor = new Color( (int)(Math.random() * 200), (int)(Math.random() * 200), (int)(Math.random() * 200));
            balls [i] = new BoxBall(x, y, 20, randomColor, box, myCanvas);
            balls[i].draw();
        }
        //Makes balls bounce around the box
        while (true) {
            myCanvas.wait(20);           // small delay
            
            //Redraws the box every frame
            box.draw();
            for(BoxBall ball : balls) {
                ball.move();
            }
        }
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
