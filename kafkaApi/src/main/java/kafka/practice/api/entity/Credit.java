package kafka.practice.api.entity;

public class Credit {

  private String id;
  private String deadlineDate;
  private String lastPaymentDate;
  private int creditBalance;
  private int penalty;
  private String borrowerId;
  private int creditType;
  private CreditStatus creditStatus;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getPenalty() {
    return penalty;
  }

  public void setPenalty(int penalty) {
    this.penalty = penalty;
  }

  public CreditStatus getCreditStatus() {
    return creditStatus;
  }

  public void setCreditStatus(CreditStatus creditStatus) {
    this.creditStatus = creditStatus;
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

  public String getDeadlineDate() {
    return deadlineDate;
  }

  public void setDeadlineDate(String deadlineDate) {
    this.deadlineDate = deadlineDate;
  }

  public String getLastPaymentDate() {
    return lastPaymentDate;
  }

  public void setLastPaymentDate(String lastPaymentDate) {
    this.lastPaymentDate = lastPaymentDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Credit credit = (Credit) o;

    if (creditBalance != credit.creditBalance) return false;
    if (penalty != credit.penalty) return false;
    if (creditType != credit.creditType) return false;
    if (!id.equals(credit.id)) return false;
    if (!deadlineDate.equals(credit.deadlineDate)) return false;
    if (!lastPaymentDate.equals(credit.lastPaymentDate)) return false;
    if (!borrowerId.equals(credit.borrowerId)) return false;
    return creditStatus == credit.creditStatus;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + deadlineDate.hashCode();
    result = 31 * result + lastPaymentDate.hashCode();
    result = 31 * result + creditBalance;
    result = 31 * result + penalty;
    result = 31 * result + borrowerId.hashCode();
    result = 31 * result + creditType;
    result = 31 * result + creditStatus.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Credit{"
        + "id="
        + id
        + ", deadline="
        + deadlineDate
        + ", lastPayment="
        + lastPaymentDate
        + ", creditBalance="
        + creditBalance
        + ", borrowerId="
        + borrowerId
        + ", creditType="
        + creditType
        + ", creditStatus="
        + creditStatus
        + '}';
  }
}
