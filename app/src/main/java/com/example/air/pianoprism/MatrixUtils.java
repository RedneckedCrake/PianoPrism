package com.example.air.pianoprism;

import android.util.Log;

import com.android.internal.util.Predicate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
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


    public ArrayDeque<Integer> find(double[] arr) {
        ArrayDeque<Integer> result = new ArrayDeque<Integer>();

        for (int i = 0; i< arr.length; i++) {
            if (arr[i] != 0 ){
                result.add(i);
            }
        }
        return result;
    }


    public ArrayDeque<Integer[]>  find(double[][] arr) {
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

    public ArrayDeque<Integer> find(boolean[] arr) {
        ArrayDeque<Integer> result = new ArrayDeque<Integer>();

        for (int i = 0; i< arr.length; i++) {
            if (arr[i]){
                result.add(i);
            }
        }

        return result;
    }

    public ArrayDeque<Integer[]>  find(boolean[][] arr) {
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


    public static boolean[] _notBool (boolean[] arr) {
        boolean[] res = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = !arr[i];
        }
        return res;
    }


    public static int[] range(int n1, int n2) {
        int[] res = new int[n2 - n1];

        for (int i = n1; i < n2; n1++) {
            res[i] = i;
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


    public static double[] mul_elemWise(double[] in, double factor) {
        double[] out = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] *= factor;
        }
        return out;
    }


    public static double[] mul_elemWise(int[] in, double factor) {
        double[] out = new double[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] *= factor;
        }
        return out;
    }


    public static int[] mul_elemWise(int[] in, int factor) {
        int[] out = new int[in.length];

        for (int i = 0; i < in.length; i++) {
            out[i] *= factor;
        }
        return out;
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


    /// TODO: check for errors

    public static double[][] repmat(double[] in,  int numToRepeatX, int numToRepeatY) {
        int inX = in.length;
        int inY = 1;
        int dimX = inX*numToRepeatX;
        int dimY = 1* numToRepeatY;
        double[][] res = new double[dimX][dimY];

        for (int i = 0; i < dimX; i++ ) {
            for (int j = 0; j < dimY; j++) {
                res[i][j] = in[i % inX];
            }
        }

        return res;
    }
}
