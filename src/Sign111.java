public class Sign111 {
    private int  priority;
    private String sign;

    Sign111(String sign, int priority) {
        this.sign = sign;
        this.priority = priority;

    }




    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setSign (String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
