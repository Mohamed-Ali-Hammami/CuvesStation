package shell.sijoumi.etatcuve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import shell.sijoumi.jeauge.JSpeedometer;

/**
 * Dashboard Swing pour afficher l'état des cuves.
 *
 * Dépendances conservées du projet original :
 * - ConnextionFTP
 * - Geauge
 * - Paramettre
 * - SoundService
 * - JSpeedometer
 */
public class GraphiqueCuve extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int REFRESH_DELAY_MS = 6000;
    private static final String ALARM_FILE = "C:\\sqlite\\Shell_App\\alarme.wav";

    private static final Color BACKGROUND = new Color(127, 185, 196);
    private static final Color TANK_GREEN = new Color(1, 180, 35);
    private static final Color WARNING_ORANGE = new Color(233, 159, 0);
    private static final Color BORDER_RED = Color.RED;

    private static final Font FIELD_FONT = new Font("Times New Roman", Font.BOLD, 14);
    private static final Font SMALL_FIELD_FONT = new Font("Times New Roman", Font.ITALIC, 12);

    private final DecimalFormat numberFormat = new DecimalFormat("#,###.000");
    private final Map<String, TankView> tanks = new LinkedHashMap<String, TankView>();
    private final JLabel updateLabel = new JLabel("UPDATE : --");

    private Timer refreshTimer;
    private boolean refreshInProgress;

    // Kept for compatibility with the old getters/setters.
    private double qteG1;
    private double qteG2;
    private double qteSSP1;
    private double qtePET;
    private double qteG50;
    private double qteSSp2;

    private double qte_G1;
    private double qte_G2;
    private double qte_SSP1;
    private double qte_SSP2;
    private double qte_PET;
    private double qte_G50;

    public GraphiqueCuve() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(BACKGROUND);

        createTankDefinitions();
        buildScreen();
        refreshData(true);
        startAutoRefresh();
    }

    private void createTankDefinitions() {
        tanks.put("5-Gasoil-1", new TankView(1, "5-Gasoil-1", 15389, new ThresholdProvider() {
            public double getThreshold(Paramettre p) {
                return p.getQteG1();
            }
        }));

        tanks.put("6-Gasoil-1", new TankView(2, "6-Gasoil-1", 15401, new ThresholdProvider() {
            public double getThreshold(Paramettre p) {
                return p.getQteG2();
            }
        }));

        tanks.put("1-SSP15M3", new TankView(3, "1-SSP15M3", 15447, new ThresholdProvider() {
            public double getThreshold(Paramettre p) {
                return p.getQteSSP1();
            }
        }));

        tanks.put("3-VPower-1", new TankView(4, "3-VPower-1", 15409, new ThresholdProvider() {
            public double getThreshold(Paramettre p) {
                return p.getQtePET();
            }
        }));

        tanks.put("4-GOSS-15M", new TankView(5, "4-GOSS-15M", 15446, new ThresholdProvider() {
            public double getThreshold(Paramettre p) {
                return p.getQteG50();
            }
        }));

        tanks.put("2-SSP15M3", new TankView(6, "2-SSP15M3", 15400, new ThresholdProvider() {
            public double getThreshold(Paramettre p) {
                return p.getQteSSp2();
            }
        }));
    }

    private void buildScreen() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BACKGROUND);

        JLabel title = new JLabel("Etat des cuves");
        title.setFont(new Font("Times New Roman", Font.BOLD, 22));
        title.setForeground(Color.BLACK);

        updateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        updateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        header.add(title, BorderLayout.WEST);
        header.add(updateLabel, BorderLayout.EAST);

        JPanel grid = new JPanel(new GridLayout(2, 3, 10, 10));
        grid.setBackground(BACKGROUND);

        for (TankView tank : tanks.values()) {
            grid.add(tank);
        }

        add(header, BorderLayout.NORTH);
        add(grid, BorderLayout.CENTER);
    }

    private void startAutoRefresh() {
        refreshTimer = new Timer(REFRESH_DELAY_MS, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                refreshData(false);
            }
        });
        refreshTimer.start();
    }

    public void stopAutoRefresh() {
        if (refreshTimer != null) {
            refreshTimer.stop();
        }
    }

    public void refreshData(final boolean firstLoad) {
        if (refreshInProgress) {
            return;
        }

        refreshInProgress = true;

        SwingWorker<List<Geauge>, Void> worker = new SwingWorker<List<Geauge>, Void>() {
            @Override
            protected List<Geauge> doInBackground() throws Exception {
                ConnextionFTP connection = new ConnextionFTP();
                List<Geauge> result = connection.getgeauge();
                return result == null ? new ArrayList<Geauge>() : result;
            }

            @Override
            protected void done() {
                try {
                    updateTanks(get(), firstLoad);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Refresh interrupted: " + e.getMessage());
                } catch (ExecutionException e) {
                    System.err.println("Unable to refresh tanks: " + e.getCause());
                } finally {
                    refreshInProgress = false;
                }
            }
        };

        worker.execute();
    }

    /**
     * Kept for compatibility with the old code.
     */
    public void initCuve() {
        refreshData(false);
    }

    /**
     * Kept for compatibility with the old code.
     */
    public void initCuve2() {
        refreshData(true);
    }

    private void updateTanks(List<Geauge> gauges, boolean firstLoad) {
        Paramettre parameters = new Paramettre();
        boolean hasLowLevelAlarm = false;

        for (Geauge gauge : gauges) {
            if (gauge == null || gauge.getCuve() == null) {
                continue;
            }

            TankView tank = findTank(gauge.getCuve());
            if (tank == null) {
                continue;
            }

            boolean tankAlarm = tank.updateFrom(gauge, parameters, firstLoad);
            hasLowLevelAlarm = hasLowLevelAlarm || tankAlarm;
            updateCompatibilityQuantities(tank.getCode(), gauge.getLevel());

            if (gauge.getHeure() != null && gauge.getHeure().trim().length() > 0) {
                updateLabel.setText("UPDATE : " + gauge.getHeure());
            }
        }

        if (hasLowLevelAlarm) {
            playAlarmAsync();
        }
    }

    private TankView findTank(String sourceName) {
        for (Map.Entry<String, TankView> entry : tanks.entrySet()) {
            if (sourceName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    private void updateCompatibilityQuantities(String code, double level) {
        if ("5-Gasoil-1".equals(code)) {
            qteG1 = level;
            qte_G1 = level;
        } else if ("6-Gasoil-1".equals(code)) {
            qteG2 = level;
            qte_G2 = level;
        } else if ("1-SSP15M3".equals(code)) {
            qteSSP1 = level;
            qte_SSP1 = level;
        } else if ("2-SSP15M3".equals(code)) {
            qteSSp2 = level;
            qte_SSP2 = level;
        } else if ("3-VPower-1".equals(code)) {
            qtePET = level;
            qte_PET = level;
        } else if ("4-GOSS-15M".equals(code)) {
            qteG50 = level;
            qte_G50 = level;
        }
    }

    private void playAlarmAsync() {
        Thread alarmThread = new Thread(new Runnable() {
            public void run() {
                try {
                    new SoundService().sound(ALARM_FILE);
                } catch (Exception e) {
                    System.err.println("Unable to play alarm: " + e.getMessage());
                }
            }
        }, "tank-alarm");

        alarmThread.setDaemon(true);
        alarmThread.start();
    }

    private Icon loadIcon(String name) {
        URL url = getClass().getResource(name);
        if (url == null) {
            url = getClass().getResource("/" + name);
        }
        return url == null ? null : new ImageIcon(url);
    }

    private int percentage(double value, double capacity) {
        if (capacity <= 0) {
            return 0;
        }

        int percent = (int) Math.round((value * 100.0) / capacity);
        return clamp(percent, 0, 100);
    }

    private int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    private interface ThresholdProvider {
        double getThreshold(Paramettre p);
    }

    private final class TankView extends JPanel {

        private static final long serialVersionUID = 1L;

        private final int index;
        private final String code;
        private final double capacity;
        private final ThresholdProvider thresholdProvider;

        private final JTextField quantityField = createReadOnlyField("0,000", 110, 20, FIELD_FONT, Color.BLUE);
        private final JTextField emptyField = createReadOnlyField("0,000", 110, 20, FIELD_FONT, Color.RED);
        private final JTextField waterLiterField = createReadOnlyField("0,000", 55, 18, SMALL_FIELD_FONT, Color.BLACK);
        private final JTextField waterMmField = createReadOnlyField("0,000", 55, 18, SMALL_FIELD_FONT, Color.BLACK);
        private final JTextField temperatureField = createReadOnlyField("0,000", 55, 18, SMALL_FIELD_FONT, Color.BLACK);

        private final JProgressBar horizontalBar = new JProgressBar(0, 100);
        private final JProgressBar verticalBar = createVerticalProgressBar();
        private final JSpeedometer speedometer = new JSpeedometer(10, "Jauge");

        private final JLabel directionIcon = new JLabel();
        private final JLabel alarmIcon = new JLabel();

        private double previousLevel = Double.NaN;

        private TankView(int index, String code, double capacity, ThresholdProvider thresholdProvider) {
            this.index = index;
            this.code = code;
            this.capacity = capacity;
            this.thresholdProvider = thresholdProvider;

            buildTankCard();
        }

        private String getCode() {
            return code;
        }

        private void buildTankCard() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createLineBorder(BORDER_RED));
            setBackground(BACKGROUND);

            speedometer.setColors(TANK_GREEN, null, Color.BLACK);
            directionIcon.setIcon(loadIcon("flech3.png"));
            directionIcon.setVisible(false);
            alarmIcon.setIcon(loadIcon("ValeurInitiale.png"));
            alarmIcon.setVisible(false);

            add(createIdentityPanel());
            add(createQuantityPanel());
            add(createHorizontalProgressPanel());
            add(createWaterAndTemperaturePanel());
            add(createWaterMmPanel());
            add(createVisualPanel());
        }

        private JPanel createIdentityPanel() {
            JPanel panel = rowPanel();
            panel.add(new JLabel("Cuve: "));
            panel.add(createReadOnlyField(code, 110, 20, FIELD_FONT, Color.BLACK));
            panel.add(new JLabel("Capacité: "));
            panel.add(createReadOnlyField(numberFormat.format(capacity), 110, 20, FIELD_FONT, Color.BLACK));
            return panel;
        }

        private JPanel createQuantityPanel() {
            JPanel panel = rowPanel();
            panel.add(new JLabel("Qte: "));
            panel.add(quantityField);
            panel.add(new JLabel("Vide: "));
            panel.add(emptyField);
            return panel;
        }

        private JPanel createHorizontalProgressPanel() {
            JPanel panel = rowPanel();
            horizontalBar.setPreferredSize(new Dimension(360, 22));
            horizontalBar.setStringPainted(true);
            horizontalBar.setForeground(TANK_GREEN);
            panel.add(horizontalBar);
            return panel;
        }

        private JPanel createWaterAndTemperaturePanel() {
            JPanel panel = rowPanel();
            panel.add(new JLabel("Eau(L):"));
            panel.add(waterLiterField);
            panel.add(new JLabel("Temp:"));
            panel.add(temperatureField);

            Icon temperatureIcon = loadIcon("phototemperature.png");
            if (temperatureIcon != null) {
                panel.add(new JLabel(temperatureIcon));
            }

            panel.add(directionIcon);
            return panel;
        }

        private JPanel createWaterMmPanel() {
            JPanel panel = rowPanel();
            panel.add(new JLabel("Eau(MM):"));
            panel.add(waterMmField);
            return panel;
        }

        private JPanel createVisualPanel() {
            JPanel panel = new JPanel(new BorderLayout(8, 0));
            panel.setBackground(BACKGROUND);
            panel.setBorder(new EmptyBorder(5, 8, 8, 8));

            JLabel numberLabel = new JLabel(String.valueOf(index), SwingConstants.CENTER);
            numberLabel.setForeground(Color.RED);
            numberLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
            numberLabel.setPreferredSize(new Dimension(45, 45));
            numberLabel.setBorder(BorderFactory.createLineBorder(BORDER_RED));

            JPanel centerPanel = new JPanel(new BorderLayout());
            centerPanel.setBackground(BACKGROUND);
            centerPanel.add(speedometer, BorderLayout.CENTER);
            centerPanel.add(alarmIcon, BorderLayout.EAST);

            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBackground(BACKGROUND);
            rightPanel.add(verticalBar, BorderLayout.CENTER);

            Icon reservoirIcon = loadIcon("photoreservoir1.png");
            if (reservoirIcon != null) {
                rightPanel.add(new JLabel(reservoirIcon), BorderLayout.EAST);
            }

            panel.add(numberLabel, BorderLayout.WEST);
            panel.add(centerPanel, BorderLayout.CENTER);
            panel.add(rightPanel, BorderLayout.EAST);
            return panel;
        }

        private boolean updateFrom(Geauge gauge, Paramettre parameters, boolean firstLoad) {
            double level = gauge.getLevel();
            int percent = percentage(level, capacity);

            quantityField.setText(numberFormat.format(level));
            emptyField.setText(numberFormat.format(gauge.getCreux()));
            temperatureField.setText(numberFormat.format(gauge.getTemperature()));

            horizontalBar.setValue(percent);
            horizontalBar.setString(percent + "%");
            verticalBar.setValue(percent);
            verticalBar.setString(percent + "%");
            speedometer.setSpeed(percent);

            double threshold = thresholdProvider.getThreshold(parameters);
            boolean lowLevel = level <= threshold;
            alarmIcon.setVisible(lowLevel);

            updateDirectionIcon(level, firstLoad);
            previousLevel = level;

            repaint();
            return lowLevel;
        }

        private void updateDirectionIcon(double level, boolean firstLoad) {
            if (firstLoad || Double.isNaN(previousLevel)) {
                directionIcon.setVisible(false);
                return;
            }

            double difference = level - previousLevel;
            if (Math.abs(difference) < 1) {
                directionIcon.setVisible(false);
                return;
            }

            if (difference > 0) {
                directionIcon.setIcon(loadIcon("flech3.png"));
            } else {
                directionIcon.setIcon(loadIcon("images.jpg"));
            }

            directionIcon.setVisible(true);
        }
    }

    private JPanel rowPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        return panel;
    }

    private JTextField createReadOnlyField(String text, int width, int height, Font font, Color foreground) {
        JTextField field = new JTextField(text);
        field.setPreferredSize(new Dimension(width, height));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        field.setFont(font);
        field.setForeground(foreground);
        field.setBackground(BACKGROUND);
        field.setEditable(false);
        return field;
    }

    private JProgressBar createVerticalProgressBar() {
        JProgressBar bar = new JProgressBar(0, 100) {
            private static final long serialVersionUID = 1L;

            @Override
            public int getOrientation() {
                for (StackTraceElement element : new Throwable().getStackTrace()) {
                    String method = element.getMethodName();
                    if ("paintText".equals(method) || "paintString".equals(method)) {
                        return JProgressBar.HORIZONTAL;
                    }
                }
                return JProgressBar.VERTICAL;
            }
        };

        bar.setPreferredSize(new Dimension(132, 68));
        bar.setStringPainted(true);
        bar.setForeground(WARNING_ORANGE);
        return bar;
    }

    public double getQteG1() {
        return qteG1;
    }

    public void setQteG1(double qteG1) {
        this.qteG1 = qteG1;
    }

    public double getQteG2() {
        return qteG2;
    }

    public void setQteG2(double qteG2) {
        this.qteG2 = qteG2;
    }

    public double getQteSSP1() {
        return qteSSP1;
    }

    public void setQteSSP1(double qteSSP1) {
        this.qteSSP1 = qteSSP1;
    }

    public double getQtePET() {
        return qtePET;
    }

    public void setQtePET(double qtePET) {
        this.qtePET = qtePET;
    }

    public double getQteG50() {
        return qteG50;
    }

    public void setQteG50(double qteG50) {
        this.qteG50 = qteG50;
    }

    public double getQteSSp2() {
        return qteSSp2;
    }

    public void setQteSSp2(double qteSSp2) {
        this.qteSSp2 = qteSSp2;
    }

    public double getQte_G1() {
        return qte_G1;
    }

    public void setQte_G1(double qte_G1) {
        this.qte_G1 = qte_G1;
    }

    public double getQte_G2() {
        return qte_G2;
    }

    public void setQte_G2(double qte_G2) {
        this.qte_G2 = qte_G2;
    }

    public double getQte_SSP1() {
        return qte_SSP1;
    }

    public void setQte_SSP1(double qte_SSP1) {
        this.qte_SSP1 = qte_SSP1;
    }

    public double getQte_SSP2() {
        return qte_SSP2;
    }

    public void setQte_SSP2(double qte_SSP2) {
        this.qte_SSP2 = qte_SSP2;
    }

    public double getQte_PET() {
        return qte_PET;
    }

    public void setQte_PET(double qte_PET) {
        this.qte_PET = qte_PET;
    }

    public double getQte_G50() {
        return qte_G50;
    }

    public void setQte_G50(double qte_G50) {
        this.qte_G50 = qte_G50;
    }
}
