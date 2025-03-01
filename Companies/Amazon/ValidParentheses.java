package Companies.Amazon;

import java.util.Stack;

/**
 * @Follow-up1: What if the string contains other characters? Add one more if
 *              statement, simply ignore those characters.
 *
 * @Follow-up2: What if we add single quotations marks?
 *
 *              Input: {'}' -> Output: false Explanation: Because we only have 1
 *              quotation after bracket.
 *
 *              I guess if we consider ' as another type of closing bracket,
 *              everything else is same.
 *
 * @Follow-up3: What if there are 'X's, which can replace any of the bracket.
 *
 * @Follow-up4: Return minimum add to make parentheses valid?
 * @see Companies.Facebook.MinimumAddtoMakeParenthesesValid
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        // Iterate through string until empty
        for (int i = 0; i < s.length(); i++) {
            // Push any open parentheses onto stack
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
                stack.push(s.charAt(i));
            // Check stack for corresponding closing parentheses, false if not valid
            else if (s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
                stack.pop();
            else if (s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
                stack.pop();
            else if (s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
                stack.pop();
            // else
            // return false;
        }
        // return true if no open parentheses left in stack
        return stack.empty();
    }

    public boolean isValidSqueeze(String s) { // not working on "()[]{}"
        for (int i = 0, j = s.length() - 1; i < j; i++) {
            char left = s.charAt(i);
            if (left == '{' || left == '[' || left == '(') {
                for (char right = s.charAt(j); right != '}' || right != ']' || right != '}'; ) {
                    right = s.charAt(j--);
                    if (isMatching(left, right)) {
                        break;
                    } else
                        return false;
                }
            }
        }
        return true;
    }

    public boolean isValidII(String s) {
        char[] ch = s.toCharArray();
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            if (top < 0 || !isMatching(ch[top], ch[i])) {
                top++;
                ch[top] = ch[i];
            } else {
                top--;
            }
        }
        return top == -1;
    }

    /**
     * @Follow-up: Using 'X' to replace any of the bracket.
     */
    private static boolean isBalanced(String s, Stack<Character> stack, int index) {
        if (index == s.length()) {
            return stack.size() == 0;
        }
        char cur = s.charAt(index);
        if (cur == '{' || cur == '(' || cur == '[') {
            stack.push(cur);
            return isBalanced(s, stack, index + 1);
        } else if (cur == '}' || cur == ')' || cur == ']') {
            if (stack.size() == 0) {
                return false;
            }
            char top = stack.pop(); // should be matching bracket or 'X'
            if (!isMatching(top, cur)) {
                return false;
            }
            return isBalanced(s, stack, index + 1);
        } else if (cur == 'X') {
            Stack<Character> temp = new Stack<>();
            temp.addAll(stack);
            temp.push(cur);
            if (isBalanced(s, temp, index + 1)) {
                return true;
            }
            if (stack.size() == 0) {
                return false;
            }
            stack.pop();
            return isBalanced(s, stack, index + 1);
        }
        return true;
    }

    private static boolean isMatching(char a, char b) {
        if ((a == '{' && b == '}') || (a == '[' && b == ']') || (a == '(' && b == ')') || a == 'X') {
            return true;
        }
        return false;
    }

    public static void main(String... args) {
        System.out.println(isBalanced("{(X[X])}", new Stack<>(), 0));
        System.out.println(isBalanced("[{X}(X)]", new Stack<>(), 0));
        ValidParentheses vp = new ValidParentheses();
        System.out.println("Stack: " + vp.isValid("{(X[X])}"));
        System.out.println("Squeeze: " + vp.isValidSqueeze("{(X[X])}"));
    }
}
