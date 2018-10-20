package br.com.pagseuturco.accounting.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileReader {
    public BufferedReader readFile () throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.print( "Insira o caminho do arquivo: " );
        String filePath = input.nextLine();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        return reader;
    }
}
