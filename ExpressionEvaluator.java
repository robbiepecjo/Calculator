/*
 * Robbie Pecjo masc1518
 * Professor Riggins 
 * Programming Assignment #1
 * 10/10/2012
 */
import java.util.StringTokenizer;
import data_structures.*;
public class ExpressionEvaluator<E> {
	static double r;
	static String answer, temp;
	private Stack<String> stack;
	private Queue<String> queue;
	public String processInput(String iOText) {
		try {
			String postFix = getPostFix(iOText);
			for(String s : queue)
				System.out.print(s);
			System.out.println();
			String answer = evaluatePostFix(postFix);
			return answer;
		}
		catch(Exception e){}
		return "ERROR";
	}

//getPostFix converts infix to postfix i.e. 3+2 == 32+
private String getPostFix(String iOText) {
	stack = new Stack<String>();
	queue = new Queue<String>();
	StringTokenizer t = new StringTokenizer(iOText);
	while(t.hasMoreTokens()) {
		temp = t.nextToken();
		if(temp.equals("("))
			stack.push(temp);
		if(temp.equals(")")) {
			while(!stack.peek().equals("(")) {
				queue.enqueue(stack.pop());
			}
			if(stack.peek().equals("("))
				stack.pop();
			}
		if(temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/") || temp.equals("^"))  { 
			while(!stack.isEmpty() && !stack.peek().equals("(") && greaterOrEquals(stack.peek(), temp)) {
				queue.enqueue(stack.pop());
			}
			stack.push(temp);
		}
		//check if temp is double using isDouble method
		if(isDouble(temp))
			queue.enqueue(temp);
	}
	while(!stack.isEmpty())	
		queue.enqueue(stack.pop());
	return queue.toString();
	}

private String evaluatePostFix(String postFix) {
	while(!queue.isEmpty()) {
		String s = (String)queue.peek();
		if(isDouble(s))
			stack.push(queue.dequeue());
		//if extra "(" or ")", will return ERROR i.e. ((3) 
		else if(queue.peek().equals("(") || queue.peek().equals(")"))
			throw new RuntimeException();
		else if(queue.peek().equals("+") || queue.peek().equals("-") || queue.peek().equals("*") 
				|| queue.peek().equals("/") || queue.peek().equals("^")) {
			double first = Double.parseDouble(stack.pop());
			double second = Double.parseDouble(stack.pop());
			String operator = (String)queue.dequeue();
			if(operator.equals("+"))
				r = second + first;
			else if(operator.equals("-"))
				r = second - first;
			else if(operator.equals("*"))
				r = second * first;
			else if(operator.equals("/"))
				r = second / first;
			else if(operator.equals("^"))
				r = Math.pow(second, first);
			stack.push(Double.toString(r));
		}
	}
	String s = (String)stack.pop();
	//stack should only have one element which is the answer.
	//now if it is not empty, then return ERROR 
	//i.e. 3(2) == 32 , stack.pop will only pop off 2, leaving 3.
	if(!stack.isEmpty())
		throw new RuntimeException();
	return s;
}

public static boolean isDouble(String input) {  
   try {  
      Double.parseDouble(input);  
      return true;
   }
   catch(Exception e) {  
      return false;
   }
     
}  
	private boolean greaterOrEquals(Object a, String b) {
		//PEMDAS (^*/+-)
		if(a.equals("^"))
			return true;
		else if(a.equals("*") || a.equals("/")) {
			if(b.equals("^"))
				return false;
			if(a.equals(b) || b.equals("+") || b.equals("-"))
				return true;
		}
		else if(a.equals("+") || a.equals("-")) {
			if(a.equals(b))
				return true;
			else
				return false;
		}	
		return false;
	}
}
