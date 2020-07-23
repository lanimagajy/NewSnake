package Snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public List<SnakeSection> getSections() {
        return sections;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public Snake(int x, int y) {
        sections = new ArrayList<SnakeSection>();
        SnakeSection hearSnake = new SnakeSection(x, y);
        sections.add(hearSnake);
        isAlive = true;

    }

    public int getX() {

        return sections.get(0).getX();
    }

    public int getY() {
        return sections.get(0).getY();
    }

    public void move() {
        if (!isAlive()) {
            return;
        } else if (direction == SnakeDirection.UP) {
            move(0, -1);
        } else if (direction == SnakeDirection.RIGHT) {
            move(1, 0);
        } else if (direction == SnakeDirection.DOWN) {
            move(0, 1);
        } else if (direction == SnakeDirection.LEFT) {
            move(-1, 0);
        }
    }

    public void move(int x , int y)
    {
        SnakeSection head = new SnakeSection(sections.get(0).getX()+x, sections.get(0).getY() + y);
        checkBorders(  head );
        checkBody(  head );

        if (isAlive)
        {getSections().add(0, head);
            getSections().remove(sections.size() - 1);}

        if (head.getX() == Room.game.getMouse().getX() && head.getY() == Room.game.getMouse().getY())
        {
            sections.add(0, head);
            Room.game.eatMouse();
        }
    }

    public void checkBorders(SnakeSection head) {
        if ((head.getX() < 0 || head.getX() >= Room.game.getWidth()) || head.getY() < 0 || head.getY() >= Room.game.getHeight()) {
            isAlive = false;
        }
    }
    public void checkBody(SnakeSection head) {
        if (sections.contains(head)) {
            isAlive = false;
        }
    }
}


