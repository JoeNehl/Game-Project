package com.mycompany.a3.Iterator;

import java.util.LinkedList;

public class GameObjectCollection implements ICollection {//implements the iterator structure
	private LinkedList<Object> gameList;
	
	public GameObjectCollection () {
		gameList = new LinkedList<Object>();
	}
	
	public void add(Object newObject) {
		gameList.add(newObject);
	}
	
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator{//private class to get next item in list
		private int currElementIndex;
		
		public GameObjectIterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (gameList.size()<=0) {
				return false;
			}
			if (currElementIndex == gameList.size()-1) {
				return false;
			}
			return true;
		}
		
		public Object getNext() {
			currElementIndex ++;
			return((gameList).get(currElementIndex));
		}
	}
}
