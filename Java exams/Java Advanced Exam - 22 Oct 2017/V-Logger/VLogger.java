import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VLogger {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<String> vloggers = new LinkedHashSet<>();
        Map<String, LinkedHashSet<String>> nameAndFollowing = new HashMap<>();
        Map<String, LinkedHashSet<String>> nameAndFollowers = new LinkedHashMap<>();

        Pattern vloggersPattern = Pattern.compile("^(?<vlogger>\\w+) joined The V-Logger$");
        Pattern followers = Pattern.compile("^(?<vloggerOne>\\w+) followed (?<vloggerTwo>\\w+)$");

        while (true) {
            String line = reader.readLine();
            if (line.equals("Statistics")) {
                break;
            }
            Matcher matcher = vloggersPattern.matcher(line);
            if (matcher.find()) {
                String name = matcher.group("vlogger");
                vloggers.add(name);
            }

            Matcher matcher1 = followers.matcher(line);
            if (matcher1.find()) {
                String nameOne = matcher1.group("vloggerOne");
                String nameTwo = matcher1.group("vloggerTwo");
                if (nameOne.equals(nameTwo)) {
                    continue;
                }
                if (vloggers.contains(nameOne) && vloggers.contains(nameTwo)) {
                    nameAndFollowing.putIfAbsent(nameOne, new LinkedHashSet<>());
                    nameAndFollowing.get(nameOne).add(nameTwo);
                    nameAndFollowers.putIfAbsent(nameTwo, new LinkedHashSet<>());
                    nameAndFollowers.get(nameTwo).add(nameOne);
                }
            }
        }

        Map<String, ArrayList<Integer>> results = new LinkedHashMap<>();
        for (String name : vloggers) {
            results.put(name, new ArrayList<>());
            try {
                results.get(name).add(nameAndFollowers.get(name).size());
            } catch (Exception ex) {
                results.get(name).add(0);
            }
            try {
                results.get(name).add(nameAndFollowing.get(name).size());
            } catch (Exception ex) {
                results.get(name).add(0);
            }
        }

        Comparator<Map.Entry<String, ArrayList<Integer>>> byFollowers = (a, b) ->
                Integer.compare(b.getValue().get(0), a.getValue().get(0));
        Comparator<Map.Entry<String, ArrayList<Integer>>> byFollowing = Comparator.comparingInt(a -> a.getValue().get(1));

        final int[] counter = {1};
        final boolean[] first = {true};
        System.out.printf("The V-Logger has a total of %d vloggers in its logs.\n", vloggers.size());

        results.entrySet().stream().sorted(byFollowers.thenComparing(byFollowing)).forEach(s -> {
            System.out.printf("%d. %s : %d followers, %d following\n", counter[0],
                    s.getKey(), results.get(s.getKey()).get(0), results.get(s.getKey()).get(1));
            counter[0]++;
            if (first[0]) {
                nameAndFollowers.get(s.getKey()).stream().sorted().forEach(person -> {
                    System.out.printf("*  %s\n", person);
                });
                first[0] = false;
            }
        });
    }
}
