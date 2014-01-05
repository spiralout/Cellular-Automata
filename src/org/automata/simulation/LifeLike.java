package org.automata.simulation;

import org.automata.base.Grid;

public class LifeLike
{
    private boolean[] born;
    private boolean[] survives;

    public LifeLike(boolean[] born, boolean[] survives)
    {
        this.born = born;
        this.survives = survives;
    }

    public Grid getUpdatedGrid(Grid grid)
    {
        Grid newGrid = new Grid(grid);

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                int numNeighbors = grid.getNumNeighbors(x, y);

                if (grid.getCell(x, y) == 1) {
                    // alive cell
                    if (!survives[numNeighbors]) {
                        newGrid.setCell(x, y, 0);
                    }
                } else {
                    // dead cell
                    if (born[numNeighbors]) {
                        newGrid.setCell(x, y, 1);
                    }
                }
            }
        }

        return newGrid;
    }
}
