package kafka.practice.api.entity;

public class Collector {

  private String id;
  private String firstName;
  private String lastName;
  private CollectorActivityStatus collectorActivityStatus;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CollectorActivityStatus getCollectorActivityStatus() {
    return collectorActivityStatus;
  }

  public void setCollectorActivityStatus(CollectorActivityStatus collectorActivityStatus) {
    this.collectorActivityStatus = collectorActivityStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Collector collector = (Collector) o;

    if (!id.equals(collector.id)) return false;
    if (!firstName.equals(collector.firstName)) return false;
    if (!lastName.equals(collector.lastName)) return false;
    return collectorActivityStatus == collector.collectorActivityStatus;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    result = 31 * result + collectorActivityStatus.hashCode();
    return result;
  }
}
