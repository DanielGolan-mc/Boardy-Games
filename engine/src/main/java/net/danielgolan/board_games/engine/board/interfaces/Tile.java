package net.danielgolan.board_games.engine.board.interfaces;


import net.danielgolan.board_games.engine.java.TileResetException;

public interface Tile{
    void reset() throws TileResetException;
    Tile clone();
}
