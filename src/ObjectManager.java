import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {

	Rocketship rocket;
	
	ArrayList <Alien> aliens = new ArrayList<>();
	
	ArrayList <Projectile> projectiles = new ArrayList<>();
	
	Random r = new Random();
	
	
	ObjectManager(Rocketship r){
		rocket = r;
	}
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void addAlien() {
		aliens.add(new Alien(r.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	
	
	void update() {
		for(int i = 0; i<aliens.size(); i++) {
			aliens.get(i).update();
			if(aliens.get(i).y >LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}
		for(int i = 0; i<projectiles.size(); i++) {
			projectiles.get(i).update();
			if(projectiles.get(i).y < 0) {
				projectiles.get(i).isActive = false;
			}
		}
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		for(int i = 0; i<aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for(int i = 0; i<projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
	}
	
	
	
	void purgeObjects() {
		
		
	}
	
}
