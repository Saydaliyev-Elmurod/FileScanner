package threadexucutor;

import thread.FileScanner;

import java.io.File;

public class MyThread implements Runnable {
    private int fileCount = 0;
    private int folderCount = 0;
    private long totalSize = 0;
    public File root;

    public MyThread(File root) {
        this.root = root;
    }

    @Override
    public void run() {
        scanFolder(root);
        FileScanner.setAll(fileCount, folderCount, totalSize);

    }

    public void scanFolder(File file) {

        if (file.list() == null) {
            return;
        }
        for (File innerFile : file.listFiles()) {
            if (innerFile.isDirectory()) {
                folderCount++;
                threadexucutor.FileScanner.threadPoolExecutor.submit(new MyThread(innerFile));
            } else if (innerFile.isFile()) {
                fileCount++;
                totalSize += innerFile.length();
            }
        }

    }
}
