package WithoutThread;

import java.io.File;

public class Main {
    public static int fileCount = 0;
    public static int folderCount = 0;
    public static long totalSize = 0;

    /*folderCount: 1471
fileCount: 3122
totalSize: 8810923476
time: 150 ms*/
    //C
    /*folderCount: 115209
fileCount: 367766
totalSize: 42106603584
time: 45047 ms
     */
    /*folderCount: 115233
fileCount: 371031
totalSize: 40623563971
time: 50853 ms*/
    /**folderCount: 115232
     fileCount: 371026
     totalSize: 38506696544
     time: 41901 ms*/

    public static void main(String[] args) {
        
        String path = "C://";
//        String path = "D:\\";

        long startTime = System.currentTimeMillis();
        File file = new File(path);
        scanFolder(file);
        long endTime = System.currentTimeMillis();

        System.out.println("folderCount: " + folderCount);
        System.out.println("fileCount: " + fileCount);
        System.out.println("totalSize: " + totalSize);
        System.out.println("time: " + (endTime - startTime)  + " ms");
    }

    public static void scanFolder(File file) {
        if (file.list() == null) {
            return;
        }
        for (File file1 : file.listFiles()) {
            if (file1.isDirectory()) {
                folderCount++;
                scanFolder(file1);
            } else if (file1.isFile()) {
                fileCount++;
                totalSize += file1.length();
            }
        }

    }
}