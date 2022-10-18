package kafka.practice.api.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreditPayedEvent {

    private String id;
    private CreditStatus creditStatus;
    private String timestamp;

    public CreditPayedEvent(){};

    public CreditPayedEvent(Credit credit){
        this.id = credit.getId();
        this.creditStatus = credit.getCreditStatus();
        this.timestamp = LocalDateTime.now().toString();
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

        CreditPayedEvent that = (CreditPayedEvent) o;

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

}
