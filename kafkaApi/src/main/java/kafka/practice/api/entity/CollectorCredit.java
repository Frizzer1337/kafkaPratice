package kafka.practice.api.entity;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class CollectorCredit {

  private String id;
  private String collectorId;
  private String creditId;
  private String lastCallDate;

  public CollectorCredit() {}
  ;

  public CollectorCredit(Credit credit) {
    this.id = String.valueOf(new ObjectId());
    this.creditId = credit.getId();
    this.lastCallDate = LocalDateTime.now().toString();
    this.collectorId = "-1";
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCollectorId() {
    return collectorId;
  }

  public void setCollectorId(String collectorId) {
    this.collectorId = collectorId;
  }

  public String getCreditId() {
    return creditId;
  }

  public void setCreditId(String creditId) {
    this.creditId = creditId;
  }

  public String getLastCallDate() {
    return lastCallDate;
  }

  public void setLastCallDate(String lastCallDate) {
    this.lastCallDate = lastCallDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CollectorCredit that = (CollectorCredit) o;

    if (!id.equals(that.id)) return false;
    if (!collectorId.equals(that.collectorId)) return false;
    if (!creditId.equals(that.creditId)) return false;
    return lastCallDate.equals(that.lastCallDate);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + collectorId.hashCode();
    result = 31 * result + creditId.hashCode();
    result = 31 * result + lastCallDate.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "CollectorCredit{"
        + "id='"
        + id
        + '\''
        + ", collectorId='"
        + collectorId
        + '\''
        + ", creditId='"
        + creditId
        + '\''
        + ", lastCallDate='"
        + lastCallDate
        + '\''
        + '}';
  }
}
