import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{

	Rocketship rocket;
	
	ArrayList <Alien> aliens = new ArrayList<>();
	
	ArrayList <Projectile> projectiles = new ArrayList<>();
	
	Random r = new Random();
	
	int score;
	
	
	ObjectManager(Rocketship r){
		rocket = r;
		score = 0;
	}
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void addAlien() {
		aliens.add(new Alien(r.nextInt(LeagueInvaders.WIDTH - 50),0,50,50));
	}
	
	
	void update() {
		
		if(rocket.isActive == true) {
		
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
			
			checkCollision();
		
		}
		
		
		purgeObjects();
		
		
		
		
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		for(int i = 0; i<aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for(int i = 0; i<projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		g.setColor(Color.red);
		g.drawString("" + score, 450, 50);
	}
	
	
	
	void purgeObjects() {
		for(int i = 0; i<aliens.size(); i++) {
			if(aliens.get(i).isActive == false) {
				aliens.remove(i);
				i++;
			}
		}
		
		for(int i = 0; i<projectiles.size(); i++) {
			if(projectiles.get(i).isActive == false) {
				projectiles.remove(i);
				i++;
			}
		}
	}
	
	
	void checkCollision() {
		
		
		
		
		for(int i = 0; i<aliens.size(); i ++) {//iterate aliens
			for(int j = 0; j<projectiles.size(); j++) {//iterate projs
				
				if(aliens.get(i).collisionBox.intersects(projectiles.get(j).collisionBox)) {
					aliens.get(i).isActive = false;
					if(projectiles.get(j).pierce == 0) {
						projectiles.get(j).isActive = false;
					}else {
						projectiles.get(j).pierce -= 1;
					}
					score++;
				}
				
				
			}
			
			if(aliens.get(i).collisionBox.intersects(rocket.collisionBox)) {
				aliens.get(i).isActive = false;
				rocket.isActive = false;
			}
		}
	}
	
	
	int returnScore() {
		return score;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addAlien();
	}
	
}
