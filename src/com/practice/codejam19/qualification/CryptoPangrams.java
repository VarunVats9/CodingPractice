package com.practice.codejam19.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CryptoPangrams {

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = bufferedReader.readLine();
            int t = Integer.parseInt(line);
            for (int j = 1; j <= t; j++) {
                ArrayList<Integer> arr = new ArrayList<>();
                Set<Integer> set = new HashSet<>();
                StringBuilder a = new StringBuilder("");
                line = bufferedReader.readLine();
                String[] nl = line.split(" ");
                int n = Integer.parseInt(nl[0]);
                int l = Integer.parseInt(nl[1]);
                line = bufferedReader.readLine();
                String[] crypt = line.split(" ");
                int first = Integer.parseInt(crypt[0]);

                CryptObject[] list = new CryptObject[l];
                CryptObject prevCrypto, currCrypto;
                int backLoop = 0, startCounter = 0, lastCurr = 0, lastIdx = 0;

                for (int i = 1; i < l; i++) {
                    int prev = Integer.parseInt(crypt[i-1]);
                    int curr = Integer.parseInt(crypt[i]);
                    if (prev != curr) {
                        lastCurr = curr;
                        lastIdx = i;
                        int gcd = calculateGcd(curr, prev);
                        if (list[i-1] == null) {
                            prevCrypto = new CryptObject(prev);
                        } else {
                            prevCrypto = list[i-1];
                        }
                        prevCrypto.front = gcd;
                        list[i-1] = prevCrypto;
                        currCrypto = new CryptObject(curr);
                        currCrypto.back = gcd;
                        list[i] = currCrypto;
                        if (backLoop != 0) {
                            while (backLoop != 0) {
                                CryptObject repeatCrypto = list[startCounter];
                                int primeFront = repeatCrypto.front;
                                repeatCrypto.back = repeatCrypto.val / primeFront;
                                startCounter--;
                                if (list[startCounter] == null) {
                                    list[startCounter] = new CryptObject(repeatCrypto.val);
                                }
                                list[startCounter].front = repeatCrypto.back;
                                backLoop--;
                            }
                            /*if (startCounter-1 >= 0) {
                                list[startCounter].back = list[startCounter-1].front;
                            }*/
                        }
                    } else {
                        startCounter = i;
                        backLoop++;
                    }
                }

                if (backLoop != 0) {
                    list[lastIdx].front = list[lastIdx].val / list[lastIdx].back;
                    while (backLoop != 0) {
                        ++lastIdx;
                        list[lastIdx] = new CryptObject(lastCurr);
                        list[lastIdx].back = list[lastIdx - 1].front;
                        list[lastIdx].front = list[lastIdx].val / list[lastIdx].back;
                        backLoop--;
                    }
                    /*if (startCounter-1 >= 0) {
                        list[startCounter].back = list[startCounter-1].front;
                    }*/
                }

                list[0].back = list[0].val / list[0].front;
                list[l-1].front = list[l-1].val / list[l-1].back;

                arr.add(list[0].back);
                set.add(list[0].back);
                for (int p = 0; p < l; p++) {
                    System.out.println("val : " + list[p].val + "--> back : " + list[p].back + ", front : " + list[p].front);
                    arr.add(list[p].front);
                    set.add(list[p].front);
                }

                ArrayList<Integer> sortedList = new ArrayList<>(set);
                Collections.sort(sortedList);

                System.out.println("primes : " + sortedList + ", and size : " + sortedList.size());

                HashMap<Integer, Character> map = new HashMap<>();
                for (int i = 0; i < sortedList.size(); i++) {
                    System.out.println("key : " + sortedList.get(i) + ", value : " + (char)('A' + i));
                    map.put(sortedList.get(i), (char)('A' + i));
                }

                System.out.println("primeMap : " + map);
                System.out.println("arr : " + arr);

                for (int i = 0; i < arr.size(); i++) {
                    a.append(map.get(arr.get(i)));
                }



                /*int prev = first;
                for (int i = 1; i < l; i++) {
                    int curr = Integer.parseInt(crypt[i]);
                    int gcd = calculateGcd(curr, prev);
                    System.out.println("prev : " + prev + ", curr : " + curr + " ---> gcd : " + gcd);
                    int value = gcd;
                    if (i == 1) {
                        int f = prev / gcd;
                        arr.add(f);
                        set.add(f);
                    }
                    arr.add(value);
                    set.add(value);
                    if (i == l-1) {
                        int e = curr / gcd;
                        arr.add(e);
                        set.add(e);
                    }
                    prev = curr;
                    System.out.println(arr);
                }

                ArrayList<Integer> sortedList = new ArrayList<>(set);
                Collections.sort(sortedList);

                System.out.println("primes : " + sortedList + ", and size : " + sortedList.size());

                HashMap<Integer, Character> map = new HashMap<>();
                for (int i = 0; i < sortedList.size(); i++) {
                    System.out.println("key : " + sortedList.get(i) + ", value : " + (char)('A' + i));
                    map.put(sortedList.get(i), (char)('A' + i));
                }

                System.out.println("primeMap : " + map);
                System.out.println("arr : " + arr);

                for (int i = 0; i < arr.size(); i++) {
                    a.append(map.get(arr.get(i)));
                }*/

                System.out.println("Case #" + j + ": " + a.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class CryptObject {

        int val, front, back;

        public CryptObject(int val) {
            this.val = val;
        }
    }

    private static int calculateGcd(int a, int b) {
        if (b == 0) return a;
        return calculateGcd(b, a%b);
    }

}
