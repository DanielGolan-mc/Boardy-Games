package net.danielgolan.board_games.games.nines;

import net.danielgolan.board_games.engine.board.Board;
import net.danielgolan.board_games.engine.board.interfaces.Tile;
import net.danielgolan.board_games.games.Game;

class Nines <T extends Tile> extends Game<T> {

    Nines(T[][] tiles, Board.Options<T> options) {
        super(new Board<>(tiles, options));
    }

    @Override
    public TileValue calculateTileDefaultValueByCoordinates(int x, int y) {
        if (x < 3 && y < 3)
            return TileValue.black;
        else if (4 < x && 4 < y)
            return TileValue.white;
        else
            return TileValue.empty;
    }

    @Override
    public int size() {
        return 8;
    }
}
