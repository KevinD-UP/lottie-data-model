import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        NavigationView {
            VStack {
                NavigationLink(destination: AnimationTransformerView()) {
                    Text("Animation")
                }
                NavigationLink(destination: ExpressionView()) {
                    Text("Expression")
                }
                NavigationLink(destination: FontTransformerView()) {
                    Text("FontTransformer")
                }
            }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
