package HW2.Q1;

import java.util.Stack;

public class ToPostfixConverter implements Converter {
    @Override
    public String convert(ArithmeticExpression expression) {
        Stack<Operator> stack = new Stack<>();
        TokenBuilder postFixedExpression = new TokenBuilder();
        String strWithoutSpace = expression.getExpression().replaceAll("\\s+", "");
        int length = strWithoutSpace.length();
        int cur = 0;
        while (cur < length) {
            char ch = strWithoutSpace.charAt(cur);
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                String subStr = nextToken(strWithoutSpace, cur);
                 int subStrLength = subStr.length();
                for (int i = 0; i <subStrLength; i++) {
                    postFixedExpression.append(subStr.charAt(i));
                }
                postFixedExpression.append(' ');
                cur += subStr.length();
            } else if (ch == '(') {
                stack.push(Operator.LEFT_PARENTHESIS);
                cur++;
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != Operator.LEFT_PARENTHESIS) {
                    postFixedExpression.append(stack.pop().getSymbol());
                    postFixedExpression.append(' ');
                }
                if (!stack.isEmpty()) stack.pop(); // Pop the left parenthesis
                cur++;
            } else if (Operator.isOperator(ch)) {
                Operator currentOperator = Operator.of(ch);
                while (!stack.isEmpty() && stack.peek().getRank() <= currentOperator.getRank()&&stack.peek()!=Operator.LEFT_PARENTHESIS) {
                    postFixedExpression.append(stack.pop().getSymbol());
                    postFixedExpression.append(' ');
                }
                stack.push(currentOperator);
                cur++;
            }
        }
        while (!stack.isEmpty()) {
            postFixedExpression.append(stack.pop().getSymbol());
            postFixedExpression.append(' ');
        }
        return postFixedExpression.build().trim();
    }

//    @Override
//    public String convert(ArithmeticExpression expression) {
//        // the first one is false, because I didn't use nextToken
//        // Sample: 7 + 3 * ( 10 / ( 12 / (  + 1 ) - 1 ) )
//        // first initialize the stack, we use String because nextToken return String
//        // Initialized the stack
//        Stack<Operator> stack = new Stack<>();
//
//        // TokenBuilder's superclass is StringBuilder
//        TokenBuilder postFixedExpression = new TokenBuilder();
//
//        // eliminate all whiteSpace in the expressionString
//        String tempString = expression.getExpression();
//        String strWithoutSpace = tempString.replaceAll("\\s+","");
//        int length = strWithoutSpace.length();
//        // Sample: 7 + 3 * ( 10 / ( 12 / ( 3 + 1 ) - 1 ) )
//        char[]  chars = strWithoutSpace.toCharArray();
//        int cur = 0;
//        while(cur < length){
//            // There are four cases: 1. Operand 2. "(" 3. ")" 4. Digit and Letters
//            // 用char[] 进行判断，用string进行读取
//            if(Character.isDigit(chars[cur])||Character.isLetter(chars[cur])){
//                 String subStr = nextToken(strWithoutSpace,cur);
//                 int subStrLength = subStr.length();
//                for (int i = 0; i <subStrLength; i++) {
//                    postFixedExpression.append(subStr.charAt(i));
//                }
//                postFixedExpression.append(' ');
//                 cur+=subStrLength;
//            }
//            //When a left parenthesis is read
//            else if(chars[cur]=='('){
//                //Push it onto the stack.
//                stack.push(Operator.LEFT_PARENTHESIS);
//                ++cur;
//            }
//            //When a right parenthesis is read
//            else if(chars[cur]==')'){
//                //Pop the stack and keep writing the tokens to the output until a left
//                //parenthesis is seen
//                while(!stack.empty() && stack.peek()!= Operator.LEFT_PARENTHESIS) {
//                    postFixedExpression.append(stack.pop().getSymbol());
//                    postFixedExpression.append(' ');
//                }
//                //This left parenthesis is popped, but not written to the output.
//                if(!stack.isEmpty()) stack.pop();
//                ++cur;
//            }
//            else if(Operator.isOperator(chars[cur])){
//                // Directly push if its empty or left parentheses
//                if(stack.isEmpty() || stack.peek()==Operator.LEFT_PARENTHESIS){
//                    stack.push(Operator.of(chars[cur]));
//                    ++cur;
//                }//If the input operator has higher precedence than the top of the stack.
//                else if(stack.peek().getRank() > Operator.of(chars[cur]).getRank()){
//                    stack.push(Operator.of(chars[cur]));
//                    ++cur;
//                } //Pop and add the top of the stack to the output, and then push the input onto the stack.
//                else if(stack.peek().getRank() == Operator.of(chars[cur]).getRank()){
//                    postFixedExpression.append(stack.pop().getSymbol());
//                    postFixedExpression.append(' ');
//                    stack.push(Operator.of(chars[cur]));
//                    ++cur;
//                }//Pop the stack and add the popped element to the output.
//                else if(stack.peek().getRank() < Operator.of(chars[cur]).getRank()){
//                    while(stack.peek().getRank() < Operator.of(chars[cur]).getRank()){
//                        postFixedExpression.append(stack.pop().getSymbol());
//                        postFixedExpression.append(' ');
//                    }
//                    stack.push(Operator.of(chars[cur]));
//                    ++cur;
//                }
//
//                while(!stack.isEmpty()){
//                    if(stack.peek()==Operator.LEFT_PARENTHESIS){
//                        stack.pop();
//                    }else{
//                        postFixedExpression.append(stack.pop().getSymbol());
//                        postFixedExpression.append(' ');
//                    }
//                }
//            }
//        }
//
//        return postFixedExpression.build().trim();
//    }





    @Override
    public String nextToken(String s, int start) {

        if(s.length()==0||start>s.length()){
            return null;
        }

        TokenBuilder tokenBuilder = new TokenBuilder();
        char currentChar = s.charAt(start);

        //if it is operand just convert it to string and return it
        if(Operator.isOperator(currentChar)||currentChar=='('|| currentChar==')'){
            return String.valueOf(currentChar);
        }

        //if it is number add into the tokenBuilder and use build() covert it to string
        if(Character.isDigit(currentChar)){
            while(start<s.length() && (Character.isDigit(s.charAt(start)) || s.charAt(start) == '.')){
                tokenBuilder.append(s.charAt(start));
                ++start;
            }
            return tokenBuilder.build();
        }
        //if it is a letter so append to tokenBuilder and use build() convert it to String
        if(Character.isLetter(currentChar)){
            while(start<s.length() && Character.isLetter(s.charAt(start))){
                tokenBuilder.append(s.charAt(start));
                ++start;
            }
            return tokenBuilder.build();
        }
//        No need to consider whiteSpace will be removed
//        if(Character.isWhitespace(currentChar)){
//            while(start<s.length() && Character.isWhitespace(s.charAt(start))){
//                ++start;
//            }
//        return nextToken(s,start);
//        }
//for any other character just return null
        return null;
    }

    @Override
    public boolean isOperand(String s) {
        // if it is empty and null just return false;
        if(s==null || s.isEmpty()){
            return false;
        }
        // if it is number change that to double else use isOperand() in enum
        try{
            Double.parseDouble(s);
            return true;
        }catch(NumberFormatException e){
            return ! Operator.isOperator(s);
        }
    }

}
