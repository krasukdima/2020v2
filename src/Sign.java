enum Sign {
    PLUS(0, "+"),
    MINUS(0, "-"),
    MULTIPLICATION (1,"*"),
    DIVISION(1,"/"),
    OPEN_PARENTHESES(-2, "("),
    CLOSE_PARENTHESES(-3, ")");

    private final int priority;
    private final String sign;

    private Sign(int priority, String sign) {
        this.priority = priority;
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public int getPriority() {
        return priority;
    }

    public static Sign getFromSign(String sign){
        for (Sign s: Sign.values()){
            if (s.getSign().equals(sign)){
                return s;
            }
        }
        return null;
    }

}
