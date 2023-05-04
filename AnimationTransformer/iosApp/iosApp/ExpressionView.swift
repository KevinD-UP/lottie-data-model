import SwiftUI
import shared


class ExpressionManager {
    private let functions: [String : FunctionInterface] = [
        "multiplyBy33": FunctionMultiplyBy33(),
        "addAndDivideBy2": FunctionAddAndDivideBy2()
    ]
    lazy var parser = ExpressionParser(functions: self.functions)
}

struct ExpressionView: View {
    let expressionManager = ExpressionManager()

    @State var text: String = ""
    @State var result: String = ""

    var body: some View {
        VStack {
            VStack(alignment: .leading) {
                Text("List of functions:")
                    .bold()
                Text("• multiplyBy33(value): value * 33")
                Text("• addAndDivideBy2(value1, value2): (value1 + value2) / 2.0")
                Divider()
            }
            VStack(alignment: .leading) {
                Text("Example of expressions:")
                    .bold() +
                Text("• 1 + 1")
                Text("• 1 * (2 + 3)")
                Text("• multiplyBy33(2) * (1 + addAndDivideBy2(1, 2))")
                Divider()
            }
                VStack(alignment: .leading) {
                Text("Expression:")
                    .bold()
                TextField("expression", text: $text)
                    .padding(10)
            }
                VStack(alignment: .leading) {
                Button {
                    evalExpression()
                } label: {
                    Text("Eval expression")
                }
                Divider()
            }
                VStack(alignment: .leading) {
                Text("Result:")
                    .bold()
                Text(result)
            }
            Spacer()
        }
    }

    func evalExpression() {
        let stringWithExtraSpaces = text
        let trimmedString = stringWithExtraSpaces.trimmingCharacters(in: .whitespaces)

        let result = expressionManager.parser.parseAndEvaluate(expression: text)
        self.result = "\(result)"
    }
}

struct ExpressionView_Previews: PreviewProvider {
    static var previews: some View {
        ExpressionView()
    }
}

class FunctionMultiplyBy33: FunctionInterface {
    func execute(args: [KotlinDouble]) -> Double {
        guard args.count == 1 else {
            return 0.0
        }
        return args[0].doubleValue * 33
    }

}

class FunctionAddAndDivideBy2: FunctionInterface {
    func execute(args: [KotlinDouble]) -> Double {
        guard args.count == 2 else {
            return 0.0
        }
        return (args[0].doubleValue + args[1].doubleValue) / 2.0
    }
}
