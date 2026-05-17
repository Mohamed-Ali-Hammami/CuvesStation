/*    */ package shell.sijoumi.etatcuve;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Collections;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ 
/*    */ /**
 * Centralized configuration for the application.
 * <p>
 * This class still preserves existing defaults for backward compatibility.
 * When present, it loads values from a local `config.properties` file.
 */
/*    */ public final class AppConfig {
/*    */ 
/*    */   // Refresh interval used by GraphiqueCuve BarThread: sleep DELAY=1000 and multiplies by 20.
/*    */   // That results in ~20s between initCuve() calls.
/*    */   public static final int REFRESH_SECONDS = 20;

/*    */   // Default values (fallback if config.properties is missing)
/*    */   public static final String DEFAULT_SQLITE_TANK_STATUS_DB = "C://sqlite/tankstatusFiles/TankStatus.db3";
/*    */   public static final String DEFAULT_SQLITE_JOURNAL_DB_DIR = "C://sqlite/db";
/*    */   public static final String DEFAULT_ALARM_WAV_FILE = "resource:/shell/sijoumi/etatcuve/items/alarme.wav";
/*    */   public static final String DEFAULT_LOG_FILE = "logs/appjeauge.log";
/*    */   public static final int DEFAULT_ALERT_TANK_1_MIN = 7000;
/*    */   public static final int DEFAULT_ALERT_TANK_2_MIN = 3000;
/*    */   public static final int DEFAULT_ALERT_TANK_3_MIN = 3000;
/*    */   public static final int DEFAULT_ALERT_TANK_4_MIN = 5000;
/*    */   public static final int DEFAULT_ALERT_TANK_5_MIN = 3000;
/*    */   public static final int DEFAULT_ALERT_TANK_6_MIN = 3000;

/*    */   public static final String DEFAULT_FTP_SERVER = "192.168.242.1";
/*    */   public static final int DEFAULT_FTP_PORT = 21;
/*    */   public static final String DEFAULT_FTP_USERNAME = "SUPERVISOR";
/*    */   public static final String DEFAULT_FTP_PASSWORD = "CHANGE_ME";
/*    */   public static final String DEFAULT_FTP_JOURNAL_DIR = "/JOURNAL/";
/*    */   public static final String DEFAULT_FTP_TANKSTATUS_DIR = "/PRO_TMS/initial/";
/*    */   public static final String DEFAULT_FTP_TANKSTATUS_FILENAME = "TankStatus.db3";

/*    */   // Runtime-loaded values
/*    */   public static String SQLITE_TANK_STATUS_DB = DEFAULT_SQLITE_TANK_STATUS_DB;
/*    */   public static String SQLITE_JOURNAL_DB_DIR = DEFAULT_SQLITE_JOURNAL_DB_DIR;
/*    */   public static String ALARM_WAV_FILE = DEFAULT_ALARM_WAV_FILE;
/*    */   public static String LOG_FILE = DEFAULT_LOG_FILE;
/*    */   public static int ALERT_TANK_1_MIN = DEFAULT_ALERT_TANK_1_MIN;
/*    */   public static int ALERT_TANK_2_MIN = DEFAULT_ALERT_TANK_2_MIN;
/*    */   public static int ALERT_TANK_3_MIN = DEFAULT_ALERT_TANK_3_MIN;
/*    */   public static int ALERT_TANK_4_MIN = DEFAULT_ALERT_TANK_4_MIN;
/*    */   public static int ALERT_TANK_5_MIN = DEFAULT_ALERT_TANK_5_MIN;
/*    */   public static int ALERT_TANK_6_MIN = DEFAULT_ALERT_TANK_6_MIN;

/*    */   public static String FTP_SERVER = DEFAULT_FTP_SERVER;
/*    */   public static int FTP_PORT = DEFAULT_FTP_PORT;
/*    */   public static String FTP_USERNAME = DEFAULT_FTP_USERNAME;
/*    */   public static String FTP_PASSWORD = DEFAULT_FTP_PASSWORD;

/*    */   public static String FTP_JOURNAL_DIR = DEFAULT_FTP_JOURNAL_DIR;
/*    */   public static String FTP_TANKSTATUS_DIR = DEFAULT_FTP_TANKSTATUS_DIR;
/*    */   public static String FTP_TANKSTATUS_FILENAME = DEFAULT_FTP_TANKSTATUS_FILENAME;

/*    */   // Tank mapping used when decoding TankStatus rows.
/*    */   // NOTE: This does NOT change the existing DB ordering logic; it only centralizes constants.
/*    */   public static final String TANK_1 = "1-SSP15M3";
/*    */   public static final String TANK_2 = "2-SSP15M3";
/*    */   public static final String TANK_3 = "3-VPower-1";
/*    */   public static final String TANK_4 = "4-GOSS-15M";
/*    */   public static final String TANK_5 = "5-Gasoil-1";
/*    */   public static final String TANK_6 = "6-Gasoil-1";

/*    */   // Tank capacities used by UI percentages and configuration screens.
/*    */   public static final double DEFAULT_CAPACITY_1 = 15447.0D;
/*    */   public static final double DEFAULT_CAPACITY_2 = 15400.0D;
/*    */   public static final double DEFAULT_CAPACITY_3 = 15409.0D;
/*    */   public static final double DEFAULT_CAPACITY_4 = 15446.0D;
/*    */   public static final double DEFAULT_CAPACITY_5 = 15447.0D;
/*    */   public static final double DEFAULT_CAPACITY_6 = 15401.0D;
/*    */   public static double CAPACITY_1 = DEFAULT_CAPACITY_1;
/*    */   public static double CAPACITY_2 = DEFAULT_CAPACITY_2;
/*    */   public static double CAPACITY_3 = DEFAULT_CAPACITY_3;
/*    */   public static double CAPACITY_4 = DEFAULT_CAPACITY_4;
/*    */   public static double CAPACITY_5 = DEFAULT_CAPACITY_5;
/*    */   public static double CAPACITY_6 = DEFAULT_CAPACITY_6;

/*    */   /**
 * Holds fixed tank name mapping by the DB row index used today (1..6).
 *
 * This is only constants; ConnextionFTP still needs to keep its current row->tank behavior.
 */
/*    */   public static final Map<Integer, String> TANK_NAME_BY_ROW;

/*    */   static {
/*    */     Map<Integer, String> m = new HashMap<>();
/*    */     m.put(1, TANK_1);
/*    */     m.put(2, TANK_2);
/*    */     m.put(3, TANK_3);
/*    */     m.put(4, TANK_4);
/*    */     m.put(5, TANK_5);
/*    */     m.put(6, TANK_6);
/*    */     TANK_NAME_BY_ROW = Collections.unmodifiableMap(m);
/*    */ 
/*    */     // Load external configuration if present.
/*    */     // Expected to be placed next to the application working directory.
/*    */     loadFromConfigProperties();
/*    */   }

/*    */   private static void loadFromConfigProperties() {
/*    */     Properties props = new Properties();
/*    */     // Prefer a root-level config.properties in working directory.
/*    */     String[] candidates = new String[] {
/*    */         "config.properties",
/*    */         "./config.properties"
/*    */     };
/*    */ 
/*    */     for (String p : candidates) {
/*    */       try (FileInputStream fis = new FileInputStream(p)) {
/*    */         props.load(fis);
/*    */         // success
/*    */         applyProps(props);
/*    */         return;
/*    */       } catch (IOException ignored) {
/*    */         // try next candidate
/*    */       }
/*    */     }
/*    */   }

/*    */   private static void applyProps(Properties props) {
/*    */     SQLITE_TANK_STATUS_DB = getOrDefault(props, "SQLITE_TANK_STATUS_DB", DEFAULT_SQLITE_TANK_STATUS_DB);
/*    */     SQLITE_JOURNAL_DB_DIR = getOrDefault(props, "SQLITE_JOURNAL_DB_DIR", DEFAULT_SQLITE_JOURNAL_DB_DIR);
/*    */     ALARM_WAV_FILE = getOrDefault(props, "ALARM_WAV_FILE", DEFAULT_ALARM_WAV_FILE);
/*    */     LOG_FILE = getOrDefault(props, "LOG_FILE", DEFAULT_LOG_FILE);
/*    */     ALERT_TANK_1_MIN = getIntOrDefault(props, "ALERT_TANK_1_MIN", DEFAULT_ALERT_TANK_1_MIN);
/*    */     ALERT_TANK_2_MIN = getIntOrDefault(props, "ALERT_TANK_2_MIN", DEFAULT_ALERT_TANK_2_MIN);
/*    */     ALERT_TANK_3_MIN = getIntOrDefault(props, "ALERT_TANK_3_MIN", DEFAULT_ALERT_TANK_3_MIN);
/*    */     ALERT_TANK_4_MIN = getIntOrDefault(props, "ALERT_TANK_4_MIN", DEFAULT_ALERT_TANK_4_MIN);
/*    */     ALERT_TANK_5_MIN = getIntOrDefault(props, "ALERT_TANK_5_MIN", DEFAULT_ALERT_TANK_5_MIN);
/*    */     ALERT_TANK_6_MIN = getIntOrDefault(props, "ALERT_TANK_6_MIN", DEFAULT_ALERT_TANK_6_MIN);
/*    */     CAPACITY_1 = getDoubleOrDefault(props, "TANK_1_CAPACITY", DEFAULT_CAPACITY_1);
/*    */     CAPACITY_2 = getDoubleOrDefault(props, "TANK_2_CAPACITY", DEFAULT_CAPACITY_2);
/*    */     CAPACITY_3 = getDoubleOrDefault(props, "TANK_3_CAPACITY", DEFAULT_CAPACITY_3);
/*    */     CAPACITY_4 = getDoubleOrDefault(props, "TANK_4_CAPACITY", DEFAULT_CAPACITY_4);
/*    */     CAPACITY_5 = getDoubleOrDefault(props, "TANK_5_CAPACITY", DEFAULT_CAPACITY_5);
/*    */     CAPACITY_6 = getDoubleOrDefault(props, "TANK_6_CAPACITY", DEFAULT_CAPACITY_6);

/*    */     FTP_SERVER = getOrDefault(props, "FTP_SERVER", DEFAULT_FTP_SERVER);
/*    */ 
/*    */     String portStr = props.getProperty("FTP_PORT");
/*    */     if (portStr != null) {
/*    */       try { FTP_PORT = Integer.parseInt(portStr.trim()); }
/*    */       catch (Exception ignored) {}
/*    */     }

/*    */     FTP_USERNAME = getOrDefault(props, "FTP_USERNAME", DEFAULT_FTP_USERNAME);
/*    */     FTP_PASSWORD = getOrDefault(props, "FTP_PASSWORD", DEFAULT_FTP_PASSWORD);

/*    */     FTP_JOURNAL_DIR = getOrDefault(props, "FTP_JOURNAL_DIR", DEFAULT_FTP_JOURNAL_DIR);
/*    */     FTP_TANKSTATUS_DIR = getOrDefault(props, "FTP_TANKSTATUS_DIR", DEFAULT_FTP_TANKSTATUS_DIR);
/*    */     FTP_TANKSTATUS_FILENAME = getOrDefault(props, "FTP_TANKSTATUS_FILENAME", DEFAULT_FTP_TANKSTATUS_FILENAME);
/*    */   }

/*    */   private static String getOrDefault(Properties props, String key, String def) {
/*    */     String v = props.getProperty(key);
/*    */     if (v == null) return def;
/*    */     v = v.trim();
/*    */     if (v.isEmpty()) return def;
/*    */     return v;
/*    */   }

/*    */   private static int getIntOrDefault(Properties props, String key, int def) {
/*    */     String v = props.getProperty(key);
/*    */     if (v == null) return def;
/*    */     try {
/*    */       return Integer.parseInt(v.trim());
/*    */     } catch (Exception ignored) {
/*    */       return def;
/*    */     }
/*    */   }

/*    */   private static double getDoubleOrDefault(Properties props, String key, double def) {
/*    */     String v = props.getProperty(key);
/*    */     if (v == null) return def;
/*    */     try {
/*    */       return Double.parseDouble(v.trim().replace(',', '.'));
/*    */     } catch (Exception ignored) {
/*    */       return def;
/*    */     }
/*    */   }

/*    */   private AppConfig() {}
/*    */ }

