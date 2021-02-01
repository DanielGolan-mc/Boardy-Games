package net.danielgolan.board_games.engine.board;

import net.danielgolan.board_games.engine.board.interfaces.Tile;
import net.danielgolan.board_games.engine.events.interfaces.BoardEventListener;
import net.danielgolan.board_games.engine.java.TileResetException;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public final Options OPTIONS;

    private final Tile[][] tiles;
    private final List<BoardEventListener> EVENT_LISTENERS = new ArrayList<>();

    protected Board(Tile[][] tiles, Options options) {
        this.tiles = tiles;
        this.OPTIONS = options;

        cleanTiles();
    }

    public Board(int size, Options options) {
        this(new Tile[size][size], options);
    }

    protected Tile[][] get() {
        for (BoardEventListener boardEventListener : EVENT_LISTENERS)
            boardEventListener.onBoardGet();
        return tiles;
    }

    protected Tile[] getRow(int x){
        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onRowGet();

        return get()[x];
    }

    protected Tile[] getLine(int y){
        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onLineGet();

        Tile[] tiles = new Tile[size()];

        for (int x = 0; x < size(); x++)
            tiles[x] = getTile(x, y);

        return tiles;
    }

    public Tile getTile(int x, int y){
        for (BoardEventListener boardEventListener : EVENT_LISTENERS)
            boardEventListener.onTileGet();

        return getRow(x)[y];
    }

    public int size(){
        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onSizeGet();

        return tiles.length;
    }

    /**
     * swapping between the tiles in the locations inserted
     */
    public void swap(int fromX, int fromY, int toX, int toY){
        Tile tile = getTile(toX, toY);
        set(getTile(fromX, fromY), toX, toY);
        set(tile, fromX, fromY);

        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onSwap();
    }

    /**
     * swapping between the tile in the first location inserted and the inserted tile
     */
    public void move(Tile tile ,int fromX, int fromY, int toX, int toY){
        set(getTile(fromX, fromY), toX, toY);
        set(tile, fromX, fromY);

        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onMove();
    }

    public void move(int fromX, int fromY, int toX, int toY){
        set(getTile(fromX, fromY), toX, toY);
        set(OPTIONS.DEFAULT_TILE, fromX, fromY);

        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onMove();
    }

    public void set(Tile tile, int x, int y){
        get()[x][y] = tile;

        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onSet();
    }

    public void set(int x, int y){
        set(OPTIONS.DEFAULT_TILE, x, y);

        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onSet();
    }

    public void cleanTiles() {
        cleanTiles(OPTIONS.USE_CLONES);
    }

    public void cleanTiles(boolean useClones){
        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onClean();

        for (int x = 0; x < size(); x++) for (int y = 0; y < size(); y++)
            if (getTile(x, y) == null)
                if (useClones)
                    set(OPTIONS.DEFAULT_TILE.clone(), x, y);
                else
                    set(OPTIONS.DEFAULT_TILE, x, y);
    }

    public boolean resetTiles() throws TileResetException {
        for (BoardEventListener boardEventListener: EVENT_LISTENERS)
            boardEventListener.onReset();

        try {
            for (int x = 0; x < get().length; x++)
                for (int y = 0; y < getRow(x).length; y++)
                    getTile(x, y).reset();

            return false;
        } catch (TileResetException tileResetException){
            return true;
        } catch (Throwable throwable){
            throw new TileResetException(OPTIONS.TILE_RESET_EXCEPTION_MESSAGE, throwable);
        }
    }

    public void register(BoardEventListener boardEventListener){
        for (BoardEventListener eventListener : EVENT_LISTENERS)
            eventListener.onListenerRegister();

        EVENT_LISTENERS.add(boardEventListener);
    }

    public static class Options {
        public final String TILE_RESET_EXCEPTION_MESSAGE;
        public final Tile DEFAULT_TILE;
        public final boolean USE_CLONES;

        public Options(String tileResetExceptionMessage,
                       Tile default_tile,
                       boolean use_clones
        ){
            TILE_RESET_EXCEPTION_MESSAGE = tileResetExceptionMessage;
            DEFAULT_TILE = default_tile;
            USE_CLONES = use_clones;
        }
    }
}
