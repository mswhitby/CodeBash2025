import java.io.*;
import java.util.*;

public class Simon {
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        // Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("simon.dat"));
        int cases = Integer.parseInt(scanner.nextLine());
        Map<String, Set<String>> studentsMap = new TreeMap<>();

        while (cases-- > 0) {
            String student1 = scanner.next();
            String student2 = scanner.next();
            Set<String> group;

            if (studentsMap.containsKey(student1)) {
                group = studentsMap.get(student1);
                group.add(student2);
            } else if (studentsMap.containsKey(student2)) {
                group = studentsMap.get(student2);
                group.add(student1);
            } else {
                studentsMap.put(student1, new TreeSet<String>(List.of(student1, student2)));
            }
        }
        scanner.close();

        ArrayList<Set<String>> groups = new ArrayList<>();

        for (String student: studentsMap.keySet()) {
            Set<String> groupMembers = studentsMap.get(student);
            boolean hasGroup =false;

            outerLoop:
            for (String member : groupMembers) {
                for (Set<String> current : groups) {
                    if (current.contains(member)) {
                        current.addAll(groupMembers);
                        hasGroup = true;
                        break outerLoop;
                    }
                }
            }
            if (!hasGroup) groups.add(groupMembers);
        }

        groups.sort(Comparator.comparing(set -> set.iterator().next()));
        ArrayList<String> output = new ArrayList<>();
        for (Set<String> group : groups) {
            output.add("{" + String.join(", ", group) + "}");
        }

        System.out.print(String.join(", ", output));
    }
}
