package mock;

public class FinalChild extends FinalParent {
    public int doSomething(int i) {
        return isEven(i) ? 0 : 1;
    }
}
