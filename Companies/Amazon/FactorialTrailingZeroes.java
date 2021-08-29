package Companies.Amazon;
/*
Given an integer n, return the number of trailing zeroes in n!.
*/
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int re = 0;
        while (n > 0) {
            n /= 5;
            re += n;
        }
        return re;

        // return n == 0? 0 : n/5 + trailingZeroes(n/5);
    }
}
/*
Every factorial can be expressed as -

n! = 2^x * 5^y * ...... where x and y >= 0

number of zeros at the end is basically min(x,y) which will always be y because multiples of 2 appear more frequently than multiples of 5....so basically we need to find y

n can be expressed as 1....5....10.....15.....20....25.... 5*i ...........n (for every i > 5)

n! = 1*...5*....10......15...20...25.......5*i..n (for every i > 5)

n/5 = number of terms contributing one 5s in above sequence
n/5^2 (or n/5/5) = number of terms contributing two 5s in above sequence (their one 5 is included in above)
n/5^3 (or n/5/5) = number of terms contributing three 5s in above sequence (their two 5s is included in above)
n/5^y = number of terms contributing y 5s in above sequence (their y-1 5s is included in above
(keep dividing n as long as n is > 5, numbers below 5 contribute 0 5s...6 contribute 1 5 because 6! = 6 x 5 x 4 x 3 x 2 x 1)

So find how many contribute one 5s, then two 5s ....and finally y 5s. Add them together to find how many are in factorial (product of terms is sum of exponent)
Following loop will count how many "y" 5s are there in 5^y

y =  0
while n:
        n /= 5
        y += n
return y
*/