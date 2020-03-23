package algorithms.dynamicprogramming;

// https://www.geeksforgeeks.org/maximum-path-sum-matrix/
public class MaximumPathSumMatrix {

    public static void main(String[] args) {
        int matrixRows = 3;
        int matrixColumns = 3;

        int[][] matrix = {{1, 2, 6}, {4, 5, 11}, {7, 8, 9}};
        int[][] memoMatrix = new int[matrixRows][matrixColumns];

        System.out.println(findMaxPathRecursively(0, 0, matrixRows, matrixColumns, matrix, memoMatrix));
        System.out.println(findMaxPathIteratively(matrixRows, matrixColumns, matrix));
    }

    public static int findMaxPathRecursively(int posX, int posY, int noOfMatrixRows, int noOfMatrixColumns, int[][] matrix, int[][] memoMatrix) {
        // in case one of positions is out out of bounds
        if (posX > noOfMatrixColumns - 1 || posY > noOfMatrixRows - 1) return 0;

        // fetch memoized result
        if (memoMatrix[posX][posY] != 0) return memoMatrix[posY][posX];

        int result = matrix[posX][posY] + Math.max(findMaxPathRecursively(posX + 1, posY, noOfMatrixRows, noOfMatrixColumns, matrix, memoMatrix),
                findMaxPathRecursively(posX, posY + 1, noOfMatrixRows, noOfMatrixColumns, matrix, memoMatrix));
        memoMatrix[posX][posY] = result;

        return result;
    }

    public static int findMaxPathIteratively(int noOfMatrixRows, int noOfMatrixColumns, int[][] matrix) {
        int[][] memoMatrix = new int[noOfMatrixRows][noOfMatrixColumns];
        for (int rowIndex = 0; rowIndex < noOfMatrixRows; rowIndex++) {
            for (int columnIndex = 0; columnIndex < noOfMatrixColumns; columnIndex++) {
                int memoIndex = matrix[rowIndex][columnIndex];

                if (rowIndex != 0 && columnIndex != 0) {
                    memoIndex += Math.max(memoMatrix[rowIndex - 1][columnIndex], memoMatrix[rowIndex][columnIndex - 1]);
                } else if (rowIndex != 0) {
                    memoIndex += memoMatrix[rowIndex - 1][columnIndex];
                } else if (columnIndex != 0) {
                    memoIndex += memoMatrix[rowIndex][columnIndex -1];
                }

                memoMatrix[rowIndex][columnIndex] = memoIndex;
            }
        }
        return memoMatrix[noOfMatrixRows - 1][noOfMatrixColumns - 1];
    }
}
