package com.narxoz.rpg.combatant;

/**
 * A simple monster encountered in a tower floor.
 * Used by floors that have combat challenges.
 */
public class Monster {

    private final String name;
    private int hp;
    private final int attackPower;

    public Monster(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
    }

    public String getName()       { return name; }
    public int getHp()            { return hp; }
    public int getAttackPower()   { return attackPower; }
    public boolean isAlive()      { return hp > 0; }

    /**
     * Reduces this monster's HP by the given amount, clamped to zero.
     *
     * @param amount the damage to apply
     */
    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
        System.out.println("👾 " + name + " takes " + amount + " damage. HP left: " + hp);
    }

    /**
     * This monster attacks a hero, dealing damage.
     * UPDATED: Now uses takeModifiedDamage to account for Hero's State.
     *
     * @param hero the target hero
     */
    public void attack(Hero hero) {
        if (this.isAlive()) {
            // Қарапайым формула: монстрдың күшінен батырдың қорғанысын азайтамыз (мин. 1 зақым)
            int rawDamage = Math.max(1, this.attackPower - (hero.getDefense() / 2));

            System.out.println("⚔️ " + name + " attacks " + hero.getName() + "!");

            // МАҢЫЗДЫ: Батырдың State-ін ескеретін әдісті шақырамыз
            hero.takeModifiedDamage(rawDamage);
        }
    }
}
