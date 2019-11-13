package com.youguu.listAndMap.linkedList;

/**
 * 1.LinkedList与ArrayList存储或者删除哪个效率高
 * 答：ArrayList集合在删除或者添加的时候，使用的是数组的扩容，
 *    System.arraycopy();效率低
 *    LinkedList不需要数组的扩容，不许要考虑数组的移位。
 *
 * 2.LinkedList查询效率非常低，不是连续的内存，一个一个
 *
 */
public class MyLinkedList<E> {

    public static void main(String[] args) {
        MyLinkedList<String> linkedList = new MyLinkedList<String>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("e");
        System.out.println(linkedList.first.next.object);
        System.out.println(linkedList.first.next.next.object);
        System.out.println(linkedList.first.next.next.next.object);
    }

    private int size;//实际存储元素

    //头节点，为了查询开始
    private Node first;

    //尾节点，为了添加开始
    private Node last;
    //add
    public void add(E e){
        Node node = new Node();
        node.object = e;
        if(first == null){
            //添加一个元素
            first=node;
            last=node;
        }else{
            //添加第二及以上数据
            //把node的prev执行last的
            node.prev = last;
            last.next = node;
        }
        last = node;
        size++;
    }

    //get

    /**
     *  if (index < (size >> 1)) {
     *             Node<E> x = first;
     *             for (int i = 0; i < index; i++)
     *                 x = x.next;
     *             return x;
     *         } else {
     *             Node<E> x = last;
     *             for (int i = size - 1; i > index; i--)
     *                 x = x.prev;
     *             return x;
     *         }
     * @param index
     * @return
     */
    E get(int index){
        if(index<0 ||index >= size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        //如果index在前半部分，从first节点中遍历  二分法查找  >>1等于除以2
        if(index < (size >>1)){
            Node x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return (E) x.object;
        }else{
            Node x = last;
            for (int i = size-1; i > index ; i--) {
                x = x.prev;
            }
            return (E) x.object;
        }
    }


    //链表节点存储元素
    private class Node<E>{

        //1、存放元素的值
        E object;
        //2.存放preNode

        private Node<E> prev;

        private Node<E> next;

    }


}
