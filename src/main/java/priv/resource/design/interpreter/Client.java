package priv.resource.design.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String []args) {
        String inputExpr = "10";
        
        Context context = new Context(inputExpr);
        
        List list = new ArrayList();

        list.add(new PlusExpression());
        list.add(new PlusExpression());
        list.add(new MinusExpression());
        list.add(new MinusExpression());
        list.add(new MinusExpression());
        
        for (int i=0;i<list.size();i++) {
          AbstractExpression expression = (AbstractExpression)list.get(i);
          expression.interpret(context);
        }
        
        System.out.println(context.getOutput());
    }
}


/**
* Context
*
*/
class Context {
	
    private String input;
    private int output;

    public Context (String input) {
        this. input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}


/**
* Expression & subclass
*
*/
abstract class AbstractExpression {
    public abstract void interpret(Context context);
}

class PlusExpression extends AbstractExpression {
	
	@Override
    public void interpret(Context context) {
        System.out.println("PlusExpression ++");
        String input = context.getInput();
        int parsedResult = Integer.parseInt(input);
        parsedResult ++;
        context.setInput(String.valueOf(parsedResult));
        context.setOutput(parsedResult);
    }
	
}

class MinusExpression extends AbstractExpression {
	
	@Override
    public void interpret(Context context) {
        System.out.println("PlusExpression --");
        String input = context.getInput();
        int parsedResult = Integer.parseInt(input);
        parsedResult --;
        context.setInput(String.valueOf(parsedResult));
        context.setOutput(parsedResult);
    }
	
}