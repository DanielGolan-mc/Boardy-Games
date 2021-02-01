package net.danielgolan.board_games.engine.java;

public class TileResetException extends Exception{
    public TileResetException() {
        super();
    }

    public TileResetException(String message) {
        super(message);
    }

    public TileResetException(String message, Throwable cause) {
        super(message, cause);
    }

    public TileResetException(Throwable cause) {
        super(cause);
    }

    protected TileResetException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}