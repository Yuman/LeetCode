/*
https://www.freecodecamp.org/learn/coding-interview-prep/algorithms/find-the-symmetric-difference
Find the Symmetric Difference
The mathematical term symmetric difference (△ or ⊕) of two sets is the set of elements 
which are in either of the two sets but not in both. For example, 
for sets A = {1, 2, 3} and B = {2, 3, 4}, A △ B = {1, 4}.

Symmetric difference is a binary operation, which means it operates on only two elements. 
So to evaluate an expression involving symmetric differences among three elements (A △ B △ C), 
you must complete one operation at a time. Thus, for sets A and B above,
 and C = {2, 3}, A △ B △ C = (A △ B) △ C = {1, 4} △ {2, 3} = {1, 2, 3, 4}.

Create a function that takes two or more arrays and returns an array of their symmetric difference. 
The returned array must contain only unique values (no duplicates).
*/
function sym(...args) {
     if (args.length = 2) return sym2(args[0], args[1]);
    return sym(args[0], args.shift());
}

sym2 = (a1, a2) => {
    let res = [];
    for (let e1 of a1) {
        if (!a2.includes(e1)) res.push(e1);
    }
    for (let e2 of a2) {
        if (!a1.includes(e2)) res.push(e2);
    }
    return res;
}

console.log(sym([3, 3, 3, 2, 5], [2, 1, 5, 7], [3, 4, 6, 6], [1, 2, 3], [5, 3, 9, 8], [1]));
//console.log(sym2([1, 2, 3], [5, 2, 1, 4]));