package org.automata.simulation;

import org.automata.base.Grid;

public class Coral implements AutomationRulesetInterface
{
    private LifeLike lifeLike;
    public static final boolean[] BORN = new boolean[]{false, false, false, true, false, false, false, false, false};
    public static final boolean[] SURVIVES = new boolean[]{false, false, false, false, true, true, true, true, true};

    public Coral()
    {
        lifeLike = new LifeLike(BORN, SURVIVES);
    }

    @Override
    public Grid getUpdatedGrid(Grid grid)
    {
        return lifeLike.getUpdatedGrid(grid);
    }

    public float getRandomDensity()
    {
        return 0.2f;
    }
}
