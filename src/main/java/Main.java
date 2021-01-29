import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Game game = new Game();
        int userinput = 0;

        System.out.println("What is your name?");
        String userName = scan.next();
        System.out.println("How old are you?");
        int userAge = scan.nextInt();

        Player player = new Player(userName, userAge);

        while (userinput != 9) {
            System.out.println("What do you want to do?");
            System.out.println("Press 1 for buy stocks");
            System.out.println("Press 2 sell stocks");
            System.out.println("Press 3 for sleep");
            System.out.println("Press 4 to see your current balance");
            userinput = scan.nextInt();


            game.whatDoYouWant(userinput, player);
        }
    }
}

