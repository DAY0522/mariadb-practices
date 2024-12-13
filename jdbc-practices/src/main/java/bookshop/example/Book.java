package bookshop.example;

public class Book {
    private int no;
    private String title;
    private String author;
    private int stateCode;

    public Book(int no, String title, String author) {
        this.no = no;
        this.title = title;
        this.author = author;
        this.stateCode = 1; // 재고 없음
    }

    public int getNo() {
        return no;
    }

    public void rent() {
        if (stateCode == 1) {
            stateCode = 0;
            System.out.println(title + "이(가) 대여 됐습니다.");
        } else {
            System.out.println(title + "은(는) 이미 대여중입니다.");
        }
    }

    public void print() {
        System.out.println("책 제목: " + title + ", 작가: " + author + ", 대여 유무: " + (stateCode == 1 ? "재고있음" : "대여중"));
    }

}
