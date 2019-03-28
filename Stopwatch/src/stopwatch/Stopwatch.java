//Isaac Lichter
package stopwatch;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Stopwatch extends JFrame {

    WatchDisplay display;

    public Stopwatch() {

        this.setSize(500, 500);
        this.setTitle("Tic Tac Toe");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);

        WatchDisplay display = new WatchDisplay();
        this.add(display, BorderLayout.CENTER);

    }

    public class WatchDisplay extends JPanel {

        JTextArea clock;

        int second = 0;
        int minute = 0;
        private JButton startButton;
        private JButton stopButton;
        private JButton resetButton;
        private boolean isRunning;

        StartButtonEventHandler startButtonListener;
        StopButtonEventHandler stopButtonListener;
        ResetButtonEventHandler resetButtonListener;

        public WatchDisplay() {

            JTextArea clock = new JTextArea();
            this.add(clock, BorderLayout.CENTER);
            clock.setText(minute + ":" + second);

            this.startButton.setText("Start");
            this.startButton.setEnabled(true);
            this.add(this.startButton, BorderLayout.SOUTH);
            StartButtonEventHandler startButtonListener = new StartButtonEventHandler();
            this.startButton.addActionListener(startButtonListener);

            this.stopButton.setEnabled(false);
            this.stopButton.setText("Stop");
            this.add(this.stopButton, BorderLayout.SOUTH);
            StopButtonEventHandler stopButtonListener = new StopButtonEventHandler();
            this.stopButton.addActionListener(stopButtonListener);

            this.resetButton.setText("Reset");
            this.resetButton.setEnabled(true);
            this.add(this.resetButton, BorderLayout.SOUTH);
            ResetButtonEventHandler resetButtonListener = new ResetButtonEventHandler();
            this.resetButton.addActionListener(resetButtonListener);
        }

        public class StartButtonEventHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                startButton.setEnabled(false);

                stopButton.setEnabled(true);

                start();
            }

        }

        public class StopButtonEventHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                startButton.setEnabled(true);

                stopButton.setEnabled(false);

                stop();
            }

        }

        public class ResetButtonEventHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                startButton.setEnabled(true);

                stopButton.setEnabled(false);

                stop();

                minute = 0;

                second = 0;
            }

        }

        private void start() {
            isRunning = true;
            long now = System.currentTimeMillis();
            while (isRunning) {
                if (System.currentTimeMillis() - now == 1000) {
                    now = System.currentTimeMillis();
                    second++;
                    if (second == 60) {
                        minute++;
                        second = 0;
                    }
                    updateDisplay();
                }
            }
        }

        private void stop() {
            isRunning = false;
        }

        private void updateDisplay() {

        }
    }
}
