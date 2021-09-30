package Companies.Amazon;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:
*/

/*n=numRows
Δ=2n-2    1                           2n-1                         4n-3
Δ=        2                     2n-2  2n                    4n-4   4n-2
Δ=        3               2n-3        2n+1              4n-5       .
Δ=        .           .               .               .            .
Δ=        .       n+2                 .           3n               .
Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
Δ=2n-2    n                           3n-2                         5n-4
*/
public class ZigZagConversion {
    // Find cycle pattern = numRows * 2 - 1, then use charArray to solve this problem
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        int cycle = numRows * 2 - 2;
        char[] charArray = new char[s.length()];
        int writer = 0;
        for (int reader = 0; reader < s.length(); reader += cycle) { // 1st row
            charArray[writer] = s.charAt(reader);
            writer++;
        }
        for (int i = 1; i < numRows - 1; i++) {
            for (int j = i, k = cycle - j; j < s.length(); j += cycle, k += cycle) {
                charArray[writer] = s.charAt(j);
                writer++;
                if (k < s.length()) {
                    charArray[writer] = s.charAt(k);
                    writer++;
                }
            }
        }
        for (int i = numRows - 1; i < s.length(); i += cycle) { // last row
            charArray[writer] = s.charAt(i);
            writer++;
        }
        return String.valueOf(charArray);
    }
}
