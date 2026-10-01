import java.io.*;
import java.util.*;

class Section implements Comparable<Section> {
    Integer sectionNumber;
    String professor;
    double avgGrade;
    String startTime;
    int duration;
    Integer distance;

    double professorRating;
    Double gradeScore;
    Double timeScore;

    Section(String[] args, Map<String, Double> professorRatings) {
        this.sectionNumber = Integer.parseInt(args[0]);
        this.professor = args[1];
        this.avgGrade = Double.parseDouble(args[2]);
        this.startTime = args[3];
        this.duration = Integer.parseInt(args[4]);
        this.distance = Integer.parseInt(args[5]);

        this.professorRating = professorRatings.get(professor);
        this.gradeScore = calcGradeScore();
        this.timeScore = calcTimeScore();
    }

    double calcGradeScore() {
        return ((professorRating * 20) + avgGrade) / 10;
    }

    double calcTimeScore() {
        return (Math.abs(covertTime(startTime) - covertTime("14:00")) + duration) * 100;
    }

    double covertTime(String time) {
        String[] timeParts = time.split(":");
        int hoursToMins = Integer.parseInt(timeParts[0]) * 60;
        return hoursToMins + Integer.parseInt(timeParts[1]);
    }

    @Override
    public int compareTo(Section other) {
        if (!Objects.equals(this.gradeScore, other.gradeScore)) return other.gradeScore.compareTo(this.gradeScore);
        if (!Objects.equals(this.timeScore, other.timeScore)) return this.timeScore.compareTo(other.timeScore);
        if (!Objects.equals(this.distance, other.distance)) return this.distance.compareTo(other.distance);
        return this.sectionNumber.compareTo(other.sectionNumber);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s", sectionNumber, professor, startTime);
    }
}


public class Danny {
    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();
        int professors = scanner.nextInt();

        Map<String, Double> professorRatings = new HashMap<>();

        for (int i = 0; i < professors; i++) {
            String professor = scanner.next();
            Double rating = Double.parseDouble(scanner.next());
            professorRatings.put(professor, rating);
        }

        while (cases-- > 0) {
            String courseName = scanner.next().trim();
            int numSections = Integer.parseInt(scanner.nextLine().trim());
            Set<Section> courseSections = new TreeSet<>();

            while (numSections-- > 0) {
                Section nextSection = new Section(scanner.nextLine().trim().split("\\s+"), professorRatings);
                courseSections.add(nextSection);
            }

            System.out.println(courseName + ":");
            for (Section section : courseSections) {
                System.out.println(section);
            }
        }
        scanner.close();
    }
}
