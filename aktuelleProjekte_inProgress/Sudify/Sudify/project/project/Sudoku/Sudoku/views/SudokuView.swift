//
//  SudokuView.swift
//  Sudoku
//
//  Created by Sandra Hartlauer on 19.12.23.
//

import SwiftUI

struct Indices {
    var row: Int
    var column: Int
}

struct SudokuView: View {
    let rows = 9
    let columns = 9
    var difficulty: Difficulty
    @Binding var isEasy: Bool
    @State private var sudokuBoard: [[Int]] = [[]]
    @State private var falseCounter: Int = 0
    let scale = 0.75
    
    var numBoard: [Int] = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    
    @State private var selectedNumber: Int = 0
    @State private var lastNumberClickedOn: Int = 0
    @State private var lastNumberRow: Int = 0
    @State private var numberIndex: Int = -1
    @State private var falseNumberIndex: Int = -1
    @State private var boolNumberIndex = false
    @State private var lastNumberColumn: Int = 0
    @State private var numberArray: [Indices] = Array(repeating: Indices(row: 0, column: 0), count: 81)
    @State private var falseNumbers: [Indices] = Array(repeating: Indices(row: 0, column: 0), count: 81)
    @State private var checkedIndices: [Indices] = Array(repeating: Indices(row: 0, column: 0), count: 81)
    
    @State private var isButtonCLicked = false
    @State private var notEmpty = true
    @State private var isCorrect: Bool = false
    
    init(difficulty: Difficulty, isEasy: Binding<Bool>) {
        self.difficulty = difficulty
        self._isEasy = isEasy
        self._sudokuBoard = State(initialValue: SudokuGenerator.generatePuzzle(for: mapDifficulty(difficulty)))
    }
    
    private func mapDifficulty(_ difficulty: Difficulty) -> SudokuDifficulty {
        switch difficulty {
        case .easy:
            return .easy
        case .medium:
            return .medium
        case .hard:
            return .hard
        }
    }
    
    var body: some View {
        NavigationView {
            ZStack {
                Image("purple_background")
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                
                VStack {
                    Text("Sudify")
                        .font(.largeTitle)
                        .bold()
                        .foregroundColor(.purple)
                        .padding()
                    
                    ZStack(alignment: .topLeading) {
                        VStack(spacing: 0) {
                            ForEach(0 ..< rows) { row in
                                HStack(spacing: 0) {
                                    ForEach(0 ..< columns) { column in
                                        Button {
                                            sudokuBoard[row][column] = selectedNumber
                                            lastNumberClickedOn = selectedNumber
                                            if sudokuBoard[row][column] != 0 {
                                                lastNumberRow = row
                                                lastNumberColumn = column
                                                
                                                numberIndex += 1
                                                numberArray[numberIndex] = Indices(row: lastNumberRow, column: lastNumberColumn)
                                            }
                                            selectedNumber = 0
                                            
                                            if numberIndex >= 0 {
                                                boolNumberIndex = true
                                            } else {
                                                boolNumberIndex = false
                                            }
                                            isButtonCLicked.toggle()
                                        } label: {
                                            SudokuCell(value: sudokuBoard[row][column], width: 1, transparent: false, isClicked: false,  isIncorrect: false)
                                        }
                                        .background(Color.white)
                                        .disabled(sudokuBoard[row][column] != 0)
                                    }
                                }
                            }
                        }
                        .background(Color.black)
                        .padding(5)
                        .scaleEffect(scale)
                        
                        VStack(spacing: 0) {
                            ForEach(0..<3) { row in
                                HStack(spacing: 0) {
                                    ForEach(0..<3) { column in
                                        SudokuCell(value: 0, width: 3, transparent: true, isClicked: false, isIncorrect: false)
                                    }
                                }
                            }
                        }
                        .background(Color.clear)
                        .padding(5)
                        .scaleEffect(scale)
                    }
                    
                    HStack(spacing: 0) {
                        ForEach(numBoard, id: \.self) { number in
                            Button {
                                selectedNumber = number
                                isButtonCLicked.toggle()
                            } label: {
                                SudokuCell(value: number, width: 2, transparent: false, isClicked: isButtonCLicked, isIncorrect: false)
                            }
                            .background(Color.purple)
                        }
                    }
                    .background(Color.black)
                    .padding(5)
                    .scaleEffect(0.5)
                    
                    HStack {
                        Button {
                            sudokuBoard[lastNumberRow][lastNumberColumn] = 0
                            numberIndex -= 1
                            if numberIndex >= 0 {
                                lastNumberRow = numberArray[numberIndex].row
                                lastNumberColumn = numberArray[numberIndex].column
                                boolNumberIndex = true
                                numberArray.removeLast()
                            } else {
                                boolNumberIndex = false
                            }
                        }
                    label: {
                        Image(systemName: "arrow.uturn.left")
                    }
                    .font(.headline)
                    .padding()
                    .background(boolNumberIndex ? Color.purple : Color.gray)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                    .disabled(!boolNumberIndex)
                        
                    if !isEasy {
                        Button {
                            
                        }
                    label: {
                        Image(systemName: "trash")
                    }
                    .font(.headline)
                    .padding()
                    .background(Color.purple)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                    }
                    
                    Button("Check") {
                        var solution: [[Int]] = []
                        
                        switch difficulty {
                        case .easy:
                            solution = Solutions.easyPuzzle
                        case .medium:
                            solution = Solutions.mediumPuzzle
                        case .hard:
                            solution = Solutions.hardPuzzle
                        }
                        
                        isCorrect = true
                        falseNumbers.removeAll()

                        for row in 0..<rows {
                            for column in 0..<columns {
                                if sudokuBoard[row][column] != solution[row][column] {
                                    isCorrect = false
                                    sudokuBoard[row][column] = 0
                                }
                            }
                        }
                        
                        if isCorrect {
                            print("Sudoku ist korrekt!")
                        } else {
                            print("Sudoku enthält Fehler!")
                        }
                    }
                    .font(.headline)
                    .padding()
                    .background(Color.purple)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                }
                    
                NavigationLink(destination: FinishView(), isActive: $isCorrect) {
                    EmptyView()
                }
            }
                .padding(.top)
            }
        }
        .tint(.purple)
    
    }
}

struct SudokuView_Previews: PreviewProvider {
    static var previews: some View {
        let isEasy = Binding.constant(true)
        return SudokuView(difficulty: .easy, isEasy: isEasy)
    }
}
