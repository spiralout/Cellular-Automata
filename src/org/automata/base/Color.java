package org.automata.base;

public class Color
{
    private float[] rgb;
    private float h;
    private float s;
    private float v;

    public Color(float h, float s, float v)
    {
        this.h = h % 1f;
        this.s = s % 1f;
        this.v = v % 1f;
    }

    public void shiftHue(float increment)
    {
        h = (h + increment) % 1f;
        rgb = null;
    }

    public float hue()
    {
        return h;
    }

    public float sat()
    {
        return s;
    }

    public float val()
    {
        return v;
    }

    public float red()
    {
        if (rgb == null) {
            rgb = hsvToRGB(h, s, v);
        }

        return rgb[0];
    }

    public float green()
    {
        if (rgb == null) {
            rgb = hsvToRGB(h, s, v);
        }

        return rgb[1];
    }

    public float blue()
    {
        if (rgb == null) {
            rgb = hsvToRGB(h, s, v);
        }

        return rgb[2];
    }

    public static float[] hsvToRGB(float h, float s, float v)
    {
        float r, g, b;

        int i = (int) Math.floor((double) h * 6);
        float f = h * 6 - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);

        switch (i % 6) {
            case 0: r = v; g = t; b = p; break;
            case 1: r = q; g = v; b = p; break;
            case 2: r = p; g = v; b = t; break;
            case 3: r = p; g = q; b = v; break;
            case 4: r = t; g = p; b = v; break;
            case 5: r = v; g = p; b = q; break;
            default: r = 0; g = 0; b = 0;
        }

        return new float[]{r, g, b};
    }
}
