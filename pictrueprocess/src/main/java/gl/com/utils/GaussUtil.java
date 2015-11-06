package gl.com.utils;


import android.util.Log;

/**
 * Created by mac on 15-11-4.
 * 高斯模糊
 * 参考资料：http://www.ruanyifeng.com/blog/2012/11/gaussian_blur.html
 *         http://my.oschina.net/tonywolf/blog/64896
 */
public class GaussUtil {
    public static void main(String[] args){
        double[] a = getGauss(1.5,1);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]+";");
        }
    }

    /**
     * 获取高斯矩阵 默认半径为1像素
     * @param k cgama
     * @param r 模糊半径 r为模糊半径，
     * @return 返回计算后的高斯矩阵
     */
    public static double[] getGauss(double k,int r){
        long startTime = System.currentTimeMillis();
        double[] gauss = new double[(int) Math.pow(1+2*r,2.0)];
        //cgame的平方的2呗
        double k22 = 2*k*k;
        double sigam22PI = (Math.PI * k22);
        //i 表示行，j表示列
        int index =  0;
        for (int i = -r; i < r+1; i++) {
            for (int j = -r; j < r+1 ; j++) {
                double xDistance = i * i ;
                double yDistance = j * j ;
                gauss[index] = Math.exp(-(xDistance + yDistance)/k22)/sigam22PI;
                index++;
            }
        }
        
        float sum = 0;
        for (int i = 0; i < gauss.length; i++) {
            sum += gauss[i];
        }

        for (int i = 0; i < gauss.length; i++) {

            gauss[i] = gauss[i]/sum;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("get gauss used time "+(endTime-startTime)+" ms");
        return gauss;
    }

    public float[][] get2DKernalData(int n, float sigma) {
        int size = 2*n +1;
        float sigma22 = 2*sigma*sigma;
        float sigma22PI = (float)Math.PI * sigma22;
        float[][] kernalData = new float[size][size];
        int row = 0;
        for(int i=-n; i<=n; i++) {
            int column = 0;
            for(int j=-n; j<=n; j++) {
                float xDistance = i*i;
                float yDistance = j*j;
                kernalData[row][column] = (float)Math.exp(-(xDistance + yDistance)/sigma22)/sigma22PI;
                column++;
            }
            row++;
        }

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                System.out.print("\t" + kernalData[i][j]);
            }
            System.out.println();
            System.out.println("\t ---------------------------");
        }
        return kernalData;
    }
}
