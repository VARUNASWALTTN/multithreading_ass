import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CallableMessage implements Callable<String>{
    public String call() {
        return "Message from callable";
    }
}

public class Q4 {
     static ExecutorService executor = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception{

        //Creating a callable task.
        CallableMessage task = new CallableMessage();
        Future<String> message = executor.submit(task);
        System.out.println(message.get().toString());

        // Creating and running runnable task using Thread class and executor class also
        RunnableMessage t = new RunnableMessage();
        Thread thread = new Thread(t);
        thread.start();
        executor.submit(t);
    }
}

class RunnableMessage implements Runnable {
    public void run(){
        System.out.println("Message from runnable");
    }
}

