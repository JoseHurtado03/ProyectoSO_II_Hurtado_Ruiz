
package Proyecto2_SO_Hurtado_Ruiz;

public class RandomGen {
    
    public int genPriority(int ability, int ps, int force, int agility) {
        int value = ability + ps + force + agility;
        if (value >= 3){
            return 1;
        } else if (value == 2){
            return 2;
        } else{
            return 3;
        }
    }
    
    public int qualityNum(int n) {
        double random = Math.random() * 100;
        if (random < n) {
            return 1; //Si retorna 1, la característica es de calidad
        } else {
            return 0;
        }
    }
    
    public int result() {
        double random = Math.random() * 100;
        if (random < 40) {
            // 40% de probabilidad para retornar 1. Hay combate con un ganador
            return 1;
        } else if (random < 67) {
            // 27% de probabilidad para retornar 2 (de 40 a 67). Hay combate y queda en empate
            return 2;
        } else {
            // 33% de probabilidad para retornar 3 (de 67 a 100). No hay combate.
            return 3;
        }
    }
    
    public int genRandomNum(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
