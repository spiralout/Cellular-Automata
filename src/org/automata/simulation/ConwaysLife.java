package org.automata.simulation;

import org.automata.base.Grid;

public class ConwaysLife implements AutomationRulesetInterface
{
    private LifeLike lifelike;
    public static final boolean[] BORN = new boolean[]{false, false, false, true, false, false, false, false, false};
    public static final boolean[] SURVIVES = new boolean[]{false, false, true, true, false, false, false, false, false};

    public ConwaysLife()
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
        return 0.15f;
    }
}
