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
import com.tadesse.bahirehasab.tray.tools.EthiopianCalendar;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.*;

/**
 *
 * @author Tadesse Angaw
 */
public class MiniCalendar extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;

    public static String GEEZ[] = {"፩", "፪", "፫", "፬", "፭", "፮", "፯", "፰", "፱", "፲",
        "፲፩", "፲፪", "፲፫", "፲፬", "፲፭", "፲፮", "፲፯", "፲፰", "፲፱", "፳",
        "፳፩", "፳፪", "፳፫", "፳፬", "፳፭", "፳፮", "፳፯", "፳፰", "፳፱", "፴"};

    public static String MONTHLY[] = {"ልደታ ለማርያም፣ ቅዱስ ራጉኤል", "አባ ጉባ", "በዓታ ለማርያም", "ቅዱስ ዮሐንስ ወንጌላዊ", "አቡነ ገብረመንፈስቅዱስ", "ቁስቋም ማርያም፣ ቅድስት አርሴማ", "ቅድስት ሥላሴ", "አቡነ ኪሮስ፣ አርባዕቱ እንስሳ",
        "ቶማስ", "መስቀለ ኢየሱስ", "ቅድስት ሐና", "ቅዱስ ሚካኤል፣ ቅድስት ክርስቶስ ሰምራ", "ቅዱስ ሩፋኤል", "አቡነ  አረጋዊ፣ ገብረ ክርስቶስ", "ቅዱስ ቂርቆስ", "ኪዳነ ምህረት", "ቅዱስ እስጢፋኖስ", "ቅዱስ ፊሊጶስ", "ቅዱስ ገብርኤል",
        "ሕንፀተ ማርያም", "ቅድስት ማርያም", "ቅዱስ ዑራኤል", "ቅዱስ ጊዮርጊስ", "አቡነ ተክለሃይማኖት", "ቅዱስ መርቆሬዎስ", "ቅዱስ ዮሴፍ፣ አቡነ ሀብተ ማርያም", "መድኀኔዓለም፣ አቡነ መብዓ ጽዮን", "አማኑኤል", "በዓለ ወልድ ", "ቅዱስ ዮሐንስ መጥምቅ"};
    int decadeStart, centuryStart, milleniemStart;
    JButton[] days = new JButton[42];
    JButton[] months = new JButton[13];
    JButton[] decade = new JButton[10];
    JButton[] century = new JButton[10];
    JButton[] millenium = new JButton[10];

    EthiopianCalendar ec;
    Holiday ho;
    int Date, Month, Year;
    int num, state;
    int currentButton;

    int con;
    Listener listener = new Listener();

    /**
     * Creates new form Calendar
     *
     * @param parent
     * @param modal
     * @param year
     * @param month
     * @param date
     */
    public MiniCalendar(java.awt.Frame parent, boolean modal, int date, int month, int year) {
        super(parent, modal);
        initComponents();
        this.Date = date;
        this.Month = month;
        this.Year = year;
        panel.setLayout(new GridLayout(6, 7));

        for (int i = 0; i < days.length; i++) {
            days[i] = new JButton();
            days[i].setFont(new Font("Ebrima", Font.PLAIN, 13));
            days[i].setHorizontalAlignment(JButton.CENTER);
            days[i].setText(" ");
            days[i].setActionCommand("");
            days[i].setToolTipText(null);
            days[i].setBackground(new Color(240, 240, 240));
            days[i].setForeground(Color.BLACK);
        }

        for (int i = 0; i < months.length; i++) {
            months[i] = new JButton();
            months[i].setText(EthiopianCalendar.MONTHS[i]);
            months[i].setFont(new Font("Ebrima", Font.PLAIN, 13));
            months[i].setActionCommand(String.format("%d", i));
            months[i].setHorizontalAlignment(JButton.CENTER);
            months[i].setBackground(new Color(240, 240, 240));
            months[i].setForeground(Color.BLACK);
        }

        for (int i = 0; i < decade.length; i++) {
            decade[i] = new JButton();
            decade[i].setFont(new Font("Ebrima", Font.PLAIN, 13));
            decade[i].setActionCommand(String.format("%d", i));
            decade[i].setHorizontalAlignment(JButton.CENTER);
            decade[i].setHorizontalAlignment(JButton.CENTER);
            decade[i].setBackground(new Color(240, 240, 240));
            decade[i].setForeground(Color.BLACK);
        }

        for (int i = 0; i < century.length; i++) {
            century[i] = new JButton();
            century[i].setFont(new Font("Ebrima", Font.PLAIN, 11));
            century[i].setActionCommand(String.format("%d", i));
            century[i].setHorizontalAlignment(JButton.CENTER);
            century[i].setHorizontalAlignment(JButton.CENTER);
            century[i].setBackground(new Color(240, 240, 240));
            century[i].setForeground(Color.BLACK);
        }

        calendarBuilder(date, month, year, 0);
        int x = parent.getX();
        int y = parent.getY();
        int width = parent.getWidth();
        int height = parent.getHeight();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                setAlwaysOnTop(true);
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        this.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                setAlwaysOnTop(false);
                dispose();
            }

        });
    }

    public void calendarBuilder(int date, int month, int year, int s) {
        this.state = s;
        if (this.state == 0) {
            ho = new Holiday();
            ec = new EthiopianCalendar();

            onOff();
            panel.removeAll();
            panel.repaint();
            panel.setLayout(new GridLayout(6, 7));
            for (JButton day : days) {
                day.setActionCommand("");
                day.setText("");
                day.setToolTipText(null);
                day.setBackground(new Color(240, 240, 240));
                day.setForeground(Color.BLACK);
                panel.add(day);
            }
            int monthstart = ec.getMonthStart(month, year);
            this.link.setText(EthiopianCalendar.MONTHS[month] + "፣ " + year);

            int remain;
            if (month == 12) {
                remain = ec.getDaysLength(year) == 365 ? 42 - 5 : 42 - 6;
            } else {
                remain = 42 - 30;
            }

            monthstart = (monthstart + 1) % 7;

            for (int i = monthstart, day = 1; i < days.length - remain + monthstart; i++, day++) {
                String geez = GEEZ[day - 1];
                String arab = String.format("%d", day);
                days[i].setText("<html>" + geez + "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + arab + "</html>");
                days[i].setActionCommand(String.format("%d", day));
                days[i].setName(String.format("%d", day));
                Calendar c = ec.toGregorianDate(day, Month + 1, Year);
                String gregorian = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + c.get(Calendar.DAY_OF_MONTH) + ", " + c.get(Calendar.YEAR);
                String tooltip = "<html><div style='font-family: Ebrima;'>" + gregorian + "</p><p style='font-family: Ebrima'>" + MONTHLY[day - 1] + "</p></html>";

                days[i].setToolTipText(tooltip);
                advancedEvent(i);

                if (ho.isHolyday(Integer.parseInt(days[i].getActionCommand()), month + 1, year)) {
                    days[i].setBackground(new Color(94, 173, 124));
                    num = i;
                    printDescription(i);
                }

                if (ec.isToday(Integer.parseInt(days[i].getActionCommand()), month + 1, year)) {
                    days[i].setBackground(Color.ORANGE);
                }
            }

            for (int i = 0; i < days.length; i++) {
                if (i % 7 == 0) {
                    days[i].setForeground(Color.RED);
                }
            }
            days[date + monthstart - 1].grabFocus();
            panel.repaint();
        } else if (this.state == 1) {

            this.link.setText(String.format("%d", year));
            onOff();
            panel.removeAll();
            panel.repaint();
            panel.setLayout(new GridLayout(4, 4));

            for (JButton m : months) {
                m.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Month = Integer.parseInt(m.getActionCommand());
                        state = 0;
                        calendarBuilder(Date, Month, Year, state);
                    }
                });
                panel.add(m);
            }
            panel.repaint();
        } else if (state == 2) {
            onOff();
            decadeStart = year - (year % 10);
            link.setText(String.format("%d - %d", decadeStart, decadeStart + 9));
            panel.removeAll();
            panel.repaint();
            panel.setLayout(new GridLayout(3, 4));
            String start = (centuryStart == 0) ? "" : "" + (decadeStart - 1);
            String end = (centuryStart == 9900) ? "" : "" + (decadeStart + 10);
            panel.add(new JButton(String.format("%s", start)));

            for (int j = 0, i = decadeStart; j < decade.length; i++, j++) {
                decade[j].setText("<html>" + i + "<br>" + ec.toEthiopicNumber(i) + "</html>");
                panel.add(decade[j]);
                decadeListener(j);
            }
            con = 0;
            panel.add(new JButton(String.format("%s", end)));
            panel.repaint();
        } else if (state == 3) {
            onOff();
            centuryStart = year - (year % 100);
            link.setText(String.format("%d - %d", centuryStart, centuryStart + 99));
            panel.removeAll();
            panel.repaint();
            panel.setLayout(new GridLayout(3, 4));
            String start = (centuryStart == 0) ? "" : "" + (centuryStart - 1);
            String end = (centuryStart == 9900) ? "" : "" + (centuryStart + 100);
            panel.add(new JButton(String.format("%s", start)));

            for (int j = 0, i = centuryStart; j < century.length; i += 10, j++) {
                century[j].setText("<html>" + i + " - " + (i + 9) + "<br>" + ec.toEthiopicNumber(i) + "-" + ec.toEthiopicNumber(i + 9) + "</html>");
                panel.add(century[j]);
                centuryListener(j);
            }
            con = 0;
            panel.add(new JButton(String.format("%s", end)));
            panel.repaint();
        }
    }

    public void decadeListener(int i) {
        decade[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Year = Integer.parseInt(decade[i].getActionCommand()) + decadeStart;
                state = 1;
                calendarBuilder(Date, Month, Year, state);
            }
        });
    }

    public void centuryListener(int i) {
        century[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Year = (Integer.parseInt(century[i].getActionCommand()) * 10) + centuryStart;
                state = 2;
                calendarBuilder(Date, Month, Year, state);
            }
        });
    }

    public void printDescription(int i) {
        days[i].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                try {
                    holydayDescription.setText(ho.getHolydayDescription(Integer.parseInt(days[i].getActionCommand()), Month + 1, Year));
                } catch (Exception ex) {
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                holydayDescription.setText("");
            }

        });
    }

    public void onOff() {
        boolean onOff = (state == 0);
        jLabel1.setVisible(onOff);
        jLabel2.setVisible(onOff);
        jLabel3.setVisible(onOff);
        jLabel4.setVisible(onOff);
        jLabel5.setVisible(onOff);
        jLabel6.setVisible(onOff);
        jLabel7.setVisible(onOff);
    }

    public void advancedEvent(int i) {
        days[i].addMouseListener(listener);
    }

    public class Listener extends MouseAdapter {

        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.isPopupTrigger()) {
                currentButton = Integer.parseInt(evt.getComponent().getName());
                pop1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            if (evt.isPopupTrigger()) {
                currentButton = Integer.parseInt(evt.getComponent().getName());
                pop1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop1 = new javax.swing.JPopupMenu();
        viewInfo = new javax.swing.JMenuItem();
        calendarPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panel = new javax.swing.JPanel();
        holydayDescription = new javax.swing.JLabel();
        next = new javax.swing.JButton();
        previous = new javax.swing.JButton();
        link = new org.jdesktop.swingx.JXHyperlink();

        viewInfo.setFont(new java.awt.Font("Ebrima", 0, 13)); // NOI18N
        viewInfo.setText("የዕለቱ መረጃ");
        viewInfo.setActionCommand("other");
        viewInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewInfoActionPerformed(evt);
            }
        });
        pop1.add(viewInfo);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        calendarPanel.setName("calendarPanel"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 11)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("እ");

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ሰ");

        jLabel3.setFont(new java.awt.Font("Ebrima", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ማ");

        jLabel4.setFont(new java.awt.Font("Ebrima", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ረ");

        jLabel5.setFont(new java.awt.Font("Ebrima", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ሐ");

        jLabel6.setFont(new java.awt.Font("Ebrima", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("አ");

        jLabel7.setFont(new java.awt.Font("Ebrima", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ቅ");

        panel.setLayout(new java.awt.GridLayout(1, 0));

        holydayDescription.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N

        next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tadesse/bahirehasab/tray/resource/right.png"))); // NOI18N
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tadesse/bahirehasab/tray/resource/left.png"))); // NOI18N
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        link.setForeground(new java.awt.Color(255, 255, 255));
        link.setText("Month, Year");
        link.setClickedColor(new java.awt.Color(255, 255, 255));
        link.setFont(new java.awt.Font("Ebrima", 1, 15)); // NOI18N
        link.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        link.setUnclickedColor(new java.awt.Color(255, 255, 255));
        link.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout calendarPanelLayout = new javax.swing.GroupLayout(calendarPanel);
        calendarPanel.setLayout(calendarPanelLayout);
        calendarPanelLayout.setHorizontalGroup(
            calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jSeparator1)
            .addGroup(calendarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(holydayDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calendarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(calendarPanelLayout.createSequentialGroup()
                        .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(link, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        calendarPanelLayout.setVerticalGroup(
            calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calendarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(previous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(link, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(holydayDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(calendarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        if (this.state == 0) {
            if (this.Month > 0) {
                calendarBuilder(this.Date, --this.Month, this.Year, this.state);
            } else if (Year != 0) {
                Month = 12;
                calendarBuilder(this.Date, this.Month, --this.Year, this.state);
            }
        } else if (this.state == 1) {
            if (this.Year != 0) {
                calendarBuilder(this.Date, this.Month, --this.Year, state);
            }
        } else if (state == 2) {
            if (decadeStart != 0) {
                this.Year -= 10;
                calendarBuilder(this.Date, this.Month, this.Year, state);
            }
        } else if (state == 3) {
            if (centuryStart != 0) {
                this.Year -= 100;
                calendarBuilder(this.Date, this.Month, this.Year, state);
            }
        }
    }//GEN-LAST:event_previousActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        if (state == 0) {
            if (this.Month < 12) {
                calendarBuilder(this.Date, ++this.Month, this.Year, this.state);
            } else if (Year != 9999) {
                Month = 0;
                calendarBuilder(this.Date, this.Month, ++this.Year, this.state);
            }
        } else if (state == 1) {
            if (this.Year != 9999) {
                calendarBuilder(this.Date, this.Month, ++this.Year, state);
            }
        } else if (state == 2) {
            if (decadeStart != 9990) {
                this.Year += 10;
                calendarBuilder(this.Date, this.Month, this.Year, state);
            }
        } else if (state == 3) {
            if (centuryStart != 9900) {
                this.Year += 100;
                calendarBuilder(this.Date, this.Month, this.Year, state);
            }
        }
    }//GEN-LAST:event_nextActionPerformed

    private void linkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkActionPerformed
        if (state != 3) {
            state += 1;
            calendarBuilder(this.Date, this.Month, this.Year, state);
        }
    }//GEN-LAST:event_linkActionPerformed

    private void viewInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewInfoActionPerformed
        AdditionalInfo additionalInfo = new AdditionalInfo((Frame) null, true, currentButton, Month, Year);
        additionalInfo.setVisible(true);
    }//GEN-LAST:event_viewInfoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel calendarPanel;
    private javax.swing.JLabel holydayDescription;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private org.jdesktop.swingx.JXHyperlink link;
    private javax.swing.JButton next;
    private javax.swing.JPanel panel;
    private javax.swing.JPopupMenu pop1;
    private javax.swing.JButton previous;
    private javax.swing.JMenuItem viewInfo;
    // End of variables declaration//GEN-END:variables
}
