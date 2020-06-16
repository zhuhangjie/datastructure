package com.zhuhangjie.datastructure.array;

/**
 * 1.封装自己的数组
 *
 * 把数据排成一排存放
 * 数组的索引可以有语意也可以没有语意
 *
 * 数组最大的优点：快速查询。
 * 数组最好应用于“索引有语意”的情况，比如：student[2]代表学号为2的同学。
 * 并非所有语意的索引都适用于数组
 * 身份证号：330192819283719203这种就太长了，数组会开辟这么大的空间，太浪费。
 *
 * 有语意太简单了，所以这里主要处理“索引没有语意”的情况数组的使用。
 *
 * 如果创建大小为8的数组，但是只有前3个位置有元素，如何表示没有元素？如何添加删除元素？
 * 数组大小创建时就固定为8，如果数组满了，要继续放，怎么办？
 *
 * 数组创建的时候会确定一个大小，叫做capacity（容量）,实际有多少个元素叫size
 */

/**
 * 2.往数组添加元素
 *
 * 因为一开始size的大小为0，而数组是从0索引开始存数据的，size为数组中第一个没有元素的位置
 * 所以向一个新数组插入元素相当于在索引为size的位置插入元素，插入后维护一下size++就行了
 */

/**
 * 3.时间复杂度
 *
 * （1）增
 * addLast(e)     O(1)
 * addFirst(e)      O(n)
 * add(int index, E e)      O(n/2)=O(n)
 * resize(capacity)     O(n) 添加操作可能会有resize()发生
 * 所以添加操作总体来说的时间复杂度是O(n)，一般用最坏情况的时间复杂度。
 * （2）删
 * removeLast()      O(1)
 * removeFirst()      O(n)
 * remove(index,e)      O(n)
 * resize(capacity)     O(n) 移除操作可能会有resize()发生
 * 所以删除操作还是O(n)
 * （3）改
 * set(index,e)     O(1) 这也是数组最大的优势，支持随机访问
 * （4）查
 * get(index)     O(1) 知道索引的话查的很快
 * contains(e)      O(n)
 * find(e)      O(n)
 *
 * 总结：
 * 增O(n)
 * 删O(n)
 * 改 未知索引O(n) 已知索引O(1)
 * 查 未知索引O(n) 已知索引O(1)
 * 最终结论，使用数组的时候最好的情况就是索引具有一定的语意
 */

/**
 * 4.均摊复杂度
 *
 * addLast(e)     O(1)
 * 但是会触发resize，所以按照最坏是O(n)
 * 但不是每次都触发resize，按照计算
 * capacity=n 的数组大概添加n+1次操作会resize,而resize会移动n次数组
 * 所以总计就是n+1+n = 2n+1     (2n+1)/(n+1)就是平均每resize一次的操作，大约比2小一点点
 * 所以其实均摊时间复杂度为O(1)
 * addLast和removeLast因为resize界限的问题会产生每次都resize从而产生复杂度震荡问题
 * 产生原因是太过急于(Eager)resize
 * 解决方案：Lazy
 * 当数组中元素size是容积capacity的1/4时才减半
 */

public class Array<E> {
  //数组的容量capacity=data.length
  private E[] data;
  //size为下一个要插入元素的位置
  private int size;

  public Array(E[] arr) {
    data = (E[])new Object[arr.length];
    for (int i = 0; i < arr.length; i++) {
      data[i] = arr[i];
    }
    size = arr.length;
  }

  public Array(int capacity) {
    //因为Java不能支持new E[capacity]这种泛型的写法，所以要绕个弯
    data = (E[])new Object[capacity];
    size = 0;
  }
  public Array() {
    this(10);
  }

  public int getSize() {
    return size;
  }

  public int getCapacity() {
    return data.length;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(E e) {
    for (int i = 0; i < size; i++) {
      if (data[i].equals(e)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 返回元素对应索引
   * @param e
   * @return
   */
  public int find(E e) {
    for (int i = 0; i < size; i++) {
      if (data[i].equals(e)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 通过封装的方式，保证用户永远无法访问到那些array中没有使用的空间。
   * @param index
   * @return
   */
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("addLast failed. Require index >=0 且 index <= size");
    }
    return data[index];
  }

  public E getFirst(){
    return get(0);
  }

  public E getLast() {
    return get(size-1);
  }

  public void set(int index, E e){
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("addLast failed. Require index >=0 且 index <= size, errorIndex = " + index + ", size = " + size);
    }
    data[index] = e;
  }

  public void addLast(E e) {
    add(size,e);
    //有了add(int index, int e)后就不用下面这个了
//    if (size == data.length) {
//      throw new IllegalArgumentException("addLast failed. Array is full.");
//    }
//    data[size] = e;
//    size++;
  }

  public void addFirst(E e) {
    add(0,e);
  }

  public void add(int index, E e) {
    if (size == data.length) {
      //这里如果容量已经满了，就扩容成两倍。注意，Java的ArrayList扩容系数是1.5倍。
      resize(data.length * 2);
    }
    if (index < 0 || index > size) {
      throw new IllegalArgumentException("addLast failed. Require index >=0 且 index <= 0");
    }
    for (int i=size; i > index; i--) {
      data[i] = data[i-1];
    }
    data[index] = e;
    size++;
  }

  /**
   * 这里不用返回被删除元素，因为用户在调用的时候就知道自己删除的是什么元素了
   * @param e
   */
  public void removeElement(E e) {
    int index = find(e);
    if (index != -1) {
      remove(index);
    }
  }

  public E removeFirst() {
    return remove(0);
  }

  public E removeLast() {
    return remove(size-1);
  }

  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("addLast failed. Require index >=0 且 index < size");
    }
    E ret = data[index];
    //这里最后在size的地方会遗留一个和size-1一样的元素，但是没关系，因为size-1了所以用户永远无法访问到这个重复的元素。
    for (int i=index ; i<size-1; i++) {
      data[i] = data[i+1];
    }
    size-- ;
    //因为size这个位置无法访问到，但是改成泛型后会是一个对象，
    // 这个对象因为根节点枚举会被扫描到所以不会被回收，所以最好手动设置null，让GC把对象回收了
    data[size] = null;
    //为了防止复杂度震荡，到1/4再缩容。此外如果长度为1的话，就让数组无法再缩容，所以要限制长度大于1。
    if (size == data.length / 4 && data.length > 1) {
      resize(data.length / 2);
    }
    return ret;
  }

  /**
   * 其实这个resize方法贼鸡儿简单，就是创建一个给定容积的数组把原数组的数据循环一个个放进去。
   * @param newCapacity
   */
  public void resize(int newCapacity) {
    E[] newData = (E[])new Object[newCapacity];
    //这里不能用System.arrayCopy() 因为这个方法的srcArray如果比destArray容量大的话就会报错,这里还有可能缩容。
    //因此在缩容的时候会抛异常，所以还是用循环方法来给新数组赋值。
    for (int i = 0; i < size; i++) {
      newData[i] = data[i];
    }
    data = newData;
  }

  public void swap(int i, int j){

    if (i < 0 || i >= size || j < 0 || j >= size) {
      throw new IllegalArgumentException("index error");
    }

    E temp = data[j];
    data[j] = data[i];
    data[i] = temp;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    res.append(String.format("Array: size = %d , capacity = % d\n", size, data.length));
    res.append("[");
    for (int i = 0; i < size; i++) {
      res.append(data[i]);
      if ( i != size-1) {
        res.append(", ");
      }
    }
    res.append("]");
    return res.toString();
  }
}
