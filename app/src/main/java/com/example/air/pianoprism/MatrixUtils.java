package com.example.air.pianoprism;

import android.util.ArrayMap;
import android.util.Log;

import com.android.internal.util.Predicate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;

import cern.colt.function.DoubleDoubleFunction;
import cern.colt.list.BooleanArrayList;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;

//import cern.colt.function.DoubleDoubleFunction;
//import cern.colt.matrix.DoubleMatrix1D;
//import cern.colt.matrix.impl.DenseDoubleMatrix2D;

/**
 * Created by Almas on 7/10/2015.
 */
public class MatrixUtils<T> {
/*
    private final Constructor<? extends T> ctor;


    public MatrixUtils(Class<? extends T> impl) throws NoSuchMethodException {
        this.ctor = impl.getConstructor();
    }*/


    public static enum NoteFrequencies {
        C0	(16.35), // 0
        Csharp0 	(17.32), //1
        D0	(18.35), // 2
        Dsharp0 (19.45), // 3
        E0	(20.60), // 4
        F0	(21.83), // 5
        Fsharp0 (23.12), // 6
        G0	(24.50), // 7
        Gsharp0	(25.96), //8
        A0	(27.50), // 9
        Asharp0 (29.14), // 10
        B0	(30.87), // 11
        C1	(32.70), // 12
        Csharp1 (34.65), // 13
        D1	(36.71), // 14
        Dsharp1 (38.89), // 15
        E1	(41.20), // 16
        F1	(43.65), // 17
        Fsharp1(46.25), // 18
        G1	(49.00), // 19
        Gsharp1	(51.91), // 20
        A1	(55.00), // 21
        Asharp1 	(58.27), // 22
        B1	(61.74), // 23
        C2	(65.41), // 24
        Csharp2  	(69.30), // 25
        D2	(73.42), // 26
        Dsharp2(77.78), // 27
        E2	(82.41), // 28
        F2	(87.31), // 29
        Fsharp2(92.50), // 30
        G2	(98.00), // 31
        Gsharp2(103.83), // 32
        A2	(110.00), // 33
        Asharp2(116.54), // 34
        B2	(123.47),  // 35
        C3	(130.81), // 36
        Csharp3(138.59), // 37
        D3	(146.83), // 38
        Dsharp3(155.56), // 39
        E3	(164.81), // 40
        F3	(174.61), // 41
        Fsharp3(185.00), // 42
        G3	(196.00), // 43
        Gsharp3(207.65), // 44
        A3	(220.00), // 45
        Asharp3(233.08), // 46
        B3	(246.94), // 47
        C4	(261.63), // 48
        Csharp4(277.18), // 49
        D4	(293.66), // 50
        Dsharp4(311.13), // 51
        E4	(329.63),  // 52
        F4	(349.23), // 53
        Fsharp4(369.99), // 54
        G4	(392.00), // 55
        Gsharp4(415.30), // 56
        A4	(440.00), // 57
        Asharp4(466.16), // 58
        B4	(493.88),  // 59
        C5	(523.25), // 60
        Csharp5(554.37), // 61
        D5	(587.33), // 62
        Dsharp5(622.25), // 63
        E5	(659.25), // 64
        F5	(698.46), // 65
        Fsharp5(739.99), // 66
        G5	(783.99),  // 67
        Gsharp5(830.61), // 68
        A5	(880.00), // 69
        Asharp5(932.33), // 70
        B5	(987.77), // 71
        C6	(1046.50), // 72
        Csharp6(1108.73), // 73
        D6	(1174.66), // 74
        Dsharp6(1244.51), // 75
        E6	(1318.51), // 76
        F6	(1396.91),  // 77
        Fsharp6(1479.98), // 78
        G6	(1567.98),  // 79
        Gsharp6 	(1661.22), // 80
        A6	(1760.00), // 81
        Asharp6 	(1864.66), // 82
        B6	(1975.53), // 83
        C7	(2093.00), // 84
        Csharp7 	(2217.46), // 85
        D7	(2349.32), // 86
        Dsharp7 	(2489.02), // 87
        E7	(2637.02), // 88
        F7	(2793.83), // 89
        Fsharp7 	(2959.96), // 90
        G7	(3135.96), // 91
        Gsharp7 	(3322.44), // 92
        A7	(3520.00),        // 93
        Asharp7 	(3729.31), // 94
        B7	(3951.07), // 95
        C8	(4186.01), // 96
        Csharp8	(4434.92), // 97
        D8	(4698.63), // 98
        Dsharp8	(4978.03), // 99
        E8	(5274.04), // 100
        F8	(5587.65), // 101
        Fsharp8 	(5919.91), // 102
        G8	(6271.93),        // 103
        Gsharp8 	(6644.88), // 104
        A8	(7040.00),         // 105
        Asharp8 	(7458.62), // 106
        B8	(7902.13);          // 107

        private final double freq;

        NoteFrequencies(double freq) {
            this.freq =  freq;
        }


        private double getFreq() { return freq; }

    }

    public static ArrayDeque<Integer> find(double[] arr) {
        ArrayDeque<Integer> result = new ArrayDeque<Integer>();

        for (int i = 0; i< arr.length; i++) {
            if (arr[i] != 0 ){
                result.add(i);
            }
        }
        return result;
    }


    public static ArrayDeque<Integer[]>  find(double[][] arr) {
        ArrayDeque<Integer[]> result = new ArrayDeque<Integer[]>();

        for (int i = 0; i< arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] !=0 ){
                    result.add(new Integer[]{i,j});
                }
            }
        }

        return result;
    }

    public static ArrayDeque<Integer> find(boolean[] arr) {
        ArrayDeque<Integer> result = new ArrayDeque<Integer>();

        for (int i = 0; i< arr.length; i++) {
            if (arr[i]){
                result.add(i);
            }
        }

        return result;
    }

    public static ArrayDeque<Integer[]>  find(boolean[][] arr) {
        ArrayDeque<Integer[]> result = new ArrayDeque<Integer[]>();

        for (int i = 0; i< arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j]){
                    result.add(new Integer[]{i,j});
                }
            }
        }

        return result;
    }

    public DenseDoubleMatrix2D transposeOf1D (DoubleMatrix1D arr) {
        DenseDoubleMatrix2D transpose = new DenseDoubleMatrix2D(arr.size(),1);

        for (int i = 0; i < arr.size(); i++) {
            transpose.setQuick(i, 0, arr.getQuick(i));
        }

        return transpose;
    }

    public ArrayList<T> toArrayList(T[] arr) {
        ArrayList<T> result = new ArrayList<T>();

        for (int i = 0; i < arr.length; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public double[] castTodouble(Double[] arr){
        double[] result = new double[arr.length];
        int i = 0;
        for (Double o: arr){
            result[i] = (double) o;
            i++;
        }
        return result;
    }



    public void roundDoubleArr(double[] arr) {

        for (int i = 0; i < arr.length; i++) {

            BigDecimal b = new BigDecimal(arr[i]);
            b = b.setScale(2, RoundingMode.CEILING);

            arr[i] = b.doubleValue();
        }
    }

    public Double[] castToDouble(double[] arr){
        Double[] result = new Double[arr.length];
       // int i = 0;
        /*for (double o: arr){
            result[i] = (Double) o;
            i++;
        }*/


        for (int i = 0; i < arr.length; i++) {

            BigDecimal b = new BigDecimal(arr[i]);
            b = b.setScale(2, RoundingMode.CEILING);

            result[i] = b.doubleValue();
        }
        return result;
    }




    public Double[][] castToDouble(double[][] arr){
        Double[][] result = new Double[arr.length][arr[0].length];
        int i = 0;
        for (double[] o: arr){
            for (int j = 0; j < arr[0].length; j++) {

                BigDecimal b = new BigDecimal(o[j]);
                b = b.setScale(2, RoundingMode.CEILING);

                result[i][j] = b.doubleValue();
            }
            i++;
        }
        return result;
    }

    public boolean[] predicateMatrix (double[] arr, Predicate<Double> tester) {
        boolean [] result = new boolean[arr.length];
        Arrays.fill(result, false);

        for (int i = 0; i < arr.length; i++) {
            if (tester.apply(arr[i])) {
                result[i] = true;
            }
        }

        return result;
    }


    public boolean[][] fillBoolArray(boolean[][] arr, boolean val) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = val;
            }
        }

        return arr;
    }

    public boolean[][] predicateMatrix (double[][] arr, Predicate<Double> tester) {
        boolean [][] result = new boolean[arr.length][arr[0].length];

        result = fillBoolArray(result, false);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (tester.apply(arr[i][j])) {
                    result[i][j] = true;
                }
            }
        }

        return result;
    }



    public ArrayList<Double> toList (double[] arr) {
        ArrayList<Double> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            res.add(arr[i]);
        }

        return res;
    }



    public ArrayList<Integer> toList (int[] arr) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            res.add(arr[i]);
        }

        return res;
    }

    public double[] unique (double[] arr, ArrayDeque<Integer> segIdx) {


        roundDoubleArr(arr);

        ArrayList<Double> list = toList(arr);


        System.out.println("LIST SIZE: " + list.size());


        TreeSet<Double> set = new TreeSet<>(list);

        Log.d("set", "len: " + set.size());


        Object[] array = set.toArray();

        double[] result = new double[array.length];




        for (int i = 0; i < array.length; i++) {
            result[i] = (double) array[i];
            if (segIdx != null) {
                for (int j = i; j < arr.length; j++) {
                    if (arr[j] == result[i]) {
                        segIdx.add(j);
                        break;
                    }
                }
            }
        }


      //  Arrays.sort(result);


        return result;
    }




    public int[] unique (int[] arr, ArrayDeque<Integer> segIdx, int[] ic) {



        ArrayList<Integer> list = toList(arr);


        System.out.println("LIST SIZE: " + list.size());


        TreeSet<Integer> set = new TreeSet<>(list);

        Log.d("set", "len: " + set.size());


        Object[] array = set.toArray();

        int[] result = new int[array.length];



        // arr(segIdx) = result
        for (int i = 0; i < array.length; i++) {
            result[i] = (int) array[i];
            if (segIdx != null) {
                for (int j = i; j < arr.length; j++) {
                    if (arr[j] == result[i]) {
                        segIdx.add(j);
                        break;
                    }
                }
            }
        }


        // result(ic) = arr
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (arr[i] == result[j]) {
                    ic[i] = j;
                }
            }
        }

        return result;
    }

//    DoubleDoubleFunction identity = new DoubleDoubleFunction() {
 //       public double apply(double a, double b) { return b; }
  //  };

    public String printArray(T[] arr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append("[");
        for (T e: arr){
            sb.append(e);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
            i++;
        }
        sb.append("]");

        return sb.toString();
    }


    public static String printArray(double[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }


    public static String printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++){
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public String printMatrix(T arr[][]) {
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                toWrite.append(arr[i][j] + " ");
                if (j == arr[0].length-1) {
                    toWrite.append('\n');
                }
            }
        }
        return toWrite.toString();
    }


    public String printBooleanMatrix(boolean[][] arr) {
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                String digit =  arr[i][j] ? "1": "0";
                toWrite.append(digit + " ");
                if (j == arr[0].length-1) {
                    toWrite.append('\n');
                }
            }
        }
        return toWrite.toString();
    }



    public static boolean[] any(double[][] arr, int dim) {
        boolean[] res;


        if (dim == 1) {
            res = new boolean[arr.length];
            Arrays.fill(res, false);
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (arr[i][j] != 0) {
                        res[i] = true;
                        break;
                    }
                }
            }
        } else {
            res = new boolean[arr[0].length];
            Arrays.fill(res, false);
            for (int j = 0; j < arr[0].length; j++) {
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i][j] != 0) {
                        res[j] = true;
                        break;
                    }
                }
            }
        }

        return res;
    }


    public static boolean any(double[] arr) {
        boolean res = false;

        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] != 0) {
                res = true;
                break;
            }
        }

        return res;
    }

    public static boolean any(boolean[] arr) {
        boolean res = false;

        for (int i = 0;  i < arr.length; i++) {
            if (arr[i]) {
                res = true;
                break;
            }
        }

        return res;
    }

    public static boolean any(int[] arr) {
        boolean res = false;

        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] != 0) {
                res = true;
                break;
            }
        }

        return res;
    }


    public static boolean[] _notBool (boolean[] arr) {
        boolean[] res = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = !arr[i];
        }
        return res;
    }





    public static int[] range(int n1, int n2) {
        int[] res = new int[n2 - n1 + 1];
        int j = 0;

        for (int i = n1; i <= n2; i++) {
            res[j] = i;
            j++;
        }

        return res;
    }


    public static double[] log_elemWise(double[] in) {
        double[] out = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] = Math.log(in[i]);
        }
        return out;
    }

    public static double[][] log_elemWise(double[][] in) {
        double[][] out = new double[in.length][in[0].length];

        for (int i = 0; i < in.length; i++) {
            for(int j = 0; j < in[0].length; j++) {
                out[i][j] = Math.log(in[i][j]);
            }
        }
        return out;
    }


    public static double[] mul_elemWise(double[] in, double factor) {
        double[] out = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] = in[i]*factor;
        }
        return out;
    }

    public static double[][] mul_elemWise(double[][] in, double factor) {

        double[][] out = new double[in.length][in[0].length];

        for (int i = 0; i < in.length; i++) {
            for(int j = 0; j < in[0].length; j++) {
                out[i][j] = in[i][j]*factor;
            }
        }
        return out;
    }

    public static double[][] mul_elemWise(double[][] x, double[][] y) throws DimensionsDoNotCorrespondException{
        if (x.length != y.length | x[0].length != y[0].length)
            throw new DimensionsDoNotCorrespondException();

        double[][] res = new double[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                res[i][j] = x[i][j] * y[i][j];
            }
        }

        return res;
    }


    public static double[] mul_elemWiseIntToDouble(int[] in, double factor) {
        double[] out = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] = in[i]*factor;
        }
        return out;
    }


    public static int[] mul_elemWise(int[] in, int factor) {
        int[] out = new int[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] = in[i]*factor;
        }
        return out;
    }



    public static double[][] div_elemWise(double[][] x, double[][] y) throws DimensionsDoNotCorrespondException{
        if (x.length != y.length | x[0].length != y[0].length)
             throw new DimensionsDoNotCorrespondException();

        double[][] res = new double[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                res[i][j] = x[i][j] / y[i][j];
            }
        }

        return res;
    }

    /*
     *
     * x - dim 1
     * y - dim 2
     */
    public static double[][] repmat(double[][] in,  int numToRepeatX, int numToRepeatY) {
        int inX = in.length;
        int inY = in[0].length;
        int dimX = inX*numToRepeatX;
        int dimY = inY*numToRepeatY;
        double[][] res = new double[dimX][dimY];

        for (int i = 0; i < dimX; i++ ) {
            for (int j = 0; j < dimY; j++) {
                res[i][j] = in[i % inX][j % inY];
            }
        }

        return res;
    }


    public static double[][] repmat(double[] in,  int numToRepeatX, int numToRepeatY) {
        int inX = 1;
        int inY = in.length;
        int dimX = inX*numToRepeatX;
        int dimY = inY* numToRepeatY;
        double[][] res = new double[dimX][dimY];

        for (int i = 0; i < dimX; i++ ) {
            for (int j = 0; j < dimY; j++) {
                res[i][j] = in[j % inY];
            }
        }

        return res;
    }



    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

/// TO TEST
    public static double[] power_elemWise(double[] arr, int n) {
        double[] res = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Math.pow(arr[i], n);
        }
        return res;
    }


    public static double[][] power_elemWise(double[][] arr, int n) {
        double[][] res = new double[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res[i][j] = Math.pow(arr[i][j], n);

            }
        }
        return res;
    }


    public static double mean (double[] in) {
        double res = 0;

        for (int i = 0; i < in.length; i++) {
            res += in[i];
        }

        res /= in.length;

        return res;
    }




    public static double[] hammingWin(int fftLen, String type) {
        double[] res = new double[fftLen];
        switch(type) {
            case "periodic": {
                int  n = fftLen + 1;
                double[] resTemp = new double[n];
                for(int i = 0; i < n; i++){
                    resTemp[i] = (float) (( 0.53836 - ( 0.46164 * Math.cos( 2*Math.PI * (double)i  / (double)( n - 1 ) ) ) ) );
                }

                for (int i = 0; i < fftLen; i++) {
                    res[i] = resTemp[i];
                }
            }
            case "none": {
                for(int i = 0; i < fftLen; i++){
                    res[i] = (float) (( 0.53836 - ( 0.46164 * Math.cos( 2*Math.PI * (double)i  / (double)( fftLen - 1 ) ) ) ) );
                }

            }

        }
        return res;
    }

    // simple transposition for 1d array
    public static double[][] transpose(double[] in) {
        double[][] res = new double[in.length][1];
        for (int i = 0; i < in.length; i++) {
            res[i][0] = in[i];
        }

        return res;
    }

    public static double[][] transpose(double[][] in) {
        double[][] res = new double[in[0].length][in.length];
        for (int j = 0; j < in[0].length; j++) {
            for (int i = 0; i < in.length; i++) {
                res[j][i] = in[i][j];
            }
        }

        return res;
    }

    public static double[] minusMatrix(double[] in1, double[] in2) throws DimensionsDoNotCorrespondException{
        if (in1.length != in2.length )
            throw new DimensionsDoNotCorrespondException();

        double[] res = new double[in1.length];

        for (int i = 0; i < in1.length; i++) {
                res[i] = in1[i] - in2[i];

        }

        return res;
    }

    public static double[][] minusMatrix(double[][] in1, double[][] in2) throws DimensionsDoNotCorrespondException {
        if (in1.length != in2.length | in1[0].length != in2[0].length)
            throw new DimensionsDoNotCorrespondException();

        double[][] res = new double[in1.length][in1[0].length];

        for (int i = 0; i < in1.length; i++) {
            for (int j = 0; j < in1[0].length;j++) {
                res[i][j] = in1[i][j] - in2[i][j];
            }
        }

        return res;
    }


    public static double[][] minusMatrix(double[][] in1, double in2) {
        double[][] res = new double[in1.length][in1[0].length];

        for (int i = 0; i < in1.length; i++) {
            for (int j = 0; j < in1[0].length;j++) {
                res[i][j] = in1[i][j] - in2;
            }
        }

        return res;
    }

    public static double[] minusMatrix(double[] in1, double in2) {
        double[] res = new double[in1.length];

        for (int i = 0; i < in1.length; i++) {

                res[i] = in1[i] - in2;
        }

        return res;
    }


    public static double[][] minusMatrix(int[][] in1, double in2) {
        double[][] res = new double[in1.length][in1[0].length];

        for (int i = 0; i < in1.length; i++) {
            for (int j = 0; j < in1[0].length;j++) {
                res[i][j] = in1[i][j] - in2;
            }
        }

        return res;
    }

    public static double[] minusMatrix(int[] in1, double in2) {
        double[] res = new double[in1.length];

        for (int i = 0; i < in1.length; i++) {
                res[i] = in1[i] - in2;

        }

        return res;
    }


    public static double[][] plusMatrix(double[][] in1, double[][] in2) throws  DimensionsDoNotCorrespondException {
        if (in1.length != in2.length | in1[0].length != in2[0].length)
            throw new DimensionsDoNotCorrespondException();



        double[][] res = new double[in1.length][in1[0].length];

        for (int i = 0; i < in1.length; i++) {
            for (int j = 0; j < in1[0].length;j++) {
                res[i][j] = in1[i][j] + in2[i][j];
            }
        }

        return res;
    }

    public static double[][] plusMatrix(double[][] in1, double in2) {
        double[][] res = new double[in1.length][in1[0].length];

        for (int i = 0; i < in1.length; i++) {
            for (int j = 0; j < in1[0].length;j++) {
                res[i][j] = in1[i][j] + in2;
            }
        }

        return res;
    }

    public static double[] plusMatrix(double[] in1, double in2) {
        double[] res = new double[in1.length];

        for (int i = 0; i < in1.length; i++) {
               res[i] = in1[i] + in2;
        }

        return res;
    }

    public static double[] plusMatrix(double[] in1, double[] in2) {
        double[] res = new double[in1.length];

        for (int i = 0; i < in1.length; i++) {
            res[i] = in1[i] + in2[i];
        }

        return res;
    }


    public static double[] toDoubleArray(int[] arr) {
        double[] res = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }

        return res;
    }


    public static double[] remainder(double[] in, double divisor) {
        double[] res = new double[in.length];
        for (int i = 0; i < in.length; i++) {
            res[i] =  in[i] % divisor;
        }
        return res;
    }

    public static int[] remainder(int[] in, int divisor) {
        int[] res = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            res[i] = in[i] % divisor;
        }
        return res;
    }

    public static double[] remainder(double[] in, int divisor) {
        double[] res = new double[in.length];
        for (int i = 0; i < in.length; i++) {
            res[i] = in[i] % divisor;
        }
        return res;
    }

    public static double[][] remainder(double[][] in, double divisor) {
        double[][] res = new double[in.length][in[0].length];
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                res[i][j] = in[i][j] %  divisor;
            }
        }
        return res;
    }


    public static double[][] remainder(double[][] in, double[][] divisor) throws DimensionsDoNotCorrespondException {
        if (in.length != divisor.length | in[0].length != divisor[0].length)
            throw new DimensionsDoNotCorrespondException();

        double[][] res = new double[in.length][in[0].length];
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                res[i][j] = in[i][j] % divisor[i][j];
            }
        }
        return res;
    }


    public static int[][] remainder(double[][] in, double[] divisor, int dim) {
        int[][] res = new int[in.length][in[0].length];

        switch (dim) {
            case 1: {
                for (int i = 0; i < in.length; i++) {
                    for (int j = 0; j < in[0].length; j++) {
                        res[i][j] = ((int) in[i][j]) / (int) divisor[j];
                    }
                }
            }
            case 2: {
                for (int i = 0; i < in.length; i++) {
                    for (int j = 0; j < in[0].length; j++) {
                        res[i][j] = ((int) in[i][j]) / (int) divisor[i];
                    }
                }
            }

        }
        return res;
    }


    public static double[] maximum(double a, double[] b) {
        double[] res = new double[b.length];

        for (int i = 0; i < b.length; i++) {
            res[i] = a > b[i] ? a: b[i];
        }
        return res;
    }


    public static double[][] maximum(double a, double[][] b) {
        double[][] res = new double[b.length][b[0].length];

        for (int i = 0; i < b.length; i++) {
           for (int j = 0; j < b[0].length; j++) {
               res[i][j] = a > b[i][j] ? a: b[i][j];

           }
        }
        return res;
    }

    /*  inclusive from begin to end */
    public static double[] sliceOfArray(double[] in, int x0, int x1) {

        if (x0 < 0 | x1 >= in.length  ) {
            throw new ArrayIndexOutOfBoundsException();
        }

        double[] res = new double[x1 - x0 + 1];
        int j = 0;
        for (int i = x0; i <= x1; i++) {
            in[j] = in[i];
            j++;
        }

        return res;
    }

    public static double[][] sliceOf2dArray(double[][] in, int x0, int x1, int y0, int y1) {

        if (x0 < 0 | x1 >= in.length | y0 < 0 | y1 >= in[0].length ) {
            throw new ArrayIndexOutOfBoundsException();
        }

        double[][] res = new double[x1 - x0 + 1][y1 - y0 + 1];
        int j = 0;
        int k = 0;
        for (int i = x0; i <= x1; i++) {
            for (int ii = y0; ii < y1; ii++) {
                res[j][k] = in[i][ii];
                k++;
            }
            j++;
        }

        return res;
    }


    public static double[] sliceOf2dArray(double[][] in, int x0, int x1, int y0, double dim) {

        if (x0 < 0 | x1 >= in.length | y0 < 0  ) {
            throw new ArrayIndexOutOfBoundsException();
        }

        double[] res = new double[x1 - x0 + 1];
        int j = 0;
        int k = 0;
        for (int i = x0; i <= x1; i++) {
                res[j] = in[i][y0];
            j++;
        }

        return res;
    }

    public static double[] sliceOf2dArray(double[][] in, int x0, int y0, int y1, boolean b) {

        if (x0 < 0 |  y0 < 0 | y1 >= in[0].length ) {
            throw new ArrayIndexOutOfBoundsException();
        }

        double[] res = new double[y1 - y0 + 1];
        int j = 0;
        int k = 0;
            for (int ii = y0; ii < y1; ii++) {
                res[k] = in[x0][ii];
                k++;
            }
        return res;
    }

    public static double[][] sliceOf2dArray(double[][] in, int x0, int x1,int[] y_inxs) {

        if (x0 < 0 | x1 > in.length |  y_inxs.length >= in[0].length ) {
            throw new ArrayIndexOutOfBoundsException();
        }

        double[][] res = new double[x1 - x0][y_inxs.length];
        int j = 0;
        int k = 0;

        for (int i = x0; i < x1; i++) {
            for (int ii = 0; ii < y_inxs.length; ii++) {
                res[i][ii] = in[i][y_inxs[ii]];
            }
        }

        return res;
    }


    public static double[] sliceOfArray(double[] orig, int[] inxs) {
        double[] res = new double[inxs.length];

        for (int i = 0; i < inxs.length; i++) {
            res[i] = orig[inxs[i]];
        }

        return res;
    }




    public static double[][] exp (double[][] in) {
        double[][] res =  new double[in.length][in[0].length];


        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                res[i][j] = Math.exp(in[i][j]);
            }
        }

        return res;

    }



    public static double[] zeroPad(double[] input, int times) {

        double[] result = new double[input.length + input.length*times];

        for (int i = 0; i < result.length; i++) {
            if (i < input.length) {
                result[i] = input[i];
            } else
            {
                result[i] = 0;
            }
        }


        return result;
    }



    public static int[] get_Mapping_Of_Notes_To_FFT_bins (int length, int buf_len, double res, Note[] notes) {
        int[] arr = new int[length];


        int j = 0;

        for (int i = 0; i < buf_len; i++) {

            while (notes[j].left_bound < (i+1)*res &&  notes[j].left_bound <= (i)*res)
            {
                if (notes[j].right_bound >= i * res)
                    arr[j] = i;
                if (j < notes.length - 1)
                    j++;
                else
                    break;

            }

        }



        return arr;
    }


    public static Note[] fillNotes() {

        NoteFrequencies[] vals = NoteFrequencies.values();
        int length = vals.length;
        Note[] notes = new Note[length];


        for (int i = 0; i < length; i++) {
            if (i > 0) {
                double dist = Math.sqrt(notes[i-1].freq*vals[i].getFreq());
                notes[i] = new Note(vals[i].name(), vals[i].getFreq(),  dist, 2*vals[i].getFreq() - dist);
            } else {
                double dist = Math.abs(vals[i].getFreq() - Math.sqrt(vals[i + 1].getFreq()*vals[i].getFreq()));
                notes[i] = new Note(vals[i].name(), vals[i].getFreq(), vals[i].getFreq() - dist, vals[i].getFreq() + dist);
            }
        }


        return notes;
    }


    public static double[] sum(double[][] in) {
        double[] res = new double[in[0].length];
        Arrays.fill(res, 0);
        for (int i = 0; i < in[0].length; i++) {
            for (int j = 0; j < in.length; j++) {
                res[i] += in[j][i];
            }
        }

        return res;
    }


    public static double sum(double[] in) {
        double res = 0;
        for (int i = 0; i < in.length; i++) {
                res += in[i];
        }

        return res;
    }



    public static double[] abs(double[] in) {
        double[] res = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            res[i] = Math.abs(in[i]);
        }

        return res;
    }

    public static double[][] abs(double[][] in) {
        double[][] res = new double[in.length][in[0].length];

        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[0].length; j++) {
                res[i][j] = Math.abs(in[i][j]);
            }
        }

        return res;
    }


    public static double[] mult_matrix(double[][] in1, double[] in2) throws DimensionsDoNotCorrespondException{
        if (in1[0].length != in2.length) {
            throw new DimensionsDoNotCorrespondException();
        }

        double[] res = new double[in1.length];
        Arrays.fill(res, 0);

        for (int i = 0; i < in1.length; i++) {
            for (int j = 0; j < in2.length; j++) {
                res[i] += in1[i][j]*in2[j];
            }
        }

        return res;
    }



    public static  double[][] mult_matrix(double[][] in1, double[][] in2) throws DimensionsDoNotCorrespondException{
        if (in1[0].length != in2.length) {
            throw new DimensionsDoNotCorrespondException();
        }

        double[][] res = new double[in1.length][in2[0].length];

        for (double[] e: res)
            Arrays.fill(e, 0);


        for (int i = 0; i < in1.length; i++) {
            for (int j = 0; j < in2[0].length; j++) {
                for (int k = 0; k < in2.length; k++ ) {
                    res[i][j] += in1[i][k]*in2[k][j];
                }
            }
        }

        return res;
    }


    public static double[] mult_elemWise(double[] in1, double[] in2) {
        double[] res = new double[in1.length];
        for (int i = 0; i < in1.length; i++) {
            res[i] = in1[i] * in2[i];
        }

        return res;
    }

    public static double[] sqrt_elemWise(double[] in) {
        double[] res = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            res[i] = Math.sqrt(in[i]);
        }

        return res;
    }

    public static boolean approxEqual(double x, double y) {
        boolean res = false;
        if (Math.abs((x - y)/x) < Math.pow(10, -10))
            res = true;

        return  res;
    }



    public static double[] retNonZeroElems(double[] in) {
        int count = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] != 0) {
                count++;
            }
        }

        double [] res = new double[count];
        int j = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] != 0) {
                res[j] = in[i];
                j++;
            }

        }

        return res;
    }


    public static boolean[] nonZeroInxs(double[] in) {
        boolean[] res = new boolean[in.length];

        for (int i = 0; i < in.length; i++ ){
            if (in[i] > 0) {
                res[i] = true;
            } else
                res[i] = false;

        }


        return res;
    }

    public static int[] nonZeroInxsInt(double[] in) {
        int[] res = new int[in.length];

        for (int i = 0; i < in.length; i++ ){
            if (in[i] > 0) {
                res[i] = 1;
            } else
                res[i] = 0;

        }


        return res;
    }



    public static double[] sum(double[][] in, int dim) {
        double[] res;

        if (dim == 1) {
            res = new double[in.length];
            Arrays.fill(res, 0.0);

            for (int i = 0; i < in.length; i++) {
                for (int j = 0; j < in[0].length; j++) {
                    res[i] += in[i][j];
                }
            }


        } else {
            res = new double[in[0].length];
            Arrays.fill(res, 0.0);

            for (int j = 0; j < in[0].length; j++) {
                for (int i = 0; i < in.length; i++) {
                    res[j] += in[i][j];
                }
            }
        }


        return res;
    }

    public static void assign(double[][] toChange, int row0, int row1, int col0, int col1, double[][] in) {
        int count_i = 0;
        int count_j = 0;
        for (int i = row0; i < row1; i++) {
            for (int j = col0; j < col1; j++) {
                toChange[i][j] = in[count_i][count_j];
                count_j++;
            }
            count_i++;
        }

    }


    public static void assign(double[][] toChange, int row0, int row1, int[] inxs, double[][] in) {
        int count_i = 0;
        int count_j = 0;
        for (int i = row0; i < row1; i++) {
            for (int j = 0; j < inxs.length; j++) {
                toChange[i][inxs[j]] = in[count_i][count_j];
                count_j++;
            }
            count_i++;
        }

    }

    public static void assign(double[][] toChange, int row0,  int[] inxs, double[] in) {
        int count_j = 0;
            for (int j = 0; j < inxs.length; j++) {
                toChange[row0][inxs[j]] = in[count_j];
                count_j++;
            }

    }


    public static void assignCol(double[][] toChange, int row0, int row1, int col, double[] in) {
        int count_i = 0;
        for (int i = row0; i < row1; i++) {
            toChange[i][col] = in[count_i];
            count_i++;
        }

    }

    public static void assignRow(double[][] toChange, int col0, int col1, int row, double[] in) {
        int count_i = 0;
        for (int j = col0; j < col1; j++) {
            toChange[row][j] = in[count_i];
            count_i++;
        }

    }


    public static double[] acos(double[] in) {
        double[] res = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            res[i] = Math.acos(in[i]);
        }

        return res;
    }



    public static double[] concatArrays(double[] in1, double[] in2) {
        double[] res = new double[in1.length + in2.length];

        for (int i = 0; i < in1.length; i++) {
            res[i] = in1[i];
        }
        int i = 0;
        for (int j = in1.length; j < in1.length + in2.length; j++){
            res[j] = in2[i];
            i++;
        }
        return res;
    }

    /*
     redundant since there is slicing function?
     */
    public static double[] aggregateArray (double[] in1, int x0, int x1, double[] in2, int y0, int y1) {
        double[] res = new double[x1 - x0 + y1 - y0];

        int count_0 = 0;
        for (int i = x0; i < x1; i++) {
            res[count_0] = in1[i];
            count_0++;
        }

        for (int j = y0; j < y1; j++) {
            res[count_0] = in2[j];
            count_0++;
        }

        return res;
    }


    public static double[][] addDimensionToArray(double[] in1, double [] in2, int dim) {
        double[][] res;
        if (dim == 1) {
            res = new double[2][in1.length];

            System.arraycopy(res[0], 0, in1, 0, in1.length);
            System.arraycopy(res[1], 0, in2, 0, in2.length);
        } else {
            res = new double[in1.length][2];
            for (int i = 0; i < in1.length; i++) {
                for (int j = 0; j < 2; j++ ){
                    if (j == 0) {
                        res[i][j] = in1[i];
                    } else
                        res[i][j] = in2[i];
                }
            }
        }
        return res;
    }


    public static double[][] addDimensionToArray(double[][] in1, double [] in2, int dim) {
        double[][] res;
        if (dim == 1) {
            res = new double[in1.length + 1][in1.length];
            for (int i = 0; i < in1.length; i++) {
                System.arraycopy(res[i], 0, in1[i], 0, in1.length);
            }
            System.arraycopy(res[in1.length], 0, in2, 0, in2.length);
        } else {
            res = new double[in1.length][2];
            for (int i = 0; i < in1.length; i++) {
                for (int j = 0; j < in1[0].length + 1; j++ ){
                    if (j != in1[0].length) {
                        res[i][j] = in1[i][j];
                    } else
                        res[i][j] = in2[i];
                }
            }
        }
        return res;
    }

    public static double[][] addDimensionsToArray(double[][] in1, double[][] in2, int dim) {
        double[][] res;
        if (dim == 1) {
            res = new double[in1.length + in2.length][in1.length];
            for (int i = 0; i < in1.length; i++) {
                System.arraycopy(res[i], 0, in1[i], 0, in1.length);
            }
            int count = 0;
            for (int j = in1.length; j < in1.length + in2.length; j++) {
                System.arraycopy(res[j], 0, in2[count], 0, in2.length);
                count++;
            }
        } else {
            res = new double[in1.length][2];
            for (int i = 0; i < in1.length; i++) {
                for (int j = 0; j < in1[0].length + in2[0].length; j++ ){
                    if (j < in1[0].length) {
                        res[i][j] = in1[i][j];
                    } else
                        res[i][j] = in2[i][j];
                }
            }
        }
        return res;
    }



    public static class DimensionsDoNotCorrespondException extends Exception {
        public DimensionsDoNotCorrespondException() {
            super();
        }
    }


    public static double[][] randn(int n, int m) {
        double[][] res = new double[n][m];
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] =r.nextGaussian();
            }
        }

        return res;
    }


    public static double[] randn(int n) {
        double[] res = new double[n];
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            res[i] = r.nextGaussian();
        }

        return res;
    }


    public static double[] rand(int n) {
        double[] res = new double[n];
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            res[i] = r.nextDouble();
        }

        return res;
    }

    public static HashMap<Integer, ArrayList<Integer>> inxsThatSatisfyComparisonRow(double[][] in, double toCompare, int moreOrLess) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();


        for (int i = 0; i < in.length; i++) {

            for (int j = 0; j < in[0].length; j++) {
                if (j == 0) {
                    map.put(i, new ArrayList<Integer>());
                }

                if (moreOrLess == 1 ? in[i][j] < toCompare : in[i][j] > toCompare) {
                    map.get(i).add(j);
                }
            }
        }

        return map;
    }

    public static HashMap<Integer, ArrayList<Integer>> inxsThatSatisfyComparisonCol(double[][] in, double toCompare, int moreOrLess) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();


        for (int i = 0; i < in[0].length; i++) {

            for (int j = 0; j < in.length; j++) {
                if (j == 0) {
                    map.put(i, new ArrayList<Integer>());
                }

                if (moreOrLess == 1 ? in[j][i] < toCompare : in[j][i] > toCompare) {
                    map.get(i).add(j);
                }
            }
        }

        return map;
    }
}
