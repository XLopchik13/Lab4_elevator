import java.util.*;

class Elevator {
    int id;
    int currentFloor;
    TreeSet<Integer> upRequests;
    TreeSet<Integer> downRequests;
    boolean movingUp;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>(Collections.reverseOrder());
        this.movingUp = true;
    }

    public void addRequest(int floor) {
        if (floor > currentFloor) {
            upRequests.add(floor);
        } else {
            downRequests.add(floor);
        }
    }

    public void move() {
        if (movingUp) {
            if (!upRequests.isEmpty()) {
                currentFloor = upRequests.pollFirst();
            } else if (!downRequests.isEmpty()) {
                movingUp = false;
                currentFloor = downRequests.pollFirst();
            }
        } else {
            if (!downRequests.isEmpty()) {
                currentFloor = downRequests.pollFirst();
            } else if (!upRequests.isEmpty()) {
                movingUp = true;
                currentFloor = upRequests.pollFirst();
            }
        }
    }

    public boolean hasRequests() {
        return !upRequests.isEmpty() || !downRequests.isEmpty();
    }

    public int getRequestCount() {
        return upRequests.size() + downRequests.size();
    }
}
