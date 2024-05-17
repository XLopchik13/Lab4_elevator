import java.util.*;

class ElevatorSystem {
    List<Elevator> elevators;

    public ElevatorSystem(int numberOfElevators) {
        elevators = new ArrayList<>();
        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public void requestElevator(int floor) {
        Elevator bestElevator = findBestElevator(floor);
        bestElevator.addRequest(floor);
    }

    private Elevator findBestElevator(int floor) {
        Elevator bestElevator = null;
        int minCost = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            int cost = calculateCost(elevator, floor);
            if (cost < minCost) {
                minCost = cost;
                bestElevator = elevator;
            }
        }

        return bestElevator;
    }

    private int calculateCost(Elevator elevator, int floor) {
        if (!elevator.hasRequests()) {
            return Math.abs(elevator.currentFloor - floor);
        }

        if ((elevator.movingUp && floor >= elevator.currentFloor) ||
                (!elevator.movingUp && floor <= elevator.currentFloor)) {
            return Math.abs(elevator.currentFloor - floor) + elevator.getRequestCount();
        } else {
            return elevator.getRequestCount() + Math.abs(elevator.currentFloor - floor);
        }
    }

    public void step() {
        for (Elevator elevator : elevators) {
            if (elevator.hasRequests()) {
                elevator.move();
            }
        }
    }

    public void printStatus() {
        for (Elevator elevator : elevators) {
            System.out.println("Elevator " + elevator.id + ": Floor " + elevator.currentFloor + ", Up Requests: " + elevator.upRequests + ", Down Requests: " + elevator.downRequests);
        }
    }
}