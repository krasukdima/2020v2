import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class QueueMakerClass {

    private StringBuilder tempStringBuilder = new StringBuilder();
    ArrayList<String> tempArrayString = new ArrayList<String>();
    ArrayDeque<Object> exitQ = new ArrayDeque<>();
    Stack<Sign> signStack = new Stack<Sign>();
    public Sign sign;
    ParserClass parser = new ParserClass();
    UserInputClass userInput = new UserInputClass();



    public ArrayList<String> ArrayMaker(StringBuilder line) {

        while (line.length() > 0) {
            if (parser.tryParseInt(String.valueOf(line.charAt(0))) != null) {
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

    public Queue makeExitQ () {

        ArrayMaker(userInput.inputStringbuilder());

        while (tempArrayString.size() > 0) {
            if (parser.tryParseInt(tempArrayString.get(0)) != null) {
                exitQ.add(parser.tryParseInt(tempArrayString.get(0)));
            } else {
                if (signStack.isEmpty()) {
                    signStack.add(sign.getFromSign(tempArrayString.get(0)));
                } else {

                    if (sign.getFromSign(tempArrayString.get(0)).getPriority() == -2) {
                        signStack.add(sign.getFromSign(tempArrayString.get(0)));

                    } else {
                        if (sign.getFromSign(tempArrayString.get(0)).getPriority() == -3) {

                            while (signStack.peek() != Sign.OPEN_PARENTHESES) {
                                exitQ.add(signStack.pop());
                            }
                            signStack.pop();
                        } else {
                            if (signStack.peek().getPriority() >= sign.getFromSign(tempArrayString.get(0)).getPriority()) {
                                exitQ.add(signStack.pop());
                                signStack.add(sign.getFromSign(tempArrayString.get(0)));
                            } else {
                                signStack.add(sign.getFromSign(tempArrayString.get(0)));
                            }
                        }
                    }
                }
            }
            tempArrayString.remove(0);
        }

        while (!signStack.isEmpty()) {
            exitQ.add((signStack.pop()));
        }

        return exitQ;

    }
}
