

        private static int[] doStackAdd(int data[]) {
            StackInterface<Integer> stack1 = new Stack<>();
            StackInterface<Integer> stack2 = new Stack<>();


            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
    

            // Process each value in the appendArray
            for (int value : data) {
                // Move values from stack2 to stack1 until the condition is met
                while (!stack1.isEmpty() && !stack2.isEmpty() && stack1.peek() <= value && stack2.peek() >= value) {
                    stack1.push(stack2.pop());
                }

                // Push the current value from appendArray into stack2
                stack2.push(value);

                // Move values back from stack1 to stack2
                

            // Collect the sorted values from stack2 into the result array
            int[] result = new int[data.length];
            int index = 0;
            while (!stack2.isEmpty()) {
                result[index++] = stack2.pop();
            }

            return result;
        }



