import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExtensions {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        List<String> specialSymbols = new ArrayList<>(Arrays.asList(".", "$", "^", "{", "[", "(", "|", ")", "*", "+", "?", "|"));

        while (true) {
            String line = reader.readLine();
            if (line.equals("Print")) {
                break;
            }
            StringBuilder pat = new StringBuilder();
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                if (aChar == '%') {
                    pat.append("[^ ]*");
                } else if (specialSymbols.contains(aChar + "")) {
                    pat.append("\\").append(aChar);
                } else {
                    pat.append(aChar);
                }
            }

            try {
                Pattern pattern = Pattern.compile(pat.toString());
                Matcher matcher = pattern.matcher(text);
                while (matcher.find()) {
                    text = text.replace(matcher.group(), new StringBuilder(matcher.group()).reverse().toString());
                }
            } catch (Exception e) {
                text = text.replace(pat.toString(), pat.reverse().toString());
            }
        }

        System.out.println(text);
    }
}