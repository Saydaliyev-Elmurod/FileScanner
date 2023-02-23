package thread;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileScanner {
    private static int fileCount = 0;
    private static int folderCount = 0;
    private static long totalSize = 0;
    private static long startTime;
    private static long endTime;
    /*FolderCount: 115209
FileCount: 367766
TotalSize: 42106609478
Time: 31181 ms*/
    /**FolderCount: 115225
     FileCount: 368024
     TotalSize: 38415008960
     Time: 65117 ms*/
    /*FolderCount: 115232
FileCount: 371026
TotalSize: 38506670465
Time: 38051 ms*/

    public static void main(String[] args) {
//        String path = "C:\\";
        String path = "D:\\";
        List<MyThread> fileScannerList = new LinkedList<>();

        startTime = System.currentTimeMillis();

        File file = new File(path);
        if (file.list() == null) {
            return;
        }
               for (File innerFile : file.listFiles()) {
            if (innerFile.isDirectory()) {
                folderCount++;
                MyThread myThread = new MyThread(innerFile);
                myThread.start();
                fileScannerList.add(myThread);
            } else if (innerFile.isFile()) {
                fileCount++;
                totalSize += innerFile.length();
            }
        }

        for (MyThread myThread : fileScannerList) {
            try {
                myThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        System.out.println("====================================");
        System.out.println("FolderCount: " + folderCount);
        System.out.println("FileCount: " + fileCount);
        System.out.println("TotalSize: " + totalSize);
        System.out.println("Time: " + (endTime - startTime) + " ms");

    }


}