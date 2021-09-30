package Topics.Design;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=3hd7zLNesaE&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=13
 * Time Complexity: O(1) Space Complexity: O(N) Single stack need O(2*N) space,
 * add a minstack can help reduce the space, but if there are multiple same min
 * existing, can push freq also as int[] pair to minstack.
 *
 * If need to popMin(),
 * 
 * @see MaxStack use treemap to make a balance.
 */
public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack; // to keep track of running min up to this point

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek()))
            minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
