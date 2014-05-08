package nl.tdegroot.software.Anim;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PreferencesWindow extends JFrame {

    private JPanel contentPane;
    private JTextField tbPaintWidth;
    private JTextField tbPaintHeight;
    private JTextField tbScale;
    private PreviewWindow previewer;
    private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private JTextField tbFilePath;
    private JTextField tbSpriteWidth;
    private JTextField tbSpriteHeight;
    private JTextField tbLoopSpeed;

    public PreferencesWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        tabbedPane.setBounds(0, 0, 442, 273);
        contentPane.add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("General", null, panel, null);
        panel.setLayout(null);

        tbFilePath = new JTextField();
        tbFilePath.setBounds(104, 11, 86, 20);
        panel.add(tbFilePath);
        tbFilePath.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Spritesheet Path:");
        lblNewLabel_2.setBounds(10, 14, 84, 14);
        panel.add(lblNewLabel_2);

        JButton button = new JButton("...");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseFile();
            }
        });
        button.setBounds(200, 10, 28, 23);
        panel.add(button);

        JLabel lblSpriteWidth = new JLabel("Sprite Width:");
        lblSpriteWidth.setBounds(10, 51, 84, 14);
        panel.add(lblSpriteWidth);

        tbSpriteWidth = new JTextField();
        tbSpriteWidth.setBounds(104, 48, 86, 20);
        panel.add(tbSpriteWidth);
        tbSpriteWidth.setColumns(10);

        JLabel label_1 = new JLabel("px");
        label_1.setBounds(199, 51, 29, 14);
        panel.add(label_1);

        JLabel lblSpriteHeight = new JLabel("Sprite Height:");
        lblSpriteHeight.setBounds(10, 82, 84, 14);
        panel.add(lblSpriteHeight);

        tbSpriteHeight = new JTextField();
        tbSpriteHeight.setBounds(104, 79, 86, 20);
        panel.add(tbSpriteHeight);
        tbSpriteHeight.setColumns(10);

        JLabel label_2 = new JLabel("px");
        label_2.setBounds(200, 82, 29, 14);
        panel.add(label_2);

        JButton btnUpdateSpritesheet = new JButton("Update Spritesheet");
        btnUpdateSpritesheet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSpriteSheet();
            }
        });
        btnUpdateSpritesheet.setBounds(41, 214, 137, 23);
        panel.add(btnUpdateSpritesheet);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Animator", null, panel_2, null);
        panel_2.setLayout(null);

        JButton button_1 = new JButton("Update Animator");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAnimator();
            }
        });
        button_1.setBounds(158, 214, 115, 23);
        panel_2.add(button_1);

        tbLoopSpeed = new JTextField();
        tbLoopSpeed.setBounds(80, 9, 86, 20);
        tbLoopSpeed.setText("8");
        tbLoopSpeed.setColumns(10);
        panel_2.add(tbLoopSpeed);

        JLabel label_3 = new JLabel("Loop Speed:");
        label_3.setBounds(10, 12, 60, 14);
        panel_2.add(label_3);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Advanced", null, panel_1, null);
        panel_1.setLayout(null);

        tbPaintWidth = new JTextField();
        tbPaintWidth.setBounds(82, 11, 86, 20);
        panel_1.add(tbPaintWidth);
        tbPaintWidth.setText("128");
        tbPaintWidth.setColumns(10);

        JLabel lblWidth = new JLabel("Paint Width:");
        lblWidth.setBounds(10, 14, 62, 14);
        panel_1.add(lblWidth);

        JLabel lblPaintHeight = new JLabel("Paint Height:");
        lblPaintHeight.setBounds(231, 14, 62, 14);
        panel_1.add(lblPaintHeight);

        tbPaintHeight = new JTextField();
        tbPaintHeight.setBounds(298, 11, 86, 20);
        panel_1.add(tbPaintHeight);
        tbPaintHeight.setText("64");
        tbPaintHeight.setColumns(10);

        JLabel lblNewLabel = new JLabel("px");
        lblNewLabel.setBounds(178, 14, 29, 14);
        panel_1.add(lblNewLabel);

        JLabel label = new JLabel("px");
        label.setBounds(394, 14, 33, 14);
        panel_1.add(label);

        tbScale = new JTextField();
        tbScale.setBounds(82, 42, 86, 20);
        panel_1.add(tbScale);
        tbScale.setText("3");
        tbScale.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Scale:");
        lblNewLabel_1.setBounds(43, 45, 29, 14);
        panel_1.add(lblNewLabel_1);

        JButton btnUpdateScreen = new JButton("Update Screen");
        btnUpdateScreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                previewer.stop();
                Boot.mainFrame.dispose();
                startPreviewer();

            }
        });
        btnUpdateScreen.setBounds(164, 214, 129, 23);
        panel_1.add(btnUpdateScreen);
        startPreviewer();
    }

    private void updateAnimator() {
        int loopSpeed = Integer.parseInt(tbLoopSpeed.getText());
        previewer.setLoopSpeed(loopSpeed);
    }

    private void updateSpriteSheet() {
        int width = Integer.parseInt(tbSpriteWidth.getText());
        int height = Integer.parseInt(tbSpriteHeight.getText());
        String path = tbFilePath.getText();
        previewer.loadSheet(width, height, path);
    }

    private void chooseFile() {
        final JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {
            public boolean accept(File file) {
                String extension = "";
                int i = file.getName().lastIndexOf('.');
                if (i > 0) {
                    extension = file.getName().substring(i + 1).toLowerCase();
                }
                if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("bmp")) return true;

                return false;
            }

            public String getDescription() {
                return "Image Files (*.png, *.jpg, *.jpeg, *.bmp)";
            }
        });
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.CANCEL_OPTION) return;
        String path = fc.getSelectedFile().toPath().toString();
        System.out.println("Path: " + path);
        tbFilePath.setText(path);
    }

    private void startPreviewer() {
        Boot.mainFrame = new JFrame();
        previewer = new PreviewWindow();
        try {
            int paintWidth = Integer.parseInt(tbPaintWidth.getText());
            int paintHeight = Integer.parseInt(tbPaintHeight.getText());
            int scale = Integer.parseInt(tbScale.getText());
            int loopSpeed = Integer.parseInt(tbLoopSpeed.getText());
            int frameWidth;
            int frameHeight;
            String sheet;
            frameWidth = Integer.parseInt(tbSpriteWidth.getText());
            frameHeight = Integer.parseInt(tbSpriteHeight.getText());
            sheet = tbFilePath.getText();
            previewer.setPainting(paintWidth, paintHeight);
            previewer.setScale(scale);
            previewer.setLoopSpeed(loopSpeed);
            if (frameWidth != 0 && frameHeight != 0 && !sheet.equals(""))
                previewer.loadSheet(frameWidth, frameHeight, sheet);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        previewer.init();
        Boot.mainFrame.getContentPane().add(previewer);
        Boot.mainFrame.pack();
        Boot.mainFrame.setTitle("Animation Previewer");
        Boot.mainFrame.setLocationRelativeTo(null);
        Boot.mainFrame.setVisible(true);
        Boot.mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        previewer.start();
    }

}
