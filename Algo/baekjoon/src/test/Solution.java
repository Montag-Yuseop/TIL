package test;

import java.util.*;

class Solution {
    public String[] solution(String[] files) {

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String head1 = o1.split("[0-9]")[0];
                String head2 = o2.split("[0-9]")[0];
                int result = head1.toLowerCase().compareTo(head2.toLowerCase());

                if(result == 0) {
                    String h1 = o1.replace(head1, "");
                    String h2 = o2.replace(head2, "");
                    int tmp1 = 0;
                    int tmp2 = 0;

                    for (int i = 0; i < Math.min(h1.length(), 5) ; i++) {
                        if (!Character.isDigit(h1.charAt(i))) {
                            tmp1 = Integer.parseInt(h1.substring(0, i));
                            break;
                        }

                        if(i == 4) {
                            tmp1 = Integer.parseInt(h1.substring(0,5));
                        }
                    }
                    for (int i = 0; i < Math.min(h2.length(), 5); i++) {
                        if (!Character.isDigit(h2.charAt(i))) {
                            tmp2 = Integer.parseInt(h2.substring(0, i));
                            break;
                        }
                        if(i == 4) {
                            tmp2 = Integer.parseInt(h2.substring(0,5));
                        }
                    }
                    result = tmp1 - tmp2;
                }

                return result;
            }
        });


        return files;
    }
}