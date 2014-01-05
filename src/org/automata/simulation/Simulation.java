package org.automata.simulation;

import org.automata.base.Color;
import org.automata.base.Grid;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Simulation
{
    private static final int DEFAULT_CELL_SIZE = 5;
    private static final float HUE_SHIFT = 0.0005f;
    private static final int MIN_CELL_SIZE = 2;
    private static final int MAX_CELL_SIZE = 64;

    private int cellSize = DEFAULT_CELL_SIZE;
    private int gridHeight;
    private int gridWidth;
    private int currentRuleset;
    private Color dynamicColor;
    private Color staticColor;
    private Color trailColor1;
    private Color trailColor2;

    private boolean paused = false;
    private boolean trails = false;
    private boolean colorCycle = true;

    private Grid grid;
    private Grid prevGrid1;
    private Grid prevGrid2;

    private TrueTypeFont font;
    private String statusMessage;

    private AutomationRulesetInterface rules;

    public Simulation()
    {
        currentRuleset = 1;
        loadRules(new ConwaysLife());
        resizeGrid(DEFAULT_CELL_SIZE);
        grid.randomize(this.rules.getRandomDensity());

        initColors();

        //Font awtFont = new Font("Times New Roman", Font.BOLD, 32);
        //font = new TrueTypeFont(awtFont, false);
    }

    public void loadRules(AutomationRulesetInterface rules)
    {
        this.rules = rules;
    }

    private void initColors()
    {
        dynamicColor = new Color(0, 0.75f, 0.95f);
        trailColor1 = new Color(dynamicColor.hue() + 0.10f, dynamicColor.sat(), dynamicColor.val() * 0.55f);
        trailColor2 = new Color(dynamicColor.hue() + 0.20f, dynamicColor.sat(), dynamicColor.val() * 0.35f);
        staticColor = new Color(0, 0, 1f);
    }

    private void resizeGrid(int cellSize)
    {
        if (cellSize >= MIN_CELL_SIZE && cellSize <= MAX_CELL_SIZE) {
            gridHeight = Display.getHeight() / (cellSize + 1);
            gridWidth = Display.getWidth() / (cellSize + 1);
            initGrid();
        }
    }

    private void initGrid()
    {
        grid = new Grid(gridWidth, gridHeight);
        prevGrid1 = new Grid(grid);
        prevGrid2 = new Grid(grid);
    }

    public void toggleTrails()
    {
        trails = !trails;

        if (trails) {
            prevGrid2 = prevGrid1 = grid;
        }
    }

    public boolean getInput()
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_1) && currentRuleset != 1) {
            currentRuleset = 1;
            loadRules(new ConwaysLife());
            //statusMessage = "Conway's Life";
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_2) && currentRuleset != 2) {
            currentRuleset = 2;
            loadRules(new ThirtyFourLife());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_3) && currentRuleset != 3) {
            currentRuleset = 3;
            loadRules(new Diamoeba());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_4) && currentRuleset != 4) {
            currentRuleset = 4;
            loadRules(new HighLife());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_5) && currentRuleset != 5) {
            currentRuleset = 5;
            loadRules(new MorleyLife());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_6) && currentRuleset != 6) {
            currentRuleset = 6;
            loadRules(new TwoByTwo());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_7) && currentRuleset != 7) {
            currentRuleset = 7;
            loadRules(new Seeds());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_8) && currentRuleset != 8) {
            currentRuleset = 8;
            loadRules(new DayAndNight());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_9) && currentRuleset != 9) {
            currentRuleset = 9;
            loadRules(new Coral());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_0) && currentRuleset != 0) {
            currentRuleset = 0;
            loadRules(new Replicator());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            grid.randomize(rules.getRandomDensity());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_MINUS)) {
            if (cellSize > MIN_CELL_SIZE) {
                resizeGrid(--cellSize);
                grid.randomize(rules.getRandomDensity());
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_ADD)) {
            if (cellSize < MAX_CELL_SIZE) {
                resizeGrid(++cellSize);
                grid.randomize(rules.getRandomDensity());
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            paused = !paused;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
            toggleTrails();
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
            colorCycle = !colorCycle;
        }

        return true;
    }

    public void update()
    {
        if (paused) {
            return;
        }

        if (trails) {
            prevGrid2 = prevGrid1;
            prevGrid1 = grid;
        }

        grid = rules.getUpdatedGrid(grid);
    }

    public void render()
    {
        cycleColors();
        Color renderColor = colorCycle ? dynamicColor : staticColor;

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                int windowX = x * cellSize + x;
                int windowY = y * cellSize + y;

                if (grid.getCell(x, y) == 1) {
                    drawCell(windowX, windowY, renderColor);
                } else if (trails) {
                    if (prevGrid1.getCell(x, y) == 1) {
                        drawCell(windowX, windowY, trailColor1);
                    } else if (prevGrid2.getCell(x, y) == 1) {
                        drawCell(windowX, windowY, trailColor2);
                    }
                }
            }
        }

        /*
        if (statusMessage != null) {
            //org.newdawn.slick.Color.white.bind();
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            font.drawString(50, 50, statusMessage, org.newdawn.slick.Color.red);
            glDisable(GL_BLEND);
        }
        */
    }

    private void cycleColors()
    {
        if (colorCycle && !paused) {
            dynamicColor.shiftHue(HUE_SHIFT);
            trailColor1.shiftHue(HUE_SHIFT);
            trailColor2.shiftHue(HUE_SHIFT);
        }
    }

    private void drawCell(int windowX, int windowY, Color color)
    {
        glColor4f(color.red(), color.green(), color.blue(), 1);

        glBegin(GL_TRIANGLES);
        {
            glVertex2i(windowX, windowY);
            glVertex2i(windowX, windowY + cellSize);
            glVertex2i(windowX + cellSize, windowY);

            glVertex2i(windowX + cellSize, windowY + cellSize);
            glVertex2i(windowX, windowY + cellSize);
            glVertex2i(windowX + cellSize, windowY);
        }
        glEnd();

    }
}
