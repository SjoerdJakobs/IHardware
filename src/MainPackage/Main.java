package MainPackage;

import TI.*;

public class Main
{
    public static void main(String[] args)
    {
        Car car = new Car();
        car.run();
    }
}

enum Direction
{
    Forward,
    Backward,
    ForwardLeft,
    ForwardRight,
    BackwardLeft,
    BackwardRight,
    RotateLeft,
    RotateRight,
    Stop,
    Default
}

class Car
{
    Servo rightWheel;
    Servo leftWheel;

    Direction botDirection = Direction.Default;

    boolean stopWhenButtonIsNotPressed = true;

    public void run()
    {
        System.out.println("listen");
        rightWheel = new Servo(15);
        leftWheel = new Servo(14);

        while (true) {
            int pulseLen = BoeBot.pulseIn(15,false,6000);
            if(pulseLen <= 2000)
            {
                long number = 0;
                int lengtes[] = new int[12];
                for(int i = 0; i <12; i++)
                {
                    lengtes[i] = BoeBot.pulseIn(15,false,20000);
                }
                for(int i = 0; i <12; i++)
                {
                    System.out.print(lengtes[i]);
                }
                number = readButtonReturnInt(lengtes);

                System.out.println("");
                System.out.println(number);

                BoeBot.wait(10);

                if (number == 200001001000l) {
                    if (botDirection != Direction.Forward) {
                        forward();
                        botDirection = Direction.Forward;
                    }
                } else if (number == 210001001000l) {
                    if (botDirection != Direction.Backward) {
                        backward();
                        botDirection = Direction.Backward;
                    }
                } else if (number == 210101001000l) {
                    if (botDirection != Direction.ForwardLeft) {
                        forwardLeft();
                        botDirection = Direction.ForwardLeft;
                    }
                } else if (number == 200101001000l) {
                    if (botDirection != Direction.ForwardRight) {
                        forwardRight();
                        botDirection = Direction.ForwardRight;
                    }
                } else if (number == 210100101000l) {
                    if (botDirection != Direction.BackwardRight) {
                        backwardRight();
                        botDirection = Direction.BackwardRight;
                    }
                }else if (number == 200010011100l) {
                    if (botDirection != Direction.BackwardLeft) {
                        backwardLeft();
                        botDirection = Direction.BackwardLeft;
                    }
                }else if (number == 201001001000l) {
                    if (botDirection != Direction.RotateRight) {
                        rotateRight();
                        botDirection = Direction.RotateRight;
                    }
                }else if (number == 211001001000l) {
                    if (botDirection != Direction.RotateLeft) {
                        rotateLeft();
                        botDirection = Direction.RotateLeft;
                    }
                }else if (number == 210000001000l) {
                    if (botDirection != Direction.Stop) {
                        stop();
                        botDirection = Direction.Stop;
                    }
                }else if(stopWhenButtonIsNotPressed)
                {
                    stop();
                    botDirection = Direction.Default;
                }
            }
        }
    }
    public long readButtonReturnInt(int lengtes[])
    {
        long number = 0;
        for(int i = 0; i <12; i++)
        {
            if(Math.abs(lengtes[i] - 600) < 250)
            {
                number += (0 * (Math.pow(10, 11-i)));
            }
            else if(Math.abs(lengtes[i] - 1200) < 250)
            {
                number +=(1* (Math.pow(10, 11-i)));
            }
            else
            {
                number +=(2* (Math.pow(10, 11-i)));
            }
        }
        if(number > 219999999999l)
        {
            number = -1;
        }
        return number;
    }

    public int[] readButtonReturnArray(int lengtes[])
    {
        int[] numbers = new int[12];
        for(int i = 0; i <12; i++)
        {
            if(Math.abs(lengtes[i] - 600) < 250)
            {
                numbers[i]=0;
            }
            else if(Math.abs(lengtes[i] - 1200) < 250)
            {
                numbers[i]=1;
            }
            else
            {
                numbers[i]=2;
            }
        }
        return (numbers);
    }

    public void rotateLeft()
    {
        rightWheel.update(1700);
        leftWheel.update(1700);
    }

    public void rotateRight()
    {
        rightWheel.update(1300);
        leftWheel.update(1300);
    }

    public void forwardRight()
    {
        rightWheel.update(1450);
        leftWheel.update(1700);
    }

    public void forwardLeft()
    {
        rightWheel.update(1300);
        leftWheel.update(1550);
    }

    public void backwardRight()
    {
        rightWheel.update(1550);
        leftWheel.update(1300);
    }

    public void backwardLeft()
    {
        rightWheel.update(1700);
        leftWheel.update(1450);
    }

    public void forward()
    {
        rightWheel.update(1300);
        leftWheel.update(1700);
    }

    public void backward()
    {
        rightWheel.update(1700);
        leftWheel.update(1300);
    }

    public void stop()
    {
        rightWheel.update(1500);
        leftWheel.update(1500);
    }
}

//BoeBot.freqOut(5,1000,1000);
/*
        PWM roodLED = new PWM(0, 0);
        PWM geelLED = new PWM(1, 0);
        PWM blauwLED = new PWM(2, 0);

        int counter = 0;

        Servo s1 = new Servo(14);
        Servo s2 = new Servo(15);

        s1.update(1500);
        s2.update(1500);

        //ThreadedRGB rgbLed1 = new ThreadedRGB();
        //ExecutorService executorService = Executors.newFixedThreadPool(1);
        //executorService.execute(rgbLed1);

        BoeBot.freqOut(5, 1000, 2000000);

        int j = 0;
        int changeJ = 1;
        while (true) {
            for (int i = 0; i <= 1000; i++, j += changeJ) {
                BoeBot.freqOut(5, 1000 + j, 2);
                BoeBot.wait(1);
            }
            changeJ *= -1;
        }
        /*
        PWM pwm1 = new PWM(0, 0);
        PWM pwm2 = new PWM(1, 0);
        PWM pwm3 = new PWM(2, 0);

        int changej =1;
        int j = 0;
        while (true) {
            for (int i = 0; i < 255; i++, j += changej) {
                pwm1.update(j);
                pwm2.update(j);
                pwm3.update(j);
                BoeBot.wait(8);
            }
            changej *= -1;
        }

        /*
        double lastTime = 0;
        double deltaTime = 0;
        double counter = 0;

        while (dutyCycle < 100) {
            long time = System.nanoTime();
            deltaTime = ((double) (time - lastTime) / 1000_000_000);//delta time in seconds
            lastTime = time;

            if (counter >= 0.1d) {
                dutyCycle++;
                pwm.update(dutyCycle);
                System.out.println(pwm.getDutyCycle());
                counter = 0;
            }
            counter+= deltaTime;
        }*/


class MotorControll
{
    Servo rightWheel = new Servo(14);
    Servo leftWheel = new Servo(15);

    int leftSpeed = 0;
    int rightSpeed = 0;

    int currentSpeed = 0;
    int targetSpeed = 0;

    void Loop()
    {
        while (true) {
            SetActualSpeed();
        }
    }

    private void SetActualSpeed()
    {
        ChangeSpeed();
        TakeCorner();
        rightWheel.update(rightSpeed);
        leftWheel.update(leftSpeed);
    }

    public void TakeCorner()
    {
        leftSpeed /= 10;
    }

    private void ChangeSpeed()
    {

    }

    public void SetTargetSpeed(int speed, int accelerationSpeed)
    {

    }
}
