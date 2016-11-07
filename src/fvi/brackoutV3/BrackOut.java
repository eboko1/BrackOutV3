package fvi.brackoutV3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by Vika on 06.11.2016.
 */
public class BrackOut  extends GraphicsProgram {

        //application window in pixel
        private static final int APPLICATION_WIDTH = 400;
        private static final int APPLICATION_HEIGHT = 600;

        // dimensions of game board
        private static final int WIDTH = APPLICATION_WIDTH;
        private static final int HEIGHT = APPLICATION_HEIGHT;

        //Dimensions of paddle up from the bottom
        private static final int PADDLE_WIDTH = 60;
        private static final int PADDLE_HEIGTH = 10;

        //offset of the paddle up from the bottom
        private static final int PADDLE_Y_OFFSET = 30;

        //number of bricks per row
        private static final int NBRICKS_PER_ROW = 10;

        // number of rows of bricks
        private static final int NBRICK_ROWS = 10;

        // separation between bricks
        private static final int BRICK_SEP = 4;

        // width between bricks
        private static final double BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

        //height of a brick
        private static final int BRICK_HEIGHT = 8;

        //radius of the ball in pixel
        private static final int BALL_RADIUS = 10;

        //offset of the top brick row from the top
        private static final int BRICK_Y_OFFSET = 70;

        //number of turns
        private static final int NTURNS = 3;
        private static final  int TIME_DELAY=20;


        private GRect paddle;
        private GOval ball;
        private double vx,vy;

    public void run() {
        setupGame();
        playGame();
    }

    private void playGame() {
        moveBall();
    }

    private void moveBall() {
        vx=rgen.nextDouble(1.0,3.0);
        if (rgen.nextBoolean(0.5)) vx=-vx ;
        vy=3.0;

        while (true){
        ball.move(vx,vy);
        pause(TIME_DELAY);
        checkWalls();}
        }

    private void checkWalls() {
        if (ball.getX()<=0){
            vx=-vx;
        }else if ((ball.getX()+2*BALL_RADIUS)>=WIDTH){
            vx=-vx;
        }else if (ball.getY()<=0){
            vy=-vy;
        }else if ((ball.getY()+2*BALL_RADIUS)>=HEIGHT-PADDLE_Y_OFFSET){
            vy=-vy;
        }

    }


    RandomGenerator rgen = new RandomGenerator();

    private void setupGame() {
        buildBricks();
        buildPaddle();
        buildball();
    }
    private void buildBricks() {
        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                GRect brick = new GRect((BRICK_WIDTH + BRICK_SEP) * col, BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) * row, BRICK_WIDTH, BRICK_HEIGHT);
                brick.setFilled(true);
                switch (row) {
                    case 0:
                        brick.setColor(Color.CYAN);
                        break;
                    case 1:
                        brick.setColor(Color.CYAN);
                        break;
                    case 2:
                        brick.setColor(Color.ORANGE);
                        break;
                    case 3:
                        brick.setColor(Color.ORANGE);
                        break;
                    case 4:
                        brick.setColor(Color.MAGENTA);
                        break;
                    case 5:
                        brick.setColor(Color.MAGENTA);
                        break;
                    case 6:
                        brick.setColor(Color.GREEN);
                        break;
                    case 7:
                        brick.setColor(Color.GREEN);
                        break;
                    case 8:
                        brick.setColor(Color.YELLOW);
                        break;
                    case 9:
                        brick.setColor(Color.YELLOW);
                        break;
                    default:
                        break;

                }
                    add(brick);
                }
            }

        }
    private void buildPaddle() {
        paddle=new GRect(WIDTH/2-PADDLE_WIDTH/2,HEIGHT-2*PADDLE_Y_OFFSET,PADDLE_WIDTH,PADDLE_HEIGTH);
        paddle.setFilled(true);
        paddle.setColor(Color.BLUE);

        add(paddle);
    }
    private void buildball() {
        ball=new GOval(WIDTH/2-BALL_RADIUS,HEIGHT/2-BALL_RADIUS,2*BALL_RADIUS,2*BALL_RADIUS);
        ball.setFilled(true);
        ball.setColor(Color.RED);
        add(ball);
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getX()>=0 && e.getX()<WIDTH-PADDLE_WIDTH){
            paddle.setLocation(e.getX(),HEIGHT-2*PADDLE_Y_OFFSET);
        } else if(e.getX()>=WIDTH-PADDLE_WIDTH){
            paddle.setLocation(WIDTH-PADDLE_WIDTH,HEIGHT-2*PADDLE_Y_OFFSET);
        }

    }
    }


