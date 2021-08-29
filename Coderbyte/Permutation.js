/*
varying the order
N! complexity
*/
const permu = (elements) => {
    if (elements.length === 0) return [[]];
    const first = elements.pop();
    const rest = elements;
    const perm_All = [];
    const perm_WO_first = permu(elements);
    perm_WO_first.forEach(p => {
        for (let i = 0; i <= p.length; i++) {
            const p_W_first = [...p.slice(0, i), first, ...p.slice(i)];
            // insert into all i positions
            perm_All.push(p_W_first);
        }
    });
    return perm_All;
};


console.log(permu(['a', 'b', 'c']));