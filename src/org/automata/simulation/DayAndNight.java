package org.automata.simulation;

import org.automata.base.Grid;

public class DayAndNight implements AutomationRulesetInterface
{
    private LifeLike lifelike;
    public static final boolean[] BORN = new boolean[]{false, false, false, true, false, false, true, true, true};
    public static final boolean[] SURVIVES = new boolean[]{false, false, false, true, true, false, true, true, true};

    public DayAndNight()
    {
        lifelike = new LifeLike(BORN, SURVIVES);
    }

    public Grid getUpdatedGrid(Grid grid)
    {
        return lifelike.getUpdatedGrid(grid);
    }

    @Override
    public float getRandomDensity()
    {
        return 0.35f;
    }
}
