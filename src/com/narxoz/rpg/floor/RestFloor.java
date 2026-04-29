package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.state.NormalState;
import java.util.List;
public class RestFloor extends TowerFloor{
    @Override
    public String getFloorName() { return "Safe Haven"; }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println(" You find a warm campfire. It's time to rest.");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        // Демалыс кезінде барлық жағымсыз күйлер кетеді
        party.forEach(hero -> {
            if (!hero.getState().getName().equals("Normal")) {
                System.out.println( hero.getName() + " is no longer " + hero.getState().getName());
                hero.setState(new NormalState());
            }
        });
        return new FloorResult(true, 0, "The party rested and cleared status effects.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        // Сыйлық жоқ, жай ғана демалыс
        System.out.println("Heroes feel refreshed.");
    }

    @Override
    protected boolean shouldAwardLoot(FloorResult result) {
        return false; // Бұл жерде awardLoot шақырылмайды
    }
}
