


StackInterface<Integer> stack1 = new Stack<>();
StackInterface<Integer> stack2 = new Stack<>();

public void doAddArrayToStack(int[] data) {

    for (int val : data) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        while (!(stack1.peek() < val && stack2.peek() > val)) {
            
            stack2.push(stack1.pop());

        }

        stack2.push(val);    
    }
   
}

public int[] printStackToArray() {

    while(!stack1.isEmpty()) {
        stack2.push(stack1.pop());
    }
    int count = 0;
    while(!stack2.isEmpty()) {
        stack1.push(stack2.pop());
        count++;
    }

    while(!stack1.isEmpty()) {
        stack2.push(stack1.pop());
    }

    int[] arr = new int[count];
    for (int i = 0; i < arr.length; i++) {
        arr[i] = stack2.pop();
    }

    return arr;
    
}






