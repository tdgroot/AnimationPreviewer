package nl.tdegroot.software.Anim;

import nl.tdegroot.software.Anim.gfx.rendermode.CustomRenderMode;
import nl.tdegroot.software.Anim.gfx.rendermode.FullRenderMode;
import nl.tdegroot.software.Anim.gfx.rendermode.LineRenderMode;
import nl.tdegroot.software.Anim.gfx.rendermode.RenderMode;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

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
    private JTabbedPane tabbedPane;
    private JTextField tbFilePath;
    private JTextField tbFrameWidth;
    private JTextField tbFrameHeight;
    private JTextField tbLoopSpeed;
    private JRadioButton rdbtnFull;
    private JRadioButton rdbtnOneLine;
    private JRadioButton rdbtnCustom;
    private JSpinner spStartColumn;
    private JSpinner spStartRow;
    private JSpinner spColumns;
    private JSpinner spRows;
    private JFrame frame;

    private Previewer previewer;
    private RenderMode renderMode;
    private SpriteSheet sheet;

    public PreferencesWindow() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
        button.setBounds(200, 11, 28, 19);
        panel.add(button);

        JLabel lblFrameWidth = new JLabel("Frame Width:");
        lblFrameWidth.setBounds(10, 51, 84, 14);
        panel.add(lblFrameWidth);

        tbFrameWidth = new JTextField();
        tbFrameWidth.setBounds(104, 48, 86, 20);
        panel.add(tbFrameWidth);
        tbFrameWidth.setColumns(10);

        JLabel label_1 = new JLabel("px");
        label_1.setBounds(199, 51, 29, 14);
        panel.add(label_1);

        JLabel lblFrameHeight = new JLabel("Frame Height:");
        lblFrameHeight.setBounds(10, 82, 84, 14);
        panel.add(lblFrameHeight);

        tbFrameHeight = new JTextField();
        tbFrameHeight.setBounds(104, 79, 86, 20);
        panel.add(tbFrameHeight);
        tbFrameHeight.setColumns(10);

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

        rdbtnFull = new JRadioButton("Full image loop");
        rdbtnFull.setBounds(10, 66, 109, 23);
        panel_2.add(rdbtnFull);

        rdbtnOneLine = new JRadioButton("Only one line");
        rdbtnOneLine.setBounds(158, 66, 115, 23);
        panel_2.add(rdbtnOneLine);

        rdbtnCustom = new JRadioButton("Custom");
        rdbtnCustom.setBounds(322, 66, 109, 23);
        panel_2.add(rdbtnCustom);

        ButtonGroup group = new ButtonGroup();

        group.add(rdbtnFull);
        group.add(rdbtnOneLine);
        group.add(rdbtnCustom);

        JLabel lblStartColumn = new JLabel("Start column:");
        lblStartColumn.setBounds(10, 118, 73, 14);
        panel_2.add(lblStartColumn);

        JLabel lblStartRow = new JLabel("Start Row:");
        lblStartRow.setBounds(150, 118, 60, 14);
        panel_2.add(lblStartRow);

        spStartColumn = new JSpinner();
        spStartColumn.setBounds(80, 116, 60, 18);
        panel_2.add(spStartColumn);

        spStartRow = new JSpinner();
        spStartRow.setBounds(213, 116, 60, 18);
        panel_2.add(spStartRow);

        JLabel lblColumns = new JLabel("Columns:");
        lblColumns.setBounds(31, 145, 63, 14);
        panel_2.add(lblColumns);

        spColumns = new JSpinner();
        spColumns.setBounds(80, 143, 60, 18);
        panel_2.add(spColumns);

        JLabel lblRows = new JLabel("Rows:");
        lblRows.setBounds(160, 145, 46, 14);
        panel_2.add(lblRows);

        spRows = new JSpinner();
        spRows.setBounds(213, 143, 60, 18);
        panel_2.add(spRows);

        group.setSelected(rdbtnFull.getModel(), true);

        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("Advanced", null, panel_1, null);
        panel_1.setLayout(null);

        tbPaintWidth = new JTextField();
        tbPaintWidth.setBounds(82, 11, 86, 20);
        panel_1.add(tbPaintWidth);
        tbPaintWidth.setText("256");
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
        tbPaintHeight.setText("128");
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
        init();
    }

    private void init() {
        renderMode = new FullRenderMode();
        startPreviewer();
    }

    private void updateAnimator() {
        int startColumn = (Integer) spStartColumn.getValue();
        int startRow = (Integer) spStartRow.getValue();
        int columns = (Integer) spColumns.getValue();
        int rows = (Integer) spRows.getValue();
        int loopSpeed = Integer.parseInt(tbLoopSpeed.getText());

        if (renderMode == null) { // TODO: Check if RenderMode changed, this is gonna bug!
            if (rdbtnFull.isSelected()) renderMode = new FullRenderMode();
            if (rdbtnOneLine.isSelected()) renderMode = new LineRenderMode(startColumn, startRow, columns, rows);
            if (rdbtnCustom.isSelected()) renderMode = new CustomRenderMode(startColumn, startRow, columns, rows);
            renderMode.setLoopSpeed(loopSpeed);
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

        String path = tbFilePath.getText();

        sheet = new SpriteSheet(width, height, path);
        renderMode = new FullRenderMode();

        previewer.setSheet(width, height, path, sheet);
        previewer.setRenderMode(renderMode);
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
                if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("bmp"))
                    return true;

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
        frame = new JFrame();
        previewer = new Previewer();
        try {
            int paintWidth = Integer.parseInt(tbPaintWidth.getText());
            int paintHeight = Integer.parseInt(tbPaintHeight.getText());
            int scale = Integer.parseInt(tbScale.getText());
            int loopSpeed = Integer.parseInt(tbLoopSpeed.getText());
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
        frame.setTitle("Animation Previewer");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        previewer.start();
    }
}
