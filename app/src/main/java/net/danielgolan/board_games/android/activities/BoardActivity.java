package net.danielgolan.board_games.android.activities;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import net.danielgolan.board_games.R;

public class BoardActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak") private static BoardActivity boardActivity;

    public final static int[][] IMAGE_VIEWS_IDS = new int[][]{
            {R.id.board_tile_view},
            {R.id.board_tile_view_1,
                    R.id.board_tile_view_1_1, R.id.board_tile_view_1_2, R.id.board_tile_view_1_3,
                    R.id.board_tile_view_1_4, R.id.board_tile_view_1_5, R.id.board_tile_view_1_6,
                    R.id.board_tile_view_1_7, R.id.board_tile_view_1_8},
            {R.id.board_tile_view_2,
                    R.id.board_tile_view_2_1, R.id.board_tile_view_2_2, R.id.board_tile_view_2_3,
                    R.id.board_tile_view_2_4, R.id.board_tile_view_2_5, R.id.board_tile_view_2_6,
                    R.id.board_tile_view_2_7, R.id.board_tile_view_2_8},
            {R.id.board_tile_view_3,
                    R.id.board_tile_view_3_1, R.id.board_tile_view_3_2, R.id.board_tile_view_3_3,
                    R.id.board_tile_view_3_4, R.id.board_tile_view_3_5, R.id.board_tile_view_3_6,
                    R.id.board_tile_view_3_7, R.id.board_tile_view_3_8},
            {R.id.board_tile_view_4,
                    R.id.board_tile_view_4_1, R.id.board_tile_view_4_2, R.id.board_tile_view_4_3,
                    R.id.board_tile_view_4_4, R.id.board_tile_view_4_5, R.id.board_tile_view_4_6,
                    R.id.board_tile_view_4_7, R.id.board_tile_view_4_8},
            {R.id.board_tile_view_5,
                    R.id.board_tile_view_5_1, R.id.board_tile_view_5_2, R.id.board_tile_view_5_3,
                    R.id.board_tile_view_5_4, R.id.board_tile_view_5_5, R.id.board_tile_view_5_6,
                    R.id.board_tile_view_5_7, R.id.board_tile_view_5_8},
            {R.id.board_tile_view_6,
                    R.id.board_tile_view_6_1, R.id.board_tile_view_6_2, R.id.board_tile_view_6_3,
                    R.id.board_tile_view_6_4, R.id.board_tile_view_6_5, R.id.board_tile_view_6_6,
                    R.id.board_tile_view_6_7, R.id.board_tile_view_6_8},
            {R.id.board_tile_view_7,
                    R.id.board_tile_view_7_1, R.id.board_tile_view_7_2, R.id.board_tile_view_7_3,
                    R.id.board_tile_view_7_4, R.id.board_tile_view_7_5, R.id.board_tile_view_7_6,
                    R.id.board_tile_view_7_7, R.id.board_tile_view_7_8},
            {R.id.board_tile_view_8,
                    R.id.board_tile_view_8_1, R.id.board_tile_view_8_2, R.id.board_tile_view_8_3,
                    R.id.board_tile_view_8_4, R.id.board_tile_view_8_5, R.id.board_tile_view_8_6,
                    R.id.board_tile_view_8_7, R.id.board_tile_view_8_8}};

    public final static int[] BUTTON_VIEWS_IDS = new int[]{
            R.id.playview_layout, R.id.playview_button_up, R.id.playview_button_left,
            R.id.playview_button_completed, R.id.playview_button_right, R.id.playview_button_down
    };

    final private TableLayout TABLE_LAYOUT = findViewById(IMAGE_VIEWS_IDS[0][0]);
    final private TableLayout ARROWS_LAYOUT = findViewById(BUTTON_VIEWS_IDS[0]);

    public BoardActivity(){
        super();
        boardActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static Drawable drawable(int id){
        return boardActivity.getDrawable(id);
    }

    public static View view(int id){
        return boardActivity.findViewById(id);
    }
}