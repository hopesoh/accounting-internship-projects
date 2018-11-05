package br.com.pagseuturco.accounting.view.init;

import br.com.pagseuturco.accounting.controller.AccountingController;
import br.com.pagseuturco.accounting.model.data.AccountingDAO;
import br.com.pagseuturco.accounting.model.data.TransactionAccount;
import br.com.pagseuturco.accounting.view.file.AccountingFileReaderView;

import java.io.*;
import java.sql.SQLException;

class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        AccountingController accountingController = new AccountingController(new AccountingFileReaderView(), new TransactionAccount(), new AccountingDAO());
        accountingController.accountForTextFiles();
    }

}