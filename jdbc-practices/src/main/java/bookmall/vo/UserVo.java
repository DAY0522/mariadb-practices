package bookmall.vo;

public class UserVo {
    private Long no;
    private String name;
    private String mail;
    private String password;
    private String phoneNumber;

    public UserVo() {
    }

    public UserVo(String name, String mail, String password, String phoneNumber) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
