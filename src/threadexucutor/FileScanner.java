package threadexucutor;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FileScanner {

    private static int fileCount = 0;
    private static int folderCount = 0;
    private static long totalSize = 0;
    private static long startTime;
    private static long endTime;
    static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);
    static ExecutorService executors = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

        String path = "C:\\";

        startTime = System.currentTimeMillis();

        File file = new File(path);
        if (file.list() == null) {
            return;
        }
        for (File file1 : file.listFiles()) {

            if (file1.isDirectory()) {
                folderCount++;
                MyThread myThread = new MyThread(file1);
                threadPoolExecutor.submit(myThread);
            } else if (file1.isFile()) {
                fileCount++;
                totalSize += file1.length();
            }
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
            showResult();
//            System.out.println(threadPoolExecutor.getActiveCount());
        }
        endTime = System.currentTimeMillis();
        showResult();

    }

    public static synchronized void setAll(int fileC, int folderC, long totalS) {
        fileCount += fileC;
        folderCount += folderC;
        totalSize += totalS;
    }

    public static void showResult() {

        System.out.println("FolderCount: " + folderCount);
        System.out.println("FileCount: " + fileCount);
        System.out.println("TotalSize: " + totalSize);
        System.out.println("Time: " + (endTime - startTime) + " ms");

    }
}
