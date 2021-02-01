package net.danielgolan.board_games.engine.events.interfaces;

public interface BoardEventListener {
    void onListenerRegister();
    void onBoardGet();
    void onRowGet();
    void onLineGet();
    void onTileGet();
    void onSizeGet();
    void onClean();
    void onReset();
    void onMove();
    void onSwap();
    void onSet();
}
