//https://www.youtube.com/watch?v=tWVWeAqZ0WU 17:00
const depthFirstPrint = (graph, source) => {
    console.log(source);
    // if (!graph[source]) return;
    for (let adj of graph[source]) {
        depthFirstPrint(graph, adj);
    }
}

const bfPrint = (graoh, src) => {
    let q = [src];
    while (q.length > 0) {
        let curr = q.shift();
        console.log(curr);
        for (let adj of graph[curr]) {
            q.push(adj);
        }
    }

}
const graph = {
    a: ['b', 'c'],
    b: ['d'],
    c: ['e'],
    d: ['f'],
    e: [],
    f: []
};

//depthFirstPrint(graph, 'a')
//console.log('bfs:')
//bfPrint(graph, 'a')
//https://www.youtube.com/watch?v=tWVWeAqZ0WU 17:00
// to run, 
// cd CodeCamp; 
// node graph.js

// hasPath 30:00? src=f, dst=k
const pathGraph = {
    f: ['g', 'i'],
    g: ['h'],
    h: [],
    i: ['g', 'k'],
    j: ['i'],
    k: []
}

const hasPathBF = (graph, src, dst) => {
    let q = [src];
    while (q.length > 0) {
        let curr = q.shift();
        if (curr == dst) return true;
        for (let adj of graph[curr]) {
            q.push(adj);
        }
    }
    return false;
}

const hasPathDF = (graph, src, dst) => {
    if (src == dst) return true;
    for (let adj of graph[src]) {
        if (hasPathDF(graph, adj, dst)) return true;
    }
    return false;
}

// console.log('BFS hasPath: ' + hasPathBF(pathGraph, 'f', 'k'));
// console.log('DFS hasPath: ' + hasPathDF(pathGraph, 'f', 'k'));

//undirected 43:00
// given edges[], find path between nodeA and NodeB
const undirectedPath = (edges, nodeA, nodeB) => {
    let g = buildGraph(edges);
    return hasPathDfs(g, nodeA, nodeB, new Set());
}

const hasPathDfs = (g, src, dst, visited) => {
    if (src == dst) return true;
    if (visited.has(src)) return false;
    visited.add(src);
    for (let adj of g[src]) {
        if (hasPathDfs(g, adj, dst, visited)) {
            return true;
        }
    }
    return false;
}

const buildGraph = (edges) => {
    let g = {};
    for (let edge of edges) {
        if (g[edge[0]]) {
            g[edge[0]].push(edge[1])
        }
        else {
            g[edge[0]] = [edge[1]];
        }

        if (g[edge[1]]) {
            g[edge[1]].push(edge[0])
        }
        else {
            g[edge[1]] = [edge[0]];
        }
    }
    return g;
}
const edges = [
    ['i', 'j'],
    ['k', 'i'],
    ['m', 'k'],
    ['k', 'l'],
    ['o', 'n']
];

console.log(buildGraph(edges));
console.log(undirectedPath(edges, 'k', 'n'));

//count connected graph components 1:01
// iterate through the nodes, 
// increment component count when starting an unvisited node

// largest component
// approach 1:13, coding 1:18

let gc = {
    0: ['8', '1', '5'],
    1: ['0'],
    5: ['0', '8'],
    8: ['0', '5'],
    2: ['3', '4'],
    3: ['2', '4'],
    4: ['3', '2']
};

//shortest path, count of edges away
// approach 1:24 code 1:31

// island count 1:39, code 1:45:40
// don't look before you leap -- look in the base case, in one place

// minimum island 1:58:30, code 2:03