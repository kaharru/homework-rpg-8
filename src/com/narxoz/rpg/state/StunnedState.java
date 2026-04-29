package com.narxoz.rpg.state;
import com.narxoz.rpg.combatant.Hero;
public class StunnedState implements HeroState{
    @Override public String getName() { return "Stunned"; }
    @Override public int modifyOutgoingDamage(int basePower) { return 0; }
    @Override public int modifyIncomingDamage(int rawDamage) { return (int)(rawDamage * 1.2); } // Есеңгірегенде көбірек зақым алады

    @Override public void onTurnStart(Hero hero) {
        System.out.println( hero.getName() + " is stunned and cannot act!");
    }

    @Override
    public void onTurnEnd(Hero hero) {
        System.out.println( hero.getName() + " recovered from stun.");
        hero.setState(new NormalState());
    }

    @Override public boolean canAct() { return false; }
}
