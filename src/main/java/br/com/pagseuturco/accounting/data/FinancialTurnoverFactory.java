package br.com.pagseuturco.accounting.data;

public class FinancialTurnoverFactory {
    public Turnover build(String turnoverType, String[] splittedLine) {
        if (turnoverType == null || splittedLine == null) {
            return null;
        } else {
            switch (turnoverType) {
                case "BOOKLET":
                    return new FinancialTurnoverBooklet(splittedLine);
                case "GENNERICCARD":
                    return new FinancialTurnoverGennericcard(splittedLine);
                case "TRANSFER":
                    return new FinancialTurnoverTransfer(splittedLine);
                default:
                    return null;

            }
        }

    }
}

