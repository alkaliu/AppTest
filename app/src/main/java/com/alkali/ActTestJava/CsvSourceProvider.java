package com.alkali.ActTestJava;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CsvSourceProvider {
    public static String CsvDataSource = "0,1,2,3,4,5,6,7,8,9";

    public static String fromResources(InputStream is) {
        String res = "";
        ByteArrayOutputStream baos = null;
        try {
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is));
            baos = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                baos.write(i);
                i = is.read();
            }
            res = baos.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
