package com.mszurgot.tanks;

import java.awt.*;
import java.util.Iterator;

/**
 * Created by Zet on 22.06.2017.
 */
public interface IDrawable {
    void draw(Graphics2D graphicsContext, Iterator iterator);
}
