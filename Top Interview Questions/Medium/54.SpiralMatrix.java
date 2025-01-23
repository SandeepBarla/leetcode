// Iterative Boundry approach
// T.C = O(m*n); visit each cell only once
// S.C = O(1); no extra space
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // define boundries
        int top = 0, bottom = matrix.length-1, left = 0, right = matrix[0].length-1;
        List<Integer> res = new ArrayList<>();

        // iterate till we are not out of boundries
        while(top<=bottom && left<=right){
            // traverse top row and increment the top boundry at last
            for(int j=left; j<=right; j++) res.add(matrix[top][j]);
            top++;
            // traverse right column and decrement the right boundry at last
            for(int i=top; i<=bottom; i++) res.add(matrix[i][right]);
            right--;
            // traverse bottom row if bottom boundry is still valid 
            if(top<=bottom){
                // traverse bottom row and decrement the bottom boundry at last
                for(int j=right; j>=left; j--) res.add(matrix[bottom][j]);
                bottom--;
            }
            // traverse left column if the left boundry is still valid
            if(left<=right){
                // traverse left column and increment the left boundry at last
                for(int i=bottom; i>=top; i--) res.add(matrix[i][left]);
                left++;
            }
        }
        
        return res;
    }
}

// Recursive approach
// T.C = O(m*n); visit each cell only once
// S.C = O(m*n); extra space for tracking visited cells
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // matrix to keep track of visited cells
        int[][] visited = new int[matrix.length][matrix[0].length];
        List<Integer> res = new ArrayList<>();
        // matrix to traverse the elements in the given direction
        int[][] dir = {
            {0,1}, // Right
            {1,0}, // Down
            {0,-1}, // Left
            {-1,0} // Top
        };
        // start with matrix[0][0] element in the right direction
        nextSpiralElement(res, 0, 0, 0, visited, dir, matrix);
        return res;
    }
    private void nextSpiralElement(List<Integer> res, int i, int j, int direction, int[][] visited, int[][] dir, int[][] matrix){
        // return if current element is out of bounds or visited
        if(i>=matrix.length || j<0 || i<0 || j>=matrix[0].length || visited[i][j]==1) return;
        // mark the current element as visited and add it to the result
        visited[i][j]=1;
        res.add(matrix[i][j]);
        // calculate the row and col of next element in the given direction
        int row = i+dir[direction][0];
        int col = j+dir[direction][1];
        // change the direction if the next element is out of bounds or visited
        if(row>=matrix.length || row<0 || col<0 || col>=matrix[0].length || visited[row][col]==1){
            direction = (direction+1)%4;
            nextSpiralElement(res,i+dir[direction][0],j+dir[direction][1],direction,visited,dir, matrix);
        }else{
            nextSpiralElement(res,row,col,direction,visited,dir, matrix);
        }
    }
}