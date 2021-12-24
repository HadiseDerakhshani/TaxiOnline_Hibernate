package ir.taxi.enumeration;


public enum SignupMenu {
    REGISTER("1. Register"),
    EXIT("2. Exit");

    private String expression;

    SignupMenu(String expression) {
        this.expression = expression;
    }

    public static void showSignupMenu() {
        for (SignupMenu item : SignupMenu.values()) {
            System.out.println(item.getExpression());
        }
    }

    public String getExpression() {
        return expression;
    }
}
