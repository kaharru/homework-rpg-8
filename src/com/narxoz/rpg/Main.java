package com.narxoz.rpg;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.state.PoisonedState;
import com.narxoz.rpg.state.StunnedState;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 1. Создаем героев
        List<Hero> party = new ArrayList<>();
        Hero knight = new Hero("Alisher", 100, 20, 10);
        knight.setState(new PoisonedState()); // Басында уланған
        party.add(knight);

        // 2. Создаем этажи башни
        List<TowerFloor> tower = new ArrayList<>();
        tower.add(new BattleFloor());
        tower.add(new RestFloor());
        tower.add(new BattleFloor()); // Екінші шайқас

        // 3. Запуск приключения
        int clearedCount = 0;
        for (TowerFloor floor : tower) {
            // State Lifecycle
            party.forEach(h -> h.getState().onTurnStart(h));

            FloorResult res = floor.explore(party);

            party.forEach(h -> h.getState().onTurnEnd(h));

            if (res.isCleared()) {
                clearedCount++;
                System.out.println("Floor Result: " + res.getSummary());
            } else {
                System.out.println("Game Over on floor " + floor.getFloorName());
                break;
            }
        }

        System.out.println("\nTotal floors cleared: " + clearedCount);
    }
}
