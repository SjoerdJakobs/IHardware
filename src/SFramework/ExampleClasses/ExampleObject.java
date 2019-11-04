package SFramework.ExampleClasses;
import SFramework.FrameworkProgram;
import SFramework.RunnableObject;

public class ExampleObject extends RunnableObject
{
    protected ExampleObject(FrameworkProgram frameworkProgram) {
        super(frameworkProgram);
    }

    protected ExampleObject(FrameworkProgram frameworkProgram, boolean usesInput, boolean usesMain, boolean usesRenderer, boolean startsActivated) {
        super(frameworkProgram, usesInput, usesMain, usesRenderer, startsActivated);
    }

    @Override
    protected void Start() {
        super.Start();
    }

    @Override
    protected void Awake() {
        super.Awake();
    }

    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        super.RenderLoop(deltaTime);
    }

    @Override
    protected void Destroy() {
        super.Destroy();
    }
}
