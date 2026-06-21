public class ProcessStringOperationsI {
    public static void main(String[] args) {
        String s = "a#b%*";
        String result ="";


        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(Character.isLowerCase(ch)){
                result+=ch;
            }
            if(ch == '#'){
                result+=result;
            }

            if(ch == '%'){
                 String reversed = new StringBuilder(result).reverse().toString();
                 result = reversed;
            }
            if(ch == '*'){
                String sub = result.substring(0,result.length()-1);
                result =sub;
            }
        }
        System.out.println(result);
    }
}
