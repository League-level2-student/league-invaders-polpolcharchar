import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
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
	Timer alienSpawn;
	
	Rocketship rocketship;
	
	boolean goingUp = false;
	boolean goingDown = false;
	boolean goingLeft = false;
	boolean goingRight = false;
	
	boolean shooting = false;
	
	ObjectManager m;
	
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	
	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		rocketship = new Rocketship(250, 700, 50, 50);
		m = new ObjectManager(rocketship);
		
		
		if (needImage) {
		    loadImage ("space.png");
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {

		m.update();
		if(rocketship.isActive == false) {
			currentState = END;
		}
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
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
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
		
		
		
		
		
		
		m.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.black);
		g.drawString("GAME OVER", 20, 100);
		g.setFont(smallFont);
		g.drawString("You killed " + m.returnScore() + " enemies", 100, 400);
		g.drawString("Press ENTER to restart", 80, 600);
	}
	
	void startGame() {
		
		goingUp = false;
		goingDown = false;
		goingLeft = false;
		goingRight = false;
		shooting = false;
		
		alienSpawn = new Timer(10, m);
		alienSpawn.start();
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
		
		//System.out.println("Action");
		if(shooting) {
			m.addProjectile(rocketship.getProjectile());
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(currentState == END) {
				currentState = MENU;
				rocketship = new Rocketship(250, 700, 50, 50);
				m = new ObjectManager(rocketship);
			}else {
				currentState++;
			}
			
			if(currentState == GAME) {
				startGame();
			}else if(currentState == END) {
				alienSpawn.stop();
			}
		}else if(arg0.getKeyChar() == ' ' && currentState == MENU) {
			JOptionPane.showMessageDialog(null, "Use arrow keys to move. Press SPACE to fire. Try not to die");
		}
		if(currentState == GAME) {
			
			if(arg0.getKeyChar() == ' ') {
				//System.out.println("a");
				//m.addProjectile(rocketship.getProjectile());
				shooting = true;
			}
			
			
			
			
			if(arg0.getKeyCode() == KeyEvent.VK_UP) {
				//System.out.println("up");
				//rocketship.up();
				goingUp = true;
			}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
				//System.out.println("down");
				//rocketship.down();
				goingDown = true;
			}else if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				//System.out.println("left");
				//rocketship.left();
				goingLeft = true;
			}else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				//System.out.println("right");
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
			}else if(arg0.getKeyChar() == ' ') {
				shooting = false;
			}
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
