//
// Created by sandr on 21.11.2023.
//

#ifndef FPS3_UE06_MC2210237030_MINHEAP_H
#define FPS3_UE06_MC2210237030_MINHEAP_H

#include <iostream>
#include <stdexcept>
#include <vector>
template <typename K>
class MinHeap {
private:
    std::vector<K> heap;
private:
    int heapCapacity() {
        return heap.capacity();
    }
    void upHeap(int index) {
        if (index > size() || index < 0) {
            throw std::out_of_range("Index out of range");
        }

        int i = index;
        while (i > 0 && heap[i] < heap[parent(i)] ) {
            swap(i, parent(i));
            i = parent(i);

        }
    }

    // recursive
    void downHeap(int index) {
        if (index >= size() || index < 0) {
            throw std::out_of_range("Index out of range");
        }

        recursiveDownHeap(index);
    }

    // helper function for downHeap
    void recursiveDownHeap(int index) {
        int left = leftChild(index);
        int right = rightChild(index);
        int smallest = index;

        if (left < size() && heap[left] < heap[smallest]) {
            smallest = left;
        }

        if (right < size() && heap[right] < heap[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(index, smallest);
            recursiveDownHeap(smallest);
        }
    }

    int parent(int index) {
        if (index > size() || index < 0) {
            throw std::out_of_range("Index out of range");
        } else if (index == 0) {
            throw std::out_of_range("root has no parent");
        }

        return (index - 1) / 2;
    }

    int leftChild(int index) {
        if (index > size() || index < 0) {
            throw std::out_of_range("Index out of range");
        }
        /*else if (((index * 2) + 1) >= size()) {
            throw std::out_of_range("there is no left child");
        }*/

        return (index * 2) + 1;
    }

    int rightChild(int index) {
        if (index > size() || index < 0) {
            throw std::out_of_range("Index out of range");
        }
        /*else if (((index * 2) + 2) >= size()) {
            throw std::out_of_range("there is no right child");
        }*/

        return (index * 2) + 2;
    }

    void swap(int index1, int index2) {
        if (index1 > size() || index2 > size() || index1 < 0 || index2 < 0) {
            throw std::out_of_range("Index out of range");
        }

        K value1 = heap[index1];
        K value2 = heap[index2];

        heap[index1] = value2;
        heap[index2] = value1;
    }

public:
    bool isEmpty() { //true if heap is empty, false otherwise
        return heap.empty();
    }

    int size() { // returns the number of elements in the heap
        return heap.size();
    }

    void insert(const K& data) { //inserts the element data into the heap
        heap.push_back(data);
        int i = heap.size() - 1;

        if (size() != 1 && size() > 0) {
            upHeap(i);
        }
    }

    const K& min() { //returns the minimum element
        if (isEmpty()) {
            throw std::out_of_range("Heap is empty");
        }
        return heap[0];
    }

    const K removeMin() { //removes and returns the minimum element
        if (isEmpty()) {
            throw std::out_of_range("Heap is empty");
        }

        K min = heap[0];
        heap[0] = heap.back();
        int i = 0;
        heap.pop_back();

        if (size() != 1 && size() > 0) {
            downHeap(i);
        }

        return min;
    }

    friend std::ostream& operator<<(std::ostream &os, const MinHeap &heap) {
        os << "Heap: ";
        for (const K& k : heap.heap) {
            os << k << " ";
        }
        return os;
    }

};

#endif //FPS3_UE06_MC2210237030_MINHEAP_H
