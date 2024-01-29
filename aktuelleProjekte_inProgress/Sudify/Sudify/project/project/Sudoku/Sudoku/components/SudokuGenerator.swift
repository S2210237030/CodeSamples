//
//  SudokuGenerator.swift
//  Sudoku
//
//  Created by Sandra Hartlauer on 20.12.23.
//

import SwiftUI

enum SudokuDifficulty {
    case easy, medium, hard
}

struct SudokuGenerator {
    
    static func generatePuzzle(for difficulty: SudokuDifficulty) -> [[Int]] {
            switch difficulty {
            case .easy:
                return generateEasyPuzzle()
            case .medium:
                return generateMediumPuzzle()
            case .hard:
                return generateHardPuzzle()
            }
        }
    
    
    static func generateEasyPuzzle() -> [[Int]] {
        
        let puzzle: [[Int]] = [
            [5, 3, 0, 0, 7, 0, 0, 0, 0],
            [6, 0, 0, 1, 9, 5, 0, 0, 0],
            [0, 9, 8, 0, 0, 0, 0, 6, 0],
            [8, 0, 0, 0, 6, 0, 0, 0, 3],
            [4, 0, 0, 8, 0, 3, 0, 0, 1],
            [7, 0, 0, 0, 2, 0, 0, 0, 6],
            [0, 6, 0, 0, 0, 0, 2, 8, 0],
            [0, 0, 0, 4, 1, 9, 0, 0, 5],
            [0, 0, 0, 0, 8, 0, 0, 7, 9]
        ]
        return puzzle
    }
    
    
    static func generateMediumPuzzle() -> [[Int]] {
        let puzzle: [[Int]] = [
            [0, 0, 0, 0, 0, 0, 0, 0, 0],
            [5, 0, 0, 0, 3, 0, 0, 0, 0],
            [0, 0, 7, 0, 0, 2, 0, 0, 0],
            [0, 0, 3, 0, 0, 0, 0, 0, 0],
            [6, 0, 0, 5, 0, 3, 0, 7, 0],
            [0, 5, 8, 0, 6, 0, 0, 0, 0],
            [9, 3, 6, 0, 5, 0, 7, 0, 2],
            [0, 0, 0, 8, 0, 0, 0, 0, 8],
            [8, 0, 0, 3, 7, 0, 6, 0, 1]
        ]
        return puzzle
    }
    
    
    static func generateHardPuzzle() -> [[Int]] {
        let puzzle: [[Int]] = [
            [8, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 3, 6, 0, 0, 0, 0, 0],
            [0, 7, 0, 0, 9, 0, 2, 0, 0],
            [0, 5, 0, 0, 0, 7, 0, 0, 0],
            [0, 0, 0, 0, 4, 5, 7, 0, 0],
            [0, 0, 0, 1, 0, 0, 0, 3, 0],
            [0, 0, 1, 0, 0, 0, 0, 6, 8],
            [0, 0, 8, 5, 0, 0, 0, 1, 0],
            [0, 9, 0, 0, 0, 0, 4, 0, 0]
        ]
        return puzzle
    }
}
