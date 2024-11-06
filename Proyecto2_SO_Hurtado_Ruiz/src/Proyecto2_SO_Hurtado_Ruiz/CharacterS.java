
package Proyecto2_SO_Hurtado_Ruiz;

public class CharacterS {
    private int id;
    private String[] names;
    private String name;
    private int ability;
    private int ps;
    private int force;
    private int agility;
    private int priority;
    private int countStarvation;
    private RandomGen random;
    private boolean out;

    public CharacterS(int id, String[] names) {
        this.id = id;
        this.names = names;
        this.name = this.searchName();
        this.random = new RandomGen();
        this.ability = random.qualityNum(60);
        this.ps = random.qualityNum(70);
        this.force = random.qualityNum(50);
        this.agility = random.qualityNum(40);
        this.priority = random.genPriority(ability, ps, force, agility);
        this.countStarvation = 0;
        this.out= false;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
    
    
    
    public void changePriority(){
        if (getCountStarvation() == 8){
            setPriority(getPriority() + 1);
            countStarvation = 0;
        }
    }
    
    public String searchName(){
        for (int i = 0; i < names.length; i++) {
            if (names[i] != ""){
                String name = names[i];
                names[i] = "";
                return name;
            }
        }
        return "¿?";
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the ability
     */
    public int getAbility() {
        return ability;
    }

    /**
     * @param ability the ability to set
     */
    public void setAbility(int ability) {
        this.ability = ability;
    }

    /**
     * @return the ps
     */
    public int getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(int ps) {
        this.ps = ps;
    }

    /**
     * @return the force
     */
    public int getForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(int force) {
        this.force = force;
    }

    /**
     * @return the agility
     */
    public int getAgility() {
        return agility;
    }

    /**
     * @param agility the agility to set
     */
    public void setAgility(int agility) {
        this.agility = agility;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return the countStarvation
     */
    public int getCountStarvation() {
        return countStarvation;
    }

    /**
     * @param countStarvation the countStarvation to set
     */
    public void setCountStarvation(int countStarvation) {
        this.countStarvation = countStarvation;
    }

    /**
     * @return the random
     */
    public RandomGen getRandom() {
        return random;
    }

    /**
     * @param random the random to set
     */
    public void setRandom(RandomGen random) {
        this.random = random;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
