package net.danielgolan.board_games.engine.events;

import net.danielgolan.board_games.engine.board.Board;
import net.danielgolan.board_games.engine.board.interfaces.Tile;

public final class Event<E extends Tile> {
    private final Board<E> BOARD;

    public Event(Board<E> board) {
        BOARD = board;
    }

    public Board<E> getBOARD() {
        return BOARD;
    }
}
