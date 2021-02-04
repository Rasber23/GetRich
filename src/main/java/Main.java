import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        try (Scanner scan = new Scanner(System.in)) {

            Game game = new Game();
            int userinput = 0;
            String userName;
            int userAge = 0;
            do {

                userAge = 0;
                System.out.println("What is your name?");
                userName = scan.nextLine();
                try {
                    System.out.println("How old are you?");
                    userAge = scan.nextInt();
                    scan.nextLine();

                    game.checkUserAmount(userAge);
                } catch (InvalidAmountException ex) {
                    System.out.println("Please enter a valid age!");
                }

            } while (userAge <= 0 || userName.equals(""));

            Player player = new Player(userName, userAge);


            player.calculateWealth();

            while (userinput != 9) {
                if (game.getRound() > 5) {
                    break;
                }

                System.out.println("Day: " + game.getRound());
                System.out.println("You are worth: " + player.getWealth());
                System.out.println("What do you want to do?");
                System.out.println("Press 1 for buy stocks");
                System.out.println("Press 2 sell stocks");
                System.out.println("Press 3 for sleep");
                System.out.println("Press 4 to see your current balance");
                userinput = scan.nextInt();
                game.whatDoYouWant(userinput, player);
            }

            if (game.endGame(player)) {
                System.out.println("Congratz" + player.getName() + ", you got some serious gains!");
                System.out.println("You started out with 100 and now u got " + player.getWealth() * (1 - Game.TAX));
            } else {
                System.out.println("GAME OVER, you lived fasted and died young!");
            }
        }


    }
}

