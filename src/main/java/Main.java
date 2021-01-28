import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Game game = new Game();
        int userinput = 0;

        while (userinput != 9) {
            System.out.println("What do you want to do?");
            System.out.println("Press 1 for buy stocks");
            System.out.println("Press 2 sell stocks");
            System.out.println("Press 3 for sleep");
            userinput = scan.nextInt();


            game.whatDoYouWant(userinput);
        }
    }
}

