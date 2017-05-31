package com.mszurgot.tanks.gamemap;

import com.mszurgot.tanks.Board;

public interface IMapaBudowniczy {
    public void budujMape(char[][] tab);
    public Board pobierzGotowyProdukt();
}
