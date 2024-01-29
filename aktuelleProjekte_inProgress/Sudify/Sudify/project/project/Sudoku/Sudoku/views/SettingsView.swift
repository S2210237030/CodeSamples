//
//  SettingsView.swift
//  Sudoku
//
//  Created by Sandra Hartlauer on 02.01.24.
//

import SwiftUI

struct SettingsView: View {
    
    @Binding var isPresented: Bool
    @State private var selectedColorScheme = 0
    let colorSchemes = ["Purple", "Yellow", "Red", "Blue"]

    var body: some View {
        ZStack {
            Image("purple_background")
                .resizable()
                .scaledToFill()
                .edgesIgnoringSafeArea(.all)
                .overlay {
                    VStack {
                        Text("Settings")
                            .font(.largeTitle)
                            .bold()
                            .foregroundColor(getColorForScheme())
                            .padding()
                        
                        Picker("Color Scheme", selection: $selectedColorScheme) {
                            ForEach(0..<colorSchemes.count) { index in
                                Text(colorSchemes[index])
                            }
                        }
                        .pickerStyle(SegmentedPickerStyle())
                        .frame(maxWidth: 380)
                        .padding()
                        
                        Spacer()
                        
                        Button("Close") {
                            isPresented = false
                        }
                        .font(.headline)
                        .padding()
                        .background(getColorForScheme())
                        .foregroundColor(.white)
                        .cornerRadius(10)
                        .padding()
                    }
                }
        }
    }
    
    // Helper function to get the color for the selected color scheme
    func getColorForScheme() -> Color {
        switch selectedColorScheme {
        case 0:
            return .purple
        case 1:
            return .yellow
        case 2:
            return .red
        case 3:
            return .blue
        default:
            return .purple
        }
    }
}

struct SettingsView_Previews: PreviewProvider {
    @State static var isPresented = true
    
    static var previews: some View {
        SettingsView(isPresented: $isPresented)
    }
}
