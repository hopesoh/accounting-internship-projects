package br.com.pagseuturco.accounting.controller;

import br.com.pagseuturco.accounting.model.dao.AccountingDAO;
import br.com.pagseuturco.accounting.model.domain.TransactionAccount;
import br.com.pagseuturco.accounting.model.domain.Turnover;
import br.com.pagseuturco.accounting.init.MockAccountingFileReaderView;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccountingControllerTest {

    @Test
    public void accountingControllerCallingAllMethodsUsingFinancialTurnoverTransferType() throws SQLException, IOException, ClassNotFoundException {
        AccountingController accountingController = new AccountingController(
                new MockAccountingFileReaderView(),
                new TransactionAccount(),
                new AccountingDAO("",""){
                    @Override
                    public void saveFinancialTurnover(List<Turnover> turnoverList) {
                        System.out.println("As mensagens foram salvas no banco de dados.");
                    }
                }
        );
        accountingController.accountForTextFiles();
    }
}
