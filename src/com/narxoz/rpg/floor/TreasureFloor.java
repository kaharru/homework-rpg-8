package com.narxoz.rpg.floor;
import com.narxoz.rpg.combatant.Hero;
import java.util.List;
public class TreasureFloor extends TowerFloor{
    @Override
    public String getFloorName() { return "Golden Vault"; }

    @Override
    protected void setup(List<Hero> party) {
        System.out.println("You found a mysterious golden chest!");
    }

    @Override
    protected FloorResult resolveChallenge(List<Hero> party) {
        // Бұл қабатта қиындық жоқ
        return new FloorResult(true, 0, "Chest opened successfully.");
    }

    @Override
    protected void awardLoot(List<Hero> party, FloorResult result) {
        System.out.println(" Every hero is fully healed by the chest's magic!");
        party.forEach(hero -> hero.heal(100));
    }

}
