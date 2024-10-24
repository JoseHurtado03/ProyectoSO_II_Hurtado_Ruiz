
package Proyecto2_SO_Hurtado_Ruiz;

public class Character {
    private int id;
    private String name;
    private int priority;
    private int ability;
    private int ps;
    private int force;
    private int agility;
    private int countStarvation;
    private Random random;

    public Character(int id, String name) {
        this.id = id;
        this.name = name;
        this.random = new Random();
        this.priority = random.genPriority();
        this.ability = random.qualityNum(60);
        this.ps = random.qualityNum(70);
        this.force = random.qualityNum(50);;
        this.agility = random.qualityNum(40);;
        this.countStarvation = 0;
    }
    
    
}
