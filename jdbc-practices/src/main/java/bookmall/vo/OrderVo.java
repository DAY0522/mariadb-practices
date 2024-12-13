package bookmall.vo;

public class OrderVo {
    private Long no;
    private Long userNo;
    private String number;
    private int payment;
    private String shipping;
    private String Status;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int price) {
        this.payment = price;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
