package br.com.pagseuturco.accounting.view.file;

import java.io.*;
import java.util.Scanner;

/**
 * View da funcionalidade de contabilização para entrada via arquivos.
 * Esta classe tem por função realizar a contabilização por entrada via arquivo.
 */
public class AccountingFileReaderView {

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

    public void showSuccessMessage() {
        System.out.println("Sucesso! Todas as mensagens foram adicionadas ao banco de dados! :D");
    }

}
