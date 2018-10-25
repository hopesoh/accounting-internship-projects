package br.com.pagseuturco.accounting.data;

import java.sql.SQLException;

public class FinancialTurnoverFactory {
    public Turnover build(String turnoverType, String[] splittedLine) throws SQLException, ClassNotFoundException {
            switch (turnoverType) {
                case "BOOKLET":
                    return new FinancialTurnoverBooklet(splittedLine);
                case "GENNERICCARD":
                    return new FinancialTurnoverGennericcard(splittedLine);
                case "TRANSFER":
                    return new FinancialTurnoverTransfer(splittedLine);
                default:
                    throw new IllegalArgumentException();
            }

    }
}

