package buildings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class GUI extends JFrame {

    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem openDwelling = new JMenuItem("Open dwelling…");
    private JMenuItem openOffice = new JMenuItem("Open office building…");
    private JMenu lf = new JMenu("Look&Feel");
    private Building building = null;
    private JTextPane bPanel = new JTextPane();
    private JTextPane fPanel = new JTextPane();
    private JTextPane sPanel = new JTextPane();
    private JPanel scheme = new JPanel();

    public GUI(){

        super("GUI");
        this.setBounds(100,100,1000,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = new Dimension(100,40);

        ActionListener listner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("openDwelling".equals(e.getActionCommand()))
                {
                    JFileChooser fc = new JFileChooser();
                    int ret = fc.showDialog(null,"Dwelling");
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fc.getSelectedFile());
                        InputStreamReader reader = new InputStreamReader(fileInputStream);
                        Buildings.setBuildingFactory(new DwellingFactory());
                        building = Buildings.readBuilding(reader);
                        bPanel.setText(building.toString());
                        fPanel.setText(building.getFloor(0).toString());
                        sPanel.setText(building.getSpace(0).toString());

                        Floor[] floors = building.getFloors();


                        scheme.setLayout(new BoxLayout(scheme,1));

                        scheme.removeAll();

                        for (int i=0;i<floors.length;i++)
                        {
                            CustomPanel panel = new CustomPanel();
                            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                            panel.setPreferredSize(new Dimension(60*i+10,40));
                            panel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 7));
                            panel.setName(floors[floors.length-1-i].toString());
                            for (int j=0;j<floors[floors.length-1-i].getSpacesNumber();j++)
                            {
                                JButton button = new JButton();
                                button.setPreferredSize(new Dimension(50,20));
                                button.setName(floors[floors.length-1-i].getSpace(j).toString());
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        sPanel.setText(button.getName());
                                    }
                                });
                                panel.add(button);
                            }
                            scheme.add(panel);
                        }

                        scheme.updateUI();

                    } catch (FileNotFoundException ex)
                    {
                        JOptionPane.showMessageDialog(null,"error");
                    } catch (IOException ex)
                    {

                    }
                }
                if ("openOffice".equals(e.getActionCommand()))
                {
                    JFileChooser fc = new JFileChooser();
                    int ret = fc.showDialog(null,"Dwelling");
                    try {
                        FileInputStream fileInputStream = new FileInputStream(fc.getSelectedFile());
                        InputStreamReader reader = new InputStreamReader(fileInputStream);
                        Buildings.setBuildingFactory(new OfficeFactory());
                        building = Buildings.readBuilding(reader);
                        bPanel.setText(building.toString());
                        fPanel.setText(building.getFloor(0).toString());
                        sPanel.setText(building.getSpace(0).toString());

                        Floor[] floors = building.getFloors();


                        scheme.setLayout(new BoxLayout(scheme,1));

                        scheme.removeAll();

                        for (int i=0;i<floors.length;i++)
                        {
                            CustomPanel panel = new CustomPanel();
                            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                            panel.setPreferredSize(new Dimension(53*i+10,40));
                            panel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 7));
                            panel.setName(floors[floors.length-1-i].toString());
                            for (int j=0;j<floors[floors.length-1-i].getSpacesNumber();j++)
                            {
                                JButton button = new JButton();
                                button.setPreferredSize(new Dimension(50,20));
                                button.setName(floors[floors.length-1-i].getSpace(j).toString());
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        sPanel.setText(button.getName());
                                    }
                                });
                                panel.add(button);
                            }
                            scheme.add(panel);
                        }

                        scheme.updateUI();

                    } catch (FileNotFoundException ex)
                    {
                        JOptionPane.showMessageDialog(null,"error");
                    } catch (IOException ex)
                    {

                    }
                }
            }
        };

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1,4));

        this.setJMenuBar(jMenuBar);
        jMenuBar.setMaximumSize(d);

        bPanel.setEditable(false);
        fPanel.setEditable(false);
        sPanel.setEditable(false);

        container.add(new JScrollPane(bPanel));
        container.add(new JScrollPane(fPanel));
        container.add(new JScrollPane(sPanel));
        container.add(new JScrollPane(scheme));

        file.setMaximumSize(d);
        jMenuBar.add(file);
        file.add(openDwelling);
        file.add(openOffice);

        openDwelling.setActionCommand("openDwelling");
        openOffice.setActionCommand("openOffice");

        openDwelling.addActionListener(listner);
        openOffice.addActionListener(listner);


        lf.setMaximumSize(d);
        jMenuBar.add(lf);


        ActionListener radiobuttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Metal".equals(e.getActionCommand()))
                {
                    try {
                        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(getContentPane());

                    } catch (Exception ex)
                    {

                    }
                }
                if ("Nimbus".equals(e.getActionCommand()))
                {
                    try {
                        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(getContentPane());

                    } catch (Exception ex)
                    {

                    }
                }
                if ("CDE\\Motif".equals(e.getActionCommand()))
                {
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(getContentPane());

                    } catch (Exception ex)
                    {

                    }
                }
                if ("Windows".equals(e.getActionCommand()))
                {
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(getContentPane());

                    } catch (Exception ex)
                    {

                    }
                }
                if ("Windows Classic".equals(e.getActionCommand()))
                {
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                        SwingUtilities.updateComponentTreeUI(getContentPane());

                    } catch (Exception ex)
                    {

                    }
                }


            }
        };

        JRadioButtonMenuItem metal = new JRadioButtonMenuItem("Metal");
        JRadioButtonMenuItem nimbus = new JRadioButtonMenuItem("Nimbus");
        JRadioButtonMenuItem cm = new JRadioButtonMenuItem("CDE\\Motif");
        JRadioButtonMenuItem win = new JRadioButtonMenuItem("Windows");
        JRadioButtonMenuItem winc = new JRadioButtonMenuItem("Windows Classic");

        ButtonGroup group = new ButtonGroup();

        metal.setSelected(true);

        metal.addActionListener(radiobuttonListener);
        nimbus.addActionListener(radiobuttonListener);
        cm.addActionListener(radiobuttonListener);
        win.addActionListener(radiobuttonListener);
        winc.addActionListener(radiobuttonListener);

        metal.setActionCommand(metal.getText());
        nimbus.setActionCommand(nimbus.getText());
        cm.setActionCommand(cm.getText());
        win.setActionCommand(win.getText());
        winc.setActionCommand(winc.getText());

        group.add(metal);
        group.add(nimbus);
        group.add(cm);
        group.add(win);
        group.add(winc);

        lf.add(metal);
        lf.add(nimbus);
        lf.add(cm);
        lf.add(win);
        lf.add(winc);

    }


    class CustomPanel extends JPanel implements MouseListener
    {

        CustomPanel()
        {
            addMouseListener(this);
        }

        @Override public void mouseClicked(MouseEvent e)
        {
            fPanel.setText(this.getName());
        }

        @Override public void mousePressed(MouseEvent e){}
        @Override public void mouseReleased(MouseEvent e){}
        @Override public void mouseEntered(MouseEvent e){}
        @Override public void mouseExited(MouseEvent e){}
    }

}