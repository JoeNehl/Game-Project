package com.mycompany.a3.GameObject;

public interface ICollider {
	public boolean collidesWith(GameObject check);
	public void handleCollision(GameObject check);
}
