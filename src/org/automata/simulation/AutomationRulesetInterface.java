package org.automata.simulation;

import org.automata.base.Grid;

/**
 * Created by sean on 1/3/14.
 */
public interface AutomationRulesetInterface
{
    public Grid getUpdatedGrid(Grid grid);
    public float getRandomDensity();
}
