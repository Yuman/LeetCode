package Coderbyte;
/**
 * A pair of sorted prime numbers. The sorting prevents duplicates such as (3,5) and (5,3).
 */
public interface PrimePair {
    int getLesser();

    int getGreater();
    
    /**
     * Checks if the pair contains the input as either the lesser or the greater
     * @param either The input to be checked.
     * @return
     */
    boolean has(int either); 

    /**
     * Output the contents as
     * (1, 5)
     * @return
     */
    String toString();
}
