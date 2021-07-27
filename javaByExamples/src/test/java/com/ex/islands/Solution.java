package com.ex.islands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Solution {

    // [[1,1,1,1,0], [1,1,0,1,0], [1,1,0,0,0], [0,0,0,0,0]]
    public int numIslands(char[][] grid) {
        // write code here
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rowNum = grid.length;
        int colNum = grid[0].length;
        int count = 0;
        for (int r = 0; r < rowNum; ++r) {
            for (int c = 0; c < colNum; ++c) {
                if (grid[r][c] == '1') {
                    ++count;
                    dfs(grid, r, c);
                }
            }
        }
        return count;
    }

    void dfs(char[][] grid, int r, int c) {
        int rowNum = grid.length;
        int colNum = grid[0].length;
        if (r < 0 || c < 0 || r >= rowNum || c >= colNum || grid[r][c] == '0') { // border or sea
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, c); // up
        dfs(grid, r + 1, c); // down
        dfs(grid, r, c - 1); // left
        dfs(grid, r, c + 1); // right
    }


    /*
    11110
    11010
    11000
    00000

    output 1
     */

    @Test
    public void test() {
        char[][] grid = new char[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}};
//        grid = new char[][]{
//                {'1', '0', '1', '1', '1'},
//                {'1', '0', '1', '0', '1'},
//                {'1', '1', '1', '0', '1'}};
        int out = numIslands(grid);
        assertEquals(1, out);
    }
}
