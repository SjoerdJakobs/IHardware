package MainPackage;

import SFramework.*;
import TI.BoeBot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) {

        boolean buttonOneIsPressed = true;
        boolean buttonOneIsOn = true;
        boolean buttonTwoIsPressed = true;
        boolean buttonTwoIsOn = true;

        Multithreading object1 = new Multithreading(1000, 1000, 15);
        Multithreading object2 = new Multithreading(500, 500,10);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(object1);
        executorService.execute(object2);

        while (true)
        {

            //System.out.println(BoeBot.digitalRead(1));
            if (!BoeBot.digitalRead(1))
            {
                if(!buttonOneIsPressed)
                {
                    buttonOneIsPressed = true;
                    if(buttonOneIsOn)
                    {
                        object1.setToFalse();
                        System.out.println("off");

                        buttonOneIsOn = false;
                    }
                    else if(!buttonOneIsOn)
                    {
                        executorService.execute(object1);
                        System.out.println("on");
                        buttonOneIsOn = true;
                    }
                    //try {Thread.sleep(150L);} catch (Exception e) {}
                    //buttonOneIsPressed = false;
                }
            }
            else if(BoeBot.digitalRead(1))
            {
                buttonOneIsPressed = false;
            }

        }

        /*

         */





        /*
        boolean state = true;

        while (true) {
            BoeBot.digitalWrite(15, state);
            BoeBot.digitalWrite(10, !state);
            BoeBot.wait(500);
            state = !state;
        }

        //Program program = new Program();
        //program.Run();
        */
    }
}

// Java code for thread creation by implementing
// the Runnable Interface
class Multithreading implements Runnable
{
    private final AtomicBoolean atomicBoolean = new AtomicBoolean();

    public void setToFalse() {
        this.atomicBoolean.set(false);
    }

    public void setToTrue() {
        this.atomicBoolean.set(true);
        //run();
    }

    public void Restart() {
        this.setToTrue();
        this.run();
    }

    int m_onTime;
    int m_offTime;
    int m_addres;
    Multithreading(int onTime, int offTime, int addres)
    {
        this.setToTrue();
        m_addres = addres;
        m_onTime = onTime;
        m_offTime = offTime;
    }

    public void run()
    {
        try
        {
            // Displaying the thread that is running
            System.out.println ("Thread " +
                    Thread.currentThread().getId() +
                    " is running with addres" + m_addres);
            boolean state = false;

            while (atomicBoolean.get()) {

                BoeBot.digitalWrite(m_addres, false);
                BoeBot.wait(m_onTime);
                if(!atomicBoolean.get())
                {
                    BoeBot.digitalWrite(m_addres, true);
                    break;
                }
                BoeBot.digitalWrite(m_addres, true);
                BoeBot.wait(m_offTime);
                if(!atomicBoolean.get())
                {
                    BoeBot.digitalWrite(m_addres, true);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            // Throwing an exception
            System.out.println ("Exception is caught");
        }
    }
}