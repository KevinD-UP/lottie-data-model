import SwiftUI
import shared


class ExpressionManager {
    private let functions: [String : KPFunctionInterface] = [
        "multiplyBy33": FunctionMultiplyBy33(),
        "addAndDivideBy2": FunctionAddAndDivideBy2(),
        "return123": FunctionReturn123()
    ]
    lazy var parser = KPDefaultExpressionParser(functions: self.functions)
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
                Text("• return123(): 123.0")
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
            VStack {
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
        let result = expressionManager.parser.parseAndEvaluate(expression: trimmedString)
        self.result = "\(result)"
    }
}

struct ExpressionView_Previews: PreviewProvider {
    static var previews: some View {
        ExpressionView()
    }
}

class FunctionMultiplyBy33: KPFunctionInterface {
    func execute(args: [KotlinDouble]) -> Double {
        guard args.count == 1 else {
            return 0.0
        }
        return args[0].doubleValue * 33
    }

}

class FunctionAddAndDivideBy2: KPFunctionInterface {
    func execute(args: [KotlinDouble]) -> Double {
        guard args.count == 2 else {
            return 0.0
        }
        return (args[0].doubleValue + args[1].doubleValue) / 2.0
    }
}

class FunctionReturn123: KPFunctionInterface {
    func execute(args: [KotlinDouble]) -> Double {
        return 123.0
    }
}

