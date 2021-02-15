package net.danielgolan.board_games.engine.events.interfaces;

import net.danielgolan.board_games.engine.board.interfaces.Tile;
import net.danielgolan.board_games.engine.events.Event;

public interface BoardEventListener<T extends Tile>{
    <tile extends Tile> void onListenerRegister(Event<tile> event);
    <tile extends Tile> void onBoardGet(Event<tile> event);
    <tile extends Tile> void onRowGet(Event<tile> event);
    <tile extends Tile> void onTileGet(Event<tile> event);
    <tile extends Tile> void onSizeGet(Event<tile> event);
    <tile extends Tile> void onClean(Event<tile> event);
    <tile extends Tile> void onReset(Event<tile> event);
    <tile extends Tile> void onMove(Event<tile> event);
    <tile extends Tile> void onSwap(Event<tile> event);
    <tile extends Tile> void onSet(Event<tile> event);
}
