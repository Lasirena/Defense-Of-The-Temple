package collision_listener;

import projectile.PlayerSpellProjectile;
import actor.enemy_assassin.EnemyAssassin;
import actor.player.Player;
import city.cs.engine.*;

/*
* A collision listener to be placed on EnemyAssassin objects which register's collisions with the player's melee and ranged attacks.
*/

public class EnemyAssassinCollider implements CollisionListener {
    private final Player player;
    private final EnemyAssassin enemy;
    
    public EnemyAssassinCollider(Player player, EnemyAssassin enemy) {
        this.player = player;
        this.enemy = enemy;
    }
    
    @Override
    public void collide(CollisionEvent e) {
        // enemy takes damage on hit from spell
        if (e.getOtherBody() instanceof PlayerSpellProjectile && e.getReportingBody() == enemy) {
            enemy.setCurrentHealth(enemy.getCurrentHealth() - player.getDamage());
            
            //enemy dies if their health drops below 0.
            if (enemy.getCurrentHealth() <= 0) {
                e.getReportingBody().destroy();
                e.getOtherBody().destroy();
                System.out.println("You killed the enemy.");
            } else {
                e.getOtherBody().destroy();
                System.out.println("You dealt " + player.getDamage() + " damage. Enemy health: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
            }
        // enemy takes damage on melee collision, provided player has more than 1 fixture (implying the 2nd is the melee hitbox)
        } else if (e.getOtherBody() == player && player.getFixtureList().size() > 1 && e.getReportingBody() == enemy) {
            enemy.setCurrentHealth(enemy.getCurrentHealth() - player.getDamage());
            
            //enemy dies if their health drops below 0.
            if (enemy.getCurrentHealth() <= 0) {
                enemy.getWorld().getPortal().makeSolid();
                e.getReportingBody().destroy();
            } else {
                System.out.println("You dealt " + player.getDamage() + " damage. Enemy health: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
            }
        }
    }
}
