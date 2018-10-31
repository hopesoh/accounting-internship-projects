package br.com.pagseuturco.accounting.view.file;

import java.io.*;
import java.util.Scanner;

public class FileReader {
    public Reader readFile () throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.print( "Insira o caminho do arquivo: " );
        String filePath = input.nextLine();

        java.io.FileReader file = new java.io.FileReader(filePath);
        try {
            file.ready();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader in = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(in);
        return reader;
    }
}
