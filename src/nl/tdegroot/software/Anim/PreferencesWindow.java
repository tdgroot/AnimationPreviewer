package nl.tdegroot.software.Anim;

import nl.tdegroot.software.Anim.gfx.SpriteSheet;
import nl.tdegroot.software.Anim.gfx.rendermode.CustomRenderMode;
import nl.tdegroot.software.Anim.gfx.rendermode.FullRenderMode;
import nl.tdegroot.software.Anim.gfx.rendermode.RowRenderMode;
import nl.tdegroot.software.Anim.gfx.rendermode.RenderMode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Font;

public class PreferencesWindow extends JFrame {

    private JPanel contentPane;
    private JTextField tbPaintWidth;
    private JTextField tbPaintHeight;
    private JTextField tbScale;
    private JTabbedPane tabbedPane;
    private JTextField tbFilePath;
    private JTextField tbFrameWidth;
    private JTextField tbFrameHeight;
    private JTextField tbInterval;
    private JSpinner spLoopSpeed;
    private JRadioButton rdbtnFull;
    private JRadioButton rdbtnOneRow;
    private JRadioButton rdbtnCustom;
    private JSpinner spStartColumn;
    private JSpinner spStartRow;
    private JSpinner spColumns;
    private JSpinner spRows;
    private JButton btnPause;
    private JLabel lblCurrentColumn;
    private JLabel lblCurrentRow;

    private JFrame frame;
    private Previewer previewer;
    private RenderMode renderMode;

    private SpriteSheet sheet;
    private String lastButton = "";
    private boolean renderModeChanged = true;
    private boolean paused = false;

    public PreferencesWindow() {
        setTitle("Animation Previewer - Preferences");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 315);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        tabbedPane.setBounds(0, 0, 442, 288);
        contentPane.add(tabbedPane);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Spritesheet", null, panel, null);
        panel.setLayout(null);

        tbFilePath = new JTextField();
        tbFilePath.setBounds(120, 8, 86, 20);
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
        button.setBounds(216, 8, 28, 19);
        panel.add(button);

        JLabel lblFrameWidth = new JLabel("Frame Width:");
        lblFrameWidth.setBounds(10, 51, 84, 14);
        panel.add(lblFrameWidth);

        tbFrameWidth = new JTextField();
        tbFrameWidth.setBounds(120, 45, 86, 20);
        panel.add(tbFrameWidth);
        tbFrameWidth.setColumns(10);

        JLabel label_1 = new JLabel("px");
        label_1.setBounds(215, 48, 29, 14);
        panel.add(label_1);

        JLabel lblFrameHeight = new JLabel("Frame Height:");
        lblFrameHeight.setBounds(10, 82, 84, 14);
        panel.add(lblFrameHeight);

        tbFrameHeight = new JTextField();
        tbFrameHeight.setBounds(120, 76, 86, 20);
        panel.add(tbFrameHeight);
        tbFrameHeight.setColumns(10);

        JLabel label_2 = new JLabel("px");
        label_2.setBounds(216, 79, 29, 14);
        panel.add(label_2);

        JButton btnUpdateSpritesheet = new JButton("Update Spritesheet");
        btnUpdateSpritesheet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSpriteSheet();
            }
        });
        btnUpdateSpritesheet.setBounds(41, 214, 137, 23);
        panel.add(btnUpdateSpritesheet);

        JLabel lblReloadInterval = new JLabel("Reload Interval:");
        lblReloadInterval.setBounds(10, 107, 100, 14);
        panel.add(lblReloadInterval);

        tbInterval = new JTextField();
        tbInterval.setText("1000");
        tbInterval.setBounds(120, 101, 86, 20);
        panel.add(tbInterval);
        tbInterval.setColumns(10);

        JLabel lblMs = new JLabel("ms");
        lblMs.setBounds(216, 104, 46, 14);
        panel.add(lblMs);

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

        JLabel label_3 = new JLabel("Loop Speed:");
        label_3.setBounds(10, 12, 60, 14);
        panel_2.add(label_3);

        rdbtnFull = new JRadioButton("Full image loop");
        rdbtnFull.setBounds(10, 95, 109, 23);
        rdbtnFull.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioEvent(e);
            }
        });
        panel_2.add(rdbtnFull);

        rdbtnOneRow = new JRadioButton("Only one row");
        rdbtnOneRow.setBounds(158, 95, 115, 23);
        rdbtnOneRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioEvent(e);
            }
        });
        panel_2.add(rdbtnOneRow);

        rdbtnCustom = new JRadioButton("Custom");
        rdbtnCustom.setBounds(322, 95, 109, 23);
        rdbtnCustom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioEvent(e);
            }
        });
        panel_2.add(rdbtnCustom);

        ButtonGroup group = new ButtonGroup();

        group.add(rdbtnFull);
        group.add(rdbtnOneRow);
        group.add(rdbtnCustom);

        JLabel lblStartColumn = new JLabel("Start column:");
        lblStartColumn.setBounds(10, 147, 73, 14);
        panel_2.add(lblStartColumn);

        JLabel lblStartRow = new JLabel("Start Row:");
        lblStartRow.setBounds(150, 147, 60, 14);
        panel_2.add(lblStartRow);

        spStartColumn = new JSpinner();
        spStartColumn.setBounds(80, 145, 60, 18);
        panel_2.add(spStartColumn);

        spStartRow = new JSpinner();
        spStartRow.setBounds(213, 145, 60, 18);
        panel_2.add(spStartRow);

        JLabel lblColumns = new JLabel("Columns:");
        lblColumns.setBounds(31, 174, 63, 14);
        panel_2.add(lblColumns);

        spColumns = new JSpinner();
        spColumns.setBounds(80, 172, 60, 18);
        panel_2.add(spColumns);

        JLabel lblRows = new JLabel("Rows:");
        lblRows.setBounds(160, 174, 46, 14);
        panel_2.add(lblRows);

        spRows = new JSpinner();
        spRows.setBounds(213, 172, 60, 18);
        panel_2.add(spRows);

        JLabel lblRenderMode = new JLabel("Render Mode");
        lblRenderMode.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblRenderMode.setBounds(176, 74, 86, 14);
        panel_2.add(lblRenderMode);

        spLoopSpeed = new JSpinner();
        spLoopSpeed.setModel(new SpinnerNumberModel(new Integer(8), new Integer(1), null, new Integer(1)));
        spLoopSpeed.setBounds(80, 10, 60, 18);
        panel_2.add(spLoopSpeed);

        group.setSelected(rdbtnFull.getModel(), true);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Advanced", null, panel_1, null);
        panel_1.setLayout(null);

        tbPaintWidth = new JTextField();
        tbPaintWidth.setBounds(83, 11, 86, 20);
        panel_1.add(tbPaintWidth);
        tbPaintWidth.setText("256");
        tbPaintWidth.setColumns(10);

        JLabel lblWidth = new JLabel("Paint Width:");
        lblWidth.setBounds(10, 14, 75, 14);
        panel_1.add(lblWidth);

        JLabel lblPaintHeight = new JLabel("Paint Height:");
        lblPaintHeight.setBounds(218, 14, 75, 14);
        panel_1.add(lblPaintHeight);

        tbPaintHeight = new JTextField();
        tbPaintHeight.setBounds(298, 11, 86, 20);
        panel_1.add(tbPaintHeight);
        tbPaintHeight.setText("128");
        tbPaintHeight.setColumns(10);

        JLabel lblNewLabel = new JLabel("px");
        lblNewLabel.setBounds(179, 14, 29, 14);
        panel_1.add(lblNewLabel);

        JLabel label = new JLabel("px");
        label.setBounds(394, 14, 33, 14);
        panel_1.add(label);

        tbScale = new JTextField();
        tbScale.setBounds(83, 42, 86, 20);
        panel_1.add(tbScale);
        tbScale.setText("4");
        tbScale.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Scale:");
        lblNewLabel_1.setBounds(43, 45, 29, 14);
        panel_1.add(lblNewLabel_1);

        JButton btnUpdateScreen = new JButton("Update Screen");
        btnUpdateScreen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                previewer.stop();
                frame.dispose();
                startPreviewer();

            }
        });
        btnUpdateScreen.setBounds(164, 214, 129, 23);
        panel_1.add(btnUpdateScreen);

        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("Control", null, panel_3, null);
        panel_3.setLayout(null);


        btnPause = new JButton("Pause");
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                pause();
            }
        });
        btnPause.setBounds(165, 214, 109, 23);
        panel_3.add(btnPause);

        JButton btnPreviousFrame = new JButton("Previous Frame");
        btnPreviousFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousFrame();
            }
        });
        btnPreviousFrame.setBounds(43, 214, 109, 23);
        panel_3.add(btnPreviousFrame);

        JButton btnNextFrame = new JButton("Next Frame");
        btnNextFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextFrame();
            }
        });
        btnNextFrame.setBounds(284, 214, 104, 23);
        panel_3.add(btnNextFrame);

        JLabel lblNewLabel_4 = new JLabel("Current Column:");
        lblNewLabel_4.setBounds(43, 37, 79, 14);
        panel_3.add(lblNewLabel_4);

        lblCurrentColumn = new JLabel("0");
        lblCurrentColumn.setBounds(132, 37, 46, 14);
        panel_3.add(lblCurrentColumn);

        lblCurrentRow = new JLabel("0");
        lblCurrentRow.setBounds(132, 66, 46, 14);
        panel_3.add(lblCurrentRow);

        JLabel lblCurrentRow_1 = new JLabel("Current Row:");
        lblCurrentRow_1.setBounds(43, 66, 79, 14);
        panel_3.add(lblCurrentRow_1);
        init();
    }

    private void init() {
        renderMode = new FullRenderMode(this);
        startPreviewer();
    }

    private void previousFrame() {
        if (sheet == null) return;
        if (!paused) pause();
        renderMode.previousFrame(sheet);
    }

    private void nextFrame() {
        if (sheet == null) return;
        if (!paused) pause();
        renderMode.nextFrame(sheet);
    }

    private void pause() {
        paused = !paused;
        if (paused) {
            previewer.pause();
            btnPause.setText("Play");
        } else {
            previewer.resume();
            btnPause.setText("Pause");
        }
    }

    private void radioEvent(ActionEvent e) {
        String button = e.getActionCommand();
        if (lastButton.equals(button)) return;
        lastButton = button;
        renderModeChanged = true;
    }

    private void updateAnimator() {
        if (sheet == null) return;

        int startColumn = (Integer) spStartColumn.getValue();
        int startRow = (Integer) spStartRow.getValue();
        int columns = (Integer) spColumns.getValue();
        int rows = (Integer) spRows.getValue();
        int loopSpeed = (Integer) spLoopSpeed.getValue();

        if (renderModeChanged) {

            spColumns.setModel(new SpinnerNumberModel(sheet.xx, 0, sheet.xx, 1));
            spRows.setModel(new SpinnerNumberModel(0, 0, sheet.yy, 1));
            spStartColumn.setModel(new SpinnerNumberModel(0, 0, sheet.xx - 1, 1));
            spStartRow.setModel(new SpinnerNumberModel(0, 0, sheet.yy, 1));

            if (rdbtnFull.isSelected()) {
                renderMode = new FullRenderMode(this);
                spStartColumn.setValue(0);
                spStartRow.setValue(0);
                if (sheet != null) {
                    spColumns.setValue(sheet.xx);
                    spRows.setValue(sheet.yy);
                }
                spStartColumn.setEnabled(false);
                spStartRow.setEnabled(false);
                spColumns.setEnabled(false);
                spRows.setEnabled(false);
            } else if (rdbtnOneRow.isSelected()) {
                renderMode = new RowRenderMode(0, 0, columns, 1, this);
                spStartColumn.setValue(0);
                spStartRow.setValue(0);
                if (sheet != null)
                    spColumns.setValue(sheet.xx);
                spRows.setValue(1);
                spStartColumn.setEnabled(true);
                spStartRow.setEnabled(true);
                spColumns.setEnabled(true);
                spRows.setEnabled(false);
            } else if (rdbtnCustom.isSelected()) {
                renderMode = new CustomRenderMode(startColumn, startRow, columns, rows, this);
                spStartColumn.setValue(0);
                spStartRow.setValue(0);
                if (sheet != null) {
                    spColumns.setValue(sheet.xx);
                    spRows.setValue(sheet.yy);
                }
                spStartColumn.setEnabled(true);
                spStartRow.setEnabled(true);
                spColumns.setEnabled(true);
                spRows.setEnabled(true);
            }
            renderMode.setLoopSpeed(loopSpeed);
            renderModeChanged = false;
        } else {
            renderMode.setStartColumn(startColumn);
            renderMode.setStartRow(startRow);
            renderMode.setColumns(columns);
            renderMode.setRows(rows);
            renderMode.setLoopSpeed(loopSpeed);
        }
        previewer.setRenderMode(renderMode);
    }

    private void updateSpriteSheet() {
        int width = Integer.parseInt(tbFrameWidth.getText());
        int height = Integer.parseInt(tbFrameHeight.getText());
        int interval = Integer.parseInt(tbInterval.getText());

        String path = tbFilePath.getText();

        sheet = new SpriteSheet(width, height, path);
        updateAnimator();
        previewer.setSheet(width, height, path, sheet);
        previewer.setReloadInterval(interval);
    }

    private void chooseFile() {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.CANCEL_OPTION) return;
        String path = fc.getSelectedFile().toPath().toString();
        System.out.println("Path: " + path);
        tbFilePath.setText(path);
    }

    private void startPreviewer() {
        frame = new JFrame();
        previewer = new Previewer();
        try {
            int loopSpeed = (Integer) spLoopSpeed.getValue();
            int paintWidth = Integer.parseInt(tbPaintWidth.getText());
            int paintHeight = Integer.parseInt(tbPaintHeight.getText());
            int scale = Integer.parseInt(tbScale.getText());
            int frameWidth;
            int frameHeight;
            String path;
            frameWidth = Integer.parseInt(tbFrameWidth.getText());
            frameHeight = Integer.parseInt(tbFrameHeight.getText());
            path = tbFilePath.getText();
            previewer.setPainting(paintWidth, paintHeight);
            previewer.setScale(scale);
            renderMode.setLoopSpeed(loopSpeed);
            previewer.setRenderMode(renderMode);

            if (frameWidth != 0 && frameHeight != 0 && !sheet.equals(""))
                previewer.setSheet(frameWidth, frameHeight, path, sheet);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        previewer.init();

        frame.getContentPane().add(previewer);
        frame.pack();
        frame.setTitle("Animation Previewer 1.0");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        previewer.start();
    }

    public void setSheet(SpriteSheet sheet) {
        this.sheet = sheet;
    }

    public void setCurrentColumn(int column) {
        lblCurrentColumn.setText("" + column);
    }

    public void setCurrentRow(int row) {
        lblCurrentRow.setText("" + row);
    }
}
