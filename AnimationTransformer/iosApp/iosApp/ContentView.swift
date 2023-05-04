import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        TabView {
            AnimationTransformerView()
                .tabItem {
                    Label("Animation", image: "plus")
                }
            ExpressionView()
                .tabItem {
                    Label("Expression", image: "plus")
                }
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
