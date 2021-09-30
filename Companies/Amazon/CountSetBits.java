package Companies.Amazon;

class CountSetBits {
    /*
     * Function to get number of set bits in binary representation of positive
     * integer n
     */
    static int countBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    static int countBitsAPI(int n) {
        return Integer.bitCount(n);
    }

    public static void main(String args[]) {
        int i = 9;
        System.out.println("Shift: " + countBits(i));
        System.out.println("API: " + countBitsAPI(i));
    }
}
