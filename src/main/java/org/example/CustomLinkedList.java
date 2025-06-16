package org.example;

import java.util.NoSuchElementException;
public class CustomLinkedList<E> {
    int sizeOfList;
    private Node <E> first;
    private Node<E> last;

    static class Node<E>{
        E data;
        Node<E> next;
        Node<E> prev;
        Node(E value){
            this.data = value;
        }
    }
    public int size() {
        return sizeOfList;
    }
    public E get(int index) {
        if (index < 0 || index >= sizeOfList) {throw new IndexOutOfBoundsException("Index: " + index + " out of range");}
        Node<E> tempNode = null;
        if(index <= sizeOfList / 2){
            tempNode = first;
            for(int i = 0; i < index; i++){
                tempNode = tempNode.next;
            }
        }
        else{
            tempNode = last;
            for(int i = sizeOfList - 1; i > index; i--){
                tempNode = tempNode.prev;
            }
        }

        return  tempNode.data;
    }
    public void addFirst(E value){

        Node<E> newNode = new Node<>(value);
        sizeOfList++;

        if(first != null){
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        else{
            first = newNode;
            last = newNode;
        }
    }
    public void addLast(E value){

        Node<E> newNode = new Node<>(value);
        sizeOfList++;

        if(last != null){
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        else{
            first = newNode;
            last = newNode;
        }
    }
    public E getFirst(){
        if(first == null){
            throw new NoSuchElementException("List is empty");
        }
        return first.data;
    }
    public E getLast(){
        if(last == null){
            throw new NoSuchElementException("List is empty");
        }
        return last.data;
    }
    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("List is empty");
        }
        E removedData = first.data;
        if (first.next == null) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        sizeOfList--;
        return removedData;
    }
    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException("List is empty");
        }
        E removedData = last.data;
        if (last.prev == null) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        sizeOfList--;
        return removedData;
    }
    public void add(int index, E value) {
        if (index < 0 || index > sizeOfList) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of range");
        }
        if (index == 0) {
            addFirst(value);
        }
        else if (index == sizeOfList) {
            addLast(value);
        } else {

            Node<E> newNode = new Node<>(value);
            Node<E> currentIndexNode;

            if(index <= sizeOfList / 2){

                currentIndexNode = first;

                for(int i = 0; i < index; i++){
                    currentIndexNode = currentIndexNode.next;
                }
                newNode.prev = currentIndexNode.prev;
                newNode.next = currentIndexNode;
                currentIndexNode.prev.next = newNode;
                currentIndexNode.prev = newNode;


            }
            else if(index > sizeOfList / 2){

                currentIndexNode = last;

                for(int i = 0; i < sizeOfList - index - 1; i++){
                    currentIndexNode = currentIndexNode.prev;
                }
                newNode.prev = currentIndexNode.prev;
                newNode.next = currentIndexNode;
                currentIndexNode.prev.next = newNode;
                currentIndexNode.prev = newNode;

            }
            sizeOfList++;
        }
    }
    public E remove(int index) {
        E removedData = null;
        if (index < 0 || index > sizeOfList - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of range");
        }
        if (index == 0) {
            return removeFirst();

        }
        else if (index == sizeOfList - 1) {
            return removeLast();

        } else {

            Node<E> currentIndexNode;

            if(index <= sizeOfList / 2){

                currentIndexNode = first;

                for(int i = 0; i < index; i++){
                    currentIndexNode = currentIndexNode.next;
                }
                removedData = currentIndexNode.data;
                currentIndexNode.prev.next = currentIndexNode.next;
                currentIndexNode.next.prev = currentIndexNode.prev;


            }
            else if(index > sizeOfList / 2){

                currentIndexNode = last;

                for(int i = 0; i < sizeOfList - index - 1; i++){
                    currentIndexNode = currentIndexNode.prev;
                }
                removedData = currentIndexNode.data;
                currentIndexNode.prev.next = currentIndexNode.next;
                currentIndexNode.next.prev = currentIndexNode.prev;

            }
            sizeOfList--;
        }
        return removedData;
    }
    @Override
    public String toString() {
        if (first == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
