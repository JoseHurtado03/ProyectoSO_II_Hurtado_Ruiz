
package Proyecto2_SO_Hurtado_Ruiz;

public class Character {
    private int id;
    private String name;
    private int ability;
    private int ps;
    private int force;
    private int agility;
    private int priority;
    private int countStarvation;
    private Random random;

    public Character(int id, String name) {
        this.id = id;
        this.name = name;
        this.random = new Random();
        this.ability = random.qualityNum(60);
        this.ps = random.qualityNum(70);
        this.force = random.qualityNum(50);;
        this.agility = random.qualityNum(40);;
        this.priority = random.genPriority(ability, ps, force, agility);
        this.countStarvation = 0;
    }
    
    public void changePriority(){
        if (countStarvation == 8){
            priority++;
        }
    }
}
