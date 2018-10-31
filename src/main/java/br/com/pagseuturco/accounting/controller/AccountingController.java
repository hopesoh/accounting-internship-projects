package br.com.pagseuturco.accounting.controller;

import br.com.pagseuturco.accounting.model.data.AccountingDAO;
import br.com.pagseuturco.accounting.model.data.Turnover;
import br.com.pagseuturco.accounting.view.file.FileReader;
import br.com.pagseuturco.accounting.view.init.TransactionAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller da funcionalidade de contabilização.
 * Esta classe tem por funcionalidade guardar a ordem de chamadas para realizar a contabilizações das movimentaçõe financeiras.
 */
public class AccountingController {

    private FileReader fileReader;
    private TransactionAccount transactionAccount;
    private AccountingDAO accountingDAO;

    public AccountingController(FileReader fileReader, TransactionAccount transactionAccount, AccountingDAO accountingDAO) {
        this.fileReader = fileReader;
        this.transactionAccount = transactionAccount;
        this.accountingDAO = accountingDAO;
    }

    public void accountForTextFiles() throws IOException, SQLException, ClassNotFoundException {
        Reader financialTurnoverFile = fileReader.readFile(); //Solicitar nomes dos locais onde se encontram os arquivos
        List<String> fileIntoList = transactionAccount.transformFileIntoList(financialTurnoverFile); //Ler arquivos

        //Contabilização
        List<Turnover> turnoverList = transactionAccount.processFile(fileIntoList);

        //Salva os dados
        accountingDAO.saveFinancialTurnover(turnoverList);

        //Apresenta uma mensagem bonita para o usuário =)
        financialTurnoverFile.close();
    }

    public void accountForDatabase() {
        //Ler dados do banco de dados
        //Contabilização
        //Salva os dados
        //Apresenta uma mensagem bonita para o usuário =)
    }

}
