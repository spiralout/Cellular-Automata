package org.automata.base;

public class Grid
{
    private int width;
    private int height;
    private int[][] cells;

    public Grid(int width, int height)
    {
        this.width = width;
        this.height = height;
        cells = new int[width][height];
    }

    public Grid(Grid oldGrid)
    {
        this.width = oldGrid.getWidth();
        this.height = oldGrid.getHeight();
        cells = new int[width][height];

        for (int x = 0; x < width; x++) {
            cells[x] = oldGrid.cells[x].clone();
        }
    }

    public void randomize(float density)
    {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.random() < density) {
                    cells[x][y] = 1;
                } else {
                    cells[x][y] = 0;
                }
            }
        }
    }

    public int getCell(int x, int y)
    {
        return cells[x][y];
    }

    public void setCell(int x, int y, int value)
    {
        cells[x][y] = value;
    }

    public int getNumNeighbors(int x, int y)
    {
        return  neighborAt(x - 1, y + 1) +
                neighborAt(x, y + 1) +
                neighborAt(x + 1, y + 1) +
                neighborAt(x - 1, y) +
                neighborAt(x + 1, y) +
                neighborAt(x - 1, y - 1) +
                neighborAt(x, y - 1) +
                neighborAt(x + 1, y - 1);
    }

    private int neighborAt(int x, int y)
    {
        if (x >= width || x < 0) {
            return 0;
        }

        if (y >= height || y < 0) {
            return 0;
        }

        return cells[x][y];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
