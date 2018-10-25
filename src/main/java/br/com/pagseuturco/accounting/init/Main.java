package br.com.pagseuturco.accounting.init;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        TransactionAccount transactionAccount = new TransactionAccount();
        transactionAccount.processFile();
    }

}