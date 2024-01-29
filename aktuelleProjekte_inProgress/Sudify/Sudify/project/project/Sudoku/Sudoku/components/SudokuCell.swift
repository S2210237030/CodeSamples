//
//  SudokuCell.swift
//  Sudoku
//
//  Created by Sandra Hartlauer on 19.12.23.
//

import SwiftUI

struct SudokuCell: View {
    let value: Int
    let width: Int
    let transparent: Bool
    let isClicked: Bool
    var isIncorrect: Bool 

    var body: some View {
        Text(value == 0 ? "" : "\(value)")
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(transparent ? Color.clear : Color.white)
            .background(isClicked ? Color.purple : Color.clear)
            .foregroundColor(Color.black)
            .border(Color.black, width: CGFloat(width))
            .font(.system(size: 40, weight: !transparent ? .regular : .bold))
            .aspectRatio(1.0, contentMode: .fit)
    }
}

