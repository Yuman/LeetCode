/*
make a table iteratively
*/

const fib = (n) => {
    const tab = Array(n + 1).fill(0);
    tab[1] = 1;
    for (let i = 0; i <= n; i++) {
        tab[i + 1] += tab[i];
        tab[i + 2] += tab[i];
    }
    return tab;
}

console.log(fib(6));

/*
gridTravler 3:22:23
*/