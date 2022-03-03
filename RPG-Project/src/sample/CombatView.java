package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class CombatView extends Pane implements CombatSubscriber{

    protected CombatModel combatModel;

    public StackPane root;
    public HBox dialogueBox;

    //combat text
    public Text phaseText;
    public Button attack;

    public CombatView(){
        root = new StackPane();
        root.setPrefWidth(275);
        root.setPrefHeight(300);

        phaseText = new Text("MORTAL KOMBAT!!! Fight!");
        phaseText.setFill(Color.BLACK);

        attack = new Button("Attack");
        attack.setDisable(true);
        attack.setAlignment(Pos.CENTER);

        VBox stack = new VBox();
        dialogueBox = new HBox();

        stack.getChildren().addAll(phaseText, dialogueBox);
        dialogueBox.getChildren().addAll(attack);
        dialogueBox.setPadding(new Insets(100,100,100,100));
        dialogueBox.setMaxHeight(250);
        dialogueBox.setMaxWidth(800);

        root.getChildren().addAll(stack);

        this.getChildren().add(root);
    }

    //sets the combat model
    public void setModel(CombatModel cm){
        combatModel = cm;
    }

    //sets widget listeners and links with controller
    public void setController(Controller controller){
        root.setOnMousePressed(controller::nextPhase);
        attack.setOnAction(controller::handleAttack);
        controller.combatText = phaseText;
    }

    public void modelChanged(){
        phaseText.setText(combatModel.combatDialogue.get(combatModel.phase));
        if(combatModel.playerTurn){
            attack.setDisable(false);
        }else{
            attack.setDisable(true);
        }
    }

}