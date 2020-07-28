import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInputClass {

    private StringBuilder stringBuilder;

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
}
