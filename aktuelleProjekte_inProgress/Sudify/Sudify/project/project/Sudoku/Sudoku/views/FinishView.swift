//
//  FinishView.swift
//  Sudoku
//
//  Created by Sandra Hartlauer on 03.01.24.
//

import SwiftUI

struct FinishView: View {
    var body: some View {
        ZStack {
            Image("purple_background")
                .resizable()
                .scaledToFill()
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                Spacer()
                
                Text("Herzlichen Glückwunsch!")
                    .font(.title)
                    .bold()
                    .foregroundColor(.purple)
                    .padding()
                
                Text("Sudoku ist korrekt!")
                    .font(.headline)
                    .padding()
                
                Spacer()
                Spacer()
                Spacer()
                
                Button("OK") {
                }
                .font(.headline)
                .padding()
                .background(Color.purple)
                .foregroundColor(.white)
                .cornerRadius(10)
                
                Spacer()
            }
        }
    }
}

struct FinishView_Previews: PreviewProvider {
    static var previews: some View {
        FinishView()
    }
}


