package net.danielgolan.board_games.android.enums;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

import net.danielgolan.board_games.R;
import net.danielgolan.board_games.android.activities.BoardActivity;

public enum TileVectors {
    BLACK_EMPTY_TILE(R.drawable.black_empty_tile), BLACK_MINUS_TILE(R.drawable.black_minus_tile),
    BLACK_PLUS_TILE(R.drawable.black_plus_tile), BLACK_SELECTED_TILE(R.drawable.black_selected_tile),

    BLUE_EMPTY_TILE(R.drawable.blue_empty_tile), BLUE_MINUS_TILE(R.drawable.blue_minus_tile),
    BLUE_PLUS_TILE(R.drawable.blue_plus_tile), BLUE_SELECTED_TILE(R.drawable.blue_selected_tile),

    RED_EMPTY_TILE(R.drawable.red_empty_tile), RED_MINUS_TILE(R.drawable.red_minus_tile),
    RED_PLUS_TILE(R.drawable.red_plus_tile), RED_SELECTED_TILE(R.drawable.red_selected_tile),

    WHITE_EMPTY_TILE(R.drawable.white_empty_tile), WHITE_MINUS_TILE(R.drawable.white_minus_tile),
    WHITE_PLUS_TILE(R.drawable.white_plus_tile), WHITE_SELECTED_TILE(R.drawable.white_selected_tile),

    ARROW_UP(R.drawable.ic_baseline_keyboard_arrow_up_24), ARROW_DOWN(R.drawable.ic_baseline_keyboard_arrow_down_24),
    ARROW_LEFT(R.drawable.ic_baseline_keyboard_arrow_left_24), ARROW_RIGHT(R.drawable.ic_baseline_keyboard_arrow_right_24),

    SELECTED(R.drawable.ic_baseline_check_24);

    final Drawable drawable;

    @SuppressLint("UseCompatLoadingForDrawables")
    TileVectors(int drawable) {
        this.drawable = BoardActivity.drawable(drawable);
    }

    public Drawable get() {
        return drawable;
    }
}
