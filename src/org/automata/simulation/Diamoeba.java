package org.automata.simulation;

import org.automata.base.Grid;

public class Diamoeba implements AutomationRulesetInterface
{
    private LifeLike lifeLike;
    public static final boolean[] BORN = new boolean[]{false, false, false, true, false, true, true, true, true};
    public static final boolean[] SURVIVES = new boolean[]{false, false, false, false, false, true, true, true, true};

    public Diamoeba()
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
        return 0.475f;
    }
}
