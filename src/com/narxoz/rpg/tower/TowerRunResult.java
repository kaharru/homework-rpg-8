package com.narxoz.rpg.tower;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.FloorResult;

import java.util.List;

/**
 * Abstract base class for tower floors, using the Template Method pattern.
 */
public abstract class TowerRunResult {

    /**
     * The fixed template method: defines the sequence of steps.
     */
    public final FloorResult explore(List<Hero> party) {
        announce();
        setup(party);

        FloorResult result = resolveChallenge(party); // 3. Қиындықты шешу (абстрактілі)

        if (shouldAwardLoot(result)) {
            awardLoot(party, result);
        }

        cleanup(party);             // 5. Тазалау (hook)
        return result;
    }


    public abstract String getFloorName();

    /**
     * Қабатқа кіргенде шығатын хабарлама.
     */
    protected void announce() {
        System.out.println("\n--- Entering: " + getFloorName() + " ---");
    }


    protected abstract void setup(List<Hero> party);


    protected abstract FloorResult resolveChallenge(List<Hero> party);


    protected boolean shouldAwardLoot(FloorResult result) {
        return result.isCleared();
    }


    protected abstract void awardLoot(List<Hero> party, FloorResult result);


    protected void cleanup(List<Hero> party) {
        // По умолчанию бос
    }
}