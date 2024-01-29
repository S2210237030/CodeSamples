#include <iostream>
#include "Minheap.h"
#include "Heapsort.h"
#include "Quicksort.h"

int main() {

    // create heap
    MinHeap<int> heap;

    // test isEmpty method
    std::cout << "is heap empty? " << (heap.isEmpty() ? "true" : "false") << std::endl;

    // test insert method
    heap.insert(5);
    heap.insert(3);
    heap.insert(8);
    heap.insert(1);
    heap.insert(4);
    heap.insert(6);
    heap.insert(12);

    // test size method
    std::cout << "size of heap is " << heap.size() << std::endl;

    // test min method
    std::cout << "min of heap is " << heap.min() << std::endl;

    // test removeMin method
    int min = heap.removeMin();
    std::cout << "removed min of heap is " << min << std::endl;
    std::cout << "heap after removeMin: " << heap << std::endl;

    std::cout << std::endl;
    for (int i = 0; i < 60; i++) {
        std::cout << "-";
    }
    std::cout << std::endl;
    std::cout << std::endl;

    std::cout << "size of heap is " << heap.size() << std::endl;

    std::cout << "min of heap is " << heap.min() << std::endl;

    std::cout << "is heap empty? " << (heap.isEmpty() ? "true" : "false") << std::endl;

    std::cout << std::endl;

    heap.insert(1);
    heap.insert(12);
    heap.insert(37);
    heap.insert(24);
    heap.insert(25);
    heap.insert(36);

    for (int i = 0; i < 60; i++) {
        std::cout << "-";
    }
    std::cout << std::endl;
    std::cout << std::endl;

    while(!heap.isEmpty()) {
        min = heap.removeMin();
        std::cout << "removed min of heap is: " << min << std::endl;
        std::cout << "heap after removeMin: " << heap << std::endl;
    }

    std::cout << std::endl;
    for (int i = 0; i < 60; i++) {
        std::cout << "-";
    }
    std::cout << std::endl;
    std::cout << std::endl;

    std::cout << "is heap empty? " << (heap.isEmpty() ? "true" : "false") << std::endl;

    try {
        min = heap.removeMin();
        std::cout << "removed min of heap is: " << min << std::endl;
        std::cout << "heap after removeMin: " << heap << std::endl;
    } catch (std::out_of_range& e) {
        std::cout << e.what() << std::endl;
    }

    try {
        heap.min();
    } catch (std::out_of_range& e) {
        std::cout << e.what() << std::endl;
    }

    std::vector<int> heapArray;
    heapArray.push_back(5);
    heapArray.push_back(3);
    heapArray.push_back(8);
    heapArray.push_back(1);
    heapArray.push_back(4);
    heapArray.push_back(6);
    heapArray.push_back(12);
    heapArray.push_back(1);
    heapArray.push_back(12);
    heapArray.push_back(37);
    heapArray.push_back(24);
    heapArray.push_back(25);
    heapArray.push_back(36);
    heapArray.push_back(15);
    heapArray.push_back(123);

    std::cout << std::endl;
    for (int i = 0; i < 60; i++) {
        std::cout << "-";
    }
    std::cout << std::endl;
    std::cout << std::endl;

    std::cout << "Vector is: ";

    for (int i = 0; i < heapArray.size(); i++) {
        std::cout << heapArray[i] << " ";
    }
    std::cout << std::endl;
    std::cout << std::endl;

    MinHeap<int> heap2;

    for (int i = 0; i < heapArray.size(); i++) {
        heap2.insert(heapArray[i]);
    }

    std::cout << heap2 << std::endl;
    std::cout << std::endl;

    std::cout << "Sorted Vector (Heapsort) is (descending): " << std::endl;
    sort(heapArray);
    std::cout << std::endl;

    std::cout << "Sorted Vector (Quicksort) is (ascending): " << std::endl;
    quicksort_mot(heapArray, 0, heapArray.size() - 1);

    return 0;
}

