package com.narxoz.rpg.state;
import com.narxoz.rpg.combatant.Hero;

public class PoisonedState implements HeroState{
    private int duration = 3;

    @Override public String getName() { return "Poisoned"; }
    @Override public int modifyOutgoingDamage(int basePower) { return basePower; }
    @Override public int modifyIncomingDamage(int rawDamage) { return rawDamage; }

    @Override
    public void onTurnStart(Hero hero) {
        System.out.println("[STATE] " + hero.getName() + " takes 5 poison damage!");
        hero.takeDamage(5);
    }

    @Override
    public void onTurnEnd(Hero hero) {
        duration--;
        if (duration <= 0) {
            System.out.println(" [STATE] Poison wore off for " + hero.getName() + ".");
            hero.setState(new NormalState());
        }
    }

    @Override public boolean canAct() { return true; }
}
