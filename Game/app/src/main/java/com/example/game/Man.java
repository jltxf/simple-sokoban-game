package com.example.game;

import android.graphics.Rect;
//获取游戏小人上下左右四个方位的坐标
public class Man {
    //小人所在的坐标
    private int row = 0;
    private int col = 0;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Rect[] getSurroundRect() {
        Rect[] rects = new Rect[4];
        int[][] offset = new int[][]{
                //对坐标进行向上下左右坐标的替换
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        }; //代表了对象的四个方框
        for (int i = 0; i < 4; i++) {
            int r = row + offset[i][0];
            int c = col + offset[i][1];
            Rect rect = new Rect(GameView.cellLength * c, GameView.cellLength * r, GameView.cellLength * (c + 1), GameView.cellLength * (r + 1));
            rects[i]=rect;//上下左右坐标的方框数组
        }
        return rects;
    }

}
