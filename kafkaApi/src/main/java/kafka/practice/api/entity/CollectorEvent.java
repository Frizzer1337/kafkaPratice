package kafka.practice.api.entity;

import java.time.LocalDate;

public class CollectorEvent {

  private String id;
  private CreditStatus creditStatus;
  private String timestamp;

  public CollectorEvent() {}
  ;

  public CollectorEvent(Credit credit) {
    this.id = credit.getId();
    this.creditStatus = credit.getCreditStatus();
    this.timestamp = LocalDate.now().toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CreditStatus getCreditStatus() {
    return creditStatus;
  }

  public void setCreditStatus(CreditStatus creditStatus) {
    this.creditStatus = creditStatus;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CollectorEvent that = (CollectorEvent) o;

    if (!id.equals(that.id)) return false;
    if (creditStatus != that.creditStatus) return false;
    return timestamp.equals(that.timestamp);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + creditStatus.hashCode();
    result = 31 * result + timestamp.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "CreditCheckEvent{"
        + "id='"
        + id
        + '\''
        + ", creditStatus="
        + creditStatus
        + ", timestamp='"
        + timestamp
        + '\''
        + '}';
  }
}
