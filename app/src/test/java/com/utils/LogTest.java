package com.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogTest {
    private static final String PATH_BASE_LOG = "/../output/logs/";
    public BufferedWriter writer = null;
    public String pathLog = null;

    public LogTest() {
        createLog();
    }

    public void createLog() {
        try {
            String logName = "run.log";
            String logPath = createPathLog() + logName;

            FileWriter outFile = new FileWriter(logPath, true);
            writer = new BufferedWriter(outFile);
        } catch (IOException ignored) {
        }
    }

    private String createPathLog() {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        Path currentRelativePath = Paths.get("");
        pathLog = currentRelativePath.toAbsolutePath().toString() + PATH_BASE_LOG + formatter.format(date) + '/';
        System.out.println("[DEBUG]createPathLog " + pathLog);

        File f = new File(pathLog);
        f.mkdirs();

        return pathLog;
    }

    public void writeLog(String msg) {
        try {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fmtLine = formatter.format(date)
                    + ' '
                    + msg
                    + System.lineSeparator();
            writer.append(fmtLine);
        } catch (IOException ignored) {
        }
    }

    public void closeLog() {
        try {
            if (writer != null) writer.close();
        } catch (IOException ignored) {
        }
    }
}
