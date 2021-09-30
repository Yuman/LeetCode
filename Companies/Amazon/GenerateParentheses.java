package Companies.Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
*/
public class GenerateParentheses {
    /*
     * The goal is to print a string of “(“ ,”)” in certain order. The length of
     * string is 2n. The constraints are that “(“s need to match “)”s. Without
     * constraints, we just simply print out “(“ or “)” until length hits n. So the
     * base case will be length ==2 n, recursive case is print out “(“ and “)”. The
     * code will look like
     * 
     * //base case if(string length == 2*n) { add(string); return; } //recursive
     * case add a “(“ add a “)"
     * 
     * Let’s add in constraints now. We need to interpret the meanings of
     * constraints. First, the first character should be “(“. Second, at each step,
     * you can either print “(“ or “)”, but print “)” only when there are more “(“s
     * than “)”s. Stop printing out “(“ when the number of “(“ s hit n. The first
     * actually merges into the second condition. The code will be:
     * 
     * //base case if(string length == 2*n) { add(string); return; } //recursive
     * case if(number of “(“s < n){ add a “(“ } if(number of “(“s > number of “)”s){
     * add a “)" }
     * 
     * https://www.youtube.com/watch?v=qBbZ3tS0McI
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    private void backtrack(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        System.out.println("Recurse: " + gp.generateParenthesis(2));
        System.out.println("Iterate: " + gp.generateParenthesis(2));
    }

    /*
     * My method is DP. First consider how to get the result f(n) from previous
     * result f(0)...f(n-1). Actually, the result f(n) will be put an extra () pair
     * to f(n-1). Let the "(" always at the first position, to produce a valid
     * result, we can only put ")" in a way that there will be i pairs () inside the
     * extra () and n - 1 - i pairs () outside the extra pair.
     * 
     * Let us consider an example to get clear view:
     * 
     * f(0): ""
     * 
     * f(1): "("f(0)")"
     * 
     * f(2): "("f(0)")"f(1), "("f(1)")"
     * 
     * f(3): "("f(0)")"f(2), "("f(1)")"f(1), "("f(2)")"
     * 
     * So f(n) = "("f(0)")"f(n-1) , "("f(1)")"f(n-2) "("f(2)")"f(n-3) ...
     * "("f(i)")"f(n-1-i) ... "(f(n-1)")"
     */
    public List<String> generateParenthesisI(int n) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));

        for (int i = 1; i <= n; ++i) {
            final List<String> list = new ArrayList<>();

            for (int j = 0; j < i; ++j) {
                for (final String first : lists.get(j)) {
                    for (final String second : lists.get(i - 1 - j)) {
                        list.add("(" + first + ")" + second);
                    }
                }
            }

            lists.add(list);
        }

        return lists.get(lists.size() - 1);
    }
}
