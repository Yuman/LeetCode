package Companies.Amazon;

/**
 * @see IntegerToRoman
 */
public class RomanToInteger {
    public int romanToInt(String s) {
        int val = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            char c = s.charAt(i);
            switch(c) {
                case 'I': val += (val >= 5?-1:1); break;
                case 'V': val += 5; break;
                case 'X': val += (val >= 50?-10:10);break;
                case 'L': val += 50; break;
                case 'C': val += (val >= 500?-100:100);break;
                case 'D': val += 500; break;
                case 'M': val += 1000;
            }
        }
        return val;
    }
}
