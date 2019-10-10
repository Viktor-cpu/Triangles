package com.focusStart.areaOfTriangles;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //-----Блок ввода имён файлов-----
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя входного файла:");
        String pathToFile = scanner.nextLine(); // Входной файл
        System.out.println("Введите имя выходного файла:");
        String outFile = scanner.nextLine(); // Выходной файл
        //-----Блок ввода имён файлов-----


        //-----Блок обработки входного файла-----
        List<String> trianglesSrt = new ArrayList<String>(); // Для хранения строк входного файла, т.е. треугольков
        try {
            //File file = new File("./resources/in.txt");
            File file = new File(pathToFile+".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line; // Для хранения строки(треугольков) -> что бы записать в trianglesSrt
            while ((line = bufferedReader.readLine()) != null) {
                trianglesSrt.add(line); // запись строки(треугольков) в trianglesSrt
            }
            bufferedReader.close();
        //-----Блок обработки входного файла-----


            //-----Блок вычисления площади и поиска наибольшего-----
            double maxS = -1; // Для запоминания большей площади
            StringBuilder maxName = new StringBuilder(""); // Для запоминания кординат большего трейгольника

            for (String triangleSrt : trianglesSrt) {

                String[] trianglesCordsSrt = triangleSrt.split(" "); // Парсим на кординаты

                if (trianglesCordsSrt.length != 6) {
                    System.out.println("Ошибка формата входных данных. " + triangleSrt ); // Выводим ошибку, если элементов не 6
                } else {

                    try {

                        int[] arrayIntCords = new int[6];

                        for(int k=0;k<6;k++){
                            arrayIntCords[k] = Integer.parseInt(trianglesCordsSrt[k]); // Приводим к int
                        }

                        double s = area(arrayIntCords[0],arrayIntCords[1],arrayIntCords[2],arrayIntCords[3],arrayIntCords[4],arrayIntCords[5]); // Считаем площадь

                        if(s>maxS){
                            maxS = s; // Запоминаем больший
                            maxName.delete(0,maxName.length()); // Чистим прошлый "больший"
                            maxName.append(triangleSrt); // Запоминаем больший
                        }

                    } catch (NumberFormatException ex) {
                        System.out.println("Ошибка формата входных данных. " + triangleSrt ); // Если не удалось привести к int
                    }
                }

            }


            if(maxS!=-1 && !maxName.equals("")){
                System.out.println("Максимальный треугольник: " + maxName +", с площадью: " + maxS);
            }
            //-----Блок вычисления площади и поиска наибольшего-----


            //-----Блок записи в файл-----
            try(FileWriter writer = new FileWriter(outFile+".txt", false))
            {
                writer.write(maxName.toString());
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            //-----Блок записи в файл-----


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }





    }


    public static double area(int x1,int y1,int x2,int y2,int x3,int y3){  // Метод вычисления площади
        return ( 0.5 *Math.abs( (x2-x1) * (y3-y1) - (x3-x1) * (y2-y1) ) );
    }



}
