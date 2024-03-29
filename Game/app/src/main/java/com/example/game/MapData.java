package com.example.game;

public class MapData {
    public static final int MAX_LEVEL = 4;
    private static int[][] map1 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 3, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static int[][] map2 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0, 3, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 4, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    public static int[][] map3 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 4, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 2, 0, 0, 0, 1, 0, 3, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 3, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 2, 0, 0, 0, 1, 0, 3, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 2, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static int[][] map4 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 3, 3, 2, 0, 1, 0},
            {0, 1, 0, 1, 0, 2, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 2, 4, 1, 0, 1, 0},
            {0, 1, 3, 2, 0, 0, 0, 1, 0},
            {0, 1, 3, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}

    };


    public static int[][] getMap(int stage) {//防止意外退出后能继续使用当前的地图数据对象
            int[][] temp;
        temp=new int[map1.length][map1[0].length];
        for (int i=0;i<map1.length;i++){
            for(int j=0;j<map1[0].length;j++){
                temp[i][j]=map1[i][j];
            }
        }
        switch (stage) {
            case 1:

                return temp;
            case 2:
                temp=new int[map2.length][map2[0].length];
                for (int i=0;i<map2.length;i++){
                    for(int j=0;j<map2[0].length;j++){
                        temp[i][j]=map2[i][j];
                    }
                }
                return temp;
            case 3:

                temp=new int[map3.length][map3[0].length];
                for (int i=0;i<map3.length;i++){
                    for(int j=0;j<map3[0].length;j++){
                        temp[i][j]=map3[i][j];
                    }
                }
                return temp;
            case 4:

                temp=new int[map4.length][map4[0].length];
                for (int i=0;i<map4.length;i++){
                    for(int j=0;j<map4[0].length;j++){
                        temp[i][j]=map4[i][j];
                    }
                }
                return temp;
            default:
                return temp;
        }
    }
}