package shell.sijoumi.etatcuve;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class AppLogger {
  private static boolean initialized = false;
  private static PrintStream originalOut;
  private static PrintStream originalErr;
  private static PrintStream fileStream;

  public static synchronized void init() {
    if (initialized) {
      return;
    }

    originalOut = System.out;
    originalErr = System.err;

    try {
      File logFile = new File(AppConfig.LOG_FILE);
      File parent = logFile.getParentFile();
      if (parent != null) {
        parent.mkdirs();
      }

      fileStream = new PrintStream(new FileOutputStream(logFile, true), true, "UTF-8");
      System.setOut(new PrintStream(new TeeOutputStream(originalOut, fileStream), true, "UTF-8"));
      System.setErr(new PrintStream(new TeeOutputStream(originalErr, fileStream), true, "UTF-8"));
      initialized = true;
      info("Logger started: " + logFile.getAbsolutePath());
    } catch (Exception ex) {
      initialized = true;
      originalErr.println("Unable to initialize log file: " + ex.getMessage());
      ex.printStackTrace(originalErr);
    }
  }

  public static void info(String message) {
    log("INFO", message, null);
  }

  public static void warn(String message) {
    log("WARN", message, null);
  }

  public static void error(String message, Throwable throwable) {
    log("ERROR", message, throwable);
  }

  private static synchronized void log(String level, String message, Throwable throwable) {
    if (!initialized) {
      init();
    }

    PrintStream target = "ERROR".equals(level) ? System.err : System.out;
    target.println(timestamp() + " [" + level + "] " + message);
    if (throwable != null) {
      throwable.printStackTrace(target);
    }
  }

  private static String timestamp() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
  }

  private static final class TeeOutputStream extends OutputStream {
    private final OutputStream first;
    private final OutputStream second;

    private TeeOutputStream(OutputStream first, OutputStream second) {
      this.first = first;
      this.second = second;
    }

    public void write(int b) throws IOException {
      first.write(b);
      second.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
      first.write(b, off, len);
      second.write(b, off, len);
    }

    public void flush() throws IOException {
      first.flush();
      second.flush();
    }
  }

  private AppLogger() {}
}
