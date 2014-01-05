package org.automata.simulation;

import org.automata.base.Grid;

public class ThirtyFourLife implements AutomationRulesetInterface
{
    private LifeLike lifeLike;
    public static final boolean[] BORN = new boolean[]{false, false, false, true, true, false, false, false, false};
    public static final boolean[] SURVIVES = new boolean[]{false, false, false, true, true, false, false, false, false};

    public ThirtyFourLife()
    {
        lifeLike = new LifeLike(BORN, SURVIVES);
    }

    @Override
    public Grid getUpdatedGrid(Grid grid)
    {
        return lifeLike.getUpdatedGrid(grid);
    }

    @Override
    public float getRandomDensity()
    {
        return 0.85f;
    }
}
