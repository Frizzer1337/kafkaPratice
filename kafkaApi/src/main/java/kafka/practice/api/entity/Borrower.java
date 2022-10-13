package kafka.practice.api.entity;


public class Borrower{

    private String id;
    private String name;
    private String surname;
    private String phone;
    private int salary;
    private double socialCredit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getSocialCredit() {
        return socialCredit;
    }

    public void setSocialCredit(double socialCredit) {
        this.socialCredit = socialCredit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Borrower borrower = (Borrower) o;

        if (salary != borrower.salary) return false;
        if (Double.compare(borrower.socialCredit, socialCredit) != 0) return false;
        if (!id.equals(borrower.id)) return false;
        if (name != null ? !name.equals(borrower.name) : borrower.name != null) return false;
        if (surname != null ? !surname.equals(borrower.surname) : borrower.surname != null) return false;
        return phone != null ? phone.equals(borrower.phone) : borrower.phone == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + salary;
        temp = Double.doubleToLongBits(socialCredit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", socialCredit=" + socialCredit +
                '}';
    }


}
