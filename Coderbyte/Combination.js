/*
a collection of things where the order does NOT matter
[a, b] = [b, a]
2^n
at every level of tree, include an element 

Leetcode 78
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
https://www.youtube.com/watch?v=B8ZEcSAliM4
 
*/

const combo = (elements) => {
    if (elements.length === 0) return [[]];
    // const first = elements[0]
    // const rest = elements.slice(1);
    const first = elements.pop()
    const rest = elements;
    const combo_W_first = [];
    const combo_WO_first = combo(rest);
    combo_WO_first.forEach(cm => {
        const cm_W_first = [...cm, first];
        combo_W_first.push(cm_W_first);

    });
    return [...combo_WO_first, ...combo_W_first];
}

console.log(combo(['a', 'b', 'c']));

