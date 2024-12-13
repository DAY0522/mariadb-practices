package bookmall.vo;

public class CartVo {
    private Long userNo;
    private Long bookNo;
    private int quantity;
    private int price;

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBookTitle() {
        // 작성
        return null;
    }
}
