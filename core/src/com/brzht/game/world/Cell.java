package com.brzht.game.world;

import com.brzht.game.entity.Entity;
import com.brzht.game.support.PosInt;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Cell {
    public Vector<Block> blocks;
    public Set<Entity> entities;
    public PosInt pos;
    Cell(PosInt pos){
        blocks = new Vector<>();
        entities = new HashSet<>();
        this.pos = pos;
    }


}
