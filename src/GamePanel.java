import java.awt.Color;
import java.awt.Graphics;

public class GamePanel {
final int MENU = 0;
final int GAME = 1;
final int END = 2;


int currentState = MENU;


void updateMenuState() {
	
}


void updateGameState() {
	
}


void updateEndState() {
	
}


void drawMenuState(Graphics g) {
	g.setColor(Color.blue);
	g.fillRect(0, 0, LeagueInvaders.WIDTH,  LeagueInvaders.HEIGHT);
}


void drawGameState(Graphics g) {
	
}


void drawEndState(Graphics g) {
	
}



}
