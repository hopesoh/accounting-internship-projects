package br.com.pagseuturco.accounting.controller;

import br.com.pagseuturco.accounting.model.dao.AccountingDAO;
import br.com.pagseuturco.accounting.model.domain.Turnover;
import br.com.pagseuturco.accounting.view.file.AccountingFileReaderView;
import br.com.pagseuturco.accounting.model.domain.TransactionAccount;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller da funcionalidade de contabilização.
 * Esta classe tem por funcionalidade guardar a ordem de chamadas para realizar a contabilizações das movimentaçõe financeiras.
 */
public class AccountingController {

    private AccountingFileReaderView accountingFileReaderView;
    private TransactionAccount transactionAccount;
    private AccountingDAO accountingDAO;

    public AccountingController(AccountingFileReaderView accountingFileReaderView, TransactionAccount transactionAccount, AccountingDAO accountingDAO) {
        this.accountingFileReaderView = accountingFileReaderView;
        this.transactionAccount = transactionAccount;
        this.accountingDAO = accountingDAO;
    }

    public void accountForTextFiles() throws IOException, SQLException, ClassNotFoundException {

        Reader financialTurnoverFile = accountingFileReaderView.readFile();
        List<String> fileIntoList = transactionAccount.transformFileIntoList(financialTurnoverFile);

        List<Turnover> turnoverList = transactionAccount.processFile(fileIntoList);

        accountingDAO.saveFinancialTurnover(turnoverList);

        accountingFileReaderView.showSuccessMessage();

        financialTurnoverFile.close();
    }

}
