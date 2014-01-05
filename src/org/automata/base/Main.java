package org.automata.base;

import org.automata.simulation.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Main
{
    private static boolean fullscreen = false;

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args)
    {
        initDisplay();
        initGL();
        initSim();
        simLoop();
        cleanUp();

        System.exit(0);
    }

    /**
     * Init window display and set window size
     */
    private static void initDisplay()
    {
        initDisplayWindowed();

        try {
            Keyboard.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private static void initDisplayWindowed()
    {
        try {
            Display.setDisplayMode(new DisplayMode(1024, 768));
            Display.setFullscreen(false);
            Display.setResizable(true);
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private static void initDisplayFullscreen()
    {
        try {
            Display.setDisplayMode(Display.getDesktopDisplayMode());
            Display.setFullscreen(true);
            Display.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Clean up LWJGL
     */
    private static void cleanUp()
    {
        Display.destroy();
        Keyboard.destroy();
    }

    /**
     * Init OpenGL for 2D mode
     */
    private static void initGL()
    {
        glEnable(GL_TEXTURE_2D);
        glShadeModel(GL_SMOOTH);
        glClearColor(0, 0, 0, 0);
        glClearDepth(1);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);

        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);
        glClearColor(0, 0, 0, 0);

        System.out.println("OpenGL version " + glGetString(GL_VERSION));
    }

    /**
     * Init simulation
     */
    private static void initSim()
    {
        simulation = new Simulation();
    }

    /**
     * Start simulation
     */
    private static void simLoop()
    {
        while (!Display.isCloseRequested()) {
            if (!getInput()) {
                break;
            }

            update();
            render();
        }
    }

    /**
     * Handle global commands
     *
     * @return boolean
     */
    private static boolean getInput()
    {
        while (Keyboard.next()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                return false;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
                fullscreen = !fullscreen;
                Display.destroy();

                if (fullscreen) {
                    initDisplayFullscreen();
                } else {
                    initDisplayWindowed();
                }

                initGL();
                initSim();
            }

            return simulation.getInput();
        }

        return true;
    }

    private static void update()
    {
        simulation.update();
    }

    private static void render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        simulation.render();

        Display.update();
        Display.sync(60);
    }

    private static Simulation simulation;
}
