
package com.mszurgot.tanks.collision;

import java.awt.*;

public interface Collidable {
    public Rectangle getDimension();

    public void collideAction();

    //TODO sprawdzic czy to dobry pomysl
//    public static class CollisionResult {
//        private Iterable<CollisionMemberAction> collisionInitializerMemberActions;
//        private Iterable<CollisionMemberAction> collisionConsumerMemberActions;
//
//        public CollisionResult(Iterable<CollisionMemberAction> collisionInitializerMemberActions, Iterable<CollisionMemberAction> collisionConsumerMemberActions) {
//            this.collisionInitializerMemberActions = collisionInitializerMemberActions;
//            this.collisionConsumerMemberActions = collisionConsumerMemberActions;
//        }
//
//        public Iterable<CollisionMemberAction> getCollisionInitializerMemberActions() {
//            return collisionInitializerMemberActions;
//        }
//
//        public Iterable<CollisionMemberAction> getCollisionConsumerMemberActions() {
//            return collisionConsumerMemberActions;
//        }
//
//    }
//
//    public static enum CollisionMemberAction{
//        DELETE
//    }
}
