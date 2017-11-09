import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageDialog extends JDialog {

    public MessageDialog(Frame owner, String text) {
        super(owner, true);
        int width = 300;
        int height = 150;
        setSize(width, height);
        setResizable(false);
        setLocation(owner.getLocation().x + owner.getWidth() / 2 - width / 2, owner.getLocation().y + owner.getHeight() / 2 - height / 2);

        setLayout(new BorderLayout());
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public MessageDialog(Dialog owner, String text) {
        super(owner, true);
        int width = 300;
        int height = 150;
        setSize(width, height);
        setResizable(false);
        setLocation(owner.getLocation().x + owner.getWidth() / 2 - width / 2, owner.getLocation().y + owner.getHeight() / 2 - height / 2);

        setLayout(new BorderLayout());
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        buttonPanel.add(okButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

}
