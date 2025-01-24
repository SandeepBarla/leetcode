// Using bit manipulation
// change 1-9 to 0-8 and perform left shift and use it as bit mask
// T.C = O(9*9) ~ O(1)
// S.C = O(9) ~ O(1) 
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[] grid = new int[9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                char temp = board[i][j];
                if(!Character.isDigit(temp)) continue;
                int gridIndex = j/3 + 3 * (i/3);
                // find the bit mask
                int mask = 1 << temp-'1';
                // check if the digit exists in the row or col or grid
                if((row[i] & mask)!=0 || (col[j] & mask)!=0 || (grid[gridIndex] & mask)!=0) return false;
                // else add it to all the arrays
                row[i] = row[i] | mask;
                col[j] = col[j] | mask;
                grid[gridIndex] = grid[gridIndex] | mask;
            }
        }
        return true;
    }
}

// Using Single HashSet
// T.C = O(9*9) ~ O(1)
// S.C = O(9) ~ O(1) 
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                char temp = board[i][j];
                if(!Character.isDigit(temp)) continue;
                int gridIndex = j/3 + 3 * (i/3);
                if(!set.add(temp + "seen in row " + i) || !set.add(temp + "seen in col " + j) || !set.add(temp + "seen in grid " + gridIndex)) return false;
            }
        }

        return true;
    }
}

// Using 9*3=27 HashSets
// T.C = O(9*9) ~ O(1)
// S.C = 3 * O(9*9) ~ O(1) 
class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Integer>[] rows = new HashSet[9];
        HashSet<Integer>[] cols = new HashSet[9];
        HashSet<Integer>[] grids = new HashSet[9];
        for(int i=0; i<9; i++){
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            grids[i] = new HashSet<>();
        }
        // iterate through all the elements and check if it exists in HashSets
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                char temp = board[i][j];
                if(!Character.isDigit(temp)) continue;
                // consider indexes as below for grids hashset and calculate gridIndex accordingly
                // 0 1 2
                // 3 4 5
                // 6 7 8
                int gridIndex = j/3 + 3 * (i/3);
                // if the number exists in any of the hashsets, then it's not a valid sudoku
                if(rows[i].contains(temp-'0') || cols[j].contains(temp-'0') || grids[gridIndex].contains(temp-'0')) return false;
                // else add the number in the corresponding 3 hashsets
                rows[i].add(temp-'0');
                cols[j].add(temp-'0');
                grids[gridIndex].add(temp-'0');
            }
        }
        return true;
    }
}

// Bruteforce approach
// T.C = 3 * O(9*9) ~ O(1)
// S.C = O(9) ~ O(1) 
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int m=9;
        int n=9;
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char temp = board[i][j];
                if(Character.isDigit(temp) && !set.contains(temp-'0')){
                    set.add(temp-'0');
                }else if(Character.isDigit(temp)) return false;
            }
            set.clear();
        }
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                char temp = board[j][i];
                if(Character.isDigit(temp) && !set.contains(temp-'0')){
                    set.add(temp-'0');
                }else if(Character.isDigit(temp)) return false;
            }
            set.clear();
        }
        
        return isValidGrid(0,0,3,3,board,set) && isValidGrid(0,3,3,6,board,set) && isValidGrid(0,6,3,9,board,set) &&
        isValidGrid(3,0,6,3,board,set) && isValidGrid(3,3,6,6,board,set) && isValidGrid(3,6,6,9,board,set) &&
        isValidGrid(6,0,9,3,board,set) && isValidGrid(6,3,9,6,board,set) && isValidGrid(6,6,9,9,board,set);
    }
    private boolean isValidGrid(int k, int l, int m, int n, char[][] board, HashSet<Integer> set){
        for(int i=k; i<m; i++){
            for(int j=l; j<n; j++){
                char temp = board[i][j];
                if(Character.isDigit(temp) && !set.contains(temp-'0')){
                    set.add(temp-'0');
                }else if(Character.isDigit(temp)) return false;
            }
        }
        set.clear();
        return true;
    }
}