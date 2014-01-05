package org.automata.simulation;

import org.automata.base.Grid;

public class TwoByTwo implements AutomationRulesetInterface
{
    private LifeLike lifeLike;
    public static final boolean[] BORN = new boolean[]{false, false, false, true, false, false, true, false, false};
    public static final boolean[] SURVIVES = new boolean[]{false, true, true, false, false, true, false, false, false};

    public TwoByTwo()
    {
        lifeLike = new LifeLike(BORN, SURVIVES);
    }

    public Grid getUpdatedGrid(Grid grid)
    {
        return lifeLike.getUpdatedGrid(grid);
    }

    @Override
    public float getRandomDensity()
    {
        return 0.15f;
    }
}
