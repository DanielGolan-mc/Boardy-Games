package net.danielgolan.board_games.games;

import net.danielgolan.board_games.engine.board.Board;
import net.danielgolan.board_games.engine.board.interfaces.Tile;

public abstract class Game<T extends Tile> {
    public enum TileValue {black, blue, empty, red, white}
    public final Board<T> BOARD;

    protected Game(Board<T> board) {
        BOARD = board;
    }

    abstract public TileValue calculateTileDefaultValueByCoordinates(int x, int y);
    abstract public int size();
}
