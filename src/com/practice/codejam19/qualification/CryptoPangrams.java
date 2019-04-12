package com.practice.codejam19.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
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
                ArrayList<BigInteger> arr = new ArrayList<>();
                Set<BigInteger> set = new HashSet<>();
                StringBuilder a = new StringBuilder("");
                line = bufferedReader.readLine();
                String[] nl = line.split(" ");
                BigInteger n = new BigInteger(nl[0]);
                int l = Integer.parseInt(nl[1]);
                line = bufferedReader.readLine();
                String[] crypt = line.split(" ");

                CryptObject[] list = new CryptObject[l];
                CryptObject prevCrypto, currCrypto;
                int backLoop = 0, startCounter = 0, lastIdx = 0;
                BigInteger lastCurr = new BigInteger("0");

                for (int i = 1; i < l; i++) {
                    BigInteger prev = new BigInteger(crypt[i-1]);
                    BigInteger curr = new BigInteger(crypt[i]);
                    if (prev.compareTo(curr) != 0) {
                        lastCurr = curr;
                        lastIdx = i;
                        BigInteger gcd = calculateGcd(curr, prev);
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
                                BigInteger primeFront = repeatCrypto.front;
                                repeatCrypto.back = repeatCrypto.val.divide(primeFront);
                                startCounter--;
                                if (list[startCounter] == null) {
                                    list[startCounter] = new CryptObject(repeatCrypto.val);
                                }
                                list[startCounter].front = repeatCrypto.back;
                                backLoop--;
                            }
                        }
                    } else {
                        startCounter = i;
                        backLoop++;
                    }
                }

                if (backLoop != 0) {
                    list[lastIdx].front = list[lastIdx].val.divide(list[lastIdx].back);
                    while (backLoop != 0) {
                        ++lastIdx;
                        list[lastIdx] = new CryptObject(lastCurr);
                        list[lastIdx].back = list[lastIdx - 1].front;
                        list[lastIdx].front = list[lastIdx].val.divide(list[lastIdx].back);
                        backLoop--;
                    }
                }

                list[0].back = list[0].val.divide(list[0].front);
                list[l-1].front = list[l-1].val.divide(list[l-1].back);

                arr.add(list[0].back);
                set.add(list[0].back);
                for (int p = 0; p < l; p++) {
                    arr.add(list[p].front);
                    set.add(list[p].front);
                }

                ArrayList<BigInteger> sortedList = new ArrayList<>(set);
                Collections.sort(sortedList);

                HashMap<BigInteger, Character> map = new HashMap<>();
                for (int i = 0; i < sortedList.size(); i++) {
                    map.put(sortedList.get(i), (char)('A' + i));
                }

                for (int i = 0; i < arr.size(); i++) {
                    a.append(map.get(arr.get(i)));
                }

                System.out.println("Case #" + j + ": " + a.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class CryptObject {

        BigInteger val, front, back;

        public CryptObject(BigInteger val) {
            this.val = val;
        }
    }

    private static BigInteger calculateGcd(BigInteger a, BigInteger b) {
        if (b.compareTo(BigInteger.valueOf(0)) == 0) return a;
        return calculateGcd(b, a.mod(b));
    }
}
