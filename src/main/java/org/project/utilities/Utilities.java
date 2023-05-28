package org.project.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utilities {


    public static String parraCommand ="last --since \"3 month ago\"| grep -v logout | grep seat0 | awk '{print $1,$NF}' | sed 's/[()]//g' | python3 UsTimeCalc.py";


    public static String readTxt(String path){
        //Path should start from src/main/resources.
        StringBuilder content = new StringBuilder();
        ClassLoader classLoader = Utilities.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public String getFileFromResources (String path){
        ClassLoader classLoader = Utilities.class.getClassLoader();
        return classLoader.getResource("data/filename.txt").toString();
    }
}
