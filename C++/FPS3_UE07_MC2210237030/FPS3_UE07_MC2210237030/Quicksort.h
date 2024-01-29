//
// Created by sandr on 04.12.2023.
//

#ifndef FPS3_UE06_MC2210237030_QUICKSORT_H
#define FPS3_UE06_MC2210237030_QUICKSORT_H

#include <iostream>
#include <vector>

// es gehört median of three

template <typename T>
void swap(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

template <typename T>
int median_of_three(std::vector<T>& arr, int left, int right) {
    int mid = left + (right - left) / 2;

    // sort indices of left, mid and right
    if (arr[mid] < arr[left]) {
        swap(arr[mid], arr[left]);
    }

    if (arr[right] < arr[left]) {
        swap(arr[right], arr[left]);
    }

    if (arr[right] < arr[mid]) {
        swap(arr[right], arr[mid]);
    }

    // mid-element is in the middle now
    return mid;
}

template <typename T>
void helper_quicksort_mot(std::vector<T>& arr, int left, int right) {

    if (left < right) {
        int pivotIndex = median_of_three(arr, left, right);
        int pivot = arr[pivotIndex];
        int i = left - 1;
        int j = right;

        swap(arr[pivotIndex], arr[right]);

        while (true) {
            while (arr[++i] < pivot) {
                if (i == right) {
                    break;
                }
            }

            while (arr[--j] > pivot) {
                if (j == left) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }

            swap(arr[i], arr[j]);
        }

        swap(arr[i], arr[right]);

        // Recursive calls to sort the left and right subarrays in place
        helper_quicksort_mot(arr, left, i - 1);
        helper_quicksort_mot(arr, i + 1, right);
    }
}

template <typename T>
void quicksort_mot(std::vector<T>& arr, int left, int right) {
    helper_quicksort_mot(arr, left, right);

    for (const auto& element : arr) {
        std::cout << element << " ";
    }
    std::cout << std::endl;
}

#endif //FPS3_UE06_MC2210237030_QUICKSORT_H
