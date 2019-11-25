package MainPackage;

import TI.BoeBot;
import java.util.concurrent.atomic.AtomicBoolean;
public class ThreadedLEDs implements Runnable
{
    private final AtomicBoolean atomicBoolean = new AtomicBoolean();

    public void setToFalse()
    {
        this.atomicBoolean.set(false);
    }

    public void setToTrue()
    {
        this.atomicBoolean.set(true);
    }

    int m_onTime;
    int m_offTime;
    int m_addres;

    ThreadedLEDs(int onTime, int offTime, int addres)
    {
        this.setToTrue();
        m_addres = addres;
        m_onTime = onTime;
        m_offTime = offTime;
    }

    public void Loop()
    {
        while (atomicBoolean.get())
        {
            BoeBot.digitalWrite(m_addres, false);
            BoeBot.wait(m_onTime);
            if (!atomicBoolean.get())
            {
                BoeBot.digitalWrite(m_addres, true);
                break;
            }
            BoeBot.digitalWrite(m_addres, true);
            BoeBot.wait(m_offTime);
            if (!atomicBoolean.get())
            {
                BoeBot.digitalWrite(m_addres, true);
                break;
            }
        }
    }

    public void run()
    {
        try
        {
            Loop();
        } catch (Exception e)
        {
            System.out.println("Exception is caught");
        }
    }
}
