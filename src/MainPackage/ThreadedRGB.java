package MainPackage;

import TI.BoeBot;
import TI.PWM;

public class ThreadedRGB implements Runnable {
    @Override
    public void run() {
        try {
            PWM roodLED = new PWM(0, 0);
            PWM geelLED = new PWM(1, 0);
            PWM blauwLED = new PWM(2, 0);
            while (true) {
                for (int i = 0; i <= 255; i++) {
                    blauwLED.update(255 - i);
                    roodLED.update(i);
                    BoeBot.wait(8);
                }
                for (int i = 0; i <= 255; i++) {
                    roodLED.update(255 - i);
                    geelLED.update(i);
                    BoeBot.wait(8);
                }
                for (int i = 0; i <= 255; i++) {
                    geelLED.update(255 - i);
                    blauwLED.update(i);
                    BoeBot.wait(8);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}
