import java.util.ArrayDeque;
import java.util.Stack;

public class Calculator {

    Stack<Integer> evalStack = new Stack<>();
    int result = 0;
    public Sign sign;
    QueueMakerClass q = new QueueMakerClass();
    private ArrayDeque<Object> makeExitQ = (ArrayDeque<Object>) q.makeExitQ();

    public void calculate () {

        while (!makeExitQ.isEmpty()) {
            if (makeExitQ.getFirst() instanceof Integer) {
                evalStack.push((int)makeExitQ.pollFirst());
            } else {
                sign = (Sign)makeExitQ.pollFirst();
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
        System.out.println(result);
    }
}




