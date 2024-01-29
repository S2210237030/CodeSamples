//
//  DifficultyView.swift
//  Sudoku
//
//  Created by Sandra Hartlauer on 20.12.23.
//

import SwiftUI

struct DifficultyView: View {
    @State private var selectedDifficulty: Difficulty?
    @State private var isActive: Bool = false
    @State private var isEasy: Bool = false

    var body: some View {
        NavigationView {
            ZStack {
                Image("purple_background")
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                
                VStack {
                    NavigationLink(
                        destination: SudokuView(difficulty: selectedDifficulty ?? .easy, isEasy: $isEasy),
                        isActive: $isActive
                    ) {
                        EmptyView()
                    }
                    .hidden()
                    
                    Button("Easy") {
                        selectedDifficulty = .easy
                        isActive = true
                        isEasy = true
                    }
                    .font(.headline)
                    .padding()
                    .background(Color.purple)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                    
                    Divider()
                    
                    Button("Medium") {
                        selectedDifficulty = .medium
                        isActive = true
                        isEasy = false
                    }
                    .font(.headline)
                    .padding()
                    .background(Color.purple)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                    
                    Divider()
                    
                    Button("Hard") {
                        selectedDifficulty = .hard
                        isActive = true
                        isEasy = false
                    }
                    .font(.headline)
                    .padding()
                    .background(Color.purple)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                }
                .padding()
            }
        }
    }
}

struct DifficultyView_Previews: PreviewProvider {
    static var previews: some View {
        DifficultyView()
    }
}
