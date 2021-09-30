package Companies.Amazon;

public class AddStrings {
    /*
     * 
     * Given two non-negative integers, num1 and num2 represented as string, return
     * the sum of num1 and num2 as a string.
     * 
     * You must solve the problem without using any built-in library for handling
     * large integers (such as BigInteger). You must also not convert the inputs to
     * integers directly.
     * 
     * This is useful in handling the addition of big integers above 2^31.
     * 
     * Example 1:
     * 
     * Input: num1 = "11", num2 = "123" Output: "134" Example 2:
     * 
     * Input: num1 = "456", num2 = "77" Output: "533" Example 3:
     * 
     * Input: num1 = "0", num2 = "0" Output: "0"
     * 
     * 
     * Constraints:
     * 
     * 1 <= num1.length, num2.length <= 104 num1 and num2 consist of only digits.
     * num1 and num2 don't have any leading zeros except for the zero itself.
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        // int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1, carry = 0; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = x + y + carry;
            sb.append(sum % 10);  // 
            //sb.insert(0, sum%10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    public String addStringsChar(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        char[] num1Array = num1.toCharArray();
        char[] num2Array = num2.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry == 1) {
            int a = i >= 0 ? (num1Array[i--] - '0') : 0;
            int b = j >= 0 ? (num2Array[j--] - '0') : 0;
            int sum = a + b + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // tricks involved:
        // moving from the tail: counter--
        // digitChar -'0'
        // 1's num%10
        // 10s: num/10
        AddStrings adder = new AddStrings();
        System.out.println(adder.addStrings("12345", "123456"));
    }
}
