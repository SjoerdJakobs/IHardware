package SFramework;

public class RunnableObject implements Runnable
{
    public boolean Active;
    public boolean Activated;
    public boolean ShouldDestruct;
    public boolean UsesInput;
    public boolean UsesMain;
    public boolean UsesRenderer;
    public boolean UsesThreading;

    public FrameworkProgram GetFrameworkProgram;

    protected RunnableObject(FrameworkProgram frameworkProgram)
    {
        this(frameworkProgram, true, true, true, true, false);
    }

    protected RunnableObject(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain,
                             boolean usesRenderer, boolean startsActivated, boolean usesThreading)
    {
        GetFrameworkProgram = frameworkProgram;
        UsesInput = usesInput;
        UsesMain = usesMain;
        UsesRenderer = usesRenderer;
        UsesThreading = usesThreading;

        ShouldDestruct = false;

        if (startsActivated)
        {
            Active = true;
            Activated = true;
            AddToLoops();
        } else
        {
            Active = false;
            Activated = false;
        }

        frameworkProgram.Objects.add(this);

        if (!UsesThreading)
        {
            this.Start();
        }
    }

    protected void InputLoop(double deltaTime)
    {

    }

    protected void MainLoop(double deltaTime)
    {

    }

    protected void RenderLoop(double deltaTime)
    {

    }

    @Override
    public void run()
    {

    }

    protected void Start()
    {

    }

    protected void Awake()
    {

    }

    protected void RemoveFromLoops()
    {
        if (UsesInput)
        {
            GetFrameworkProgram.InputObjects.remove(this);
        }
        if (UsesMain)
        {
            GetFrameworkProgram.MainObjects.remove(this);
        }
        if (UsesRenderer)
        {
            GetFrameworkProgram.RenderObjects.remove(this);
        }
    }

    protected void AddToLoops()
    {
        if (UsesInput)
        {
            GetFrameworkProgram.InputObjects.add(this);
        }
        if (UsesMain)
        {
            GetFrameworkProgram.MainObjects.add(this);
        }
        if (UsesRenderer)
        {
            GetFrameworkProgram.RenderObjects.add(this);
        }
    }

    protected void Destroy()
    {
        RemoveFromLoops();
    }
}

