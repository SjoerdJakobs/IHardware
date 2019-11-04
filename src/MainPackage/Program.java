package MainPackage;

import SFramework.FrameworkProgram;
import TI.BoeBot;

public class Program extends FrameworkProgram
{
    boolean state = true;

    Program()
    {

    }

    @Override
    protected void Start() {
        super.Start();

        System.out.println("Hello World!");


        //BoeBot.digitalWrite(10, !state);


    }

    @Override
    protected void AddToLoop() {
        super.AddToLoop();

        BoeBot.digitalWrite(15, state);
        BoeBot.digitalWrite(10, state);
        BoeBot.wait(500);
        state = !state;

    }

    @Override
    protected void ExitProgram() {
        super.ExitProgram();
    }
}
