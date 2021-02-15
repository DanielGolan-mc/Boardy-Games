package net.danielgolan.board_games.engine.board;

import net.danielgolan.board_games.engine.board.interfaces.Tile;
import net.danielgolan.board_games.engine.events.Event;
import net.danielgolan.board_games.engine.events.interfaces.BoardEventListener;
import net.danielgolan.board_games.engine.java.TileResetException;

import java.util.ArrayList;
import java.util.List;

public class Board<T extends Tile> {
    public final Options<T> OPTIONS;

    private final T[][] tiles;
    private final List<BoardEventListener<T>> EVENT_LISTENERS = new ArrayList<>();

    public Board(T[][] tiles, Options<T> options) {
        this.tiles = tiles;
        this.OPTIONS = options;

        cleanTiles();
    }

    public T[][] get() {
        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onBoardGet(new Event<>(this));
        return tiles;
    }

    protected T[] getRow(int x) {
        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onRowGet(new Event<>(this));

        return get()[x];
    }

    public T getTile(int x, int y) {
        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onTileGet(new Event<>(this));

        return getRow(x)[y];
    }

    public int size() {
        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onSizeGet(new Event<>(this));

        return tiles.length;
    }

    /**
     * swapping between the tiles in the locations inserted
     */
    public void swap(int fromX, int fromY, int toX, int toY) {
        T tile = getTile(toX, toY);
        set(getTile(fromX, fromY), toX, toY);
        set(tile, fromX, fromY);

        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onSwap(new Event<>(this));
    }

    /**
     * swapping between the tile in the first location inserted and the inserted tile
     */
    public void move(T tile, int fromX, int fromY, int toX, int toY) {
        set(getTile(fromX, fromY), toX, toY);
        set(tile, fromX, fromY);

        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onMove(new Event<>(this));
    }

    public void move(int fromX, int fromY, int toX, int toY) {
        set(getTile(fromX, fromY), toX, toY);
        set(OPTIONS.DEFAULT_TILE, fromX, fromY);

        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onMove(new Event<>(this));
    }

    public void set(T tile, int x, int y) {
        get()[x][y] = tile;

        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onSet(new Event<>(this));
    }

    public void set(int x, int y) {
        set(OPTIONS.DEFAULT_TILE, x, y);

        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onSet(new Event<>(this));
    }

    public void cleanTiles() {
        cleanTiles(OPTIONS.USE_CLONES);
    }

    public void cleanTiles(boolean useClones) {
        for (BoardEventListener<T> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onClean(new Event<>(this));

        for (int x = 0; x < size(); x++)
            for (int y = 0; y < size(); y++)
                if (getTile(x, y) == null)
                    if (useClones)
                        set((T) OPTIONS.DEFAULT_TILE.clone(), x, y);
                    else
                        set(OPTIONS.DEFAULT_TILE, x, y);
    }

    public boolean resetTiles() throws TileResetException {
        for (BoardEventListener<? extends Tile> boardEventListener : EVENT_LISTENERS)
            boardEventListener.onReset(new Event<>(this));

        try {
            for (int x = 0; x < get().length; x++)
                for (int y = 0; y < getRow(x).length; y++)
                    getTile(x, y).reset();

            return false;
        } catch (TileResetException tileResetException) {
            return true;
        } catch (Throwable throwable) {
            throw new TileResetException(OPTIONS.TILE_RESET_EXCEPTION_MESSAGE, throwable);
        }
    }

    public void register(BoardEventListener<T> boardEventListener) {
        for (BoardEventListener<? extends Tile> eventListener : EVENT_LISTENERS)
            eventListener.onListenerRegister(new Event<>(this));

        EVENT_LISTENERS.add(boardEventListener);
    }

    public static class Options<T extends Tile> {
        public final String TILE_RESET_EXCEPTION_MESSAGE;
        public final T DEFAULT_TILE;
        public final boolean USE_CLONES;

        public Options(String tileResetExceptionMessage,
                       T default_tile,
                       boolean use_clones
        ) {
            TILE_RESET_EXCEPTION_MESSAGE = tileResetExceptionMessage;
            DEFAULT_TILE = default_tile;
            USE_CLONES = use_clones;
        }
    }
}
