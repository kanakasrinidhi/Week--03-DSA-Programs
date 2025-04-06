import java.util.*;

class State {
    String content;
    State prev, next;

    State(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

public class TextEditorUndoRedo {
    State head = null, tail = null, current = null;
    int maxHistory = 10;
    int size = 0;

    void addState(String newContent) {
        State newState = new State(newContent);
        if (current != null) {
            current.next = null;
            tail = current;
        }

        if (tail != null) {
            tail.next = newState;
            newState.prev = tail;
        } else {
            head = newState;
        }

        tail = newState;
        current = newState;
        size++;

        if (size > maxHistory) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo steps.");
        }
    }

    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo steps.");
        }
    }

    void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.content);
        } else {
            System.out.println("Editor is empty.");
        }
    }

    public static void main(String[] args) {
        TextEditorUndoRedo editor = new TextEditorUndoRedo();
        Scanner sc = new Scanner(System.in);

        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState();

        editor.redo();
        editor.displayCurrentState();

        editor.addState("New Content");
        editor.displayCurrentState();

        editor.redo();  // should say no more redo steps
    }
}
