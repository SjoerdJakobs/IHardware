package SFramework;

public class RunnableObject
{
    public boolean Active;
    public boolean Activated;
    public boolean ShouldDestruct;
    public boolean UsesInput;
    public boolean UsesMain;
    public boolean UsesRenderer;

    public FrameworkProgram m_Framework_program;

    protected RunnableObject(FrameworkProgram frameworkProgram)
    {
        this(frameworkProgram, true, true, true, true);
    }

    protected RunnableObject(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated) {
        m_Framework_program = frameworkProgram;
        UsesInput = usesInput;
        UsesMain = usesMain;
        UsesRenderer = usesRenderer;

        ShouldDestruct = false;

        if(startsActivated) {
            Active = true;
            Activated = true;
            AddToLoops();
        }
        else
        {
            Active = false;
            Activated = false;
        }

        frameworkProgram.Objects.add(this);

        this.Start();
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
            m_Framework_program.InputObjects.remove(this);
        }
        if (UsesMain)
        {
            m_Framework_program.MainObjects.remove(this);
        }
        if (UsesRenderer)
        {
            m_Framework_program.RenderObjects.remove(this);
        }
    }

    protected void AddToLoops()
    {
        if (UsesInput)
        {
            m_Framework_program.InputObjects.add(this);
        }
        if (UsesMain)
        {
            m_Framework_program.MainObjects.add(this);
        }
        if (UsesRenderer)
        {
            m_Framework_program.RenderObjects.add(this);
        }
    }

    protected void Destroy()
    {
        RemoveFromLoops();
    }
}

