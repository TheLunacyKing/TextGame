

package textGame;


import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author nath8150
 */
public class TextGame {

    /**
     * @param args the command line arguments
     */
    
    public static int GOLD = 15, WALLET = 50;
    public static int toNext, EXPLEVEL = 15;
    public static int[] itemNo = {3};
    public static int[] idgafShopValue = {10};
    public static String[] idgafShopInven = {"1.Health Potion\t\t"+ idgafShopValue[0]};
    public static String[] inven = {"1.Health Potion\t\t"+ itemNo[0]};
    public static int MAXPLAYERHP, PLAYERHP, PLAYERATK;
    public static double PLAYERACC, PLAYERAGI;
    public static int MAXENEMYHP, ENEMYHP, ENEMYATK, idx;
    public static double ENEMYACC, ENEMYAGI;
    public static String PLAYERNAME, PLAYERCLASS, PLAYERATKTYPE;
    public static Random rd = new Random();
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        for(boolean charDone = false; charDone == false;){
            System.out.print("Choose a name for yourself:");
            PLAYERNAME = input.nextLine();
            for(boolean selected = false; selected == false;){
                System.out.print("So your name is " + PLAYERNAME + "?\n\t\t(y/n)");
                String confirm = input.nextLine();
                if(confirm.equals("y")){
                    break;
                }else if (confirm.equals("n")){
                   System.out.print("Then enter a more preferable name: ");
                   PLAYERNAME = input.nextLine();
                }
            }
            System.out.print("CHOOSE A CLASS:\n\t1:WARRIOR\n\t2:ARCHER\n\t3:BARBARIAN\n\t(1/2/3):");
            String PLAYERCLASSSELECT = input.next();
            for(boolean selected = false; selected == false;){
                switch (PLAYERCLASSSELECT) {
                    case "1":
                    case "warrior":
                        PLAYERHP = 50;
                        MAXPLAYERHP = 50;
                        PLAYERATK = 10;
                        PLAYERACC = .90;
                        PLAYERAGI = .10;
                        PLAYERCLASS = "Warrior";
                        PLAYERATKTYPE = "Sword";
                        selected = true;
                        break;
                    case "2":
                    case "archer":
                        PLAYERHP = 25;
                        MAXPLAYERHP = 25;
                        PLAYERATK = 13;
                        PLAYERACC = .90;
                        PLAYERAGI = .20;
                        PLAYERATKTYPE = "Bow";
                        PLAYERCLASS = "Archer";
                        selected = true;
                        break;
                    case "3":
                    case "barbarian":
                        PLAYERHP = 75;
                        MAXPLAYERHP = 75;
                        PLAYERATK = 15;
                        PLAYERACC = .8;
                        PLAYERAGI = .07;
                        PLAYERATKTYPE = "Club";
                        PLAYERCLASS = "Barbarian";
                        selected = true;
                        break;
                    default:
                        System.err.println("Please, try entering 1, 2, or 3.");
                        PLAYERCLASS = input.nextLine();
                        break;
                }
            }
            System.out.print("You are " + PLAYERNAME + " the " + PLAYERCLASS +", correct?\n\t\t\t(y/n):");
            String response = input.next();
            if (response.equals("y")){
                System.out.println("Then let's begin.");
                System.out.println("You wake up in a forest."
                    + "\nAll you see around you are trees.");
                System.out.print("You've only just begun walking, when ");
                break;
            }else if(response.equals("n")){
                for(int clear = 0; clear < 1000; clear++)
                {
                    System.out.println("\b") ;
                }
                System.out.println("From the top.");
            }
        }
        Health heal = new Health(PLAYERHP);
        //forest starts here
        for (int encounters = 0;PLAYERHP>0 && encounters <= 5; encounters++){
            idx = rd.nextInt(3);
            String[] enemy = {"a Skeleton", "a Slime", "Tim Cao"};
            System.out.println("you are attacked by " + enemy[idx] + "!");
            switch (idx) {
                case 0:
                    MAXENEMYHP = 15;
                    ENEMYHP = MAXENEMYHP;
                    ENEMYATK = 10;
                    ENEMYACC = .75;
                    ENEMYAGI = .10;
                    for (boolean runaway = false;runaway == false && PLAYERHP>0 && ENEMYHP>0;){
                        System.out.println("The Skeleton has " + ENEMYHP + " HP");
                        System.out.println(PLAYERNAME + " has " + PLAYERHP + " HP");
                        System.out.println("Do you:"
                                + "\n\t1.Attack"
                                + "\n\t2.Use an item"
                                + "\n\t3.Run");
                        String response = input.next();
                switch (response) {
                    case "1":
                        int damage = rd.nextInt((PLAYERATK-(PLAYERATK-3))+1)+(PLAYERATK-3);
                        double hit = Math.random();
                        double dodge = Math.random();
                        if(hit < PLAYERACC && dodge > ENEMYAGI){
                            ENEMYHP = ENEMYHP - damage;
                            System.out.println("\nYou hit the Skeleton with your " + PLAYERATKTYPE);
                            System.out.println("The skeleton took" + damage +".\nit now has " + ENEMYHP);
                            TimeUnit.SECONDS.sleep(2);

                        }else{
                            System.out.println(PLAYERNAME + " missed the skeleton");
                            TimeUnit.SECONDS.sleep(2);
                        }if(ENEMYHP>0){
                        int enemyDamage = rd.nextInt((ENEMYATK-(ENEMYATK-3))+1)+(ENEMYATK-3);
                        hit = Math.random();
                        dodge = Math.random();
                        if(hit < ENEMYACC && dodge > PLAYERAGI){
                            PLAYERHP = PLAYERHP - enemyDamage;
                            System.out.println("\nThe skeleton hit " + PLAYERNAME + "!");
                            System.out.println(PLAYERNAME + " took" + enemyDamage +".\n" + PLAYERNAME + " now has " + PLAYERHP + "\n");
                            TimeUnit.SECONDS.sleep(2);

                        }
                        }
                        break;
                    case "2":
                        System.out.println("Which item?");
                        System.out.println(Arrays.toString(inven));
                        System.out.print("Enter the list number of the item to be used:");
                        int itemUsed = input.nextInt();
                        if(itemUsed == 1 && itemNo[0]>=1){
                            itemNo[0]=itemNo[0] - 1;
                            inven[0] = "1.Health Potion\t\t"+itemNo[0];
                            heal.setHealthValue(PLAYERHP + 20);
                            PLAYERHP = heal.getHealthValue();
                            System.out.println(PLAYERNAME + " healed for 20 HP!");
                            System.out.println(PLAYERNAME + " now has " + PLAYERHP + " HP!\n\n");
                            TimeUnit.SECONDS.sleep(2);
                        }
                        break;
                    case "3":
                        double run = Math.random();
                        if(run >= PLAYERAGI){
                            System.out.println(PLAYERNAME + " escaped from the Skeleton");
                            runaway = true;
                            TimeUnit.SECONDS.sleep(2);
                        }else{
                            System.out.println(PLAYERNAME + " couldn't escape!");
                            TimeUnit.SECONDS.sleep(2);
                            int enemyDamage = rd.nextInt((ENEMYATK-(ENEMYATK-3))+1)+(ENEMYATK-3);
                            PLAYERHP = PLAYERHP - enemyDamage;
                            System.out.println("\nThe skeleton hit " + PLAYERNAME + "!");
                            System.out.println(PLAYERNAME + " took" + enemyDamage +".\n" + PLAYERNAME + " now has " + PLAYERHP + "\n");
                            TimeUnit.SECONDS.sleep(2);
                        }
                        break;
                    default:
                        break;
                }
                        if(ENEMYHP<1){
                            int goldDrop = rd.nextInt(GOLD-(GOLD/2)+1)+(GOLD/2);
                            int expDrop = rd.nextInt ((8-6)+1)+6;
                            toNext = EXPLEVEL - expDrop;
                            WALLET = WALLET + goldDrop;
                            System.out.println(PLAYERNAME + " defeated the skeleton!");
                            TimeUnit.SECONDS.sleep(1);
                            //System.out.println(PLAYERNAME + " gained " + expDrop + " experience!\nNext level is in " + toNext + " experience.");
                            //TimeUnit.SECONDS.sleep(1);
                            System.out.println("The skeleton dropped " + goldDrop + " gold doubloons");
                            System.out.println(PLAYERNAME + " grabs 'em and goes.\n"
                                    + PLAYERNAME + " now has " + WALLET + " gold doubloons");
                            TimeUnit.SECONDS.sleep(4);
                        }
                        
                    }   break;
                case 1:
                    MAXENEMYHP = 15;
                    ENEMYHP = MAXENEMYHP;
                    ENEMYATK = 4;
                    ENEMYACC = .75;
                    ENEMYAGI = .10;
                    for (boolean runaway = false;runaway == false && PLAYERHP>0 && ENEMYHP>0;){
                        System.out.println("The Slime has " + ENEMYHP + " HP");
                        System.out.println(PLAYERNAME + " has " + PLAYERHP + " HP");
                        System.out.println("Do you:"
                                + "\n\t1.Attack"
                                + "\n\t2.Use an item"
                                + "\n\t3.Run");
                        String response = input.next();
                        if (response.equals("1")){
                            int damage = rd.nextInt((PLAYERATK-(PLAYERATK-3))+1)+(PLAYERATK-3);
                            double hit = Math.random();
                            double dodge = Math.random();
                            if(hit < PLAYERACC && dodge > ENEMYAGI){
                                ENEMYHP = ENEMYHP - damage;
                                System.out.println("\nYou hit the slime with your " + PLAYERATKTYPE);
                                System.out.println("The slime took" + damage +".\nIt now has " + ENEMYHP);
                                TimeUnit.SECONDS.sleep(2);
                            }if(ENEMYHP>0){
                        int enemyDamage = rd.nextInt((ENEMYATK-(ENEMYATK-3))+1)+(ENEMYATK-3);
                        hit = Math.random();
                        dodge = Math.random();
                        if(hit < ENEMYACC && dodge > PLAYERAGI){
                            PLAYERHP = PLAYERHP - enemyDamage;
                            System.out.println("\nThe slime hit " + PLAYERNAME + "!");
                            System.out.println(PLAYERNAME + " took" + enemyDamage +".\n" + PLAYERNAME + " now has " + PLAYERHP + "\n");
                            TimeUnit.SECONDS.sleep(2);
                        }
                        }
                        }else if(response.equals("2")){
                            System.out.println("Which item?");
                            System.out.println(Arrays.toString(inven));
                            System.out.print("Enter the list number of the item to be used:");
                            int itemUsed = input.nextInt();
                            if(itemUsed == 1 && itemNo[0]>=1){
                                itemNo[0]=itemNo[0] - 1;
                                inven[0] = "1.Health Potion\t\t"+itemNo[0];
                                heal.setHealthValue(PLAYERHP + 20);
                                PLAYERHP = heal.getHealthValue();
                                System.out.println(PLAYERNAME + " healed for 20 HP!");
                                System.out.println(PLAYERNAME + " now has " + PLAYERHP + " HP!\n\n");
                                TimeUnit.SECONDS.sleep(2);
                            }
                        }else if(response.equals("3")){
                            double run = Math.random();
                            if(run >= PLAYERAGI){
                                System.out.println(PLAYERNAME + " escaped from the slime");
                                runaway = true;
                                TimeUnit.SECONDS.sleep(2);
                            }else{
                                System.out.println(PLAYERNAME + " couldn't escape!");
                                TimeUnit.SECONDS.sleep(2);
                                int enemyDamage = rd.nextInt((ENEMYATK-(ENEMYATK-3))+1)+(ENEMYATK-3);
                                PLAYERHP = PLAYERHP - enemyDamage;
                                System.out.println("\nThe slime hit " + PLAYERNAME + "!");
                                System.out.println(PLAYERNAME + " took" + enemyDamage +".\n" + PLAYERNAME + " now has " + PLAYERHP + "\n");
                                TimeUnit.SECONDS.sleep(2);
                            }
                        }
                        if(ENEMYHP<1){
                            int goldDrop = rd.nextInt(GOLD-(GOLD/2)+1)+(GOLD/2);
                            int expDrop = rd.nextInt ((8-6)+1)+6;
                            toNext = EXPLEVEL - expDrop;
                            WALLET = WALLET + goldDrop;
                            System.out.println(PLAYERNAME + " defeated the slime!");
                            TimeUnit.SECONDS.sleep(1);
                            //System.out.println(PLAYERNAME + " gained " + expDrop + " experience!\nNext level is in " + toNext + " experience.");
                            //TimeUnit.SECONDS.sleep(1);
                            System.out.println("The slime dropped " + goldDrop + " gold doubloons");
                            System.out.println(PLAYERNAME + " grabs 'em and goes.\n"
                                    + PLAYERNAME + " now has " + WALLET + " gold doubloons");
                            TimeUnit.SECONDS.sleep(4);
                        }
                        
                    }   break;
                case 2:
                    MAXENEMYHP = 18;
                    ENEMYHP = MAXENEMYHP;
                    ENEMYATK = 1;
                    ENEMYACC = .75;
                    ENEMYAGI = .10;
                    for (boolean runaway = false;runaway == false && PLAYERHP>0 && ENEMYHP>0;){
                        System.out.println("Tim Cao has " + ENEMYHP + " HP");
                        System.out.println(PLAYERNAME + " has " + PLAYERHP + " HP");
                        System.out.println("Do you:"
                                + "\n\t1.Attack"
                                + "\n\t2.Use an item"
                                + "\n\t3.Run");
                        String response = input.next();
                        if (response.equals("1")){
                            int damage = rd.nextInt((PLAYERATK-(PLAYERATK-3))+1)+(PLAYERATK-3);
                            double hit = Math.random();
                            double dodge = Math.random();
                            if(hit < PLAYERACC && dodge > ENEMYAGI){
                                ENEMYHP = ENEMYHP - damage;
                                System.out.println("\nYou hit Tim Cao with your " + PLAYERATKTYPE);
                                System.out.println("Tim Cao took" + damage +".\nIt now has " + ENEMYHP);
                                TimeUnit.SECONDS.sleep(2);
                            }if(ENEMYHP>0){
                        int enemyDamage = rd.nextInt((ENEMYATK-(ENEMYATK-3))+1)+(ENEMYATK-3);
                        hit = Math.random();
                        dodge = Math.random();
                        if(hit < ENEMYACC && dodge > PLAYERAGI){
                            PLAYERHP = PLAYERHP - enemyDamage;
                            System.out.println("\nTim Cao hit " + PLAYERNAME + "!");
                            System.out.println(PLAYERNAME + " took" + enemyDamage +".\n" + PLAYERNAME + " now has " + PLAYERHP + "\n");
                            TimeUnit.SECONDS.sleep(2);
                        }
                        }
                        }else if(response.equals("2")){
                            System.out.println("Which item?");
                            System.out.println(Arrays.toString(inven));
                            System.out.print("Enter the list number of the item to be used:");
                            int itemUsed = input.nextInt();
                            if(itemUsed == 1 && itemNo[0]>=1){
                                itemNo[0]=itemNo[0] - 1;
                                inven[0] = "1.Health Potion\t\t"+itemNo[0];
                                heal.setHealthValue(PLAYERHP + 20);
                                PLAYERHP = heal.getHealthValue();
                                System.out.println(PLAYERNAME + " healed for 20 HP!");
                                System.out.println(PLAYERNAME + " now has " + PLAYERHP + " HP!\n\n");
                                TimeUnit.SECONDS.sleep(2);
                                
                            }
                        }else if(response.equals("3")){
                            double run = Math.random();
                            if(run >= PLAYERAGI){
                                System.out.println(PLAYERNAME + " escaped from Tim Cao");
                                runaway = true;
                                TimeUnit.SECONDS.sleep(2);
                            }else{
                                System.out.println(PLAYERNAME + " couldn't escape!");
                                TimeUnit.SECONDS.sleep(1);
                                int enemyDamage = rd.nextInt((ENEMYATK-(ENEMYATK-3))+1)+(ENEMYATK-3);
                                PLAYERHP = PLAYERHP - enemyDamage;
                                System.out.println("\nTim Cao hit " + PLAYERNAME + "!");
                                System.out.println(PLAYERNAME + " took" + enemyDamage +".\n" + PLAYERNAME + " now has " + PLAYERHP + "\n");
                                TimeUnit.SECONDS.sleep(2);
                            }
                        }
                        if(ENEMYHP<1){
                            int goldDrop = rd.nextInt(GOLD-(GOLD/2)+1)+(GOLD/2);
                            int expDrop = rd.nextInt ((8-6)+1)+6;
                            toNext = EXPLEVEL - expDrop;
                            WALLET = WALLET + goldDrop;
                            System.out.println(PLAYERNAME + " defeated Tim Cao!");
                            TimeUnit.SECONDS.sleep(1);
                            //System.out.println(PLAYERNAME + " gained " + expDrop + " experience!\nNext level is in " + toNext + " experience.");
                            //TimeUnit.SECONDS.sleep(1);
                            System.out.println("Tim Cao dropped " + goldDrop + " gold doubloons");
                            System.out.println(PLAYERNAME + " grabs 'em and goes.\n"
                                    + PLAYERNAME + " now has " + WALLET + " gold doubloons");
                            TimeUnit.SECONDS.sleep(4);
                        }
                        
                    }   break;
                default:
                    break;
            }
            if(PLAYERHP<1){
                System.out.print("You appear to have died, would you like to start over from the beginning of the forest?(y/n)");
                String response = input.next();
                if (response.equals("y")){
                    PLAYERHP = MAXPLAYERHP;
                    itemNo[1] = 3;
                }else
                    System.exit(GOLD);
        }
        }
        System.out.println(PLAYERNAME + " reached the town of Idgaf");
        for(boolean inIdgaf = true; inIdgaf = true;){
        System.out.print(PLAYERNAME + " see in front of you:\n\t1.A shop\n\t2.An Inn\n\t3.A Foreboding Ruins\nWhere does "+ PLAYERNAME + " go?");
        String response = input.nextLine();
        if(response.equals("1") || response.equals("shop")){
        	for(boolean inShop = true; inShop = true;){
        		
        		System.out.println(Arrays.toString(idgafShopInven));
        	}
        	
        }
        }
    }
}


