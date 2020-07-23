package Snake;

import java.awt.event.KeyEvent;

public class Room {
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public static Room game;

    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        game = this;
    }

    public Snake getSnake() {
        return snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public void run() {

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();


        while (snake.isAlive()) {

            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                if (event.getKeyChar() == 'q') return;

                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);

                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);

                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);

                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();
            print();
            sleep();
        }

        System.out.println("Game Over!");
    }

    public void print() {

        char[][] matrix = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = '.';
            }
        }

        matrix[snake.getSections().get(0).getY()][snake.getSections().get(0).getX()] = 'X';

        for (int i = 1; i < snake.getSections().size(); i++) {
            matrix[snake.getSections().get(i).getY()][snake.getSections().get(i).getX()] = 'x';
        }

        matrix[mouse.getY()][mouse.getX()]='^';

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j]);
            }

            System.out.println();

        }
    }

    public void eatMouse() {
        createMouse();
    }

    public void createMouse() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);
    }

    public static void main(String[] args) {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }

    public void sleep() {
        for (int i = 0; i < snake.getSections().size(); i++) {
            try {
                if (snake.getSections().size() < 15) {
                    Thread.sleep(520 - (20 * snake.getSections().size()));
                } else {
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
    }
}