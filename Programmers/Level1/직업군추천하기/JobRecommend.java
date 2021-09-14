
import java.util.*;

public class JobRecommend {
    public static void main(String[] args) {
        String[] table = { "SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
                "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
                "GAME C++ C# JAVASCRIPT C JAVA" };
        String[] languages = { "PYTHON", "C++", "SQL" };
        int[] preference = { 7, 5, 5 };
        System.out.println(solution(table, languages, preference));
    }
    public static String solution(String[] table, String[] languages, int[] preference) {
        List<JobScore> jobScores = new ArrayList<>();
        String[] info = {};

        for (String s : table) {
            info = s.split(" ");
            String infoName = info[0];
            int curSum = 0;

            for (int i = 1; i < 6; i++) {
                for (int j = 0; j < languages.length; j++) {
                    if (info[i].equals(languages[j])) {
                        curSum += (6 - i) * preference[j];
                        break;
                    }
                }
            }
            jobScores.add(new JobScore(infoName, curSum));

            //sort 점수순으로 내림차순 정렬, 이름순으로 오름차순 정렬
            Collections.sort(jobScores);
            //jobScores.sort(Comparator.comparing(JobScore::getScore).reversed().thenComparing(JobScore::getName));
        }
        return jobScores.get(0).getName();
    }
}
// VO
class JobScore implements Comparable<JobScore> {
    private String name;
    private int score;

    public JobScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }
    public Integer getScore(){
        return this.score;
    }

    @Override
    public int compareTo(JobScore o) {
        if (this.score < o.getScore()) {
            return 1;
        } else if (this.score > o.getScore()) {
            return -1;
        }else { //점수가 같으면 이름순으로 정렬
            if (this.name.compareTo(o.getName()) > 0) {
                return 1;
            }else if(this.name.compareTo(o.getName()) < 0) {
                return -1;
            }
            return 0;
        }
    }
}
