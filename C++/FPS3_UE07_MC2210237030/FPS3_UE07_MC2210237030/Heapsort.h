//
// Created by sandr on 04.12.2023.
//

#ifndef FPS3_UE06_MC2210237030_HEAPSORT_H
#define FPS3_UE06_MC2210237030_HEAPSORT_H

#include <vector>
#include "Minheap.h"

template <typename T>
void sort(std::vector<T>& heapArray) {

    MinHeap<T> heap;

    for (int i = 0; i < heapArray.size(); i++) {
        heap.insert(heapArray[i]);
    }

    for (int i = 0; i < heapArray.size(); i++) {
        heapArray[i] = heap.removeMin();
    }

    for (int i = 0; i < heapArray.size() / 2; i++) {
        std::swap(heapArray[i], heapArray[heapArray.size() - i - 1]);
    }

    for (int i = 0; i < heapArray.size(); i++) {
        std::cout << heapArray[i] << " ";
    }
    std::cout << std::endl;
}


#endif //FPS3_UE06_MC2210237030_HEAPSORT_H
