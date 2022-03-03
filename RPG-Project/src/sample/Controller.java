package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class Controller {

    protected CombatModel combatModel;
    protected Text combatText;

    protected gameModel g;

    //sets the combat model
    public void setModels(CombatModel cm, gameModel gM){
        combatModel = cm;
        g = gM;
    }



    public void moveUp(){
        g.setPlayerY(g.getPlayerY()-g.getPlayerSpeed());
    }
    public void moveDown() {
        g.setPlayerY(g.getPlayerY() + g.getPlayerSpeed());
    }
    public void moveLeft() {
        g.setPlayerX(g.getPlayerX() - g.getPlayerSpeed());
    }
    public void moveRight() {
        g.setPlayerX(g.getPlayerX() + g.getPlayerSpeed());
    }

    public void nextPhase(MouseEvent e) {
        if(!combatModel.playerTurn){
            try {
                combatModel.nextPhase();
                combatModel.typeOutDialogue(0, combatText);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void handleAttack(ActionEvent event){
        combatModel.attack();
    }

    public void handleRun(){
        combatModel.runAway();
    }

    public void handleMagic(){
        combatModel.usedMagic();
    }
}