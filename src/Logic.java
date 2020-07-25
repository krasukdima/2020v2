import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class Logic {

    ArrayDeque<Object> exitQ = new ArrayDeque<>();
    Stack<Sign> signStack = new Stack<Sign>();
    Stack<Integer> evalStack = new Stack<>();
    ArrayList<String> tempArrayString = new ArrayList<String>();
    private StringBuilder stringBuilder;
    private StringBuilder tempStringBuilder = new StringBuilder();
    int result = 0;
    public Sign sign;


    public String getUserInput() {
        String inputLine = null;
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("Ioexception" + e);
        }
        return inputLine;
    }

    public StringBuilder inputStringbuilder() {
        stringBuilder = new StringBuilder(getUserInput());
        return stringBuilder;
    }

    private static Integer tryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public ArrayList<String> gg(StringBuilder line) {

        while (line.length() > 0) {
            if (tryParseInt(String.valueOf(line.charAt(0))) != null) {
                tempStringBuilder.append(String.valueOf(line.charAt(0)));
            } else {
                if (tempStringBuilder.length() > 0) {
                    tempArrayString.add(tempStringBuilder.toString());
                    tempStringBuilder.delete(0, tempStringBuilder.length());
                }
                tempArrayString.add(line.substring(0, 1));
            }
            line.delete(0, 1);
        }
        if (tempStringBuilder.length() != 0) {
            tempArrayString.add(tempStringBuilder.toString());
        }
        return tempArrayString;
    }

    public Queue makeExitQ (ArrayList<String> str) {

        while (str.size() > 0) {
            if (tryParseInt(str.get(0)) != null) {
                exitQ.add(tryParseInt(str.get(0)));
            } else {
                if (signStack.isEmpty()) {
                    signStack.add(sign.getFromSign(str.get(0)));
                } else {

                    if (sign.getFromSign(str.get(0)).getPriority() == -2) {
                        signStack.add(sign.getFromSign(str.get(0)));

                    } else {
                        if (sign.getFromSign(str.get(0)).getPriority() == -3) {

                            while (signStack.peek() != Sign.OPEN_PARENTHESES) {
                                exitQ.add(signStack.pop());
                            }
                            signStack.pop();
                        } else {
                            if (signStack.peek().getPriority() >= sign.getFromSign(str.get(0)).getPriority()) {
                                exitQ.add(signStack.pop());
                                signStack.add(sign.getFromSign(str.get(0)));
                            } else {
                                signStack.add(sign.getFromSign(str.get(0)));
                            }
                        }
                    }
                }
            }
            str.remove(0);
        }

       while (!signStack.isEmpty()) {
           exitQ.add((signStack.pop()));
       }

       return exitQ;

    }

    public int calculate (ArrayDeque e) {

              while (!e.isEmpty()) {
            if (e.getFirst() instanceof Integer) {
                evalStack.push((int)e.pollFirst());
            } else {
                sign = (Sign)e.pollFirst();
                switch (sign) {
                    case PLUS:
                        result = evalStack.pop() + evalStack.pop();
                        evalStack.push(result);
                        break;
                    case MULTIPLICATION:
                        result = evalStack.pop() * evalStack.pop();
                        evalStack.push(result);
                        break;
                    case DIVISION:
                        int temp = evalStack.pop();
                        result = evalStack.pop() / temp;
                        evalStack.push(result);
                        break;
                    case MINUS:
                        int temp1 = evalStack.pop();
                        result = evalStack.pop() - temp1;
                        evalStack.push(result);
                        break;
                }
            }
        }
        return result;
    }
}




