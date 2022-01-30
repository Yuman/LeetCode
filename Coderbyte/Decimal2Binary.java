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

    public String dec2binShort(int num) {
        String res = "";
        for (; num > 0; num /= 2) {
            res = num % 2 + res;
        }
        return res;
    }

    public String dec2binRecurse(int num) {
        if (num == 0)
            return "0";
        if (num == 1)
            return "1";
        return dec2binRecurse(num / 2) + num % 2;
    }

    public String dec2binRecurseShort(int num) {
        return num == 0 ? "0" : num == 1 ? "1" : dec2binRecurseShort(num / 2) + num % 2;
    }

    public static void main(String[] a) {
        Decimal2Binary d2b = new Decimal2Binary();
        System.out.println("dec2binI-6: " + d2b.dec2binI(6));
        System.out.println("dec2binShort-6: " + d2b.dec2binShort(6));
        System.out.println("dec2binR-6: " + d2b.dec2binRecurse(6));
        System.out.println("dec2binRS-6: " + d2b.dec2binRecurseShort(6));

        System.out.println("dec2binI-8: " + d2b.dec2binI(8));
        System.out.println("dec2binShort-8: " + d2b.dec2binShort(8));
        System.out.println("dec2binR-0: " + d2b.dec2binRecurse(0));
        System.out.println("dec2binRS-8: " + d2b.dec2binRecurseShort(8));
    }
}
