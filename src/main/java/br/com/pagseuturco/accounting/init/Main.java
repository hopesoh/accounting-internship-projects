package br.com.pagseuturco.accounting.init;

import br.com.pagseuturco.accounting.controller.AccountingController;
import br.com.pagseuturco.accounting.model.dao.AccountingDAO;
import br.com.pagseuturco.accounting.model.domain.TransactionAccount;
import br.com.pagseuturco.accounting.view.file.AccountingFileReaderView;

import java.io.*;
import java.sql.SQLException;

public class Main {
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_JDBC_URL = "jdbc:mysql://localhost/financial_turnover?user=root&password=SS748596&useSSL=false";

    public static void main(String[] args) throws IOException {
        AccountingController accountingController = new AccountingController(new AccountingFileReaderView(), new TransactionAccount(), new AccountingDAO(MYSQL_JDBC_DRIVER, MYSQL_JDBC_URL));
        accountingController.accountForTextFiles();
    }

}