import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;

	int currentState = MENU;
	
	Font titleFont;
	Font smallFont;
	
	Timer frameDraw;
	
	Rocketship rocketship;
	
	boolean goingUp = false;
	boolean goingDown = false;
	boolean goingLeft = false;
	boolean goingRight = false;
	
	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		rocketship = new Rocketship(250, 700, 50, 50);
	}

	void updateMenuState() {

	}

	void updateGameState() {

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.yellow);
		g.drawString("LEAGUE INVADERS", 20, 100);
		g.setFont(smallFont);
		g.drawString("Press ENTER to start", 100, 400);
		g.drawString("Press SPACE for instructions", 80, 600);
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		if(goingUp) {
			rocketship.up();
		}
		if(goingDown) {
			rocketship.down();
		}
		if(goingLeft) {
			rocketship.left();
		}
		if(goingRight) {
			rocketship.right();
		}
		
		
		
		
		
		
		rocketship.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.black);
		g.drawString("GAME OVER", 20, 100);
		g.setFont(smallFont);
		g.drawString("You killed variable enemies", 100, 400);
		g.drawString("Press ENTER to restart", 80, 600);
	}

	@Override
	public void paintComponent(Graphics g) {

		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU) {
			updateMenuState();
		}else if(currentState == GAME) {
			updateGameState();
		}else if(currentState == END) {
			updateEndState();
		}
		
		System.out.println("Action");
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(currentState == END) {
				currentState = MENU;
			}else {
				currentState++;
			}
		}
		if(currentState == GAME) {
			if(arg0.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("up");
				//rocketship.up();
				goingUp = true;
			}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("down");
				//rocketship.down();
				goingDown = true;
			}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("left");
				//rocketship.left();
				goingLeft = true;
			}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("right");
				//rocketship.right();
				goingRight = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == GAME) {
			if(arg0.getKeyCode() == KeyEvent.VK_UP) {
				
				goingUp = false;
			}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
				
				goingDown = false;
			}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				
				goingLeft = false;
			}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				goingRight = false;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
