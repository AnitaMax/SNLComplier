package cn.jlu.edu.ccst.View.Tests;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Test {
    static int xx , yy;
    static Point p = new Point(0, 0);
    static int w,h;
    static boolean b = true;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new LineBorder(Color.black));
        panel.setPreferredSize(new Dimension(260,55));

        frame.setBounds(100, 100, 450, 300);
        final JLabel label = new JLabel(){
            public void repaint() {
                this.setLocation(p);
                this.setSize(w,h);
                super.repaint();
            }
        };
        LineBorder border = new LineBorder(Color.black);
        label.setBorder(border);
        label.setForeground(Color.black);
        label.setPreferredSize(new Dimension(50,30));
        label.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent arg0) {
                b = false;
            }
            public void mousePressed(MouseEvent mouseEvent) {
                xx = mouseEvent.getX();
                yy = mouseEvent.getY();
                w = label.getSize().width;
                h = label.getSize().height;
                b = true;
            }
            public void mouseExited(MouseEvent arg0) {
            }
            public void mouseEntered(MouseEvent e) {
                if (label.getSize().width-8<=e.getX()) {
                    label.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));//右
                } else if (8>=e.getX()) {
                    label.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));//左
                } else {
                    label.setCursor(Cursor.getDefaultCursor());
                }
            }
            public void mouseClicked(MouseEvent arg0) {
            }
        });
        label.addMouseMotionListener(new MouseAdapter() {

                                         public void mouseMoved(MouseEvent e) {
                                             Cursor cursor = label.getCursor();
                                             if (label.getSize().width-5<=e.getX()) {
                                                 label.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));//右
                                             } else if (5>=e.getX()) {
                                                 label.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));//左
                                             } else {
                                                 label.setCursor(Cursor.getDefaultCursor());
                                             }
                                             if (b) {
                                                 label.setCursor(cursor);
                                             }
                                         };
                                         public void mouseDragged(MouseEvent e) {
                                             p = label.getLocation();
                                             if (label.getCursor().getType() == Cursor.W_RESIZE_CURSOR) {

                                                 Point point = label.getLocation();
                                                 p.x = e.getPoint().x + point.x - 0;
                                                 p.y = point.y;
                                                 label.setLocation(p);
                                                 w = label.getSize().width - e.getPoint().x;
                                                 h = label.getSize().height;
                                                 label.setSize(w,h);
                                             } else if (label.getCursor().getType() == Cursor.E_RESIZE_CURSOR) {
                                                 w = e.getPoint().x;
                                                 h = label.getSize().height;
                                                 label.setSize(w,h);
                                                 label.setLocation(p);
                                             } else {
                                                 Point point = label.getLocation();
////////////随意移动
//p.x = e.getPoint().x + point.x - xx;
 //p.y = e.getPoint().y + point.y - yy;
//label.setLocation(e.getPoint().x + point.x - xx,e.getPoint().y + point.y - yy);
////////////沿着X轴方向移动
                                                 p.x = e.getPoint().x + point.x - xx;
                                                 p.y = point.y;
                                                 label.setLocation(e.getPoint().x + point.x - xx,point.y); }
                                         }
                                     }
        );
        panel.add(label);
        frame.add(panel);
        JButton btn = new JButton("确认");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < panel.getComponentCount(); i++) {
                    Component c = panel.getComponent(i);
                    System.out.println(c.getLocation().x);
                }
            }
        });
        frame.add(btn);
        frame.setVisible(true);
    }
}