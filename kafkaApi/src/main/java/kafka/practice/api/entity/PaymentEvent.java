package kafka.practice.api.entity;

import java.time.LocalDate;

public class PaymentEvent {

    private String id;
    private int currentBalance;
    private String timestamp;

    public PaymentEvent(){};

    public PaymentEvent(Credit credit) {
        this.id = credit.getId();
        this.currentBalance = credit.getCreditBalance();
        this.timestamp = LocalDate.now().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
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

        PaymentEvent that = (PaymentEvent) o;

        if (currentBalance != that.currentBalance) return false;
        if (!id.equals(that.id)) return false;
        return timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + currentBalance;
        result = 31 * result + timestamp.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "PaymentEvent{" +
                "id='" + id + '\'' +
                ", currentBalance=" + currentBalance +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

}
