const median = arr => {
    let len = arr.length;
    if (len % 2 == 1) {
        let mo = arr[Math.floor(len / 2)];
        return mo;
    }

    else {
        let m = (arr[len / 2] + arr[len / 2 - 1]) / 2;
        return m;
    }
}



// This function returns median of ar1[] and ar2[].
// Assumption in this function:
// Both ar1[] and ar2[] are sorted arrays
function getMedian(ar1, ar2) {
    let iEnd = ar1.length, jEnd = ar2.length;
    // Current index of input array ar1[]
    let i = 0;

    // Current index of input array ar2[]
    let j = 0;
    let count;
    let mOdd = -1, mEven = -1;

    // Since there are (n+m) elements,
    // There are following two cases
    // if n+m is odd then the middle
    // index is median i.e. (m+n)/2
    if ((jEnd + iEnd) % 2 == 1) {
        for (count = 0;
            count <= (iEnd + jEnd) / 2;
            count++) {
            if (i != iEnd && j != jEnd) {
                mOdd = (ar1[i] > ar2[j]) ?
                    ar2[j++] : ar1[i++];
            }
            else if (i < iEnd) {
                mOdd = ar1[i++];
            }

            // For case when j<m,
            else {
                mOdd = ar2[j++];
            }
        }
        return mOdd;
    }
    // Median will be average of elements
    // at index ((m+n)/2 - 1) and (m+n)/2
    // in the array obtained after merging
    // ar1 and ar2
    else {
        for (count = 0;
            count <= (iEnd + jEnd) / 2;
            count++) {
            mEven = mOdd;
            if (i != iEnd && j != jEnd) {
                mOdd = (ar1[i] > ar2[j]) ?
                    ar2[j++] : ar1[i++];
            }
            else if (i < iEnd) {
                mOdd = ar1[i++];
            }

            // For case when j<m,
            else {
                mOdd = ar2[j++];
            }
        }
        return (mOdd + mEven) / 2;
    }
}
//console.log(median([1, 2, 3, 4, 5, 6, 7]))

//console.log(merge([1, 2, 3, 4, 5, 6, 7], [5, 6, 7, 8, 9]))
console.log(getMedian([1, 2, 3, 4, 5, 6, 7], [8, 9], 7, 2))
