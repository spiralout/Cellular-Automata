package org.automata.simulation;

import org.automata.base.Grid;

public class Seeds implements AutomationRulesetInterface
{
    private LifeLike lifeLike;
    public static final boolean[] BORN = new boolean[]{false, false, true, false, false, false, false, false, false};
    public static final boolean[] SURVIVES = new boolean[]{false, false, false, false, false, false, false, false, false};

    public Seeds()
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
        return 0.0025f;
    }
}
