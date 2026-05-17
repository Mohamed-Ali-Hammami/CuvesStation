package shell.sijoumi.etatcuve;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ConnextionFTP {
  private List<Transaction> listjournal;

  public boolean verifPompe(String pompe, List<String> listpompes) {
    return listpompes != null && listpompes.contains(pompe);
  }

  public List<Geauge> selectGeauge() throws ClassNotFoundException {
    String sql = "SELECT Timestamp, LevelQuantity, Temperature FROM TankStatus LIMIT 6";
    List<Geauge> listGeauge = new ArrayList<>();
    Class.forName("org.sqlite.JDBC");

    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + AppConfig.SQLITE_TANK_STATUS_DB);
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
      int row = 1;
      while (resultSet.next()) {
        Geauge g = buildGauge(row, resultSet);
        if (g != null) {
          listGeauge.add(g);
        }
        row++;
      }
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }

    return listGeauge;
  }

  private Geauge buildGauge(int row, ResultSet resultSet) throws SQLException {
    String cuve = AppConfig.TANK_NAME_BY_ROW.get(Integer.valueOf(row));
    if (cuve == null) {
      return null;
    }

    double[] capacities = {
        AppConfig.CAPACITY_1,
        AppConfig.CAPACITY_2,
        AppConfig.CAPACITY_3,
        AppConfig.CAPACITY_4,
        AppConfig.CAPACITY_5,
        AppConfig.CAPACITY_6
    };
    double levelQuantity = Double.parseDouble(resultSet.getString("LevelQuantity")) / 100.0D;
    double temperature = Double.parseDouble(resultSet.getString("Temperature")) / 100.0D;

    Geauge g = new Geauge();
    g.setCuve(cuve);
    g.setHeure(resultSet.getString("Timestamp"));
    g.setCreux(capacities[row - 1] - levelQuantity);
    g.setLevel(levelQuantity);
    g.setTemperature(temperature);
    return g;
  }

  public List<Transaction> selectAlljournal(Date date, String txtHhmm, String txt_h_fin, List<String> listpompes) throws ClassNotFoundException {
    this.listjournal = new ArrayList<>();
    if (date == null || listpompes == null || listpompes.isEmpty()) {
      return this.listjournal;
    }

    SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd");
    String dates = fileFormat.format(date);
    File journalDb = new File(AppConfig.SQLITE_JOURNAL_DB_DIR, dates + ".db3");
    String sql = "SELECT Time, Journaltext FROM JournalMaster WHERE Selection = 'DISP HANDLER' AND Time BETWEEN ? AND ?";

    Class.forName("org.sqlite.JDBC");
    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + journalDb.getPath());
         PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, sanitizeTime(txtHhmm, "00:00"));
      statement.setString(2, sanitizeTime(txt_h_fin, "23:59"));

      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          Transaction tr = parseTransaction(resultSet.getString("Time"), resultSet.getString("Journaltext"), dates, listpompes);
          if (tr != null) {
            this.listjournal.add(tr);
          }
        }
      }
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    } catch (Exception e) {
      System.out.println("Erreur lecture journal: " + e.getMessage());
    }

    return this.listjournal;
  }

  private String sanitizeTime(String value, String fallback) {
    if (value == null) {
      return fallback;
    }
    value = value.trim();
    return value.length() == 0 ? fallback : value;
  }

  private Transaction parseTransaction(String time, String journal, String dates, List<String> listpompes) {
    if (journal == null) {
      return null;
    }

    String[] requetes = journal.trim().split("\\s+");
    if (requetes.length <= 10 || !requetes[2].contains("PMP") || !listpompes.contains(requetes[2])) {
      return null;
    }

    DecimalFormat df = new DecimalFormat("0.000");
    Transaction tr = new Transaction();
    tr.setHeure(time);
    tr.setDates(dates);
    tr.setPompe(requetes[2]);
    tr.setPistolet(requetes[4]);
    tr.setArticle(getArticle(Integer.valueOf(Integer.parseInt(requetes[5].substring(5)))));
    tr.setQte(Double.parseDouble(requetes[6].substring(4)));
    tr.setMontant(Double.parseDouble(requetes[7].substring(4)));
    double p = Double.parseDouble(requetes[10].substring(4));
    tr.setPrix(df.format(p));
    return tr;
  }

  private String getArticle(Integer num) {
    if (num.intValue() == 1) {
      return "SSP";
    }
    if (num.intValue() == 3) {
      return "GASOIL";
    }
    if (num.intValue() == 4) {
      return "SSP-V-POWER";
    }
    return "GASOIL 50";
  }

  public void connexionFTP3(Date date) {
    SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
    String dates = f.format(date);
    downloadFile(
        AppConfig.FTP_JOURNAL_DIR,
        dates + ".db3",
        new File(AppConfig.SQLITE_JOURNAL_DB_DIR, dates + ".db3"));
  }

  public void connexionFTP2(Date date) {
    downloadFile(
        AppConfig.FTP_TANKSTATUS_DIR,
        AppConfig.FTP_TANKSTATUS_FILENAME,
        new File(AppConfig.SQLITE_TANK_STATUS_DB));
  }

  private void downloadFile(String remoteDir, String remoteFileName, File localFile) {
    FTPClient ftpClient = new FTPClient();
    try {
      ftpClient.connect(AppConfig.FTP_SERVER, AppConfig.FTP_PORT);
      reponseServeur(ftpClient);

      int reponse = ftpClient.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reponse)) {
        System.out.println("Operation echouee. Reponse Serveur: " + reponse);
        return;
      }

      boolean etat = ftpClient.login(AppConfig.FTP_USERNAME, AppConfig.FTP_PASSWORD);
      reponseServeur(ftpClient);
      if (!etat) {
        System.out.println("Impossible d'acceder au serveur");
        return;
      }

      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

      if (localFile.getParentFile() != null) {
        localFile.getParentFile().mkdirs();
      }

      String remotePath = normalizeRemotePath(remoteDir, remoteFileName);
      long remoteSize = getRemoteSize(ftpClient, remoteDir, remoteFileName);
      try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFile));
           InputStream inputStream = ftpClient.retrieveFileStream(remotePath)) {
        if (inputStream == null) {
          System.out.println("Fichier FTP introuvable: " + remotePath);
          return;
        }

        byte[] bytesArray = new byte[4096];
        int bytesRead;
        int transferred = 0;
        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
          outputStream.write(bytesArray, 0, bytesRead);
          transferred += bytesRead;
          if (remoteSize > 0L) {
            int pourcentage = (int)((transferred * 100L) / remoteSize);
          }
        }
      }
      ftpClient.completePendingCommand();
    } catch (IOException ex) {
      System.out.println("Une erreur lors de la connexion a ete detectee");
      ex.printStackTrace();
    } finally {
      try {
        if (ftpClient.isConnected()) {
          ftpClient.logout();
          ftpClient.disconnect();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  private String normalizeRemotePath(String remoteDir, String remoteFileName) {
    if (remoteDir == null || remoteDir.trim().length() == 0) {
      return remoteFileName;
    }
    String dir = remoteDir.trim();
    if (!dir.endsWith("/")) {
      dir = dir + "/";
    }
    return dir + remoteFileName;
  }

  private long getRemoteSize(FTPClient ftpClient, String remoteDir, String remoteFileName) throws IOException {
    FTPFile[] files = ftpClient.listFiles(remoteDir);
    if (files == null) {
      return 0L;
    }
    for (int i = 0; i < files.length; i++) {
      if (remoteFileName.equals(files[i].getName())) {
        return files[i].getSize();
      }
    }
    return 0L;
  }

  private void reponseServeur(FTPClient ftpClient) {
    String[] reponses = ftpClient.getReplyStrings();
    if (reponses != null && reponses.length > 0) {
      for (int i = 0; i < reponses.length; i++) {
        System.out.println("SERVEUR :" + reponses[i]);
      }
    }
  }
}
