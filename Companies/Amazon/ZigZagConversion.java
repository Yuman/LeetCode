package Companies.Amazon;
/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:
*/
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        char[] c = s.toCharArray();
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
//        Arrays.fill(sbs, new StringBuilder());  // !!! not work
        int i = 0;
        while (i < s.length()) {
            for (int ix = 0; ix < numRows && i < s.length(); ix++) {
                sbs[ix].append(c[i++]);
            }
            for (int ix = numRows-2; ix >=1 && i < s.length(); ix--) {
                sbs[ix].append(c[i++]);
            }
        }
        for (int row = 1; row < numRows; row++) {
            sbs[0].append(sbs[row]);
        }
        return sbs[0].toString();
    }
}
