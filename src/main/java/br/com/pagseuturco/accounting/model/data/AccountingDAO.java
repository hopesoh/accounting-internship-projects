package br.com.pagseuturco.accounting.model.data;

import br.com.pagseuturco.accounting.view.init.TransactionAccount;

import java.math.RoundingMode;
import java.sql.*;
import java.util.List;

public class AccountingDAO {

    TransactionAccount transactionAccount;

    public void saveFinancialTurnover(List<Turnover> turnoverList) {


//        for ( Turnover turnover : turnoverList ){
//            if (turnover.getType() == "X") {
//
//                saveX()
//            } else if  (turnover.getType() == "Y"){
//                saveY()/
//            }
//        }
//
//
//        try {
//
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connect = DriverManager.getConnection("jdbc:mysql://localhost/financial_turnover?"
//                    + "user=sqluser&password=sqluserpw");
//
//
//
//        } catch (Exception e) {
//            throw e;
//        }
//
//
////        String turnoverType = transactionAccount.identifyTurnoverByType(fileIntoList.get(0));
////
////        for (int index=1; index < fileIntoList.size(); index++ ) {
////
////        }
//    }
//
//    private void saveX() {
//
//        preparedStatement = connect.prepareStatement("insert into financial_turnover.gennericcard values(default,?,?,?,?)");
//        preparedStatement.setInt(1, account);
//        preparedStatement.setBigDecimal(2, gennericcardValueSubtractTax.setScale(2, RoundingMode.DOWN));
//        preparedStatement.setString(3, type);
//        preparedStatement.setString(4, date);
//        preparedStatement.executeUpdate();
//
//        preparedStatement = connect.prepareStatement("insert into financial_turnover.pagseuturco_account values(default,?,?,?,?)");
//        preparedStatement.setInt(1,account);
//        preparedStatement.setBigDecimal(2,valueToAccountNumberOne.setScale(2, RoundingMode.DOWN));
//        preparedStatement.setString(3, "Gennericard");
//        preparedStatement.setString(4, date);
//        preparedStatement.executeUpdate();
    }
}