package com.zhuhangjie.datastructure.tree.unionfind;

public class UnionFind1 implements UF{
  private int[] id;

  public UnionFind1(int size) {
    id = new int[size];
    for (int i = 0; i < id.length; i++) {
      id[i] = i;
    }
  }

  @Override
  public int getSize() {
    return id.length;
  }

  private int find(int q) {
    if (q < 0 || q>=id.length) {
      throw new IllegalArgumentException("index q can't smaller than 0 or bigger than id.length");
    }
    return id[q];
  }

  //时间复杂度O(1)
  @Override
  public boolean isConnected(int p, int q) {
    return find(q) == find(p);
  }

  //时间复杂度O(n)
  @Override
  public void unionElements(int p, int q) {
    int pID = find(p);
    int qID = find(q);

    if (qID == pID) {
      return;
    }
    //把所有pID改成qID，或者反过来也是一样的
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pID) {
        id[i] = qID;
      }
    }
  }
}
