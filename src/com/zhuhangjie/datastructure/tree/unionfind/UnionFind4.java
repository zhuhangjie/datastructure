package com.zhuhangjie.datastructure.tree.unionfind;

public class UnionFind4 implements UF {

  private int[] parent;
  private int[] rank;

  public UnionFind4(int size) {
    parent = new int[size];
    rank = new int[size];
    for (int i = 0; i < parent.length; i++) {
      parent[i] = i;
      rank[i] = 1;
    }
  }

  @Override
  public int getSize() {
    return parent.length;
  }

  private int findRoot(int q) {
    if (q < 0 || q >= parent.length) {
      throw new IllegalArgumentException("index q can't smaller than 0 or bigger than id.length");
    }
    //当自己的父节点不是自己说明还没到根节点
    while (q != parent[q]) {
      q = parent[q];
    }
    return q;
  }

  //时间复杂度O(1)
  @Override
  public boolean isConnected(int p, int q) {
    return findRoot(q) == findRoot(p);
  }

  //时间复杂度O(n)
  @Override
  public void unionElements(int p, int q) {
    int pRoot = findRoot(p);
    int qRoot = findRoot(q);

    if (pRoot == qRoot) {
      return;
    }
    if (rank[pRoot] < rank[qRoot]) {
      parent[pRoot] = qRoot;
    } else{
      parent[qRoot] = pRoot;
      if (rank[qRoot] == rank[pRoot]) {
        rank[pRoot] += 1;
      }
    }
  }
}
