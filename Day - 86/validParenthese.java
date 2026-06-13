public class validParenthese {
    public static void main(String[] args) {
        String s ="()";

        int left=0;
        int right=0;
        int star=0;


        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                left++;
            }
            else if(ch == ')'){
                right++;
            }
            else{
                star++;
            }
        }

        System.out.println(left);
        System.out.println(right);
        System.out.println(star);


        if(left!=right){
            if(left>right){
                left +=star;
            }
            else{

                right-=star;
            }
        }

        if(left == right){
            System.out.println(true);
        }
        else{
            System.out.println(false);
        }



    }
}
