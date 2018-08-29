package br.com.pagseuturco.accounting.init;

import java.io.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws Exception {
        TransactionAccount transactionAccount = new TransactionAccount();
        transactionAccount.processFile();
    }

}