package nilian.Player;

import nilian.Player.suit.PlayerSuit;

public class AttackFrameProvider {

    private PlayerSuit suit;
    private AttackState attackState = AttackState.NO_ATTACK;
    private AttackState lastAttackState = AttackState.NO_ATTACK;


    public AttackFrameProvider(PlayerSuit suit) {
        this.suit = suit;
    }

    public void updateAttackState(PlayerDirection direction) {
        // first check if he is not already attacking !
//        if(attackState.equals(AttackState.NO_ATTACK)) {
//            if(lastAttackState.equals(AttackState.NO_ATTACK)) {
//                if(direction.equals(PlayerDirection.idle)) {
//
//                }
//            }
//        }
        // TODO: implement your updating logics
        // TODO: but before that make sure you know the player direction is to left or right!

    }
}
