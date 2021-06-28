import javax.swing.JFrame;

public class LeagueInvaders {

	JFrame frame;

	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	
	GamePanel panel;
	

	LeagueInvaders() {
		frame = new JFrame();
		panel = new GamePanel();
	}

	public static void main(String[] args) {
		LeagueInvaders game = new LeagueInvaders();
		game.setup();
	}

	void setup() {
		frame.add(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(panel);
	}
}
