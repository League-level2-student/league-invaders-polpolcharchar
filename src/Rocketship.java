import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 3;
	}

	void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}

	public void up() {
		if (y > speed + 1) {
			y -= speed;
		}
	}

	public void down() {
		if (y < 800 - speed - 50) {
			y += speed;
		}
	}

	public void left() {
		if (x > speed + 1) {
			x -= speed;
		}
	}

	public void right() {
		if (x < 500 - speed - 50) {
			x += speed;
		}
	}

}
