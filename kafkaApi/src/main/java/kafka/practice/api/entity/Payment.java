package kafka.practice.api.entity;

public class Payment {

    private String id;
    private int payment;
    private String creditId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment1 = (Payment) o;

        if (payment != payment1.payment) return false;
        if (!id.equals(payment1.id)) return false;
        return creditId.equals(payment1.creditId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + payment;
        result = 31 * result + creditId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", payment=" + payment +
                ", creditId='" + creditId + '\'' +
                '}';
    }

}
