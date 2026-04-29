package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.Monster;
import java.util.List;
public class BattleFloor extends TowerFloor{
    private Monster enemy;

    @Override
    public String getFloorName() { return "Dark Arena"; }

    @Override
    protected void setup(List<Hero> party) {
        // Шайқас алдында монстрды дайындау
        enemy = new Monster("Skeleton Warrior", 40, 12);
        System.out.println( enemy.getName() + " blocks your path!");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        int totalDamageTaken = 0;

        // Монстр немесе батырлар өлгенше шайқас жалғасады
        while (enemy.isAlive() && party.stream().anyMatch(Hero::isAlive)) {
            for (Hero hero : party) {
                if (hero.isAlive() && hero.getState().canAct()) {
                    int damage = hero.getAttackPower(); // Бұл жерде State-ті қолдануға болады
                    enemy.takeDamage(damage);
                    System.out.println( hero.getName() + " hits " + enemy.getName() + " for " + damage + "!");
                }
            }

            if (enemy.isAlive()) {
                for (Hero hero : party) {
                    if (hero.isAlive()) {
                        enemy.attack(hero);
                        totalDamageTaken += (enemy.getAttackPower() - 2); // қарапайым есептеу
                        break; // Монстр тек бір батырды ұрады
                    }
                }
            }
        }

        boolean cleared = !enemy.isAlive();
        return new FloorResult(cleared, totalDamageTaken, cleared ? "Monster defeated!" : "Party wiped out...");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println("Each hero gains 10 XP from the fallen foe.");

    }
}
