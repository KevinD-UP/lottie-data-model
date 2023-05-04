import SwiftUI
import shared


struct AnimationTransformerView: View {
    let animationTransformerExample = AnimationTransformerExample()

    var body: some View {
        Text(animationTransformerExample.animText())
    }
}

struct AnimationTransformerView_Previews: PreviewProvider {
    static var previews: some View {
        AnimationTransformerView()
    }
}
