class Solution {
    public static Node merge(Node list1,Node list2){
        Node dummy = new Node(-1);
        Node res = dummy;
        
        while(list1!=null && list2!=null){
            if(list1.data<list2.data){
                res.bottom = list1;
                res= res.bottom;
                list1= list1.bottom;
            }
            else{
                res.bottom = list2;
                res= res.bottom;
                list2= list2.bottom;
            }

        }
        if(list1!=null) res.bottom=list1;
        else res.bottom= list2;
        
        return dummy.bottom;
    }
    public Node flatten(Node root) {
        // code here
        if(root == null || root.next == null){
            return root;
        }
        
        // we can rececurively call the head 
        root.next = flatten(root.next);
        root= merge(root,root.next);
        return root;
    }
}