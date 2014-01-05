package org.automata.simulation;

import org.automata.base.Grid;

public class Replicator implements AutomationRulesetInterface
{
    private LifeLike lifeLike;
    public static final boolean[] BORN = new boolean[]{false, false, false, true, true, true, false, false, false};
    public static final boolean[] SURVIVES = new boolean[]{true, true, true, true, false, true, true, true, true};

    public Replicator()
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
        return 0.0075f;
    }
}
