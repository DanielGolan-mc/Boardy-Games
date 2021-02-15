package net.danielgolan.board_games.android.engine;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import net.danielgolan.board_games.R;
import net.danielgolan.board_games.android.activities.BoardActivity;
import net.danielgolan.board_games.engine.board.interfaces.Tile;
import net.danielgolan.board_games.engine.java.TileResetException;

public class TileView implements Tile, Cloneable{
    @SuppressLint("StaticFieldLeak")
    public static final TileView DEFAULT_TILE = new TileView();

    public final ImageView IMAGE_VIEW;

    public TileView(@NonNull ImageView imageView) {
        IMAGE_VIEW = imageView;
    }
    private TileView() {
        IMAGE_VIEW = (ImageButton) BoardActivity.view(R.id.board_tile_view_1_1);
    }

    @Override
    public void reset() throws TileResetException {
        throw new TileResetException("Can't reset this tile type");
    }

    @Override
    public Tile clone() {
        return new TileView(IMAGE_VIEW);
    }

    public void setOnClickListener(View.OnClickListener listener){
        IMAGE_VIEW.setOnClickListener(listener);
    }
    public void setDrawable(Drawable drawable){
        IMAGE_VIEW.setImageDrawable(drawable);
    }
}
