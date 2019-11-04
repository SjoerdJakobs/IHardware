package SFramework;

import java.util.ArrayList;
import java.util.Iterator;

public class FrameworkProgram
{
    private boolean run;

    ArrayList<RunnableObject>Objects = new ArrayList<RunnableObject>();
    ArrayList<RunnableObject>InputObjects = new ArrayList<RunnableObject>();
    ArrayList<RunnableObject>MainObjects = new ArrayList<RunnableObject>();
    ArrayList<RunnableObject>RenderObjects = new ArrayList<RunnableObject>();

    public double deltaTime = 0;

    public FrameworkProgram()
    {

    }

    public void Run() {

        Start();

        long lastTime = System.nanoTime();

        run = true;
        while (run) {

            /**
             * calculate deltatime
             */
            long time = System.nanoTime();
            deltaTime = ((double)(time - lastTime) / 1000_000_000);//delta time in seconds
            lastTime = time;

            //uncomment to print the deltatime in seconds
            //String s = String.format("%.5f", deltaTime);
            //System.out.println(s);

            AddToLoop();

            for (RunnableObject object : InputObjects) {
                object.InputLoop(deltaTime);
            }
            for (RunnableObject object : MainObjects) {
                object.MainLoop(deltaTime);
            }
            for (RunnableObject object : RenderObjects) {
                object.RenderLoop(deltaTime);
            }

            Iterator<RunnableObject> it = Objects.iterator();
            while (it.hasNext()) {
                RunnableObject ro = it.next();
                if (ro.ShouldDestruct) {
                    ro.Destroy();
                    it.remove();
                }
                else if(ro.Active && !ro.Activated)
                {
                    ro.AddToLoops();
                    ro.Activated = true;
                    ro.Awake();
                }
                else if(!ro.Active && ro.Activated)
                {
                    ro.RemoveFromLoops();
                    ro.Activated = false;
                }
            }
        }
    }

    protected void Start()
    {

    }

    protected void AddToLoop()
    {

    }

    protected void ExitProgram()
    {
        run = false;
    }
}

