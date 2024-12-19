// Manipulate and restore data to track visited cells
class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int columns = board[0].length;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                // loop through each element and find if there is a math with first letter of word and apply dfs
                if(board[i][j]==word.charAt(0) && dfs(board, word, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index){
        
        // if index reaches lenght of the word, it means we found the word
        if(index == word.length()) return true;

        // check all boundry conditions, also check the character mismatch
        if(row<0 || row>board.length-1 || col<0 || col>board[0].length-1
        || board[row][col]!=word.charAt(index)){
            return false;
        }

        // store visited cell in temp and mark as '#'
        char temp = board[row][col];
        board[row][col] = '#';

        // perform dfs for all 4 adjacent cells
        boolean found = dfs(board,word,row+1,col,index+1)||
        dfs(board,word,row-1,col,index+1)||
        dfs(board,word,row,col+1,index+1)||
        dfs(board,word,row,col-1,index+1);

        // restore visited cell
        board[row][col] = temp;
        return found;
    }
}

// Using a 2d array to track visited cells
class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int columns = board[0].length;
        // create a 2d array to track visited cells
        int[][] isVisited = new int[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                // loop through each element and find if there is a math with first letter of word and apply dfs
                if(board[i][j]==word.charAt(0) && dfs(board, word, i, j, 0, isVisited)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col, int index, int[][] isVisited){
        
        // if index reaches lenght of the word, it means we found the word
        if(index == word.length()) return true;

        // check all boundry conditions, also check the character mismatch and if the cell visited already
        if(row<0 || row>board.length-1 || col<0 || col>board[0].length-1
        || board[row][col]!=word.charAt(index) || isVisited[row][col]==1){
            return false;
        }

        // mark cell as visited
        isVisited[row][col] = 1;

        // perform dfs for all 4 adjacent cells
        boolean found = dfs(board,word,row+1,col,index+1,isVisited)||
        dfs(board,word,row-1,col,index+1,isVisited)||
        dfs(board,word,row,col+1,index+1,isVisited)||
        dfs(board,word,row,col-1,index+1,isVisited);

        // unmark cell as visited
        isVisited[row][col] = 0;
        
        return found;
    }
}