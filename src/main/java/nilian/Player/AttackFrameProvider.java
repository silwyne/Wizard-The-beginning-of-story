package nilian.Player;

import nilian.Player.suit.PlayerSuit;

public class AttackFrameProvider {

    private PlayerSuit suit;
    private AttackState attackState = AttackState.NO_ATTACK;
    private AttackState lastAttackState = AttackState.NO_ATTACK;


    public AttackFrameProvider(PlayerSuit suit) {
        this.suit = suit;
    }

    public void updateAttackState(PlayerState direction) {
        // first check if he is not already attacking !

    }
}
