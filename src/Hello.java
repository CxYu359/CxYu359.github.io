import java.util.Date;
import java.util.Scanner;

public class Hello {
    public static void main(String[] args){

        Matrix mat2 = new Matrix();
        //double[][] arr1 = {{1, 2, 3, 0}, {4, 5, 6, -3}, {7, 8, 9, -1}, {2, 5, 9, 8}};
        double[][] arr1 = {{1, 2, 3, 0, 1000}, {4, 5, 6, -3, 30}, {7, 8, 9, -1, 6.9}, {2, 5, 9, 8, -3.333}, {0, 2, 99, 66, 8888}};
        //double[][] arr1 = {{1, 0}, {4, 5}};
        Matrix mat1 = new Matrix(arr1);
        /*
        mat1.printMatrix();
        System.out.println("=================");
        mat1.getRes(1,1).printMatrix();
        System.out.println("=================");
        System.out.println(mat1.getElement(1,1));
        System.out.println("=================");
        System.out.println(mat1.det());
        */
        //System.out.println(mat1.isSquare);

    }
}

/**
 * 这是一个矩阵
 */
class Matrix{
    boolean isSquare;//是否为方阵
    int row;//行数
    int col;//列数
    double[][] mat;//矩阵



    //构造方法,二维数组初始化
    public Matrix(double[][] input){
        this.row = input.length;
        this.col = input[0].length;
        this.isSquare = this.row == this.col;
        this.mat = new double[this.row][this.col];
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                this.mat[i][j] = input[i][j];
            }
        }
    }
    public  Matrix(){}

    //取元素 a_ij
    public double getElement(int i, int j){
        return this.mat[i-1][j-1];
    }

    //显示矩阵
    public void printMatrix(){
        for (int i = 0; i < this.row; i++){
            for (int j = 0; j < this.col; j++){
                System.out.print(this.mat[i][j] + "\t\t");
            }
            System.out.println("");
        }
    }

    //获得余子式M_ij
    public Matrix getRes(int i, int j){
        Matrix res;
        //当矩阵为方阵且ij不越界
        if(this.isSquare && i <= this.row && i >= 1 && j <= this.col && j >= 1){
            double[][] resMat = new double[this.row - 1][this.col - 1];//二维数组resMat暂存余子式
            for (int m = 0; m < this.row ; m++){
                for (int n = 0; n < this.col ; n++){
                    if (m == i - 1 || n == j - 1){
                        continue;
                    }else if (m < i - 1 && n < j - 1){
                        resMat[m][n] = this.mat[m][n];
                    }else if (m < i - 1 && n > j - 1){
                        resMat[m][n - 1] = this.mat[m][n];
                    }else if (m > i - 1 && n < j - 1){
                        resMat[m - 1][n] = this.mat[m][n];
                    }else if (m > i - 1 && n > j - 1){
                        resMat[m - 1][n - 1] = this.mat[m][n];
                    }
                }
            }
            res = new Matrix(resMat);
        }else {
            double[][] resMat = {{0}};
            res = new Matrix(resMat);

        }
        return res;
    }

    //计算行列式
    public double det(){
        double ans = 0;
        if(this.isSquare){
            if(this.row == 1){
                ans = this.getElement(1, 1);
            }else {
                for (int m = 0; m < this.col; m++){
                    ans += Math.pow(-1, m) *this.getElement(1, m+1) * this.getRes(1, m+1).det();
                }
            }

        }
        return ans;
    }
}