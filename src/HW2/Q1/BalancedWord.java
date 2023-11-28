package HW2.Q1;

import java.util.Stack;
public class BalancedWord {
    //创建一个String变量
    private final String word;
    //构建器内方法，调用了 isBalnced 这个方法，来判断这个String 变量是否符合要求
    public BalancedWord(String word) {
        if (isBalanced(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a balanced word.", word));
        if(isInfix(word)){
            throw new IllegalArgumentException(String.format("%s is not a infixed word.", word));
        }
    }

    /*
    *这个方法去判断，表达式是否有合适的括号结构，一道典型的利用stack的题目
    * */

    private static boolean isBalanced(String word) {
        char[] chars = word.toCharArray();

        Stack<Character> stack = new Stack<Character>();

        for (char ch : chars) {

            if(!Character.isLetter(ch)){
                if (ch == '(' || ch == '[' || ch == '{') {
                    if (ch == '(') {
                        stack.push(')');
                    } else if (ch == '[') {
                        stack.push(']');
                    } else if (ch == '{') {
                        stack.push('}');
                    }
                } else if(ch == ')' || ch == ']' || ch == '}') {
                    if (stack.isEmpty()||stack.pop()!=ch) {
                        return false; // 添加此行来处理未匹配的闭括号
                    }
                }
            }
        }
        return stack.isEmpty();
    }// TODO
    /*
    * 判断表达式是否是符合数学逻辑的
    * 运算符号是否前后位数字
    * 小数点前后是否位数字
    * */
    private static boolean isInfix(String expression){
        // 判断是否为空
        if(expression.isEmpty()) return false;

        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]=='+'||chars[i]=='-'||chars[i]=='*'||chars[i]=='/'){
                // 检查边界
                if (i == 0 || i == chars.length - 1) {
                    return false;
                }
                //检查符号前
                if(Character.isWhitespace(chars[i-1])){
                    if(!Character.isDigit(chars[i-2])||chars[i-2]!=')'){
                        return false;
                    }
                }else if(!Character.isDigit(chars[i-1])||chars[i-1]!=')'){
                    return false;
                }
                //检查符号后
                if(Character.isWhitespace(chars[i+1])){
                    if(!Character.isDigit(chars[i+2])||chars[i+2]!='('){
                        return false;
                    }else if(!Character.isDigit(chars[i+1])||chars[i+1]!='('){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public String getWord() { return word; }
// For test
//    public static void main(String[] args) {
//        String str = "(1.1+1.2*1.3)";
//        String str1 = ")1.1+1.2*1.3(";
//        String str2 = "))3+5*2-8/4";
//        String str3 = "(((((3++5)))))";
//        System.out.println("isValid Test: ");
//        System.out.println(isInfix(str));//true
//        System.out.println(isInfix(str1));//true
//        System.out.println(isInfix(str2));//true
//        System.out.println(isInfix(str3));//false
//        System.out.println("isBalenced Test: ");
//        System.out.println(isBalanced(str));//true
//        System.out.println(isBalanced(str1));//false
//        System.out.println(isBalanced(str2));//false
//        System.out.println(isBalanced(str3));//true
//
//    }
}
