package cn.xkyln.newfeature;

public enum SexEnum {
    MALE(1, "男"),
    FEMALE(0, "女");

    private Integer code;
    private String message;

    private SexEnum() {

    }

    private SexEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
