package com.mszurgot.tanks.gamemap;

import com.mszurgot.tanks.Board;

@Deprecated
public interface MapBuilder {
    public void budujMape(char[][] tab);
    public Board pobierzGotowyProdukt();
}
