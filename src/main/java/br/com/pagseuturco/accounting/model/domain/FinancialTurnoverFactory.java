package br.com.pagseuturco.accounting.model.domain;

public class FinancialTurnoverFactory {
    public Turnover build(String turnoverType, String[] splittedLine) {
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

