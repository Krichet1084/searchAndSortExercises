package searchAndSortPart2;

import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;

class User {
    private String username;
    private String password;
    private int[] temperatures;
    private int temperatureCount;

    public User(String u, String p, int m) {
        username = u;
        password = p;
        temperatures = new int[m];
        temperatureCount = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getTemperatureCount(){
        return temperatureCount;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addTemperature(int t) {
        if (temperatureCount < temperatures.length) {
            temperatures[temperatureCount++] = t;
            sortTemperatures(); 
        } else {
            System.out.println("Maximum number of temperatures reached for " + username);
        }
    }

    public void sortTemperatures() {
        quickSort(temperatures, 0, temperatureCount - 1);
    }

    public int searchTemperature(int t) {
        return binarySearch(temperatures, 0, temperatureCount - 1, t);
    }

    public void printTemperatures() {
        int[] actualTemperatures = Arrays.copyOfRange(temperatures, 0, temperatureCount);
        System.out.println("Temperatures for " + username + ": " + Arrays.toString(actualTemperatures));
    }

    private void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(a, low, high);
            quickSort(a, low, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, high);
        }
    }

    private int partition(int[] a, int l, int h) {
        int pivot = a[h];
        int i = l - 1;

        for (int j = l; j < h; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        int temp = a[i + 1];
        a[i + 1] = a[h];
        a[h] = temp;

        return i + 1;
    }

    private int binarySearch(int[] a, int l, int h, int k) {
        if (h >= l) {
            int mid = l + (h - l) / 2;

            if (a[mid] == k) {
                return mid;
            }

            if (a[mid] > k) {
                return binarySearch(a, l, mid - 1, k);
            }

            return binarySearch(a, mid + 1, h, k);
        }

        return -1;
    }
}