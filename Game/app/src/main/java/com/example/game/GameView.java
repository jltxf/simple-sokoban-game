package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameView extends View {
    private static final int FLOOR = 0, WALL = 1, MAN = 4, TARGET = 3, BOX = 2, BOX_IN_TARGET = 5, MAN_ON_TARGET = 6;
    //MAN_ON_TARGET 代表人站在点上把点覆盖的状态 BOX_IN_TARGET 代表盒子已经放在正确的位置了
    private int level = 1;//当前的关数
    private int[][] map = MapData.getMap(level);//获取地图数据
    private Bitmap floorBitmap, wallBitmap, manBitmap, targetBitmap, boxBitmap, boxInTargetBitmap;//声明bitmap对象（存储图片对象）
    private Rect srcFloorRect, srcWallRect, srcManRect, srcTargetRect, srcBoxRect, srcBoxInTargetRect;//方框，选择显示图片的多少
    private Paint mPaint = new Paint();//画笔
    private Man man = new Man();//小人坐标对象
    public static int cellLength = 0;//代表了方格的长度

    public OnWinListener onWinListener;

    public GameView(Context context) {
        super(context);
        //本身的构造方法
    }

    /**
     * 调用xml文件的时候的构造方法
     * @param context
     * @param attrs
     */
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /**
         *实例化bitmap对象，把一个png文件加载成bitmap对象
         * BitmapFactory BitmapFactory工厂 提供了一个decodeResource
         * decode 解码 后面加的是解码的对象decodeResource()从资源文件中解码
         */
        floorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.floor);
        srcFloorRect = new Rect(0, 0, floorBitmap.getWidth(), floorBitmap.getHeight());
        //获取的的是左上定点和右下顶点的坐标

        wallBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
        srcWallRect = new Rect(0, 0, wallBitmap.getWidth(), wallBitmap.getHeight());

        manBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.man);
        srcManRect = new Rect(0, 0, manBitmap.getWidth(), manBitmap.getHeight());

        targetBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.target);
        srcTargetRect = new Rect(0, 0, targetBitmap.getWidth(), targetBitmap.getHeight());

        boxBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        srcBoxRect = new Rect(0, 0, boxBitmap.getWidth(), boxBitmap.getHeight());

        boxInTargetBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.redbox);
        srcBoxInTargetRect = new Rect(0, 0, boxInTargetBitmap.getWidth(), boxInTargetBitmap.getHeight());

    }

    /**
     * 给外部设置onWinListener的公告方法
     * @param onWinListener
     */
    public void setOnWinListener(OnWinListener onWinListener) {
        this.onWinListener = onWinListener;
    }

    public void setLevel(int level) {
        this.level = level;
        map = MapData.getMap(level);//按照关数更改地图数据
        postInvalidate();//更新图片
    }

    //onDraw()
    @Override
    protected void onDraw(Canvas canvas) {
        //Canvas代表一个无限的画板，但是我们只能显示其中一块范围
        //以旁，屏幕左上角为原点，向右向下延伸出x、y(都为正数)
        int width = getMeasuredWidth();//获取com.example.game.GameView的宽度
        int height = getMeasuredHeight();//获取com.example.game.GameView的高度


        //判断画上的网格线是否宽大于高，如果是就将地图转向
        if (((width < height) && map[0].length > map.length) ||
                ((width > height) && map[0].length < map.length)) {
            map = mapRotate(map);
        }

        cellLength = width / map[0].length;//每个小方格长度==屏幕宽度除以地图宽度map[0].length
        mPaint.setColor(Color.RED);//确认画笔颜色

        /**画网格线
        *画横线 map.length宽度
        *startX,startY 是这条线开始的坐标， stopX，stopY是横线结束的坐标，最后是画笔对象
         * */
        for (int i = 0; i <= map.length; i++) {
            canvas.drawLine(0, cellLength * i, cellLength * map[0].length, cellLength * i, mPaint);//画板的方法写入
        }


        //画竖线
        for (int i = 0; i <= map[0].length; i++) {
            canvas.drawLine(cellLength * i, 0, cellLength * i, cellLength * map.length, mPaint);//画板的方法写入
        }
        int boxNum = 0;//记录箱子的数量
        //在方格里面填充地图元素
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Rect dest = new Rect(cellLength * j, cellLength * i, cellLength * (j + 1), cellLength * (i + 1));
                if (map[i][j] == FLOOR) {
                    /**
                     * 第一个参数,拿到bitmap参数,只有第一个参数的画说明把图片完整的放进去，
                     * 第二个参数,Rect的方框 框住图片的大小 指定方框左上角和右下角,说明bitmap这个图片里面的可用范围（图片要放的是哪一个部分）
                     * 第三个参数,Rect的方框 说明bitmap这个图片要放在屏幕了那个范围
                     * 第四个参数可以是pant画笔对象，也可以为空
                     */
                    canvas.drawBitmap(floorBitmap, srcFloorRect, dest, mPaint);

                } else if (map[i][j] == WALL) {
                    canvas.drawBitmap(wallBitmap, srcWallRect, dest, mPaint);
                } else if (map[i][j] == MAN || map[i][j] == MAN_ON_TARGET) {
                    canvas.drawBitmap(manBitmap, srcManRect, dest, mPaint);

                    /*更新man的坐标*/
                    man.setCol(j);
                    man.setRow(i);

                } else if (map[i][j] == TARGET) {
                    canvas.drawBitmap(targetBitmap, srcTargetRect, dest, mPaint);
                } else if (map[i][j] == BOX) {
                    canvas.drawBitmap(boxBitmap, srcBoxRect, dest, mPaint);
                    boxNum++;
                } else if (map[i][j] == BOX_IN_TARGET) {
                    canvas.drawBitmap(boxInTargetBitmap, srcBoxInTargetRect, dest, mPaint);
                }
            }
        }
        checkWin(boxNum);
//canvas.drawCircle(width/2,height/2,width/2,mPaint);
    }

    private void checkWin(int boxNum) {//是否已经完成游戏
        if (boxNum == 0) {//箱子的数量为0
            //弹出一个对话框 显示下一关 涉及到gameView和GameActivity通讯的问题
            //通讯问题：采用一个回调接口的方法
            if (onWinListener != null) {
                onWinListener.onWin();//其实调用的是GameActivity.java里面的onWin方法(回调)
            }
        }
    }

    private int[][] mapRotate(int[][] map) {
        int[][] temp = new int[map[0].length][map.length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                temp[j][i] = map[i][j];
            }
        }
        return temp;
    }

    /*onTouchEvent()是View自带的方法，响应触摸事件 其中包括拖时间、点击事件等，点击事件是2次触摸事件
     *dispatchTouchEvent()也是View自带的方法，分发触摸事件，对于View来说，分发所做的工作是相应事件，本方法能找到的setOnTouchListener()为不为空，即是否有重写，有的化执行它，没有执行OnTouchEvent()
     *因为在本View中
     * */

    /*@Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }*/
/*@Override
    boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
        onTouchEvent() 在本方法里有机会被调用
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*event事件对象（是什么类型) event.getAction()事件动作
        在事件里面写点击响应动作逻辑*/

        if (event.getAction() != MotionEvent.ACTION_DOWN) {//MotionEvent.ACTION_DOWN按下事件 MotionEvent.ACTION_MOVE拖动事件 MotionEvent.ACTION_UP松手事件
            //返回，中断
            return true;
        }

        int touchX = (int) event.getX();
        int touchY = (int) event.getY();//取得触摸事件的坐标
        Rect[] suroundRects = man.getSurroundRect();//取得小人坐标上下左右的方框数组
        int direction = 0;

        //开始判断小人是否在人是上下左右4个坐标中的一个
        for (; direction < 4; direction++) {
            if (suroundRects[direction].contains(touchX, touchY)) {
                break;
            }
        }

        //说明不响应
        if (direction == 4) {
            return true;
        }

        int[][] offset = new int[][]{
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };
        //获取点击的行和列
        int touchRow = man.getRow() + offset[direction][0];
        int touchCol = man.getCol() + offset[direction][1];


        //点击坐标为空地
        if (map[touchRow][touchCol] == FLOOR) {//当前点击的地图数据为空地
            map[touchRow][touchCol] = MAN;
            if (map[man.getRow()][man.getCol()] == MAN) {//当前点击的地图数据为小人
                map[man.getRow()][man.getCol()] = FLOOR;//当前人站的地方还原成绿地
            } else if (map[man.getRow()][man.getCol()] == MAN_ON_TARGET) {//小人站在点上
                map[man.getRow()][man.getCol()] = TARGET;//当前人站的地方还原成绿地
            }

        } else if (map[touchRow][touchCol] == TARGET) {
            map[touchRow][touchCol] = MAN_ON_TARGET;

            if (map[man.getRow()][man.getCol()] == MAN) {
                map[man.getRow()][man.getCol()] = FLOOR;
            } else if (map[man.getRow()][man.getCol()] == MAN_ON_TARGET) {//小人站在点上
                map[man.getRow()][man.getCol()] = TARGET;//空出来的位置放的点
            }

        }

        //点击坐标为箱子
        else if (map[touchRow][touchCol] == BOX) {
            //想走将要移动到的行和列
            int boxMoveToRow = touchRow + offset[direction][0];
            int boxMoveToCol = touchCol + offset[direction][1];

            if (map[boxMoveToRow][boxMoveToCol] == FLOOR) {//当前点击的地图数据为空地
                map[boxMoveToRow][boxMoveToCol] = BOX;
                map[touchRow][touchCol] = MAN;

                if (map[man.getRow()][man.getCol()] == MAN) {
                    map[man.getRow()][man.getCol()] = FLOOR;
                } else if (map[man.getRow()][man.getCol()] == MAN_ON_TARGET) {//小人站在点上
                    map[man.getRow()][man.getCol()] = TARGET;//空出来的位置放的点
                }

            } else if (map[boxMoveToRow][boxMoveToCol] == TARGET) {
                map[boxMoveToRow][boxMoveToCol] = BOX_IN_TARGET;
                map[touchRow][touchCol] = MAN;

                if (map[man.getRow()][man.getCol()] == MAN) {
                    map[man.getRow()][man.getCol()] = FLOOR;
                } else if (map[man.getRow()][man.getCol()] == MAN_ON_TARGET) {//小人站在点上
                    map[man.getRow()][man.getCol()] = TARGET;//空出来的位置放的点
                }

            }
        }
        //在目标点
        else if (map[touchRow][touchCol] == BOX_IN_TARGET) {
            int boxMoveToRow = touchRow + offset[direction][0];
            int boxMoveToCol = touchCol + offset[direction][1];

            if (map[boxMoveToRow][boxMoveToCol] == FLOOR) {
                map[boxMoveToRow][boxMoveToCol] = BOX;
                map[touchRow][touchCol] = MAN_ON_TARGET;

                if (map[man.getRow()][man.getCol()] == MAN) {
                    map[man.getRow()][man.getCol()] = FLOOR;
                } else if (map[man.getRow()][man.getCol()] == MAN_ON_TARGET) {//小人站在点上
                    map[man.getRow()][man.getCol()] = TARGET;//空出来的位置放的点
                }

            } else if (map[boxMoveToRow][boxMoveToCol] == TARGET) {
                map[boxMoveToRow][boxMoveToCol] = BOX_IN_TARGET;
                map[touchRow][touchCol] = MAN_ON_TARGET;

                if (map[man.getRow()][man.getCol()] == MAN) {
                    map[man.getRow()][man.getCol()] = FLOOR;
                } else if (map[man.getRow()][man.getCol()] == MAN_ON_TARGET) {//小人站在点上
                    map[man.getRow()][man.getCol()] = TARGET;//空出来的位置放的点
                }

            }
        }
        //请求重新花一次地图数据（重新调用onDraw（）方法）
        postInvalidate();

        /** 触摸事件作用于xml资源文件，在事件到达资源文件时，父布局获取了事件event并询问子布局是否需要并传递（子布局获取可能进行处理），此时的event有父布局传入
         * 返回true代表触摸事件已经响应完成，告诉父布局不用往下传（传给别人）
         * 返回false代表触摸事件还没有响应完成，可以将event对象继续往下面传给其他控件（如果有的话），本方法不处理，给其他方法处理
         **/
        return true;
    }

    //自设内部接口 用于回调
    public interface OnWinListener {
        public void onWin();
    }
}
