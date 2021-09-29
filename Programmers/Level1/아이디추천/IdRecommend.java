package com.algorithmTraining;

public class IdRecommend {
    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        String new_id2 ="z-+.^.";
        String id = "=.=";

        System.out.print(solution(new_id) + "\n"); //expected value: "bat.y.abcdefghi"
        System.out.print(solution(new_id2)+ "\n"); //expected value: "z--"
        System.out.print(solution(id)); //expected value: "aaa"
    }
    public static String solution(String new_id) {
        String answer ="";
        new_id = new_id.toLowerCase();  //1
        new_id =new_id.replaceAll("[^a-z0-9._-]", "");//2
        new_id = new_id.replaceAll("[.]{2,}", "."); //3
        new_id = new_id.replaceAll("^[.]|[.]$", ""); //4
        new_id = new_id.isEmpty() ? "a" : new_id; //5

        if(new_id.length() > 15){ //6
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("^[.]|[.]$", "");
        }

        while (new_id.length() < 3){ //7
            new_id = new_id + new_id.charAt(new_id.length()-1);
        }

        answer = new_id;
        return  answer;
    }
}
