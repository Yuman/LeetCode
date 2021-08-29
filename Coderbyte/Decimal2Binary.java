package Coderbyte;

public class Decimal2Binary {
    public String dec2binI(int num) {
        String res = "";
        for (; num > 0; num /= 2) {
            if (num % 2 == 0) {
                res = '0' + res;
            } else {
                res = '1' + res;
            }
        }
        return res;
    }

    public String dec2binR(int num) {
        if (num == 0)
            return "";
        if (num % 2 == 0)
            return '0' + dec2binR(num / 2);
        else
            return '1' + dec2binR(num / 2);
    }

    public static void main(String[] a) {
        Decimal2Binary d2b = new Decimal2Binary();
        System.out.println("dec2binI: " + d2b.dec2binI(9));
        System.out.println("dec2binR: " + d2b.dec2binR(9));
    }
}
