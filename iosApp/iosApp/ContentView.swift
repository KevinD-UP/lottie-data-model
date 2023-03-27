import SwiftUI
import shared

struct ContentView: View {
	let animationTransformerExample = AnimationTransformerExample()

	var body: some View {
        Text(animationTransformerExample.animText())
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
