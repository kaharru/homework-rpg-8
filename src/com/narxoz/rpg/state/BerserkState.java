package com.narxoz.rpg.state;
import com.narxoz.rpg.combatant.Hero;

public class BerserkState implements HeroState{
    @Override public String getName() { return "Berserk"; }
    @Override public int modifyOutgoingDamage(int basePower) { return basePower * 2; }
    @Override public int modifyIncomingDamage(int rawDamage) { return (int)(rawDamage * 1.5); }

    @Override public void onTurnStart(Hero hero) {
        System.out.println(hero.getName() + " is in RAGE! Double damage dealt!");
    }

    @Override public void onTurnEnd(Hero hero) {
        if (hero.getHp() > hero.getMaxHp() * 0.5) {
            System.out.println( hero.getName() + " calmed down.");
            hero.setState(new NormalState());
        }
    }

    @Override public boolean canAct() { return true; }
}
