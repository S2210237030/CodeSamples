//
//  SudokuView.swift
//  Sudoku
//
//  Created by Sandra Hartlauer on 19.12.23.
//

import SwiftUI

struct ContentView: View {
    let rows = 9
    let columns = 9
    var sudokuBoard: [[Int]] = [
        [0, 0, 0, 2, 0, 8, 0, 0, 0],
        [5, 0, 0, 0, 0, 9, 0, 0, 0],
        [0, 7, 0, 0, 0, 0, 3, 0, 0],
        [0, 0, 0, 0, 6, 0, 0, 4, 0],
        [0, 0, 1, 0, 0, 0, 0, 7, 0],
        [0, 0, 0, 5, 0, 0, 0, 0, 6],
        [0, 0, 8, 0, 0, 0, 0, 0, 0],
        [0, 3, 0, 0, 7, 0, 0, 0, 0],
        [6, 0, 0, 0, 0, 4, 0, 2, 0]
    ]
    let scale = 0.6
    
    @State private var chooseDifficulty = false
    @State private var isButtonCLicked = false
    @State private var showSettings = false

    var body: some View {
        
        NavigationStack {
            ZStack {
                Image("purple_background")
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                
                VStack {
                    Text("Welcome to Sudify")
                        .font(.largeTitle)
                        .bold()
                        .foregroundColor(.purple)
                        .padding()
                    
                    ZStack(alignment: .topLeading) {
                        VStack(spacing: 0) {
                            ForEach(0 ..< rows) { row in
                                HStack(spacing: 0) {
                                    ForEach(0 ..< columns) { column in
                                        SudokuCell(value: sudokuBoard[row][column], width: 1, transparent: false, isClicked: false, isIncorrect: false)
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
                                        SudokuCell(value: 0, width: 3, transparent: true, isClicked: false,  isIncorrect: false)
                                    }
                                }
                            }
                        }
                        .background(Color.clear)
                        .padding(5)
                        .scaleEffect(scale)
                    }
                    
                    VStack {
                        NavigationLink(destination: DifficultyView(),
                                       isActive: $chooseDifficulty)
                        {
                            EmptyView()
                        }

                        HStack {
                            Spacer()
                            
                            Button("Start Game") {
                                chooseDifficulty.toggle()
                            }
                            .font(.headline)
                            .padding()
                            .background(Color.purple)
                            .foregroundColor(.white)
                            .cornerRadius(10)
                            
                            Spacer()
                            
                            Button {
                                showSettings.toggle()
                            } label: {
                                Image(systemName: "gear")
                            }
                            .font(.headline)
                            .padding()
                            .background(Color.purple)
                            .foregroundColor(.white)
                            .cornerRadius(10)
                            .popover(isPresented: $showSettings) {
                                SettingsView(isPresented: $showSettings)
                            }
                            
                            Spacer()
                        }
                        .padding()
                        
                    }.padding()
                    
                    Spacer()

                }
            }
        }
        .tint(.purple)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
