package thread;

import thread.FileScanner;

import java.io.File;

public class MyThread extends Thread {
    private  int fileCount = 0;
    private  int folderCount = 0;
    private  long totalSize = 0;
    public File root;

    public MyThread(File root) {
        this.root = root;
    }

    @Override
    public void run() {
        scanFolder(root);
        FileScanner.setAll(fileCount, folderCount, totalSize);
    }

    public  void scanFolder(File file) {
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
