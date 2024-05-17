import java.util.*;

public class ElevatorSimulation {
    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(3);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a floor number or type 'exit' to quit: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int floor = Integer.parseInt(input);
                system.requestElevator(floor);
                system.printStatus();
                system.step();
                System.out.println("----------------------");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        scanner.close();
    }
}