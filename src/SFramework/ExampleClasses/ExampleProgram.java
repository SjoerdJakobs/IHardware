package SFramework.ExampleClasses;
import SFramework.FrameworkProgram;

public class ExampleProgram extends FrameworkProgram
{

    public class Program extends FrameworkProgram
    {
        Program()
        {

        }

        @Override
        protected void Start()
        {
            super.Start();

            System.out.println("Hello World!");


        }

        @Override
        protected void AddToLoop()
        {
            super.AddToLoop();

        }

        @Override
        protected void ExitProgram()
        {
            super.ExitProgram();
        }
    }
}
