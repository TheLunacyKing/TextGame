

package textGame;


import java.util.Random;
import java.util.Scanner;
//import java.util.Arrays;
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
    public static String[] items = new String[20];
    public static int[] invenNo = new int[20];
    public static int[] idgafShopValue = {10,40};
    public static String[] idgafShopInven = new String[20];
    public static String[] inven = new String[20];
    public static int MAXPLAYERHP, PLAYERHP, PLAYERATK;
    public static double PLAYERACC, PLAYERAGI;
    public static int MAXENEMYHP, ENEMYHP, ENEMYATK, idx;
    public static double ENEMYACC, ENEMYAGI;
    public static String PLAYERNAME, PLAYERCLASS, PLAYERATKTYPE;
    public static Random rd = new Random();
    public static Scanner input = new Scanner(System.in);
	public static int buying;
	public static Health heal = new Health(MAXPLAYERHP);
	public static idgafShop idgafShop = new idgafShop();
	public static BattleCommands battle = new BattleCommands();
    public static void main(String[] args) throws InterruptedException {
//		        Setting array values
    			items[0] = "Health Potion";
		        items[1] = "Sharp Stick";
		        invenNo[0] = 3;
		        idgafShopValue[0] = 10;
		        idgafShopValue[1] = 40;
		        idgafShopInven[0] = "1." + items[0]+"\t\t"+ idgafShopValue[0];
		        idgafShopInven[1] = "2." + items[1]+"\t\t"+ idgafShopValue[1];
		        inven[0] = "1." + items[0]+"\t\t"+ invenNo[0];
//		        choosing character name
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
//            choosing class
            System.out.print("CHOOSE A CLASS:\n\t1:WARRIOR\n\t2:ARCHER\n\t3:BARBARIAN\n\t(1/2/3):");
            for(boolean selected = false; selected == false;){
                String PLAYERCLASSSELECT = input.next();
                switch (PLAYERCLASSSELECT) {
                    case "1":
                    case "warrior":
                        PLAYERHP = 50;
                        MAXPLAYERHP = 50;
                        PLAYERATK = 10;
                        PLAYERACC = .90;
                        PLAYERAGI = .10;
                        PLAYERCLASS = "Warrior";
                        PLAYERATKTYPE = "Dull Wooden Sword";
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
                        break;
                }
            }
            System.out.print("You are " + PLAYERNAME + " the " + PLAYERCLASS +", correct?\n\t\t\t(y/n):");
            String response = input.next();
            if (response.equals("y")){
                System.out.println("Then let's begin.");
                System.out.println("You wake up in a forest."
                    + "\nAll you see around you are trees.");
                TimeUnit.SECONDS.sleep(1);
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
        
        //forest starts here
        for (int encounters = 1;PLAYERHP>0 && encounters <= 5; encounters++){
            idx = rd.nextInt(3);
            String[] enemy = {"Skeleton", "Slime", "Wild Timothy"};
            System.out.println("you are attacked by a " + enemy[idx] + "!");
            String ENEMYTYPE = enemy[idx];
            TimeUnit.SECONDS.sleep(1);
            switch (idx) {
                case 0:
                    MAXENEMYHP = 15;
                    ENEMYHP = MAXENEMYHP;
                    ENEMYATK = 10;
                    ENEMYACC = .75;
                    ENEMYAGI = .10;
                    break;
                case 1:
                    MAXENEMYHP = 15;
                    ENEMYHP = MAXENEMYHP;
                    ENEMYATK = 4;
                    ENEMYACC = .75;
                    ENEMYAGI = .10;
                    break;
                case 2:
                    MAXENEMYHP = 18;
                    ENEMYHP = MAXENEMYHP;
                    ENEMYATK = 3;
                    ENEMYACC = .75;
                    ENEMYAGI = .10;
                    break;
                default:
                    break;
            }
            for (boolean runaway = false;runaway == false && PLAYERHP>0 && ENEMYHP>0;){
                System.out.println("The " + ENEMYTYPE + " has " + ENEMYHP + " HP");
                System.out.println(PLAYERNAME + " has " + PLAYERHP + " HP");
                System.out.println("Do you:"
                        + "\n\t1.Attack"
                        + "\n\t2.Use an item"
                        + "\n\t3.Run");
                String response = input.next();
                switch (response) {
                    case "1":
                        if(battle.hitEnemy() == true){
                        	battle.damageEnemy();
                            ENEMYHP = battle.getEnemyHp();
                            System.out.println(battle.enemyHitText(ENEMYTYPE));
                            TimeUnit.SECONDS.sleep(2);

                        }else{
                            System.out.println(PLAYERNAME + " missed the " + ENEMYTYPE);
                            TimeUnit.SECONDS.sleep(2);
                        }if(ENEMYHP>0 && battle.hitPlayer()==true){
                        	battle.damagePlayer();
                            PLAYERHP = battle.getPlayerHp();
                            System.out.println(battle.playerHitText(ENEMYTYPE));
                            TimeUnit.SECONDS.sleep(2);
                        }
                        break;
                    case "2":
                        System.out.println("Which item?");
                        for(int i = 0, n = inven.length; i < n; i++){
	        				if(inven[i] != null){
	        					System.out.println(inven[i]);
	        				}
	        			}
                        System.out.print("Enter the list number of the item to be used:");
                        int itemUsed = input.nextInt();
                        if(itemUsed == 1 && invenNo[0]>=1){
                            invenNo[0]=invenNo[0] - 1;
                            inven[0] = "1."+ items[0]+ "\t\t" +invenNo[0];
                            heal.setHealthValue(PLAYERHP + 20);
                            PLAYERHP = heal.getHealthValue();
                            System.out.println(PLAYERNAME + " healed for 20 HP!");
                            System.out.println(PLAYERNAME + " now has " + PLAYERHP + " HP!\n\n");
                            TimeUnit.SECONDS.sleep(2);
                        }
                        break;
                    case "3":
                        double run = Math.random();
                        if(run/PLAYERAGI >= 1){
                            System.out.println(PLAYERNAME + " escaped from the " + ENEMYTYPE);
                            runaway = true;
                            TimeUnit.SECONDS.sleep(2);
                        }else{
                            System.out.println(PLAYERNAME + " couldn't escape!");
                            TimeUnit.SECONDS.sleep(2);
                            battle.damagePlayer();
                            PLAYERHP = battle.getPlayerHp();
                            System.out.println(battle.playerHitText(ENEMYTYPE));
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
                            System.out.println(PLAYERNAME + " defeated the " + ENEMYTYPE + "!");
                            TimeUnit.SECONDS.sleep(1);
                            //System.out.println(PLAYERNAME + " gained " + expDrop + " experience!\nNext level is in " + toNext + " experience.");
                            //TimeUnit.SECONDS.sleep(1);
                            System.out.println("The " + ENEMYTYPE + " dropped " + goldDrop + " gold doubloons");
                            System.out.println(PLAYERNAME + " grabs 'em and goes.\n"
                                    + PLAYERNAME + " now has " + WALLET + " gold doubloons");
                            TimeUnit.SECONDS.sleep(4);
                        }
                        
                    }
            if(PLAYERHP<1){
                System.out.print("You appear to have died, would you like to start over from the beginning of the forest?(y/n)");
                String response = input.next();
                if (response.equals("y")){
                    PLAYERHP = MAXPLAYERHP;
                    invenNo[0] = 3;
                }else
                    System.exit(GOLD);
        }
        }
        System.out.println(PLAYERNAME + " reached the town of Idgaf");
        System.out.print(PLAYERNAME + " sees in front of themself :\n\t1.A shop\n\t2.An Inn\n\t3.A Foreboding Ruins\nWhere does "+ PLAYERNAME + " go?");
        for(boolean inIdgaf = true; inIdgaf != false;){
	        String response = input.nextLine();
	        if(response.equals("1") || response.equals("shop")){
	        	for(boolean inShop = true; inShop != false;){
	        		System.out.print("What would you like to do?\n\t1.Buy\n\t2.Talk\n\t3.Exit\n");
	        		String doing = input.next();
	        		switch(doing){
		        		case"1":
		        		case"shop":
		        		case"Shop":
		        			System.out.println("\t\t\tGold:" + WALLET);
		        			for(int i = 0, n = idgafShopInven.length; i < n; i++){
		        				if(idgafShopInven[i] != null){
		        					System.out.println(idgafShopInven[i]);
		        				}
		        			}
		        			System.out.print("Enter the list number of the item you wish to purchase('exit' to exit): ");
		        			String choice = input.next();
		        			if(choice.matches("[exitEXIT]")){
		        				break;
		        			}
		        			idgafShop.setInven(buying);
		        			WALLET = idgafShop.getGold();
		        			invenNo[buying] = idgafShop.getInven();
		        			inven[buying] = items[buying] + "\t\t" + invenNo[buying];
		        			break;
		        		case"2":
		        		case"talk":
		        		case"Talk":
		        			System.out.println("We've got everything you could ever need here, potions, weapons, you name it!");
		        			TimeUnit.SECONDS.sleep(2);
		        			break;
		        		case"3":
		        		case"exit":
		        		case"Exit":
		        			inShop = false;
		        			System.out.print(PLAYERNAME + " sees in front of themself :\n\t1.A shop\n\t2.An Inn\n\t3.A Foreboding Ruins\nWhere does "+ PLAYERNAME + " go?");
		        			break;
	        		}
	        	} 
	        }else if(response.equals("2")||response.matches("[aAinnInn]")){
	    		for(boolean inInn = true; inInn != false;){
	    		System.out.print("What would you like to do?\n\t1.Stay the night\t\t5G\n\t2.Talk\n\t3.Exit\n");
	    		String doing = input.next();
	    		switch(doing){
	        		case"1":
	        		case"":
	        		case"stay":
	        			if(WALLET>=5){
	        				PLAYERHP = MAXPLAYERHP;
	        				WALLET -= 5;
	        				//testing this
	        				//System.out.println(PLAYERHP);
	        				System.out.println(PLAYERNAME + " feels rested and refreshed!\n" + PLAYERNAME + " now has full HP!");
	        			}	
	        			break;
	        		case"2":
	        		case"talk":
	        		case"Talk":
	        			System.out.println("Welcome to the Drowsy Marsupial. Our rooms are cheap, our people are friendly, and our food is good!");
	        			TimeUnit.SECONDS.sleep(2);
	        			break;
	        		case"3":
	        		case"exit":
	        		case"Exit":
	        			inInn = false;
	        			System.out.print(PLAYERNAME + " sees in front of themself:\n\t1.A shop\n\t2.An Inn\n\t3.A Foreboding Ruins\nWhere does "+ PLAYERNAME + " go?");
	        			break;
	    		}
	    	}
	    	
	        }else if(response.equals("3")){
	        	inIdgaf = false;
	        }else{
	        	System.out.println("Please enter 1, 2, or 3");
	        }
    }

    }
}

