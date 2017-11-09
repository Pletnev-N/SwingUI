import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YesNoDialog extends JDialog {

    private boolean answerYes = false;
    private boolean answerNo = false;

    public YesNoDialog(Frame owner, String text) {
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

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerYes = true;
                answerNo = false;
                setVisible(false);
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerYes = false;
                answerNo = true;
                setVisible(false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public boolean isAnswerYes() {
        return answerYes;
    }

    public boolean isAnswerNo() {
        return answerNo;
    }
}
