package kafka.practice.borrowermicroservice.entity;

import java.sql.Timestamp;

public class Credit {

    int id;
    Timestamp returnDate;
    int creditBalance;
    String borrowerId;
    int creditType;
    CreditStatus creditStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditStatus getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(CreditStatus creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public int getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(int creditBalance) {
        this.creditBalance = creditBalance;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getCreditType() {
        return creditType;
    }

    public void setCreditType(int creditType) {
        this.creditType = creditType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        if (id != credit.id) return false;
        if (creditBalance != credit.creditBalance) return false;
        if (creditType != credit.creditType) return false;
        if (returnDate != null ? !returnDate.equals(credit.returnDate) : credit.returnDate != null) return false;
        if (!borrowerId.equals(credit.borrowerId)) return false;
        return creditStatus == credit.creditStatus;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + creditBalance;
        result = 31 * result + borrowerId.hashCode();
        result = 31 * result + creditType;
        result = 31 * result + (creditStatus != null ? creditStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", returnDate=" + returnDate +
                ", creditBalance=" + creditBalance +
                ", borrowerId=" + borrowerId +
                ", creditType=" + creditType +
                ", creditStatus=" + creditStatus +
                '}';
    }


}
