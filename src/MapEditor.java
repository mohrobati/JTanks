import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class MapEditor extends JPanel {

    /**
     * A framework for drawing the map
     * to play in, as the limit we had,
     * only has 14 objects to make a map
     * and still works fine...
     * needs a different thread to save png
     * so works with the MapSaver class...
     */

    private JPanel[][] points;
    private JToggleButton[] choices;
    private int selectedButton = 0;

    public MapEditor(Windows windows) {

        points = new JPanel[20][20];
        choices = new JToggleButton[15];

        Images.importImages();

        JButton export = new JButton("Make The Map!");

        export.setBackground(new Color(200,255,100));

        JPanel mapPanel = new JPanel();
        JPanel options = new JPanel();
        mapPanel.setSize(550,550);
        options.setSize(100,100);

        ButtonGroup buttonGroup = new ButtonGroup();

        for (int i=0;i<14;i++){
            choices[i] = new JToggleButton();
            choices[i].setBackground(Color.WHITE);
            choices[i].setBorder(new LineBorder(Color.BLACK));
            buttonGroup.add(choices[i]);
            options.add(choices[i]);
            switch (i){
                case 0: choices[i].setIcon(new ImageIcon(Images.getSOIL())); break;
                case 1: choices[i].setIcon(new ImageIcon(Images.getHardWallS())); break;
                case 2: choices[i].setIcon(new ImageIcon(Images.getSoftWall1S())); break;
                case 3: choices[i].setIcon(new ImageIcon(Images.getTeazelS())); break;
                case 4: choices[i].setIcon(new ImageIcon(Images.getTank1S())); break;
                case 5: choices[i].setIcon(new ImageIcon(Images.getBugEnemyS())); break;
                case 6: choices[i].setIcon(new ImageIcon(Images.getTankEnemyS())); break;
                case 7: choices[i].setIcon(new ImageIcon(Images.getStaticEnemyS())); break;
                case 8: choices[i].setIcon(new ImageIcon(Images.getMineS())); break;
                case 9: choices[i].setIcon(new ImageIcon(Images.getCannonCrateS())); break;
                case 10: choices[i].setIcon(new ImageIcon(Images.getRepairCrateS())); break;
                case 11: choices[i].setIcon(new ImageIcon(Images.getMachineGunCrateS())); break;
                case 12: choices[i].setIcon(new ImageIcon(Images.getUpgradeStarS())); break;
                case 13: choices[i].setIcon(new ImageIcon(Images.getKeyS())); break;
            }
            int finalI = i;
            choices[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedButton = finalI;
                }
            });
        }

        for(int i=0;i<20;i++) {
            for(int j=0;j<20;j++) {
                points[i][j] = new JPanel();
                points[i][j].setBackground(Color.BLACK);
                points[i][j].setBorder(new LineBorder(Color.WHITE));
                points[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        switch (selectedButton){
                            case 0: ((JPanel)e.getSource()).setBackground(new Color(0,0,0)); break;
                            case 1: ((JPanel)e.getSource()).setBackground(new Color(255,0,0)); break;
                            case 2: ((JPanel)e.getSource()).setBackground(new Color(200,0,0)); break;
                            case 3: ((JPanel)e.getSource()).setBackground(new Color(50,0,0)); break;
                            case 4: ((JPanel)e.getSource()).setBackground(new Color(0,0,255)); break;
                            case 5: ((JPanel)e.getSource()).setBackground(new Color(0,255,0)); break;
                            case 6: ((JPanel)e.getSource()).setBackground(new Color(0,253,0)); break;
                            case 7: ((JPanel)e.getSource()).setBackground(new Color(0,254,0)); break;
                            case 8: ((JPanel)e.getSource()).setBackground(new Color(0,249,0)); break;
                            case 9: ((JPanel)e.getSource()).setBackground(new Color(0,255,255)); break;
                            case 10: ((JPanel)e.getSource()).setBackground(new Color(0,255,150)); break;
                            case 11: ((JPanel)e.getSource()).setBackground(new Color(0,255,210)); break;
                            case 12: ((JPanel)e.getSource()).setBackground(new Color(0,255,50)); break;
                            case 13: ((JPanel)e.getSource()).setBackground(new Color(255,255,0)); break;
                        }

                    }
                });
                mapPanel.add(points[i][j]);
            }
        }


        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapSaver mapSaver = new MapSaver(getRGB());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                Game game = new Game(windows,1,true,1, false);
                windows.remove(MapEditor.this);
                windows.revalidate();
            }
        });

        GridLayout gridLayout1 = new GridLayout(20,20);
        mapPanel.setLayout(gridLayout1);
        GridLayout gridLayout2 = new GridLayout(7,2);
        options.setLayout(gridLayout2);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(mapPanel)
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(options)
                                    .addComponent(export,-1,-1,Short.MAX_VALUE)
                        )
                .addContainerGap()
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(mapPanel)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(options)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(export)
                                .addContainerGap()
                        )
        );

    }

    /**
     *
     * @return byte array contains rgb raster of every
     * point.
     */

    private byte[] getRGB() {
        byte[] rgbs = new byte[1200];
        int counter=0;
        for(int x=0;x<20;x++) {
            for (int y = 0; y < 20; y++) {
                rgbs[counter] = (byte) points[x][y].getBackground().getRed();
                counter++;
                rgbs[counter] = (byte) points[x][y].getBackground().getGreen();
                counter++;
                rgbs[counter] = (byte) points[x][y].getBackground().getBlue();
                counter++;
            }
        }
        return rgbs;
    }

}
