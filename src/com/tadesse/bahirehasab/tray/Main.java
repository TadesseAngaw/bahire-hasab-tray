/*
 * Copyright (C) 2016 Tadesse Angaw
 *
 * This program is written by Tadesse Angaw.
 * You can use and redistribute it without modification.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You can contact me at tadesseangaw@gmail.com
 */
package com.tadesse.bahirehasab.tray;

import com.tadesse.bahirehasab.tray.tools.Holiday;
import com.tadesse.bahirehasab.tray.tools.Fasting;
import com.tadesse.bahirehasab.tray.tools.EthiopianCalendar;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.tadesse.bahirehasab.tray.tools.HolydayNotification;
import com.tadesse.bahirehasab.tray.tools.Sound;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author user pc
 */
public class Main {

    private EthiopianCalendar ec;
    private Image image;
    private SystemTray sysTray;
    private JPopupMenu pop;
    private JMenuItem start;
    private JMenuItem pin;
    private JMenuItem exit;
    private TrayIcon trayIcon;
    private Preferences root = Preferences.userRoot().node("bahire-hasab");
    boolean onOff = true;

    private void startTray() {
        initComponents();
        boolean showToday = root.getBoolean("is-widget-active", true);
        if (showToday) {
            hifi();
            WidgetOne widgetOne = new WidgetOne(new javax.swing.JFrame(), false);
            widgetOne.setVisible(true);
        }
    }

    private void initComponents() {

        hifi();

        if (SystemTray.isSupported()) {
            sysTray = SystemTray.getSystemTray();
        }
        image = createImage(0, true);
        pop = new JPopupMenu();
        exit = new JMenuItem("መውጫ");
        exit.setFont(new Font("Ebrima", Font.PLAIN, 13));
        pin = new JMenuItem("ዛሬን ማሳያ");
        pin.setFont(new Font("Ebrima", Font.PLAIN, 13));
        start = new JMenuItem("የቀን መቁጠሪያ");
        start.setFont(new Font("Ebrima", Font.PLAIN, 13));
        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        pin.addActionListener((ActionEvent e) -> {
            if (!root.getBoolean("is-widget-active", false)) {
                hifi();
                WidgetOne widgetOne = new WidgetOne(new javax.swing.JFrame(), false);
                root.putBoolean("is-widget-active", true);
                widgetOne.setVisible(true);
            }
        });

        start.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {
                hifi();
                ec = new EthiopianCalendar();
                int ethiopic[] = ec.toEthiopicDate(Calendar.getInstance());
                int date = ec.formattedDate(ethiopic[0], ethiopic[1])[0];
                int month = ec.formattedDate(ethiopic[0], ethiopic[1])[1];
                int year = ec.formattedDate(ethiopic[0], ethiopic[1])[2];
                
                javax.swing.JFrame frame = new javax.swing.JFrame();
                MiniCalendar w = new MiniCalendar(frame, false, date, month - 1, year);

                int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                int width = w.getWidth();
                int height = w.getHeight();
                Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
                int taskBarHeight = scnMax.bottom;
                w.setLocation(screenWidth - width - 5, screenHeight - height - taskBarHeight - 5);
            
                w.setVisible(true);
                
            });
        });

        pop.add(pin);
        pop.add(start);
        pop.add(new javax.swing.JSeparator());
        pop.add(exit);
        ec = new EthiopianCalendar();
        UIManager.put("ToolTip.font", new FontUIResource("Ebrima", Font.PLAIN, 13));
        trayIcon = new TrayIcon(image, "Ethiopian Calendar", null);
        trayIcon.setImageAutoSize(true);

        trayIcon.addActionListener((ActionEvent e) -> {
            if (!root.getBoolean("is-widget-active", false)) {
                hifi();
                WidgetOne widgetOne = new WidgetOne(new javax.swing.JFrame(), false);
                root.putBoolean("is-widget-active", true);
                widgetOne.setVisible(true);
            }
        });

        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
                    pop.setLocation(evt.getX(), evt.getY() - pop.getHeight() - 5);
                    pop.setInvoker(pop);
                    pop.setVisible(true);
                }
            }
        });

        trayIcon.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                try {
                    Thread.sleep(5000);
                    pop.setVisible(false);
                } catch (InterruptedException ex) {

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (pop.isVisible()) {
                    pop.setVisible(false);

                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (onOff) {
                    pop.setLocation(e.getX(), e.getY() - pop.getHeight() - 5);
                    pop.setInvoker(pop);
                    pop.setVisible(true);
                    onOff = !onOff;
                } else {
                    pop.setVisible(false);
                    onOff = !onOff;
                }
            }
        });

        try {
            new Thread() {
                @Override
                public void run() {
                    ec = new EthiopianCalendar();
                    Holiday h = new Holiday();
                    Calendar c;
                    int ethiopic[];
                    int date, month, year;
                    int thisDate = 0;
                    int thisMonth = 0;
                    int thisYear = 0;

                    do {
                        c = Calendar.getInstance();
                        ethiopic = ec.toEthiopicDate(c);
                        date = ec.formattedDate(ethiopic[0], ethiopic[1])[0];
                        month = ec.formattedDate(ethiopic[0], ethiopic[1])[1];
                        year = ec.formattedDate(ethiopic[0], ethiopic[1])[2];
                        if (thisDate != date || thisMonth != month || thisYear != year) {
                            thisDate = date;
                            thisMonth = month;
                            thisYear = year;

                            sysTray.remove(trayIcon);
                            image = createImage(date, h.isHolyday(date, month, year));
                            trayIcon.setImage(image);
                            trayIcon.setImageAutoSize(true);
                            if (!h.isHolyday(date, month, year)) {
                                trayIcon.setToolTip(ec.getMonthFormat(ethiopic[0], ethiopic[1])
                                        + "\n-----------------------------\n"
                                        + "በዛሬዋ ቀን\n"
                                        + MiniCalendar.MONTHLY[date - 1]
                                        + "\nታስቦ ይውላል");
                            } else {
                                trayIcon.setToolTip(ec.getMonthFormat(ethiopic[0], ethiopic[1])
                                        + "\n-----------------------------\n"
                                        + "እንኳን ለበዓለ " + h.getHolydayDescription(date, month, year)
                                        + "\nበሰላም አደረሰዎ፡፡::");
                            }
                            try {
                                sysTray.add(trayIcon);
                            } catch (AWTException ex) {
                            }

                            for (int l = 7; l > 0; l--) {
                                Fasting f = new Fasting();
                                if (f.isFastStarting(ec.toNumberDay(date + l, month, year), year)) {
                                    if (l > 1) {
                                        trayIcon.displayMessage(l + " ቀናት",
                                                f.getFastingDescription(ec.toNumberDay(date + l, month, year), year) + " ሊገባ " + l + " ቀናት ብቻ ቀረው::",
                                                TrayIcon.MessageType.INFO);
                                    } else {
                                        trayIcon.displayMessage(l + " ቀን",
                                                f.getFastingDescription(ec.toNumberDay(date + l, month, year), year) + " ሊገባ " + l + " ቀን ብቻ ቀረው::",
                                                TrayIcon.MessageType.INFO);
                                    }
                                    break;
                                }
                            }

                            if (h.isHolyday(date, month, year)) {
                                HolydayNotification holydayNotification = new HolydayNotification(null, true, date, month, year);
                                new Thread() {
                                    @Override
                                    public void run() {
                                        Sound.playSound(Sound.HOLYDAY_SOUND);
                                    }
                                }.start();
                                holydayNotification.setVisible(true);

                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {

                        }
                        c = null;
                        ethiopic = null;
                    } while (true);
                }

            }.start();
        } catch (Exception ex) {

        }

    }

    public void hifi() {

        try {
            UIManager.put("ToolTip.font", new FontUIResource("Ebrima", Font.PLAIN, 13));
            Properties p = new Properties();
            p.put("windowTitleFont", "Ebrima PLAIN 15");
            p.put("logoString", "");
            p.put("windowDecoration", "off");
            HiFiLookAndFeel.setCurrentTheme(p);

            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
            UIManager.getLookAndFeelDefaults().put("defaultFont", loadFont());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    BufferedImage createImage(int day, boolean isHoliday) {
        ec = new EthiopianCalendar();
        IconPanel ip = new IconPanel(day);
        JPanel panel = new JPanel();

        panel.setOpaque(false);
        Dimension dim = new Dimension(18, 18);
        panel.setSize(dim);
        panel.setLayout(new java.awt.BorderLayout());
        JLabel label = new JLabel(String.format("%d", day));

        if (isHoliday) {
            label.setForeground(Color.RED);
        } else {
            label.setForeground(Color.BLACK);
        }
        label.setFont(new Font("Ebrima", Font.BOLD, 13));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        label.setSize(dim);
        panel.add(label, BorderLayout.CENTER);

        BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        panel.paint(bi.createGraphics());

        return bi;
    }

    private Font loadFont() {
        try {
            InputStream mainFontInputStream = getClass().getResourceAsStream("/com/tadesse/bahirehasab/tray/resource/ebrima.ttf");
            InputStream boldFontInputStream = getClass().getResourceAsStream("/com/tadesse/bahirehasab/tray/resource/ebrima-bold.ttf");

            if (mainFontInputStream == null || mainFontInputStream == null) {
                throw new IOException("Cannot load font from resource");
            }

            Font font = Font.createFont(Font.TRUETYPE_FONT, mainFontInputStream);
            Font fontBold = Font.createFont(Font.TRUETYPE_FONT, boldFontInputStream);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            ge.registerFont(fontBold);

            return font.deriveFont(Font.PLAIN, 12);
        } catch (IOException | FontFormatException e) {
            System.out.println("Not found");
            return new Font("Ebrima", Font.PLAIN, 12);
        }
    }

    public static void main(String[] args) {

        System.setProperty("apple.awt.UIElement", "true");
        System.setProperty("apple.awt.enableTemplateImages", "true");
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.startTray();
        });
    }

}
