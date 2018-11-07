package br.com.pagseuturco.accounting.view.init;

import br.com.pagseuturco.accounting.controller.AccountingController;
import br.com.pagseuturco.accounting.model.data.AccountingDAO;
import br.com.pagseuturco.accounting.model.data.TransactionAccount;
import br.com.pagseuturco.accounting.view.file.AccountingFileReaderView;

import java.io.*;
import java.sql.SQLException;

public class Main {
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_JDBC_URL = "jdbc:mysql://localhost/financial_turnover?user=sqluser&password=sqluserpw";

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        AccountingController accountingController = new AccountingController(new AccountingFileReaderView(), new TransactionAccount(), new AccountingDAO(MYSQL_JDBC_DRIVER, MYSQL_JDBC_URL));
        accountingController.accountForTextFiles();
    }

}