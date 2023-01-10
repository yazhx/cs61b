public class UnionFind{
    private int[] parents;
    private int[] sz;

    public UnionFind(int n) {
        parents = new int[n];
        sz = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sz[i] = 1;
        }
    }

    public void validate(int v1) {
        if (v1 < 0 || v1 >= parents.length)
            throw new IndexOutOfBoundsException(v1 + " is not a valid index");
    }

    public int sizeOf(int v1) {
        return sz[v1];
    }

    public int parent(int v1) {
         return find(v1);
    }

    public int find(int v1) {
        /*
        find the parent of p
         */
        while (v1 != parents[v1])
            v1 = parents[v1];
        return v1;
    }

    public void union(int v1, int v2) {
        if (connected(v1, v2))
            return;
        int v1Parent = find(v1);
        int v2Parent = find(v2);
        if (sizeOf(v1Parent) <= sizeOf(v2Parent)) {
            parents[v1Parent] = v2Parent;
            sz[v2Parent] += sz[v1Parent];
        } else {
            parents[v2Parent] = v1Parent;
            sz[v1Parent] += sz[v2Parent];
        }
    }

    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }


    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        uf.union(0, 2);
        uf.union(0, 3);
        uf.union(5, 3);
        uf.union(4, 3);
        uf.union(6, 7);
        uf.union(6, 8);

    }
}
