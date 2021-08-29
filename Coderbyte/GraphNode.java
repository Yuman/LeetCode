package Coderbyte;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphNode<T> {
    private final List<GraphNode<T>> mEdgeslist;
    private final T mValue;


    public GraphNode(T aValue) {
        this.mValue = aValue;
        mEdgeslist = new ArrayList<>();
    }

    public void connect(GraphNode<T> aNode) {
        if (!mEdgeslist.contains(aNode)) {
            mEdgeslist.add(aNode);
            aNode.connect(this);
        }
    }

    public boolean equals(Object aObj) {
        if (this == aObj) {
            return true;
        }
        if (aObj == null || getClass() != aObj.getClass()) {
            return false;
        }
        GraphNode<?> node = (GraphNode<?>) aObj;
        return Objects.equals(mValue, node.mValue);
    }

    public List<GraphNode<T>> getAdjacentNodes() {
        return mEdgeslist;
    }

    public T value() {
        return mValue;
    }

    public int hashCode() {
        return Objects.hash(mValue);
    }

    public boolean isConnected(GraphNode<T> node) {
        return mEdgeslist.contains(node);
    }

    public String toString() {
        return "GraphNode{" +
               "value=" + mValue +
               '}';
    }
}