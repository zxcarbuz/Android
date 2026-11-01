package edu.synergy.practice.ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** A minimal informational desktop application for the university case. */
public final class InfoFrame extends JFrame {
    private final SnowPanel snow = new SnowPanel();

    public InfoFrame() {
        super("Синергия | Информационная панель — учебный проект ПМ.02");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(640, 480));
        setSize(780, 585);
        setLocationRelativeTo(null);

        JPanel header = new JPanel(new BorderLayout(12,0));
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(20,24,20,24));
        JLabel logo = new JLabel("◆", SwingConstants.CENTER);
        logo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 42));
        logo.setForeground(new Color(218,40,48));
        logo.setPreferredSize(new Dimension(62,58));
        header.add(logo, BorderLayout.WEST);
        JLabel title = new JLabel("УНИВЕРСИТЕТ «СИНЕРГИЯ»");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        title.setForeground(new Color(30,40,60));
        JLabel subtitle = new JLabel("демонстрационная информационная панель | ПМ.02");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        subtitle.setForeground(new Color(90,100,112));
        JPanel labels = new JPanel(new GridLayout(2,1));
        labels.setOpaque(false);
        labels.add(title);
        labels.add(subtitle);
        header.add(labels,BorderLayout.CENTER);
        add(header,BorderLayout.NORTH);

        snow.setLayout(new GridBagLayout());
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(251,252,254));
        card.setBorder(new EmptyBorder(22,24,22,24));
        JLabel welcome = new JLabel("Добро пожаловать!");
        welcome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel caption = new JLabel("учебное приложение с анимацией снежинок");
        caption.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
        caption.setAlignmentX(Component.CENTER_ALIGNMENT);
        caption.setForeground(new Color(100,108,123));
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER,15,4));
        actions.setOpaque(false);
        JButton about = new JButton("Об университете");
        JButton info = new JButton("О проекте");
        about.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Университет «Синергия» — образовательная организация.\n" +
                "Источник сведений: официальный сайт synergy.ru.",
                "Информация", JOptionPane.INFORMATION_MESSAGE));
        info.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Практика ПМ.02 — учебный Java Swing проект.\n" +
                "Анимация: перемещение нарисованных изображений снежинок.\n" +
                "Для ревью добавлены проверки ввода и тесты калькулятора.",
                "О проекте", JOptionPane.INFORMATION_MESSAGE));
        actions.add(about);
        actions.add(info);
        card.add(welcome);
        card.add(Box.createVerticalStrut(9));
        card.add(caption);
        card.add(Box.createVerticalStrut(18));
        card.add(actions);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,20,20,20);
        snow.add(card,c);
        add(snow,BorderLayout.CENTER);

        JLabel footer = new JLabel("  Демонстрационный учебный проект | Java Swing | 2026",SwingConstants.CENTER);
        footer.setForeground(new Color(88,96,110));
        footer.setBorder(new EmptyBorder(12,8,12,8));
        add(footer,BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosed(WindowEvent e) {snow.stop();}
        });
    }
}